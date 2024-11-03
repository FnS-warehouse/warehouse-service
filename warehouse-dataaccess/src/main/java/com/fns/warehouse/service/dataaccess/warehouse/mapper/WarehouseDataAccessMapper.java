package com.fns.warehouse.service.dataaccess.warehouse.mapper;

import com.fns.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.fns.warehouse.service.dataaccess.warehouse.entity.WarehouseLocationEntity;
import com.fns.domain.valueobject.*;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.valueobject.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static com.fns.warehouse.service.domain.entity.Warehouse.FAILURE_MESSAGE_DELIMITER;

@Component
public class WarehouseDataAccessMapper {
    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(warehouse.getWarehouseId().getValue())
                .name(warehouse.getName())
                .warehouseStatus(warehouse.getStatus())
                .failureMessages(warehouse.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, warehouse.getFailureMessages()) : "")
                .build();

        // Map Location to WarehouseLocationEntity
        if (warehouse.getLocation() != null) {
            WarehouseLocationEntity locationEntity = locationToWarehouseLocationEntity(warehouse.getLocation());
            locationEntity.setWarehouseEntity(warehouseEntity);
            warehouseEntity.setWarehouseLocation(locationEntity);
        }

        return warehouseEntity;
    }

    public WarehouseLocationEntity locationToWarehouseLocationEntity(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        return WarehouseLocationEntity.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .address(location.getAddress())
                .city(location.getCity())
                .province(location.getProvince())
                .district(location.getDistrict())
                .postalCode(location.getPostalCode())
                .build();
    }


    public Warehouse warehouseEntityToWarehouse(WarehouseEntity warehouseEntity) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(warehouseEntity.getId()))
                .name(warehouseEntity.getName())
                .status(warehouseEntity.getWarehouseStatus())
                .failureMessages(warehouseEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(warehouseEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }
}
