package com.fun.committee.dao;

import com.fun.committee.model.sql.QuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by harshams on 26/06/2020
 */
public interface QuestionRepository extends CrudRepository<QuestionEntity,Long> {

    @Query("select count(id) from QuestionEntity  where id in :questionIds")
    Long validateQuestionNumbers(@Param("questionIds") List<Long>questionIds);
}
