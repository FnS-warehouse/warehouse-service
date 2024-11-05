//package com.fns.warehouse.service.domain;
//
//import com.fns.warehouse.service.domain.event.StockTransferRequestedEvent;
//import com.fns.warehouse.service.domain.ports.output.message.publisher.StockRequestRequestMessagePublisher;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionalEventListener;
//
//@Slf4j
//@Component
//public class StockTransferEventApplicationListener {
//    private final StockRequestRequestMessagePublisher stockRequestRequestMessagePublisher;
//
//    public StockTransferEventApplicationListener(StockRequestRequestMessagePublisher
//                                                        stockRequestRequestMessagePublisher) {
//        this.stockRequestRequestMessagePublisher = stockRequestRequestMessagePublisher;
//    }
//
//    @TransactionalEventListener
//    void process(StockTransferRequestedEvent stockTransferRequestedEvent) {
//        stockRequestRequestMessagePublisher.publish(stockTransferRequestedEvent);
//    }
//
//}
