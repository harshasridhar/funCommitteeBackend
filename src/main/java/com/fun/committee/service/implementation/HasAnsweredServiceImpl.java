package com.fun.committee.service.implementation;

import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.dao.*;
import com.fun.committee.model.json.Answers;
import com.fun.committee.model.json.AnswersList;
import com.fun.committee.model.json.QuestionIdAnswer;
import com.fun.committee.model.sql.HasAnsweredEntity;
import com.fun.committee.model.sql.UserEntity;
import com.fun.committee.service.interfaces.HasAnsweredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshams on 26/06/2020
 */
@Service
public class HasAnsweredServiceImpl implements HasAnsweredService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    HasAnsweredRepository hasAnsweredRepository;
    @Autowired
    CompositeDao compositeDao;
    @Autowired
    AnswerAttemptsRepository answerAttemptsRepository;
    @Autowired
    GameCompletionStatusRepository gameCompletionStatusRepository;

    public void submitQuestionaire(Answers answers)throws Exception{
        UserEntity userEntity = userRepository.findByUsername(answers.getUsername());
        if(userEntity == null){
            throw new FunCommitteeException(ErrorCode.INVALID_ARGUMENTS,"username missing");
        }
        List<HasAnsweredEntity> hasAnsweredEntities = new ArrayList<>();
        List<Long> questionIds = new ArrayList<>();
        for(QuestionIdAnswer questionIdAnswer: answers.getList()){
            HasAnsweredEntity hasAnsweredEntity = new HasAnsweredEntity();
            hasAnsweredEntity.setUserId(userEntity.getId());
            hasAnsweredEntity.setQuestionId(questionIdAnswer.getQuestionId());
            hasAnsweredEntity.setAnswer(questionIdAnswer.getAnswer());
            questionIds.add(questionIdAnswer.getQuestionId());
            hasAnsweredEntities.add(hasAnsweredEntity);
        }
        Long quesNum = questionRepository.validateQuestionNumbers(questionIds);
        if(quesNum!= questionIds.size()){
            throw new FunCommitteeException(ErrorCode.INVALID_ARGUMENTS,"One or more invalid question id(s) is invalid");
        }
        hasAnsweredRepository.saveAll(hasAnsweredEntities);
    }

    public AnswersList getAnswers(String username)throws Exception{
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null){
            throw new FunCommitteeException(ErrorCode.INVALID_ARGUMENTS,"Invalid username");
        }
        AnswersList answersList = new AnswersList();
        answersList.setList(new ArrayList<>());
        Double percentage = gameCompletionStatusRepository.getPercentageCompletionByUserId(userEntity.getId());
        answersList.setPercentageCompletion(percentage == null ? 0 : percentage);
        List<Long> userIds = userRepository.getOtherUserIds(userEntity.getId());
        for(Long userId: userIds) {
            List<QuestionIdAnswer> answeredEntities = compositeDao.getAnswersForUser(userId);
            Answers answers = new Answers();
            answers.setId(userId);
            String status = answerAttemptsRepository.getStatusByUserIdAndGuessId(userEntity.getId(),userId);
            if(status != null)
                answers.setStatus(status);
            answers.setList(answeredEntities);
            answersList.getList().add(answers);
        }
        return answersList;
    }
}
