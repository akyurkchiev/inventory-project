package com.inventory.api.v1.account.service;

import com.inventory.api.v1.account.domain.AccountEntity;
import com.inventory.api.v1.account.dto.AccountResponse;
import com.inventory.api.v1.account.repository.AccountRepository;
import com.inventory.api.v1.asset.domain.AssetEntity;
import com.inventory.api.v1.asset.dto.AssetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public List<AccountResponse> getAllAccounts(){
        List<AccountEntity> accounts = repository.findAll();
        return accounts.stream().map(this::populateAccountResponse).collect(Collectors.toList());
    }

    private AccountResponse populateAccountResponse(AccountEntity account){
        AccountResponse response = new AccountResponse();
        response.setUsername(account.getUsername());
        response.setName(account.getFullName());
        response.setEmail(account.getEmail());
        response.setItems(account.getItems().stream().map(this::populateItem).collect(Collectors.toList()));
        return response;
    }

    private AssetDto populateItem(AssetEntity item){
        return AssetDto.builder()
                .serialNumber(item.getSerialNumber())
                .model(item.getAssetDefinition().getModel())
                .manufacturer(item.getAssetDefinition().getManufacturer())
                .category(item.getAssetDefinition().getCategory().getName())
                .status(item.getStatus())
                .assignedDate(dateFormatter(item.getModifiedAt()))
                .build();
    }

    private String dateFormatter(LocalDateTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(formatter);
    }
}
