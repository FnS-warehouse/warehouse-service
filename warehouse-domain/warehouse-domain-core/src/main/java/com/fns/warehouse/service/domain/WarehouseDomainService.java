package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.entity.*;
import com.fns.warehouse.service.domain.valueobject.*;
import com.fns.warehouse.service.domain.event.*;

import java.util.List;

public interface WarehouseDomainService {

    WarehouseCreatedEvent createWarehouse(String name, Location location, User user) throws IllegalAccessException;
    Warehouse updateWarehouse(Warehouse warehouse, Location newLocation, String newName, User user) throws IllegalAccessException;
    void deleteWarehouse(Warehouse warehouse, User user) throws IllegalAccessException;
    Warehouse assignWarehouseAdmin(Warehouse warehouse, User admin, User superAdmin) throws IllegalAccessException;

}
