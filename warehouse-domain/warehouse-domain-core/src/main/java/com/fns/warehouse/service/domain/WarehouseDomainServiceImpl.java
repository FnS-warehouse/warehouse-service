package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.entity.*;
import com.fns.warehouse.service.domain.event.*;
import com.fns.warehouse.service.domain.exception.WarehouseDomainException;
import com.fns.warehouse.service.domain.valueobject.Location;
import com.fns.warehouse.service.domain.valueobject.UserRoleType;
import com.fns.warehouse.service.domain.valueobject.TransferStatus;
import com.fns.warehouse.service.domain.valueobject.TransferType;
import com.fns.domain.valueobject.*;

import com.fns.domain.event.publisher.DomainEventPublisher;

import java.time.ZonedDateTime;
import java.util.UUID;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {

    @Override
    public WarehouseCreatedEvent createWarehouse(String name, Location location) throws WarehouseDomainException {
        Warehouse warehouse = Warehouse.builder()
                .warehouseId(new WarehouseId(UUID.randomUUID()))
                .name(name)
                .location(location)
                .build();

        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now());
    }

    @Override
    public WarehouseUpdatedEvent updateWarehouse(Warehouse warehouse, Location newLocation, String newName) throws WarehouseDomainException {
        if (newLocation != null) {
            warehouse.updateLocation(newLocation);
        }
        if (newName != null && !newName.isEmpty()) {
            warehouse.updateName(newName);
        }
        return new WarehouseUpdatedEvent(warehouse, ZonedDateTime.now());
    }

    @Override
    public WarehouseDeletedEvent deleteWarehouse(Warehouse warehouse) throws WarehouseDomainException {
        warehouse.deactivateWarehouse();
        return new WarehouseDeletedEvent(warehouse, ZonedDateTime.now());
    }

    @Override
    public Warehouse assignWarehouseAdmin(Warehouse warehouse, User admin) throws WarehouseDomainException {
        warehouse.assignAdmin(admin);
        return warehouse;
    }

    @Override
    public StockCreatedEvent createStock(UUID warehouseId, UUID productId, int quantity) {

        Stock stock = Stock.builder()
                .stockId(new StockId(UUID.randomUUID()))
                .warehouseId(new WarehouseId(warehouseId))
                .productId(new ProductId(productId))
                .totalQuantity(quantity)
                .availableQuantity(quantity)
                .reservedQuantity(0)
                .build();

        return new StockCreatedEvent(stock, quantity, ZonedDateTime.now());
    }

    @Override
    public StockUpdatedEvent updateStock(Stock stock, int quantityChange) {

        if (quantityChange > 0) {
            stock.increaseQty(quantityChange);
        } else {
            stock.reduceQty(-quantityChange);
        }

        return new StockUpdatedEvent(stock, quantityChange, ZonedDateTime.now());
    }

    @Override
    public StockTransferRequestedEvent requestStockTransfer(Stock sourceStock, Stock destinationStock, int quantity, TransferType transferType, DomainEventPublisher<StockTransferRequestedEvent> publisher) throws WarehouseDomainException {
        if (quantity <= 0) {
            throw new WarehouseDomainException("Transfer quantity must be positive.");
        }
        if (!sourceStock.getProductId().equals(destinationStock.getProductId())) {
            throw new WarehouseDomainException("Product IDs must match for stock transfer.");
        }
        // Initiate transfer in source stock
        sourceStock.initiateTransfer(transferType);
        // Reserve quantity in source stock
        sourceStock.reserve(quantity);
        // Create and return event
        StockTransferRequestedEvent event = new StockTransferRequestedEvent(sourceStock, destinationStock, quantity, ZonedDateTime.now(), publisher);
        event.fire();
        return event;
    }

    @Override
    public StockTransferApprovedEvent approveStockTransfer(Stock sourceStock, Stock destinationStock, int quantity, DomainEventPublisher<StockTransferApprovedEvent> publisher) throws WarehouseDomainException {
        if (quantity <= 0) {
            throw new WarehouseDomainException("Transfer quantity must be positive.");
        }
        if (sourceStock.getTransferStatus() != TransferStatus.PENDING) {
            throw new WarehouseDomainException("Transfer must be in PENDING state to approve.");
        }

        // Change status
        sourceStock.approveTransfer();

        // Decrease source quantity
        sourceStock.finalizeReservation(quantity);

        // Increase destination quantity
        destinationStock.increaseQty(quantity);

        StockTransferApprovedEvent event = new StockTransferApprovedEvent(sourceStock, destinationStock, quantity, ZonedDateTime.now(), publisher);
        event.fire();
        return event;
    }

    @Override
    public StockTransferCanceledEvent cancelStockTransfer(Stock sourceStock, int quantity, DomainEventPublisher<StockTransferCanceledEvent> publisher) throws WarehouseDomainException {
        if (quantity <= 0) {
            throw new WarehouseDomainException("Transfer quantity must be positive.");
        }
        if (sourceStock.getTransferStatus() != TransferStatus.PENDING) {
            throw new WarehouseDomainException("Only PENDING transfers can be canceled.");
        }

        sourceStock.cancelTransfer();

        sourceStock.cancel(quantity);

        StockTransferCanceledEvent event = new StockTransferCanceledEvent(sourceStock, null, quantity, ZonedDateTime.now(), publisher);
        event.fire();
        return event;
    }

//    private void validateSuperAdminAccess(User user) throws WarehouseDomainException {
//        if (user.getUserRole().getType() != UserRoleType.SUPER_ADMIN) {
//            throw new WarehouseDomainException("Only Super Admins can perform this action.");
//        }
//    }
//
//    private void validateAdminAccess(User user) {
//        if (user.getUserRole().getType() != UserRoleType.SUPER_ADMIN && user.getUserRole().getType() != UserRoleType.WH_ADMIN) {
//            throw new IllegalStateException("Only Super Admins or Warehouse Admins can manage inventory.");
//        }
//    }
}
