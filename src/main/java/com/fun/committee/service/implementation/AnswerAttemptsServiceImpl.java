package com.fun.committee.service.implementation;

import com.fun.committee.ErrorCode;
import com.fun.committee.FunCommitteeException;
import com.fun.committee.dao.AnswerAttemptsRepository;
import com.fun.committee.dao.UserRepository;
import com.fun.committee.model.json.AnswerAttempt;
import com.fun.committee.model.sql.AnswerAttemptsEntity;
import com.fun.committee.model.sql.UserEntity;
import com.fun.committee.service.interfaces.AnswerAttemptsService;
import com.fun.committee.service.interfaces.GameCompletionStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by harshams on 27/06/2020
 */
@Service
public class AnswerAttemptsServiceImpl implements AnswerAttemptsService {

    private static final Long MAX_RETRY_ATTEMPTS = 2L;

    @Autowired
    AnswerAttemptsRepository answerAttemptsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameCompletionStatusService gameCompletionStatusService;

    public AnswerAttempt validateAttempt(AnswerAttempt answerAttempt)throws Exception{
        UserEntity userEntity = userRepository.findByUsername(answerAttempt.getUsername());
        if(userEntity == null){
            throw new FunCommitteeException(ErrorCode.INVALID_ARGUMENTS,"Invalid username");
        }
        if(gameCompletionStatusService.hasUserCompletedGame(userEntity.getId())){
            return answerAttempt;
        }
        AnswerAttemptsEntity answerAttemptsEntity = answerAttemptsRepository.getByUserIdAndGuessId(userEntity.getId(),answerAttempt.getGuessId());
        if(answerAttemptsEntity == null){
            answerAttemptsEntity = new AnswerAttemptsEntity();
            answerAttemptsEntity.setUserId(userEntity.getId());
            answerAttemptsEntity.setGuessId(answerAttempt.getGuessId());
            answerAttemptsEntity.setRetriesLeft(MAX_RETRY_ATTEMPTS);

        }else{
            if(answerAttemptsEntity.getStatus().equalsIgnoreCase("CORRECT")){
                answerAttempt.setStatus("CORRECT");
                return answerAttempt;
            }
            if(answerAttemptsEntity.getRetriesLeft() == 0){
                throw new FunCommitteeException(ErrorCode.MAX_ATTEMPTS_EXHAUSTED,"Max attempts exhausted!");
            }
            answerAttemptsEntity.setRetriesLeft(answerAttemptsEntity.getRetriesLeft()-1);
        }
        UserEntity userToGuess = userRepository.getById(answerAttempt.getGuessId());
        if(userToGuess == null){
            throw new FunCommitteeException(ErrorCode.INVALID_ARGUMENTS,"Invalid guess id");
        }
        if(userToGuess == userEntity){
            throw new FunCommitteeException(ErrorCode.INVALID_DATA_ACCESS,"Data Invalid");
        }
        if(answerAttempt.getAnswer().equalsIgnoreCase(userToGuess.getUsername())){
            answerAttemptsEntity.setStatus("CORRECT");
        }else{
            answerAttemptsEntity.setStatus("INCORRECT");
        }
        answerAttemptsEntity.setAnswer(answerAttempt.getAnswer());
        answerAttemptsRepository.save(answerAttemptsEntity);
        AnswerAttempt returnAnswerAttempt = new AnswerAttempt();
        BeanUtils.copyProperties(answerAttemptsEntity,returnAnswerAttempt);
        returnAnswerAttempt.setUsername(userEntity.getUsername());
        returnAnswerAttempt.setAnswer(answerAttempt.getAnswer());
        gameCompletionStatusService.refreshStatusForUserId(userEntity.getId());
        return returnAnswerAttempt;
    }
}
