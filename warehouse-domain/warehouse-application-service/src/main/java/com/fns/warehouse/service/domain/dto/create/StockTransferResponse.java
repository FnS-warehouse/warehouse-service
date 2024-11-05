package com.fns.warehouse.service.domain.dto.create;

import com.fns.warehouse.service.domain.valueobject.TransferStatus;
import com.fns.warehouse.service.domain.valueobject.TransferType;
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
public class StockTransferResponse {
    @NotNull
    private final TransferStatus transferStatus;

    private final UUID transferTrackingId;
}
