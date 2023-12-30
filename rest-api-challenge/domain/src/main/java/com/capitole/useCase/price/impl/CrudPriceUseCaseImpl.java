package com.capitole.useCase.price.impl;

import com.capitole.drivenPort.repository.PriceRepositoryPort;
import com.capitole.entity.price.Price;
import com.capitole.exception.BusinessException;
import com.capitole.exception.EntityNotFoundException;
import com.capitole.exception.constant.BrandConstant;
import com.capitole.exception.constant.PriceConstant;
import com.capitole.useCase.price.CrudPriceUseCase;

import java.util.List;
import java.util.Optional;

public class CrudPriceUseCaseImpl implements CrudPriceUseCase {
    private final PriceRepositoryPort priceRepositoryPort;

    public CrudPriceUseCaseImpl(PriceRepositoryPort priceRepositoryPort){
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price createPrice(Price priceEntity) {
            return  priceRepositoryPort.save(priceEntity);
    }

    @Override
    public Optional<Price> updatePrice(Long id, Price updatedPrice) {
        updatedPrice.setId(id);
        return priceRepositoryPort.update(updatedPrice);
    }

    @Override
    public Price getPriceById(Long id) {
        return priceRepositoryPort.findById(id)
                .orElseThrow(() -> createPriceNotFoundException(id));
    }

    @Override
    public boolean deletePrice(Long id) {
        return priceRepositoryPort.deleteById(id);
    }

    @Override
    public List<Price> getAllPrices() {
        return priceRepositoryPort.findAll();
    }

    private EntityNotFoundException createPriceNotFoundException(Long id) {
        return new EntityNotFoundException(
                String.format(PriceConstant.PRICE_NOT_FOUND_MESSAGE_ERROR, id)
        );
    }
}
