package com.inventory.api.v1.asset.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "asset_definition")
public class AssetDefinitionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @OneToMany(mappedBy = "assetDefinition")
    private List<AssetEntity> assets;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private CategoryEntity category;
}
