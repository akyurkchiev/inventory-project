package com.inventory.api.v1.asset.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class InventoryDto {
    private final String category;
    private final List<AssetDto> assets;
}
