package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.*;
import com.fns.domain.valueobject.*;
import com.fns.warehouse.service.domain.valueobject.UserRole;

public class User extends BaseEntity<UserId> {

    private UserId userId;
    private String name;
    private UserRole userRole;

    public User(UserId userId, String name, UserRole userRole) {
        super.setId(userId);
        this.name = name;
        this.userRole = userRole;
    }

    public User(UserId userId) {
        super.setId(userId);
    }

    public UserId getUserId() { return userId; }
    public String getName() { return name; }
    public UserRole getUserRole() { return userRole; }


}
