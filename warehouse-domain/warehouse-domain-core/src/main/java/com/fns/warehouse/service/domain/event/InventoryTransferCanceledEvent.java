package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.*;
import com.fns.domain.valueObject.*;

import java.time.ZonedDateTime;

public class InventoryTransferCanceledEvent extends InventoryTransferEvent {
    public InventoryTransferCanceledEvent(InventoryTransfer transfer, ZonedDateTime requestedAt) {
        super(transfer, requestedAt);
    }
}

