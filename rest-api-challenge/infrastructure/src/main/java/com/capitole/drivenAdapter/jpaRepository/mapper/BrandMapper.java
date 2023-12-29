package com.capitole.drivenAdapter.jpaRepository.mapper;


import com.capitole.drivenAdapter.jpaRepository.entity.BrandEntity;
import com.capitole.entity.brand.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface BrandMapper {
    Brand toDomainEntity(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);

    void updateEntity(Brand brand, @MappingTarget BrandEntity brandEntity);
}
