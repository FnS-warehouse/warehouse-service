package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.AggregateRoot;
import com.fns.domain.valueObject.*;

public class User extends AggregateRoot<UserId> {

    private UserId userId;
    private String name;
    private UserRole role;

    private User(Builder builder) {
        super.setId(builder.userId);
        this.name = builder.name;
        this.role = builder.role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isSuperAdmin() {
        return role.getType() == UserRoleType.SUPER_ADMIN;
    }

    public UserId getUserId() { return userId; }
    public String getName() { return name; }
    public UserRole getRole() { return role; }

    // Builder class
    public static class Builder {
        private UserId userId;
        private String name;
        private UserRole role;

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
