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
import java.util.UUID;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {
    @Override
    public WarehouseCreatedEvent createWarehouse(String name, Location location, User user) throws IllegalAccessException {
        validateSuperAdminAccess(user);
        Warehouse warehouse = Warehouse.builder()
                .warehouseId(new WarehouseId(UUID.randomUUID()))
                .name(name)
                .location(location)
                .createdBy(user)
                .build();

        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now());
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse, Location newLocation, String newName, User user) throws IllegalAccessException {
        validateSuperAdminAccess(user);
        if (newLocation != null) {
            warehouse.updateLocation(newLocation);
        }
        if (newName != null && !newName.isEmpty()) {
            warehouse.updateName(newName);
        }
        return warehouse;
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse, User user) throws IllegalAccessException {
        validateSuperAdminAccess(user);
        warehouse.deactivateWarehouse();
    }

    @Override
    public Warehouse assignWarehouseAdmin(Warehouse warehouse, User admin, User superAdmin) throws IllegalAccessException {
        validateSuperAdminAccess(superAdmin);
        warehouse.assignAdmin(admin);
        return warehouse;
    }

    private void validateSuperAdminAccess(User user) throws IllegalAccessException {
        if (user.getRole().getType() != UserRoleType.SUPER_ADMIN) {
            throw new IllegalAccessException("Only Super Admins can perform this action.");
        }
    }

    private void validateAdminAccess(Warehouse warehouse, User user) throws IllegalAccessException {
        if (user.getRole().getType() == UserRoleType.WH_ADMIN) {
            throw new IllegalAccessException("Warehouse Admins can only access their assigned warehouses.");
        }
        if (user.getRole().getType() != UserRoleType.SUPER_ADMIN && user.getRole().getType() != UserRoleType.WH_ADMIN) {
            throw new IllegalAccessException("Only Admins can perform this action.");
        }
    }
}
