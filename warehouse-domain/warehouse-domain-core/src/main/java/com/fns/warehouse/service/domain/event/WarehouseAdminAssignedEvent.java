package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.entity.User;
import java.time.ZonedDateTime;

public class WarehouseAdminAssignedEvent extends WarehouseEvent {
    public WarehouseAdminAssignedEvent(Warehouse warehouse, User assignedAdmin, ZonedDateTime assignedAt) {
        super(warehouse, assignedAt);
    }
}
