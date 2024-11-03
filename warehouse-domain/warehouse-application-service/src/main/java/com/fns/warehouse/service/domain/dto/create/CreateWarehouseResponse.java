package com.fns.warehouse.service.domain.dto.create;

import com.fns.warehouse.service.domain.valueobject.WarehouseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseResponse {
    @NotNull
    private final UUID warehouseId;
    @NotNull
    private final WarehouseStatus warehouseStatus;
    @NotNull
    private final String message;
}
