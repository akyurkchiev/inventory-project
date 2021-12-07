package com.inventory.api.v1.asset.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AssetDto {
    private final String model;
    private final int total;
    private final int unassigned;
    private final List<AssetDetails> details;
}
