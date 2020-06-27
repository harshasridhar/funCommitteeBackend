package com.fun.committee.dao;

import com.fun.committee.model.sql.AnswerAttemptsEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by harshams on 27/06/2020
 */
public interface AnswerAttemptsRepository extends CrudRepository<AnswerAttemptsEntity,Long> {

    AnswerAttemptsEntity getByUserIdAndGuessId(Long userId,Long guessId);
}
