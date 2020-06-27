package com.fun.committee.service.implementation;

import com.fun.committee.dao.AnswerAttemptsRepository;
import com.fun.committee.dao.GameCompletionStatusRepository;
import com.fun.committee.dao.UserRepository;
import com.fun.committee.model.json.AnswerAttempt;
import com.fun.committee.model.sql.AnswerAttemptsEntity;
import com.fun.committee.model.sql.GameCompletionStatusEntity;
import com.fun.committee.service.interfaces.GameCompletionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by harshams on 27/06/2020
 */
@Service
public class GameCompletionStatusServiceImpl implements GameCompletionStatusService {

    @Autowired
    GameCompletionStatusRepository gameCompletionStatusRepository;
    @Autowired
    AnswerAttemptsRepository answerAttemptsRepository;
    @Autowired
    UserRepository userRepository;

    public GameCompletionStatusEntity createEntryForUser(Long userId){
        GameCompletionStatusEntity gameCompletionStatusEntity = new GameCompletionStatusEntity();
        gameCompletionStatusEntity.setUserId(userId);
        gameCompletionStatusEntity.setStatus("IN_PROGRESS");
        gameCompletionStatusEntity.setPercentageCompletion(0.0);
        gameCompletionStatusRepository.save(gameCompletionStatusEntity);
        return gameCompletionStatusEntity;
    }

    public Boolean hasUserCompletedGame(Long userId){
        GameCompletionStatusEntity gameCompletionStatusEntity = gameCompletionStatusRepository.getByUserId(userId);
        return gameCompletionStatusEntity.getStatus().equalsIgnoreCase("COMPLETED");
    }

    @Async
    public void refreshStatusForUserId(Long userId){
        GameCompletionStatusEntity gameCompletionStatusEntity = gameCompletionStatusRepository.getByUserId(userId);
        if(gameCompletionStatusEntity == null){
            gameCompletionStatusEntity = createEntryForUser(userId);
        }
        if(gameCompletionStatusEntity.getStatus().equalsIgnoreCase("COMPLETED")){
            return;
        }
        List<Long> userIds = userRepository.getOtherUserIds(userId);
        Long correctCount = 0L;
        for(Long guessId: userIds) {
            AnswerAttemptsEntity answerAttemptsEntity = answerAttemptsRepository.getByUserIdAndGuessId(userId, guessId);
            if(answerAttemptsEntity == null){
                continue;
            }
            if(answerAttemptsEntity.getStatus().equalsIgnoreCase("CORRECT")){
                correctCount++;
            }
        }
        if(correctCount == userIds.size()){
            gameCompletionStatusEntity.setPercentageCompletion(100.0);
            gameCompletionStatusEntity.setStatus("COMPLETED");
        }else {
            gameCompletionStatusEntity.setPercentageCompletion(100.0 * correctCount / new Double(userIds.size()));
        }
        gameCompletionStatusRepository.save(gameCompletionStatusEntity);
    }
}
