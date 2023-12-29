package com.capitole.drivenAdapter.jpaRepository.entity;

import com.capitole.drivenAdapter.jpaRepository.entity.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandEntity extends BaseEntity {
    private String name;
    private String description;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<PriceEntity> prices = new HashSet<>();
}
