package com.fns.warehouse.service.domain.ports.output.message.publisher;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.event.StockTransferRequestedEvent;

public interface StockRequestRequestMessagePublisher extends DomainEventPublisher<StockTransferRequestedEvent>  {
}
