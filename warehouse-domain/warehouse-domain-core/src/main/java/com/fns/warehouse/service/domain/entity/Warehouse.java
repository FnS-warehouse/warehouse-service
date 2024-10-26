package com.fns.warehouse.service.domain.entity;

import com.fns.domain.valueObject.*;
import com.fns.domain.entity.AggregateRoot;
import com.fns.warehouse.service.domain.valueobject.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AggregateRoot<WarehouseId> {

    private String name;
    private Location location;
    private User createdBy;
    private List<User> warehouseAdmins;
    private WarehouseStatus status;

    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        this.name = builder.name;
        this.location = builder.location;
        this.createdBy = builder.createdBy;
        this.warehouseAdmins = builder.warehouseAdmins != null ? builder.warehouseAdmins : new ArrayList<>();
        this.status = builder.status != null ? builder.status : WarehouseStatus.ACTIVE;
        LocalDateTime createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public void updateName(String newName, UserRole userRole) {
        validateSuperAdmin(userRole);
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLocation(Location newLocation, UserRole userRole) {
        validateSuperAdmin(userRole);
        this.location = newLocation;
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteWarehouse(UserRole userRole) {
        validateSuperAdmin(userRole);
        this.status = WarehouseStatus.DEACTIVE;
        this.deletedAt = LocalDateTime.now();
    }

    public void assignWarehouseAdmin(User user, UserRole userRole) {
        validateSuperAdmin(userRole);
        if (!warehouseAdmins.contains(user)) {
            warehouseAdmins.add(user);
        }
    }

    private void validateSuperAdmin(UserRole userRole) {
        if (userRole.getType() != UserRoleType.SUPER_ADMIN) {
            throw new IllegalStateException("Only Super Admins can perform this action.");
        }
    }

    // Getters
    public String getName() { return name; }
    public Location getLocation() { return location; }
    public WarehouseStatus getStatus() { return status; }
    public List<User> getWarehouseAdmins() { return warehouseAdmins; }

    // Builder class
    public static class Builder {
        private WarehouseId warehouseId;
        private String name;
        private Location location;
        private User createdBy;
        private List<User> warehouseAdmins;
        private WarehouseStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;

        public Builder warehouseId(WarehouseId warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Builder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder warehouseAdmins(List<User> warehouseAdmins) {
            this.warehouseAdmins = warehouseAdmins;
            return this;
        }

        public Builder status(WarehouseStatus status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            return this;
        }

        public Builder deletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
