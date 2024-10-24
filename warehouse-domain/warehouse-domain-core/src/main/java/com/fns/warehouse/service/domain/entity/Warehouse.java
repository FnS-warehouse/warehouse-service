package com.fns.warehouse.service.domain.entity;

import com.fns.domain.valueObject.*;
import com.fns.domain.entity.AggregateRoot;

import java.time.LocalDateTime;
import java.util.List;

public class Warehouse extends AggregateRoot<WarehouseId> {

    private String name;
    private Location location;
    private List<String> failureMessages;
    private WarehouseStatus warehouseStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        this.name = builder.name;
        this.location = builder.location;
        this.failureMessages = builder.failureMessages;
        this.warehouseStatus = builder.warehouseStatus;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.deletedAt = builder.deletedAt;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public WarehouseStatus getWarehouseStatus() {
        return warehouseStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void createWarehouse(String name, Location location) {
        this.name = name;
        this.location = location;
        this.warehouseStatus = WarehouseStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public void updateName(String newName) {
        if (this.warehouseStatus != WarehouseStatus.DEACTIVE) {
            if (newName == null || newName.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty.");
            }
            this.name = newName;
            this.updatedAt = LocalDateTime.now();  // Update the updatedAt timestamp
        } else {
            throw new IllegalStateException("Cannot update a deactivated warehouse.");
        }
    }

    public void updateLocation(Location newLocation, UserRole userRole) {
        if (userRole.getType() == UserRoleType.SUPER_ADMIN) {
            this.location = newLocation;
            this.updatedAt = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Only Super Admins can update warehouse locations.");
        }
    }


    public void deleteWarehouse() {
        if (this.warehouseStatus != WarehouseStatus.DEACTIVE) {
            this.warehouseStatus = WarehouseStatus.DEACTIVE;
            this.deletedAt = LocalDateTime.now();  // set deletion time
        } else {
            throw new IllegalStateException("Warehouse is already deactivated.");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private WarehouseId warehouseId;
        private String name;
        private Location location;
        private List<String> failureMessages;
        private WarehouseStatus warehouseStatus;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;

        private Builder() {
        }

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

        public Builder failureMessages(List<String> failureMessages) {
            this.failureMessages = failureMessages;
            return this;
        }

        public Builder warehouseStatus(WarehouseStatus warehouseStatus) {
            this.warehouseStatus = warehouseStatus;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
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
