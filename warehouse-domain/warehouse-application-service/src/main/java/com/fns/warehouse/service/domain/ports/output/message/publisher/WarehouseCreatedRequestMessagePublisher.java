package com.fns.warehouse.service.domain.ports.output.message.publisher;

import com.fns.domain.event.publisher.DomainEventPublisher;
import com.fns.warehouse.service.domain.event.WarehouseCreatedEvent;
import org.springframework.stereotype.Component;

public interface WarehouseCreatedRequestMessagePublisher extends DomainEventPublisher<WarehouseCreatedEvent> {

}
