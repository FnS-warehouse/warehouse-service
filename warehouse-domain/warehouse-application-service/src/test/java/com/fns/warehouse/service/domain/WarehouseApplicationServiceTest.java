package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.fns.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.fns.warehouse.service.domain.dto.create.WarehouseLocation;
import com.fns.warehouse.service.domain.entity.Product;
//import com.fns.warehouse.service.domain.entity.User;
import com.fns.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.fns.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import com.fns.domain.valueobject.*;
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
import java.util.List;
import java.util.Optional;
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
    private CreateWarehouseCommand createWarehouseCommandWrongPrice;
    private CreateWarehouseCommand createWarehouseCommandWrongProductPrice;
    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID SELLER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final UUID ORDER_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");
    private final BigDecimal PRICE = new BigDecimal("200.00");

    @BeforeAll
    public void init(){
        createWarehouseCommand = CreateWarehouseCommand.builder()
                .name("Sinar Jaya")
                .location(WarehouseLocation.builder()
                        .latitude(90.84034903f)
                        .longitude(103.9090332f)
                        .postalCode("100AB")
                        .city("Paris")
                        .district("Sinai")
                        .province("France")
                        .address("Jl. 123")
                        .build())
                .build();

//        createWarehouseCommandWrongPrice = CreateWarehouseCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .sellerId(SELLER_ID)
//                .address(OrderAddress.builder()
//                        .street("Street_1")
//                        .postalCode("100AB")
//                        .city("Paris")
//                        .build())
//                .price(new BigDecimal("250.00"))
//                .items(List.of(OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(1)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("50.00"))
//                                .build(),
//                        OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();
//
//        createWarehouseCommandWrongProductPrice = CreateWarehouseCommand.builder()
//                .customerId(CUSTOMER_ID)
//                .sellerId(SELLER_ID)
//                .address(OrderAddress.builder()
//                        .street("Street_1")
//                        .postalCode("100AB")
//                        .city("Paris")
//                        .build())
//                .price(new BigDecimal("210.00"))
//                .items(List.of(OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(1)
//                                .price(new BigDecimal("60.00"))
//                                .subTotal(new BigDecimal("60.00"))
//                                .build(),
//                        OrderItem.builder()
//                                .productId(PRODUCT_ID)
//                                .quantity(3)
//                                .price(new BigDecimal("50.00"))
//                                .subTotal(new BigDecimal("150.00"))
//                                .build()))
//                .build();

//        Customer customer = new Customer();
//        customer.setId(new CustomerId(CUSTOMER_ID));
//        Seller sellerResponse = Seller.builder()
//                .sellerId(new SellerId(createWarehouseCommand.getSellerId()))
//                .products(List.of(new Product(new ProductId(PRODUCT_ID), "product-1",
//                                new Money(new BigDecimal("50.00"))),
//                        new Product(new ProductId(PRODUCT_ID), "product-2",
//                                new Money(new BigDecimal("50.00")))))
//                .active(true)
//                .build();
//        Order order = warehouseDataMapper.createOrderCommandToOrder(createWarehouseCommand);
//        order.setId(new OrderId(ORDER_ID));
//
//        when(stockRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
//        when(userRepository.findSellerInformation(warehouseDataMapper.createOrderCommandToSeller(createWarehouseCommand)))
//                .thenReturn(Optional.of(sellerResponse));
//        when(warehouseRepository.save(any(Order.class))).thenReturn(order);
    }

    @Test
    public void testCreateOrder(){
        System.out.println(createWarehouseCommand.getName());
        System.out.println(createWarehouseCommand.getLocation().getAddress());
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        assertEquals(createWarehouseResponse.getWarehouseStatus(), WarehouseStatus.ACTIVE);
        assertEquals(createWarehouseResponse.getMessage(), "Warehouse created successfully");
    }
//
//    @Test
//    public void testCreateOrderWithWrongTotalPrice() {
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> warehouseApplicationService.createOrder(createWarehouseCommandWrongPrice));
//        assertEquals(orderDomainException.getMessage(),
//                "Total price: 250.00 is not equal to Order items total: 200.00!");
//    }
//
//    @Test
//    public void testCreateOrderWithWrongProductPrice() {
//        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
//                () -> warehouseApplicationService.createOrder(createWarehouseCommandWrongProductPrice));
//        assertEquals(orderDomainException.getMessage(),
//                "Order item price: 60.00 is not valid for product " + PRODUCT_ID);
//    }


}
