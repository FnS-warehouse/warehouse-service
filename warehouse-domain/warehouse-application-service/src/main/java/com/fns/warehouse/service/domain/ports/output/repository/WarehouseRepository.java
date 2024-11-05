package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.entity.*;

import java.util.UUID;

public interface WarehouseRepository  {
    Warehouse save(Warehouse warehouse);

    Stock getStock(UUID stockId);
}
