package com.capitole.useCase.price.impl;

import com.capitole.drivenPort.repository.PriceRepositoryPort;
import com.capitole.entity.price.Price;
import com.capitole.useCase.price.PriceUseCase;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PriceUseCaseImpl implements PriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceUseCaseImpl(PriceRepositoryPort priceRepositoryPort){
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price getQueryPrice(Long idBrand, Long idProduct, LocalDateTime applicationDate) {
        List<Price> priceList = priceRepositoryPort.getQueryPrice(idBrand, idProduct, applicationDate);
        Optional<Price> maxPriorityPrice = priceList.stream()
                .max(Comparator.comparingLong(Price::getPriority));
        return maxPriorityPrice.orElse(null);
    }



}
