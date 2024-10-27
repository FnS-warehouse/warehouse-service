package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.entity.*;
import com.fns.warehouse.service.domain.event.*;
import com.fns.warehouse.service.domain.valueobject.*;
import com.fns.domain.valueObject.*;
import java.time.ZonedDateTime;
import java.util.UUID;

public class InventoryDomainServiceImpl implements InventoryDomainService {

    @Override
    public InventoryCreatedEvent createInventory(UUID warehouseId, UUID productId, int quantity, User user) {
        validateAdminAccess(user);

        Inventory inventory = Inventory.builder()
                .warehouseId(new WarehouseId(warehouseId))
                .productId(new ProductId(productId))
                .totalQuantity(quantity)
                .availableQuantity(quantity)
                .reservedQuantity(0)
                .build();
        inventory.initializeInventory();

        return new InventoryCreatedEvent(inventory, quantity, ZonedDateTime.now());
    }

    @Override
    public InventoryUpdatedEvent updateInventory(Inventory inventory, JournalReason reason, int quantityChange, User user) {
        validateAdminOrWarehouseAccess(inventory, user);

        if (quantityChange > 0) {
            inventory.addTotalQty(quantityChange, user.getRole());
        } else {
            inventory.reduceTotalQty(-quantityChange, user.getRole());
        }

        return new InventoryUpdatedEvent(inventory, quantityChange, reason ,ZonedDateTime.now());
    }

    @Override
    public InventoryTransferRequestedEvent requestTransfer(Inventory sourceInventory, UUID destinationWarehouseId, int quantity, User user) {
        validateAdminAccess(user);

        InventoryTransfer transfer = InventoryTransfer.builder()
                .inventoryId(sourceInventory.getId())
                .user(user.getId())
                .WHsourceId(sourceInventory.getWarehouseId())
                .WHdestinationId(new WarehouseId(destinationWarehouseId))
                .quantity(quantity)
                .status(TransferStatus.PENDING)
                .transferType(TransferType.MANUAL)
                .build();
        transfer.createMutationRequest(quantity, user.getRole());

        return new InventoryTransferRequestedEvent(transfer, quantity, ZonedDateTime.now());
    }

    @Override
    public InventoryTransferApprovedEvent approveTransfer(InventoryTransfer transfer, Inventory inventory, User user) {
        validateAdminAccess(user);

        transfer.approveTransfer(user.getRole());
        inventory.reduceTotalQty(transfer.getQuantity(), user.getRole());
        inventory.addTotalQty(transfer.getQuantity(), user.getRole());

        // Return an event indicating the transfer was approved
        return new InventoryTransferApprovedEvent(transfer, inventory, ZonedDateTime.now());
    }

    @Override
    public InventoryTransferCanceledEvent cancelTransfer(InventoryTransfer transfer, User user) {
        validateAdminAccess(user);

        transfer.cancelTransfer(user.getRole());

        // Return an event indicating the transfer was canceled
        return new InventoryTransferCanceledEvent(transfer, ZonedDateTime.now());
    }

    private void validateAdminAccess(User user) {
        if (user.getRole().getType() != UserRoleType.SUPER_ADMIN && user.getRole().getType() != UserRoleType.WH_ADMIN) {
            throw new IllegalStateException("Only Super Admins or Warehouse Admins can manage inventory.");
        }
    }

    private void validateAdminOrWarehouseAccess(Inventory inventory, User user) {
        if (user.getRole().getType() == UserRoleType.WH_ADMIN) {
            throw new IllegalStateException("Warehouse Admins can only access their assigned warehouse inventory.");
        }
        validateAdminAccess(user);
    }

}
