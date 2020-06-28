package com.fun.committee.service.interfaces;

import com.fun.committee.ConfigKeyValues;

/**
 * Created by harshams on 27/06/2020
 */
public interface ConfigKeyValuesService {

    Boolean getBooleanConfigKeyValue(ConfigKeyValues key, Boolean defaultValue);

    String getStringConfigKeyValue(ConfigKeyValues key, String  defaultValue);

    Integer getIntegerConfigKeyValue(ConfigKeyValues key, Integer defaultValue);

    Long getLongConfigKeyValue(ConfigKeyValues key, Long defaultValue);
}
