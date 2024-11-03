package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseCreateCommandHandler {
    private final WarehouseCreateHelper warehouseCreateHelper;

    private final WarehouseDataMapper warehouseDataMapper;

//    private final WarehouseCreatedRequestMessagePublisher warehouseCreatedRequestMessagePublisher;

    public WarehouseCreateCommandHandler(WarehouseCreateHelper warehouseCreateHelper,
                                     WarehouseDataMapper warehouseDataMapper) {
        this.warehouseCreateHelper = warehouseCreateHelper;
        this.warehouseDataMapper = warehouseDataMapper;
//        this.warehouseCreatedRequestMessagePublisher = warehouseCreatedRequestMessagePublisher;
    }

    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseCreateHelper.persistOrder(createWarehouseCommand);
        log.info("Warehouse is created with id: {}", warehouseCreatedEvent.getEntity().getId().getValue());
//        warehouseCreatedRequestMessagePublisher.publish(warehouseCreatedEvent);
        return warehouseDataMapper.orderToCreateOrderResponse(warehouseCreatedEvent.getEntity(),
                "Warehouse created successfully");
    }
}
