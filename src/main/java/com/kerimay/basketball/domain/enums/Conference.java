package com.kerimay.basketball.domain.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public enum Conference implements IEnum {

    WEST("WEST", "Conference.WEST"),
    EAST("EAST", "Conference.EAST"),
    EUROPE("EUROPE", "Conference.EUROPE");

    private final String key;
    private final String messageKey;

    Conference(String key, String messageKey) {
        this.key = key;
        this.messageKey = messageKey;
    }

    public static Optional<Conference> forKey(String key) {
        if (StringUtils.isBlank(key)) {
            return Optional.empty();
        }
        for (Conference conference : values()) {
            if (StringUtils.equals(conference.key(), key)) {
                return Optional.of(conference);
            }
        }

        return Optional.empty();
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }
}
