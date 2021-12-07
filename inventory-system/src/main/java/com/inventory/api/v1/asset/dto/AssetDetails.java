package com.inventory.api.v1.asset.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AssetDetails {
    private final String fullName;
    private final String email;
    private final String serialNumber;
    private final AssetStatus status;
    private final String assignedDate;
}
