package com.fns.warehouse.service.messaging.publisher.kafka;

//import com.fns.app.kafka.avro.model.PaymentRequestAvroModel;
//import com.fns.kafka.producer.KafkaMessageHelper;
//import com.fns.kafka.producer.service.KafkaProducer;
//import com.fns.order.service.domain.config.OrderServiceConfigData;
//import com.fns.order.service.domain.event.OrderCreatedEvent;
//import com.fns.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
//import com.fns.order.service.messaging.mapper.OrderMessagingDataMapper;
import com.fns.warehouse.service.domain.event.StockTransferRequestedEvent;
import com.fns.warehouse.service.domain.ports.output.message.publisher.StockRequestRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class StockTransferRequestKafkaMessagePublisher implements StockRequestRequestMessagePublisher {
    @Override
    public void publish(StockTransferRequestedEvent domainEvent) {
        log.info("Stock requested sent to Kafka");
    }
}
