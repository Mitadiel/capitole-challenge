package com.capitole.entryPoint.rest.mapper;

import com.capitole.entity.price.Price;
import com.capitole.entryPoint.rest.dto.price.request.PriceRequest;
import com.capitole.entryPoint.rest.dto.price.response.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PriceApiMapper {
    Price toModel(PriceRequest priceRequest);

    PriceResponse toResponseDto(Price price);
}
