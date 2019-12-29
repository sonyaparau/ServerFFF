package com.mobile.freeforfun.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum EVoteType{
    UPVOTE(1),
    DOWNVOTE(-1);

    @Getter
    private Integer voteType;

    private static Map<Integer, EVoteType> map = new HashMap<>();
    static {
        for (EVoteType voteType1 : EVoteType.values()) {
            map.put(voteType1.voteType, voteType1);
        }
    }
    public static EVoteType valueOf(Integer voteType) {
        return map.get(voteType);
    }
}