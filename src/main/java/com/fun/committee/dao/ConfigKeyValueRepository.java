package com.fun.committee.dao;

import com.fun.committee.model.sql.ConfigKeyValueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by harshams on 27/06/2020
 */
public interface ConfigKeyValueRepository extends CrudRepository<ConfigKeyValueEntity,Long> {

    @Query("select value from ConfigKeyValueEntity where cKey = :key")
    public String getByKey(@Param("key")String key);
}
