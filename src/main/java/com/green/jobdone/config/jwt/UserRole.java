package com.green.jobdone.config.jwt;

public enum UserRole {
    ROLE_ADMIN(101),
    ROLE_USER(100),
    ROLE_EMPLOYEE(110),
    ROLE_MANAGER(120),
    ROLE_PRESIDENT(130),
    ROLE_FREELANCER(140);

    private final int code;

    UserRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserRole fromCode(int code) {
        for (UserRole role : UserRole.values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role code: " + code);
    }


}
