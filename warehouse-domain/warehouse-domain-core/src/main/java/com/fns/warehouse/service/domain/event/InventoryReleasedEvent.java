package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Inventory;
import java.time.ZonedDateTime;

public class InventoryReleasedEvent extends InventoryEvent {
    public InventoryReleasedEvent(Inventory inventory, int releasedQuantity, ZonedDateTime releasedAt) {
        super(inventory, releasedAt);
    }


}
