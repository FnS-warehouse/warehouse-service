package com.fns.warehouse.service.domain.entity;

import com.fns.domain.entity.*;
import com.fns.domain.valueObject.*;

public class UserRole extends BaseEntity<UserRoleId> {

    private UserRoleId roleId;
    private UserRoleType type;

    private UserRole(Builder builder) {
        super.setId(builder.roleId);
        this.type = builder.type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UserRoleId getRoleId() { return roleId; }
    public UserRoleType getType() { return type; }

    // Builder class
    public static class Builder {
        private UserRoleId roleId;
        private UserRoleType type;

        public Builder roleId(UserRoleId roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder type(UserRoleType type) {
            this.type = type;
            return this;
        }

        public UserRole build() {
            return new UserRole(this);
        }
    }
}
