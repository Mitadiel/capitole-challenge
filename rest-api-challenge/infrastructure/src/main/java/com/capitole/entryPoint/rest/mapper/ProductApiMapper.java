package com.capitole.entryPoint.rest.mapper;

import com.capitole.entity.product.Product;
import com.capitole.entryPoint.rest.dto.product.request.ProductRequest;
import com.capitole.entryPoint.rest.dto.product.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductApiMapper {
    Product toModel(ProductRequest productRequest);

    ProductResponse toResponseDto(Product product);
}
