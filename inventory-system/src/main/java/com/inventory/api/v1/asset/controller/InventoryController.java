package com.inventory.api.v1.asset.controller;

import com.inventory.api.v1.asset.dto.InventoryDto;
import com.inventory.api.v1.asset.dto.AssetResponse;
import com.inventory.api.v1.asset.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InventoryController {
    Logger log = LoggerFactory.getLogger(InventoryController.class);

    private final InventoryService inventoryService;

    @GetMapping(value = "/assets")
    public ResponseEntity<List<InventoryDto>> getAllAssets(){
        log.info("Get all items");
        AssetResponse response = this.inventoryService.getAllAssets();
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
