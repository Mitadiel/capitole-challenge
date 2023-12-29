package com.capitole.drivenAdapter.jpaRepository.entity;

import com.capitole.drivenAdapter.jpaRepository.entity.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductEntity extends BaseEntity {
    private String code;
    private String name;
    private String sku;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<PriceEntity> prices = new HashSet<>();
}

