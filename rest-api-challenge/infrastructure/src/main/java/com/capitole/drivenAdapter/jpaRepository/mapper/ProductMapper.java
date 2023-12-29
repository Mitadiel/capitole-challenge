package com.capitole.drivenAdapter.jpaRepository.mapper;

import com.capitole.drivenAdapter.jpaRepository.entity.ProductEntity;
import com.capitole.entity.product.Product;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {
    Product toDomainEntity(ProductEntity productEntity);
    ProductEntity toEntity(Product product);
    void updateEntity(Product product, @MappingTarget ProductEntity productEntity);
}
