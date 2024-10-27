package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.entity.User;
import com.fns.warehouse.service.domain.valueobject.Location;
import com.fns.warehouse.service.domain.event.InventoryTransferRequestedEvent;
import com.fns.warehouse.service.domain.event.InventoryCreatedEvent;
import com.fns.warehouse.service.domain.event.InventoryTransferCompletedEvent;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.domain.valueObject.*;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.time.ZonedDateTime;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {
    @Override
    public WarehouseCreatedEvent createWarehouse(Warehouse warehouse, Location location, User user) throws IllegalAccessException {
        validateSuperAdminAccess(user);
        warehouse.updateLocation(location, user.getRole());
        warehouse.initializeWarehouse(user);

        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now());
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse, Location newLocation, String newName, User user) throws IllegalAccessException {
        validateSuperAdminAccess(user);
        if (newLocation != null) {
            warehouse.updateLocation(newLocation, user.getRole());
        }
        if (newName != null && !newName.isEmpty()) {
            warehouse.updateName(newName, user.getRole());
        }
        return warehouse;
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse, User user) throws IllegalAccessException {
        validateSuperAdminAccess(user);
        warehouse.deactivateWarehouse(user.getRole());
    }

    @Override
    public Warehouse assignWarehouseAdmin(Warehouse warehouse, User admin, User superAdmin) throws IllegalAccessException {
        validateSuperAdminAccess(superAdmin);
        warehouse.assignAdmin(admin, admin.getRole());
        return warehouse;
    }

    // Helper method to validate Super Admin access
    private void validateSuperAdminAccess(User user) throws IllegalAccessException {
        if (user.getRole().getType() != UserRoleType.SUPER_ADMIN) {
            throw new IllegalAccessException("Only Super Admins can perform this action.");
        }
    }

    // Helper method to validate Admin or Warehouse Admin access
    private void validateAdminAccess(Warehouse warehouse, User user) throws IllegalAccessException {
        if (user.getRole().getType() == UserRoleType.WH_ADMIN) {
            throw new IllegalAccessException("Warehouse Admins can only access their assigned warehouses.");
        }
        if (user.getRole().getType() != UserRoleType.SUPER_ADMIN && user.getRole().getType() != UserRoleType.WH_ADMIN) {
            throw new IllegalAccessException("Only Admins can perform this action.");
        }
    }
}
