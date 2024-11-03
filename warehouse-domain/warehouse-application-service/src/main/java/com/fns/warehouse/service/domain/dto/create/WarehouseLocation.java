package com.fns.warehouse.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class WarehouseLocation {
    @NotNull
    private final float latitude;
    @NotNull
    private final float longitude;
    @NotNull
    @Max(value = 50)
    private final String address;
    @NotNull
    @Max(value = 10)
    private final String postalCode;
    @NotNull
    @Max(value = 50)
    private final String city;
    @NotNull
    @Max(value = 50)
    private final String province;
    @NotNull
    @Max(value = 50)
    private final String district;

}
