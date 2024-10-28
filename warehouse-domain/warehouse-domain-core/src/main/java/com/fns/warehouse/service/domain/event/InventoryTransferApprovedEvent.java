package com.fns.warehouse.service.domain.event;

import com.fns.domain.valueObject.*;
import com.fns.warehouse.service.domain.entity.*;

import java.time.ZonedDateTime;

public class InventoryTransferApprovedEvent extends InventoryTransferEvent {
    public InventoryTransferApprovedEvent(InventoryTransfer transfer, Inventory sourceInventory, Inventory destinationInventory ,ZonedDateTime requestedAt) {
        super(transfer, requestedAt);
    }
}
