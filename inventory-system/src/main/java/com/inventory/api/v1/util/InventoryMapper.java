package com.inventory.api.v1.util;

import com.inventory.api.v1.asset.domain.AssetDefinitionEntity;
import com.inventory.api.v1.asset.domain.AssetEntity;
import com.inventory.api.v1.asset.domain.CategoryEntity;
import com.inventory.api.v1.asset.dto.AssetDetails;
import com.inventory.api.v1.asset.dto.AssetDto;
import com.inventory.api.v1.asset.dto.AssetStatus;
import com.inventory.api.v1.asset.dto.InventoryDto;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryMapper {
    private InventoryMapper() {
    }

    public static List<InventoryDto> returnInventory(List<CategoryEntity> categories){
        return categories.stream().map(InventoryMapper::populateInventory).collect(Collectors.toList());
    }

    private static InventoryDto populateInventory(CategoryEntity category){
        return InventoryDto.builder()
                .category(category.getName())
                .assets(populateAssets(category.getAssetDefinitions()))
                .build();
    }

    private static List<AssetDto> populateAssets(List<AssetDefinitionEntity> assetDefinitions){
        return assetDefinitions.stream().map(InventoryMapper::addAssets).collect(Collectors.toList());
    }

    private static AssetDto addAssets(AssetDefinitionEntity assetDefinition){
        return AssetDto.builder()
                .model(assetDefinition.getModel())
                .total(addTotal(assetDefinition.getAssets()))
                .unassigned(addUnassigned(assetDefinition.getAssets()))
                .details(populateDetails(assetDefinition.getAssets()))
                .build();
    }

    private static List<AssetDetails> populateDetails(List<AssetEntity> assets){
        return assets.stream().map(InventoryMapper::addDetails).collect(Collectors.toList());
    }

    private static AssetDetails addDetails(AssetEntity asset){
        return AssetDetails.builder()
                .fullName(ObjectUtils.isNotEmpty(asset.getAccount()) ? asset.getAccount().getFullName() : StringUtils.EMPTY)
                .email(ObjectUtils.isNotEmpty(asset.getAccount()) ? asset.getAccount().getEmail() : StringUtils.EMPTY)
                .serialNumber(asset.getSerialNumber())
                .status(asset.getStatus())
                .assignedDate(dateFormatter(asset.getAssignedAt()))
                .build();
    }

    private static int addTotal(List<AssetEntity> assets){
        return assets.size();
    }

    private static int addUnassigned(List<AssetEntity> assets){
        return (int) assets.stream().filter(a -> AssetStatus.FREE.equals(a.getStatus())).count();
    }

    private static String dateFormatter(LocalDateTime time){
        if (null == time){
            return StringUtils.EMPTY;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(formatter);
    }
}
