package com.inventory.api.v1.asset.service;

import com.inventory.api.v1.asset.domain.CategoryEntity;
import com.inventory.api.v1.asset.dto.AssetResponse;
import com.inventory.api.v1.asset.repository.AssetRepository;
import com.inventory.api.v1.asset.repository.CategoryRepository;
import com.inventory.api.v1.util.InventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final AssetRepository assetRepository;
    private final CategoryRepository categoryRepository;

    //TODO add some findByUser/findByEmail to get items for the logged user, if the user is with role USER

    public AssetResponse getAllAssets(){
        log.info("Get list with all available items");
        AssetResponse response = new AssetResponse();
        List<CategoryEntity> categories = categoryRepository.findAll();
        response.setAssets(InventoryMapper.returnInventory(categories));
        return response;
    }


}
