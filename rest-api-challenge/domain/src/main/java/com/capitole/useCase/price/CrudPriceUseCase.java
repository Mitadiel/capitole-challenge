package com.capitole.useCase.price;

import com.capitole.entity.price.Price;

import java.util.List;
import java.util.Optional;

public interface CrudPriceUseCase {
    Price createPrice(Price price);
    Optional<Price> updatePrice(Long id, Price updatePrice);
    Optional<Price> getPriceById(Long id);
    boolean deletePrice(Long id);
    List<Price> getAllPrices();
}
