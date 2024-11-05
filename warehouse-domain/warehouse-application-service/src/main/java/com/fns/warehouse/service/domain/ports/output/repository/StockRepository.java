package com.fns.warehouse.service.domain.ports.output.repository;

import com.fns.warehouse.service.domain.entity.Stock;

import java.util.UUID;

public interface StockRepository {
    void initializeDefaultInventory(UUID warehouseId);
}
