package com.fns.warehouse.service.domain.dto.create;

import com.fns.warehouse.service.domain.valueobject.TransferType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class StockTransferCommand {
    @NotNull
    private final UUID sourceStockId;
    @NotNull
    private final UUID destinationStockId;
    @NotNull
    private final Integer qty;
    @NotNull
    private final TransferType transferType;
}
