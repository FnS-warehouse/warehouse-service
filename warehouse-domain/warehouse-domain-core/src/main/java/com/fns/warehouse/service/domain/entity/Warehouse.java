package com.fns.warehouse.service.domain.entity;

import com.fns.domain.valueObject.WarehouseId;
import com.fns.domain.entity.AggregateRoot;

import java.util.List;

public class Warehouse extends AggregateRoot<WarehouseId> {

    private String name;
    private final Location location;
    private String specialty;
    private List<String> failureMessages;
//    private WarehouseStatus warehouseStatus;

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        this.name = builder.name;
        this.location = builder.location;
        this.specialty = builder.specialty;
        this.failureMessages = builder.failureMessages;
//        this.warehouseStatus = builder.warehouseStatus;
    }




    // Getters
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getSpecialty() {
        return specialty;
    }

//    public WarehouseStatus getWarehouseStatus() {
//        return warehouseStatus;
//    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    // Builder pattern implementation for Warehouse entity
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private WarehouseId warehouseId;
        private String name;
        private Location location;
        private String specialty;
        private List<String> failureMessages;
//        private WarehouseStatus warehouseStatus;

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

        public Builder specialty(String specialty) {
            this.specialty = specialty;
            return this;
        }

        public Builder failureMessages(List<String> failureMessages) {
            this.failureMessages = failureMessages;
            return this;
        }

//        public Builder warehouseStatus(WarehouseStatus warehouseStatus) {
//            this.warehouseStatus = warehouseStatus;
//            return this;
//        }

        public Warehouse build() {
            return new Warehouse(this);
        }

        public String viewWarehouse()  {
            return this.name;
        }

    }

}
