package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Warehouse;
import java.time.ZonedDateTime;

public class WarehouseUpdatedEvent extends WarehouseEvent {

    public WarehouseUpdatedEvent(Warehouse warehouse, String newName, ZonedDateTime updatedAt) {
        super(warehouse, updatedAt);
    }
}
