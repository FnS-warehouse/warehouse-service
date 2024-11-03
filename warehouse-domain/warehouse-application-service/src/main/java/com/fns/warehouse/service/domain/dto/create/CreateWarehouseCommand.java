package com.fns.warehouse.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@AllArgsConstructor
@Builder
public class CreateWarehouseCommand {
    @NotNull
    private final String name;
    @NotNull
    private final WarehouseLocation location;

    private final List<User> users;

}

