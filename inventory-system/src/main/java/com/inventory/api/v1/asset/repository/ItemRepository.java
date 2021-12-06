package com.inventory.api.v1.asset.repository;

import com.inventory.api.v1.asset.domain.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<AssetEntity, String> {
}
