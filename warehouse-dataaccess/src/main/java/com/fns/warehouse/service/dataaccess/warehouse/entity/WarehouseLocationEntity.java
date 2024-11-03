package com.fns.warehouse.service.dataaccess.warehouse.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouse_location")
@Entity
public class WarehouseLocationEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private float latitude;

    private float longitude;

    private String address;

    private String city;

    private String province;

    private String district;

    private String postalCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WarehouseEntity warehouse;

    public void setWarehouseEntity(WarehouseEntity warehouseEntity) {
    }
}
