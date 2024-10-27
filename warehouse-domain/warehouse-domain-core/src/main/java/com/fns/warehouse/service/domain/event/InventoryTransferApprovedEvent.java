package com.fns.warehouse.service.domain.event;

import com.fns.domain.valueObject.*;
import com.fns.warehouse.service.domain.entity.*;

import java.time.ZonedDateTime;

public class InventoryTransferApprovedEvent extends InventoryTransferEvent {
    public InventoryTransferApprovedEvent(InventoryTransfer transfer, Inventory inventory, ZonedDateTime requestedAt) {
        super(transfer, requestedAt);
    }
}
