package com.inventory.api.v1.asset.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AssetDto {
    private final String model;
    private final String serialNumber;
    private final String manufacturer;
    private final String category;
    private final AssetStatus status;
    private final String assignedDate;
}
