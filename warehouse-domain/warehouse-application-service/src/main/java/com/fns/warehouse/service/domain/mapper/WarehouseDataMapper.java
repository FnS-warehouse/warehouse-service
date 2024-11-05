package com.fns.warehouse.service.domain.mapper;

import com.fns.domain.valueobject.ProductId;
import com.fns.domain.valueobject.WarehouseId;
import com.fns.warehouse.service.domain.dto.create.*;
import com.fns.warehouse.service.domain.entity.Stock;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.exception.WarehouseDomainException;
import com.fns.warehouse.service.domain.valueobject.Location;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataMapper {
    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(createWarehouseCommand.getWarehouseId()))
                .name(createWarehouseCommand.getName())
                .location(warehouseLocationToLocation(createWarehouseCommand.getLocation()))
//                .warehouseAdmins(usersToUserEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateWarehouseResponse warehouseToCreateWarehouseResponse(Warehouse warehouse, String message) {
        return CreateWarehouseResponse.builder()
                .warehouseId(warehouse.getWarehouseId().getValue())
                .warehouseStatus(warehouse.getStatus())
                .message(message)
                .build();
    }

    public StockTransferResponse warehouseToCreateWarehouseResponse(Stock stock, String message) {
        return StockTransferResponse.builder()
                .transferStatus(stock.getTransferStatus())
                .build();
    }

//    public Stock stockTransferCommandToStock(StockTransferCommand stockTransferCommand) {
//        return Stock.builder()
//                .warehouseId(new WarehouseId(stockTransferCommand.getDestinationWarehouseId()))
//                .productId(new ProductId(stockTransferCommand.getStockId()))
//                .reservedQuantity(stockTransferCommand.getQty())
//                .build();
//    }

//    private List<User> usersToUserEntities(
//        @NotNull List<com.fns.warehouse.service.domain.dto.create.User> users) {
//        return users.stream()
//            .map(user ->
//                    user.getName()
//        }

    private Location warehouseLocationToLocation(WarehouseLocation warehouseLocation) {
        if(warehouseLocation == null){
            throw new WarehouseDomainException("Location must be set.");
        }

        return Location.builder()
                .latitude(warehouseLocation.getLatitude())
                .longitude(warehouseLocation.getLongitude())
                .address(warehouseLocation.getAddress())
                .city(warehouseLocation.getCity())
                .postalCode(warehouseLocation.getPostalCode())
                .province(warehouseLocation.getProvince())
                .district(warehouseLocation.getDistrict())
                .build();
    }
}
