package com.fun.committee.dao;

import com.fun.committee.model.sql.McqEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by harshams on 25/06/2020
 */
@Repository
@Transactional
public interface McqRepository extends CrudRepository<McqEntity,Long> {

}
