package com.capitole.drivenAdapter.jpaRepository.mapper;

import com.capitole.drivenAdapter.jpaRepository.entity.ProductEntity;
import com.capitole.entity.product.Product;
import com.capitole.entryPoint.rest.dto.product.request.ProductRequest;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {
    Product toDomainEntity(ProductEntity productEntity);
    Product toDomainEntity(ProductRequest productRequest);
    ProductEntity toEntity(Product product);
    void updateEntity(Product product, @MappingTarget ProductEntity productEntity);
}