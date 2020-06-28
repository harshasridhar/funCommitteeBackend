package com.fun.committee;

/**
 * Created by harshams on 27/06/2020
 */
public enum ConfigKeyValues {
    EMAIL_NOTIFICATION_ENABLED,
    EMAIL_ID,
    EMAIL_PASSWORD,
    MAX_RETRY_ATTEMPTS;

    public static class DefaultValue{
        public static final boolean EMAIL_NOTIFICATION_ENABLED = false;
        public static final String EMAIL_ID = "email@gmail.com";
        public static final String EMAIL_PASSWORD = "password";
        public static final Long MAX_RETRY_ATTEMPTS = 2L;
    }
}
