package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Inventory;
import java.time.ZonedDateTime;

public class InventoryCreatedEvent extends InventoryEvent {
    public InventoryCreatedEvent(Inventory inventory, int totalQuantity, ZonedDateTime createdAt) {
        super(inventory, createdAt);
    }


}
