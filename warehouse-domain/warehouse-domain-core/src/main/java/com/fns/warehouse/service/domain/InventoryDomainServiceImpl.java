package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.entity.*;
import com.fns.warehouse.service.domain.event.*;
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

        return new InventoryCreatedEvent(inventory, quantity, ZonedDateTime.now());
    }

    @Override
    public InventoryUpdatedEvent updateInventory(Inventory inventory, int quantityChange, User user) {
        validateAdminOrWarehouseAccess(inventory, user);

        if (quantityChange > 0) {
            inventory.increaseQty(quantityChange);
        } else {
            inventory.reduceQty(-quantityChange);
        }

        return new InventoryUpdatedEvent(inventory, quantityChange, ZonedDateTime.now());
    }

    @Override
    public InventoryTransferRequestedEvent requestTransfer(Inventory sourceInventory, UUID destinationWarehouseId, int quantity, User user) {
        validateAdminAccess(user);

        InventoryTransfer transfer = InventoryTransfer.builder()
                .inventoryId(sourceInventory.getId())
                .userId(user.getId())
                .sourceWarehouseId(sourceInventory.getWarehouseId())
                .destinationWarehouseId(new WarehouseId(destinationWarehouseId))
                .quantity(quantity)
                .status(TransferStatus.PENDING)
                .transferType(TransferType.MANUAL)
                .build();
        transfer.createMutationRequest(user.getRole());

        sourceInventory.reserve(quantity);

        return new InventoryTransferRequestedEvent(transfer, quantity, ZonedDateTime.now());
    }

    @Override
    public InventoryTransferApprovedEvent approveTransfer(InventoryTransfer transfer, Inventory sourceInventory, Inventory destinationInventory ,User user) {
        validateAdminAccess(user);

        transfer.approveTransfer(user.getRole());
        sourceInventory.finalizeReservation(transfer.getQuantity(), JournalReason.TRANSFERRED);
        destinationInventory.increaseQty(transfer.getQuantity());

        return new InventoryTransferApprovedEvent(transfer, sourceInventory, destinationInventory, ZonedDateTime.now());
    }

    @Override
    public InventoryTransferCanceledEvent cancelTransfer(InventoryTransfer transfer, Inventory inventory,User user) {
        validateAdminAccess(user);

        transfer.cancelTransfer(user.getRole());
        inventory.cancel(transfer.getQuantity());
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
