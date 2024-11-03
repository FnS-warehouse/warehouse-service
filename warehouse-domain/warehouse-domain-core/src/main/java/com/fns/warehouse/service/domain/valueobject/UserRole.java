package com.fns.warehouse.service.domain.valueobject;


public class UserRole {

    private final UserRoleType type;

    public UserRole(UserRoleType type) {
        this.type = type;
    }

    public UserRoleType getType() { return type; }

}
