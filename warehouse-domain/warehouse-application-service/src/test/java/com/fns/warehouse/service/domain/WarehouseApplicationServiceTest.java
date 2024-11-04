package com.fns.warehouse.service.domain;

import com.fns.domain.valueobject.WarehouseId;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.create.WarehouseLocation;
//import com.fns.warehouse.service.domain.entity.User;
import com.fns.warehouse.service.domain.entity.Warehouse;
import com.fns.warehouse.service.domain.exception.WarehouseDomainException;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import com.fns.warehouse.service.domain.ports.output.repository.StockRepository;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.valueobject.WarehouseStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = WarehouseTestConfiguration.class)
public class WarehouseApplicationServiceTest {
    @Autowired
    private WarehouseApplicationService warehouseApplicationService;

    @Autowired
    private WarehouseDataMapper warehouseDataMapper;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    private CreateWarehouseCommand createWarehouseCommand;
    private CreateWarehouseCommand createWarehouseCommandLocationNotCompleted;
    private CreateWarehouseCommand createWarehouseCommandWrongProductPrice;
    private final UUID WAREHOUSE_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID STOCK_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final BigDecimal PRICE = new BigDecimal("200.00");

    @BeforeAll
    public void init(){
        createWarehouseCommand = CreateWarehouseCommand.builder()
                .warehouseId(WAREHOUSE_ID)
                .name("Sinar Jaya")
                .location(WarehouseLocation.builder()
                        .latitude(56.90f)
                        .longitude(103.90f)
                        .postalCode("100AB")
                        .city("Paris")
                        .district("Sinai")
                        .province("France")
                        .address("Jl. 123")
                        .build())
                .build();

        createWarehouseCommandLocationNotCompleted = CreateWarehouseCommand.builder()
                .name("sinar Jaya")
                .build();

        Warehouse warehouse = warehouseDataMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        warehouse.setId(new WarehouseId(WAREHOUSE_ID));

        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);
    }

    @Test
    public void testCreateOrder(){
        System.out.println(createWarehouseCommand.getName());
        System.out.println(createWarehouseCommand.getLocation().getAddress());
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        assertEquals(createWarehouseResponse.getWarehouseStatus(), WarehouseStatus.ACTIVE);
        assertEquals(createWarehouseResponse.getMessage(), "Warehouse created successfully");
    }

    @Test
    public void testCreateOrderWithNullLocation() {
        WarehouseDomainException warehouseDomainException = assertThrows(WarehouseDomainException.class,
                () -> warehouseApplicationService.createWarehouse(createWarehouseCommandLocationNotCompleted));
        assertEquals(warehouseDomainException.getMessage(),
                "Location must be set.");
    }

}
