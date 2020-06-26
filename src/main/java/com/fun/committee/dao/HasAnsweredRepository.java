package com.fun.committee.dao;

import com.fun.committee.model.sql.HasAnsweredEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by harshams on 26/06/2020
 */
public interface HasAnsweredRepository extends CrudRepository<HasAnsweredEntity,Long> {
}
