package com.fns.warehouse.service.dataaccess.warehouse.entity;

import com.fns.warehouse.service.domain.valueobject.WarehouseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouses")
@Entity
public class WarehouseEntity {

    @Id
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private WarehouseStatus warehouseStatus;

    private String failureMessages;

//    @JoinColumn(name = "warehouse_location_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private WarehouseLocationEntity warehouseLocation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseEntity that = (WarehouseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
