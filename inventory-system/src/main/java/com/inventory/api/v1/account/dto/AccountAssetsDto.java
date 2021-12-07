package com.inventory.api.v1.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountAssetsDto {
    private final String category;
    private final String model;
    private final String serialNumber;
    private final String assignedDate;
}
