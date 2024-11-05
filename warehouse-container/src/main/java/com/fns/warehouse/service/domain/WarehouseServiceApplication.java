package com.fns.warehouse.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.fns.warehouse.service.dataaccess", "com.fns.dataaccess", "com.fns.warehouse.service.domain.ports.output.repository" })
@EntityScan(basePackages = { "com.fns.warehouse.service.dataaccess", "com.fns.warehouse.dataaccess", "com.fns.warehouse.service.messaging","com.fns.warehouse.messaging" })
@SpringBootApplication(scanBasePackages = "com.fns")
public class WarehouseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }
}

