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
    private final List<User> warehouseAdmins;
    private WarehouseStatus status;

    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        this.name = builder.name;
        this.location = builder.location;
        User createdBy = builder.createdBy;
        this.warehouseAdmins = new ArrayList<>(builder.warehouseAdmins);
        this.status = builder.status;
        LocalDateTime createdAt = builder.createdAt;
        this.deletedAt = builder.deletedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void deactivateWarehouse() {
        this.status = WarehouseStatus.DEACTIVE;
        this.deletedAt = LocalDateTime.now();
    }

    public void updateName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLocation(Location newLocation) {
        this.location = newLocation;
        this.updatedAt = LocalDateTime.now();
    }

    public void assignAdmin(User user) {
        if (!warehouseAdmins.contains(user)) {
            warehouseAdmins.add(user);
            this.updatedAt = LocalDateTime.now();
        }
    }

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
        private List<User> warehouseAdmins = new ArrayList<>();
        private WarehouseStatus status = WarehouseStatus.ACTIVE;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime deletedAt;

        public Builder warehouseId(WarehouseId warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder name(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty.");
            }
            this.name = name;
            return this;
        }

        public Builder location(Location location) {
            if (location == null) {
                throw new IllegalArgumentException("Location cannot be null.");
            }
            this.location = location;
            return this;
        }

        public Builder createdBy(User createdBy) {
            if (createdBy == null) {
                throw new IllegalArgumentException("Creator cannot be null.");
            }
            this.createdBy = createdBy;
            return this;
        }

        public Builder warehouseAdmins(List<User> warehouseAdmins) {
            if (warehouseAdmins != null) {
                this.warehouseAdmins = new ArrayList<>(warehouseAdmins);
            }
            return this;
        }

        public Builder status(WarehouseStatus status) {
            if (status != null) {
                this.status = status;
            }
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            if (createdAt != null) {
                this.createdAt = createdAt;
            }
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
            if (this.warehouseId == null) {
                throw new IllegalStateException("WarehouseId must be set.");
            }
            if (this.name == null || this.name.trim().isEmpty()) {
                throw new IllegalStateException("Name must be set.");
            }
            if (this.location == null) {
                throw new IllegalStateException("Location must be set.");
            }
            if (this.createdBy == null) {
                throw new IllegalStateException("Creator must be set.");
            }
            return new Warehouse(this);
        }
    }
}
