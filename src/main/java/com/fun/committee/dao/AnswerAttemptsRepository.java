package com.fun.committee.dao;

import com.fun.committee.model.sql.AnswerAttemptsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by harshams on 27/06/2020
 */
public interface AnswerAttemptsRepository extends CrudRepository<AnswerAttemptsEntity,Long> {

    AnswerAttemptsEntity getByUserIdAndGuessId(Long userId,Long guessId);

    @Query("select status from AnswerAttemptsEntity where userId = :userId and guessId = :guessId")
    String getStatusByUserIdAndGuessId(@Param("userId")Long userId,@Param("guessId")Long guessId);

    @Query("from AnswerAttemptsEntity where userId = :userId and guessId = :guessId")
    AnswerAttemptsEntity getAnswerAttemptsEntityByUserIdAndGuessId(@Param("userId")Long userId,@Param("guessId")Long guessId);
}
