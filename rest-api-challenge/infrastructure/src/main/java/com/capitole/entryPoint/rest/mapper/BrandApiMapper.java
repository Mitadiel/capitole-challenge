package com.capitole.entryPoint.rest.mapper;


import com.capitole.entity.brand.Brand;
import com.capitole.entryPoint.rest.dto.brand.request.BrandRequest;
import com.capitole.entryPoint.rest.dto.brand.response.BrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BrandApiMapper {
    Brand toModel(BrandRequest brandRequest);

    BrandResponse toResponseDto(Brand brandEntity);
}
