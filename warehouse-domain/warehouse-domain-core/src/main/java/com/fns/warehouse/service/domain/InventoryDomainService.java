package com.fns.warehouse.service.domain;

import com.fns.domain.valueObject.JournalReason;
import com.fns.domain.valueObject.WarehouseId;
import com.fns.warehouse.service.domain.valueobject.*;

import com.fns.warehouse.service.domain.entity.*;
import com.fns.warehouse.service.domain.event.*;

import java.util.UUID;


public interface InventoryDomainService {

    InventoryCreatedEvent createInventory(UUID warehouseId, UUID productId, int quantity, User user);

    InventoryUpdatedEvent updateInventory(Inventory inventory, JournalReason reason, int quantityChange, User user);

    InventoryTransferRequestedEvent requestTransfer(Inventory sourceInventory, UUID destinationWarehouseId, int quantity, User user);

    InventoryTransferApprovedEvent approveTransfer(InventoryTransfer transfer, Inventory inventory, User user);

    InventoryTransferCanceledEvent cancelTransfer(InventoryTransfer transfer, User user);
}
