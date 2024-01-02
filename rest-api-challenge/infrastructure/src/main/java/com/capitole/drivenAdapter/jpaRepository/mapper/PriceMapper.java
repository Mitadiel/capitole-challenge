package com.capitole.drivenAdapter.jpaRepository.mapper;

import com.capitole.drivenAdapter.jpaRepository.entity.PriceEntity;
import com.capitole.entity.price.Price;
import com.capitole.entryPoint.rest.dto.price.request.PriceRequest;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PriceMapper {
    Price toPriceDomainEntity(PriceEntity priceEntity);
    Price toPriceDomainEntity(PriceRequest priceRequest);

    PriceEntity toPriceEntity(Price price);

    void updateEntity(Price price, @MappingTarget PriceEntity priceEntity);
}
