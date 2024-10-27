package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Inventory;
import java.time.ZonedDateTime;

public class InventoryReservedEvent extends InventoryEvent {

    public InventoryReservedEvent(Inventory inventory, int reservedQuantity, ZonedDateTime reservedAt) {
        super(inventory, reservedAt);
    }


}
