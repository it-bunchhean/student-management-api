package com.it_bunchhean.studentmanagement.constant;

public enum Role {
    ADMIN,
    USER;

    public String getAuthorityName() {
        return "ROLE_" + this.name();
    }
}
