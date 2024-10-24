package com.fns.warehouse.service.domain.entity;

import com.fns.domain.valueObject.*;
import java.util.List;
import java.util.UUID;

public class UserRole {
    private UserRoleId roleId;
    private UserRoleType type;
    private List<WarehouseId> assignedWarehouses;

    public UserRole(UserRoleId roleId, UserRoleType type, List<WarehouseId> assignedWarehouses) {
        this.roleId = roleId;
        this.type = type;
        this.assignedWarehouses = assignedWarehouses;
    }

    // Getters
    public UserRoleId getRoleId() {
        return roleId;
    }

    public UserRoleType getType() {
        return type;
    }

    public List<WarehouseId> getAssignedWarehouses() {
        return assignedWarehouses;
    }

    // Assign a warehouse to a Warehouse Admin
    public void assignWarehouse(WarehouseId warehouseId) {
        if (!assignedWarehouses.contains(warehouseId)) {
            assignedWarehouses.add(warehouseId);
        } else {
            throw new IllegalStateException("Warehouse Admin is already assigned to this warehouse.");
        }
    }

    // Reassign a Warehouse Admin to another warehouse
    public void reassignWarehouse(WarehouseId oldWarehouseId, WarehouseId newWarehouseId) {
        if (assignedWarehouses.contains(oldWarehouseId)) {
            assignedWarehouses.remove(oldWarehouseId);
            assignedWarehouses.add(newWarehouseId);
        } else {
            throw new IllegalStateException("Warehouse Admin is not assigned to the previous warehouse.");
        }
    }

    // Remove access to a warehouse
    public void removeWarehouseAssignment(WarehouseId warehouseId) {
        if (assignedWarehouses.contains(warehouseId)) {
            assignedWarehouses.remove(warehouseId);
        } else {
            throw new IllegalStateException("Warehouse Admin is not assigned to this warehouse.");
        }
    }

    // Check if Warehouse Admin has access to a specific warehouse
    public boolean hasAccessToWarehouse(WarehouseId warehouseId) {
        return assignedWarehouses.contains(warehouseId);
    }
}
