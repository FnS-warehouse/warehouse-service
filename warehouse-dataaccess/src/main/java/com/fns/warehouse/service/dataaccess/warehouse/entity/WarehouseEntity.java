//package com.fns.app.warehouse.service.dataaccess.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.Builder;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "warehouses")
//public class WarehouseEntity {
//
//    @Id
//    private UUID warehouseId;
//
//    private String name;
//
//    @Embedded
//    private Location location;
//
//    @Enumerated(EnumType.STRING)
//    private WarehouseStatus status;
//
//    private LocalDateTime createdAt;
//
//    private LocalDateTime deletedAt;
//
//    private LocalDateTime updatedAt;
//
//    // Methods for warehouse operations
//    public void createWarehouse() {
//        // Logic for creating warehouse
//    }
//
//    public void updateWarehouse() {
//        // Logic for updating warehouse
//    }
//
//    public void deleteWarehouse() {
//        // Logic for deleting warehouse
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        WarehouseEntity that = (WarehouseEntity) o;
//        return warehouseId.equals(that.warehouseId);
//    }
//
//    @Override
//    public int hashCode() {
//        return warehouseId.hashCode();
//    }
//}
