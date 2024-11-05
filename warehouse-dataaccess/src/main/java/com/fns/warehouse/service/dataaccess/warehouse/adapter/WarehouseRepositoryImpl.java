package com.fns.warehouse.service.dataaccess.warehouse.adapter;

import com.fns.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.warehouse.mapper.WarehouseDataAccessMapper;
import com.fns.warehouse.service.dataaccess.warehouse.repository.WarehouseJpaRepository;
import com.fns.warehouse.service.domain.entity.Stock;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseRepositoryImpl implements WarehouseRepository {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final WarehouseDataAccessMapper warehouseDataAccessMapper;

    public WarehouseRepositoryImpl(WarehouseJpaRepository warehouseJpaRepository,
                                   WarehouseDataAccessMapper warehouseDataAccessMapper) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.warehouseDataAccessMapper = warehouseDataAccessMapper;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseDataAccessMapper.warehouseEntityToWarehouse(warehouseJpaRepository
                .save(warehouseDataAccessMapper.warehouseToWarehouseEntity(warehouse)));
    }

    @Override
    public Stock getStock(UUID stockId) {
        return null;
    }

}