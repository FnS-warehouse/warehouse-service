//package com.fns.warehouse.service.dataaccess.warehouse.adapter;
//
//import com.fns.domain.valueobject.OrderId;
//import com.fns.warehouse.service.dataaccess.warehouse.mapper.OrderDataAccessMapper;
//import com.fns.warehouse.service.dataaccess.warehouse.repository.OrderJpaRepository;
//import com.fns.warehouse.service.domain.entity.Warehouse;
//import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
//import org.springframework.stereotype.Component;
//import java.util.Optional;
//
//@Component
//public class OrderRepositoryImpl implements OrderRepository {
//
//    private final OrderJpaRepository orderJpaRepository;
//    private final OrderDataAccessMapper orderDataAccessMapper;
//
//    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository,
//                               OrderDataAccessMapper orderDataAccessMapper) {
//        this.orderJpaRepository = orderJpaRepository;
//        this.orderDataAccessMapper = orderDataAccessMapper;
//    }
//
//    @Override
//    public Order save(Order order) {
//        return orderDataAccessMapper.orderEntityToOrder(orderJpaRepository
//                .save(orderDataAccessMapper.orderToOrderEntity(order)));
//    }
//
//    @Override
//    public Optional<Order> findById(OrderId orderId) {
//        return orderJpaRepository.findById(orderId.getValue()).map(orderDataAccessMapper::orderEntityToOrder);
//    }
//
//    @Override
//    public Optional<Order> findByTrackingId(TrackingId trackingId) {
//        return orderJpaRepository.findByTrackingId(trackingId.getValue())
//                .map(orderDataAccessMapper::orderEntityToOrder);
//    }
//}