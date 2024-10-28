package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Inventory;
import com.fns.domain.valueObject.JournalReason;
import java.time.ZonedDateTime;

public class InventoryUpdatedEvent extends InventoryEvent {
    private final int quantityChange;

    public InventoryUpdatedEvent(Inventory inventory, int quantityChange, ZonedDateTime updatedAt) {
        super(inventory, updatedAt);
        this.quantityChange = quantityChange;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

}
