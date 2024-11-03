package com.fns.warehouse.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Getter
@AllArgsConstructor
@Builder
public class CreateWarehouseCommand {
    private final UUID warehouseId;
    @NotNull
    private final String name;
    @NotNull
    private final WarehouseLocation location;

    private final List<User> users;

}

