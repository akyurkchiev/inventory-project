package com.inventory.api.v1.asset.controller;

import com.inventory.api.v1.asset.dto.AssetDto;
import com.inventory.api.v1.asset.dto.AssetResponse;
import com.inventory.api.v1.asset.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AssetController {
    Logger log = LoggerFactory.getLogger(AssetController.class);

    private final AssetService assetService;

    @GetMapping(value = "/assets")
    public ResponseEntity<List<AssetDto>> getAllAssets(){
        log.info("Get all items");
        AssetResponse response = this.assetService.getAllAssets();
        return ResponseEntity.ok(response.getAssets());
    }

    @GetMapping(value = "/asset/{asset_id}")
    public ResponseEntity<?> getItem(@PathVariable("id") String id) {

        //get item

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/item")
    public void addItem(){

    }

}
