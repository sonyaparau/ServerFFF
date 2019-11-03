package com.mobile.freeforfun.persistence.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum ERoleType {

    USER(0),
    ADMIN(1),
    SUPERADMIN(2);

    @Getter
    private Integer role;

    private static Map<Integer, ERoleType> map = new HashMap<Integer, ERoleType>();
    static {
        for (ERoleType roles : ERoleType.values()) {
            map.put(roles.role, roles);
        }
    }

    ERoleType(int i) {
    }

    public static ERoleType valueOf(Integer role) {
        return map.get(role);
    }
}
