package com.fun.committee.dao;

import com.fun.committee.model.sql.GameCompletionStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by harshams on 27/06/2020
 */
@Repository
public interface GameCompletionStatusRepository extends CrudRepository<GameCompletionStatusEntity,Long> {

    GameCompletionStatusEntity getByUserId(Long userId);
}
