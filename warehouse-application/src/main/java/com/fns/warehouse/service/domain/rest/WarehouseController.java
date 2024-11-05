package com.fns.warehouse.service.domain.rest;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.create.StockTransferCommand;
import com.fns.warehouse.service.domain.dto.create.StockTransferResponse;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/warehouses", produces = "application/vnd.api.v1+json")
public class WarehouseController {

    private final WarehouseApplicationService warehouseApplicationService;

    public WarehouseController(WarehouseApplicationService warehouseApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateWarehouseResponse> createWarehouse(@RequestBody CreateWarehouseCommand createWarehouseCommand) {
        log.info("Creating warehouse {} at {}", createWarehouseCommand.getName(), createWarehouseCommand.getLocation().getAddress());
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        log.info("Warehouse created");
        return ResponseEntity.ok(createWarehouseResponse);
    }

    @PostMapping("/stock/transfer")
    public ResponseEntity<StockTransferResponse> stockTransferRequest(@RequestBody StockTransferCommand stockTransferCommand) {
        log.info("Stock transfer {} to {}", stockTransferCommand.getStockId(), stockTransferCommand.getDestinationWarehouseId());
        StockTransferResponse stockTransferResponse = warehouseApplicationService.transferStock(stockTransferCommand);
        log.info("Stock transfer requested");
        return ResponseEntity.ok(stockTransferResponse);
    }
}
