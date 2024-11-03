package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedRequestMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedRequestMessagePublisher;
import com.fns.warehouse.service.domain.ports.output.repository.StockRepository;
import com.fns.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import com.fns.warehouse.service.domain.ports.output.repository.UserRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.fns")
public class WarehouseTestConfiguration {
    @Bean
    public WarehouseCreatedRequestMessagePublisher warehouseCreatedRequestMessagePublisher(){
        return Mockito.mock(WarehouseCreatedRequestMessagePublisher.class);
    }

    @Bean
    public WarehouseRepository warehouseRepository(){
        return Mockito.mock(WarehouseRepository.class);
    }

    @Bean
    public StockRepository stockRepository(){
        return Mockito.mock(StockRepository.class);
    }

    @Bean
    public UserRepository userRepository(){
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public WarehouseDomainService warehouseDomainService(){
        return new WarehouseDomainServiceImpl();
    }

}
