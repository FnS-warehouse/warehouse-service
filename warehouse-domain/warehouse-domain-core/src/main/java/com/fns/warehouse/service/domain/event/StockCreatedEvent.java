package com.fns.warehouse.service.domain.event;

import com.fns.warehouse.service.domain.entity.Stock;
import java.time.ZonedDateTime;

public class StockCreatedEvent extends StockEvent {
    public StockCreatedEvent(Stock stock, int totalQuantity, ZonedDateTime createdAt) {
        super(stock, createdAt);
    }


    @Override
    public void fire() {

    }
}
