package com.fun.committee.dao;

import com.fun.committee.model.sql.GameCompletionStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by harshams on 27/06/2020
 */
@Repository
public interface GameCompletionStatusRepository extends CrudRepository<GameCompletionStatusEntity,Long> {

    GameCompletionStatusEntity getByUserId(Long userId);

    @Query("select percentageCompletion from GameCompletionStatusEntity where userId = :userId")
    Double getPercentageCompletionByUserId(@Param("userId")Long userId);
}
