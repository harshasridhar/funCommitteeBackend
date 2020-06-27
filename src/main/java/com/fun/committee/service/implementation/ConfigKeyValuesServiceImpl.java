package com.fun.committee.service.implementation;

import com.fun.committee.ConfigKeyValues;
import com.fun.committee.dao.ConfigKeyValueRepository;
import com.fun.committee.model.sql.ConfigKeyValueEntity;
import com.fun.committee.service.interfaces.ConfigKeyValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by harshams on 27/06/2020
 */
@Service
public class ConfigKeyValuesServiceImpl implements ConfigKeyValuesService {

    @Autowired
    ConfigKeyValueRepository configKeyValueRepository;

    @Override
    public Boolean getBooleanConfigKeyValue(ConfigKeyValues key, Boolean defaultValue) {
        Boolean value = defaultValue;
        String dbValue = configKeyValueRepository.getByKey(key.name());
        if(dbValue != null){
            value = Boolean.parseBoolean(dbValue);
        }
        return value;
    }

    @Override
    public String getStringConfigKeyValue(ConfigKeyValues key, String defaultValue) {
        String value = defaultValue;
        String dbValue = configKeyValueRepository.getByKey(key.name());
        if(dbValue != null){
            value = dbValue;
        }
        return value;
    }

    @Override
    public Integer getIntegerConfigKeyValue(ConfigKeyValues key, Integer defaultValue) {
        Integer value = defaultValue;
        String dbValue = configKeyValueRepository.getByKey(key.name());
        if(dbValue != null){
            value = Integer.parseInt(dbValue);
        }
        return value;
    }
}
