package com.capitole.useCase.price;

import com.capitole.entity.price.Price;

import java.time.LocalDateTime;

public interface PriceUseCase {
    Price getQueryPrice(Long idBrand, Long idProduct, LocalDateTime dateApplication);
}
