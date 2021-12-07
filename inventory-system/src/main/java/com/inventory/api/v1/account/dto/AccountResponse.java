package com.inventory.api.v1.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountResponse {
    private String name;
    private String username;
    private String email;
    private List<AccountAssetsDto> assets;

}
