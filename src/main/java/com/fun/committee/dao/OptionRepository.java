package com.fun.committee.dao;

import com.fun.committee.model.sql.OptionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by harshams on 26/06/2020
 */
public interface OptionRepository extends CrudRepository<OptionEntity,Long> {
}
