package com.fns.warehouse.service.domain.ports.input.service;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseResponse;
//import com.fns.warehouse.service.domain.dto.track.TrackOrderQuery;
//import com.fns.warehouse.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface WarehouseApplicationService {
    CreateWarehouseResponse createWarehouse(@Valid CreateWarehouseCommand createOrderCommand);

}
