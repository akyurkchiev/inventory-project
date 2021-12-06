package com.inventory.api.v1.asset.service;

import com.inventory.api.v1.asset.dto.AssetDto;
import com.inventory.api.v1.asset.domain.AssetEntity;
import com.inventory.api.v1.asset.dto.AssetResponse;
import com.inventory.api.v1.asset.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssetService {

    private final ItemRepository itemRepository;

    //TODO add some findByUser/findByEmail to get items for the logged user, if the user is with role USER

    public AssetResponse getAllAssets(){
        log.info("Get list with all available items");
        AssetResponse response = new AssetResponse();
        List<AssetEntity> items = itemRepository.findAll();
        response.setAssets(populateItems(items));
        return response;
    }

    private List<AssetDto> populateItems(List<AssetEntity> items){
        return items.stream().map(this::addItem).collect(Collectors.toList());
    }

    private AssetDto addItem(AssetEntity asset){
        return AssetDto.builder()
                .model(asset.getAssetDefinition().getModel())
                .category(asset.getAssetDefinition().getCategory().getName())
                .manufacturer(asset.getAssetDefinition().getManufacturer())
                .serialNumber(asset.getSerialNumber())
                .status(asset.getStatus())
                .assignedDate(dateFormatter(asset.getModifiedAt()))
                .build();
    }

    private String dateFormatter(LocalDateTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(formatter);
    }
}
