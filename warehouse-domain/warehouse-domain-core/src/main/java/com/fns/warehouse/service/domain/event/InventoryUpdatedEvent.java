package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Inventory;
import com.fns.domain.valueObject.JournalReason;
import java.time.ZonedDateTime;

public class InventoryUpdatedEvent extends InventoryEvent {
    private final int quantityChange;
    private final JournalReason reason;

    public InventoryUpdatedEvent(Inventory inventory, int quantityChange, JournalReason reason, ZonedDateTime updatedAt) {
        super(inventory, updatedAt);
        this.quantityChange = quantityChange;
        this.reason = reason;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

    public JournalReason getReason() {
        return reason;
    }
}
