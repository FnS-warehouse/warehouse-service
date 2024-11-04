package com.fns.warehouse.service.domain.mapper;

import com.fns.domain.valueobject.WarehouseId;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.create.WarehouseLocation;
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
