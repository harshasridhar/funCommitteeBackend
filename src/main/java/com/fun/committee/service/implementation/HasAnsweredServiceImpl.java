package com.fun.committee.service.implementation;

import com.fun.committee.ConfigKeyValues;
import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.dao.*;
import com.fun.committee.model.json.Answers;
import com.fun.committee.model.json.AnswersList;
import com.fun.committee.model.json.QuestionIdAnswer;
import com.fun.committee.model.sql.AnswerAttemptsEntity;
import com.fun.committee.model.sql.HasAnsweredEntity;
import com.fun.committee.model.sql.UserEntity;
import com.fun.committee.service.interfaces.ConfigKeyValuesService;
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
    @Autowired
    ConfigKeyValuesService configKeyValuesService;

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
        Boolean globalGameCompleted = configKeyValuesService.getBooleanConfigKeyValue(ConfigKeyValues.GLOBAL_GAME_COMPLETED,ConfigKeyValues.DefaultValue.GLOBAL_GAME_COMPLETED);
        Double percentageCompletion = gameCompletionStatusRepository.getPercentageCompletionByUserId(userEntity.getId());
        percentageCompletion = percentageCompletion == null ? 0.0 : percentageCompletion;
        Boolean hasUserCompletedGame = percentageCompletion == 100.0 ? true : false;
        if( globalGameCompleted || hasUserCompletedGame){
            answersList.setPercentageCompletion(100.0);
            List<UserEntity> users = userRepository.getOtherUsers(username);
            for(UserEntity otherUser: users){
                List<QuestionIdAnswer> answeredEntities = compositeDao.getAnswersForUser(otherUser.getId());
                if (answeredEntities.size() == 0)
                    continue;
               Answers answers = new Answers();
               answers.setId(otherUser.getId());
               answers.setStatus("CORRRECT");
               answers.setAnswer(otherUser.getUsername());
               answers.setRetriesLeft(0L);
                answers.setList(answeredEntities);
                answersList.getList().add(answers);
            }

        }else {
            List<Long> userIds = userRepository.getOtherUserIds(userEntity.getId());
            for (Long userId : userIds) {
                List<QuestionIdAnswer> answeredEntities = compositeDao.getAnswersForUser(userId);
                if (answeredEntities.size() == 0)
                    continue;
                Answers answers = new Answers();
                answers.setId(userId);
                AnswerAttemptsEntity answerAttemptsEntity = answerAttemptsRepository.getAnswerAttemptsEntityByUserIdAndGuessId(userEntity.getId(), userId);
                if (answerAttemptsEntity != null) {
                    answers.setStatus(answerAttemptsEntity.getStatus());
                    answers.setAnswer(answerAttemptsEntity.getAnswer());
                    answers.setRetriesLeft(answerAttemptsEntity.getRetriesLeft());
                } else {
                    answers.setStatus("UNATTEMPTED");
                    answers.setAnswer("");
                    answers.setRetriesLeft(configKeyValuesService.getLongConfigKeyValue(ConfigKeyValues.MAX_RETRY_ATTEMPTS, ConfigKeyValues.DefaultValue.MAX_RETRY_ATTEMPTS));
                }
                answers.setList(answeredEntities);
                answersList.getList().add(answers);
            }
        }
        return answersList;
    }
}
