package com.capitole.service.price;

import com.capitole.entity.price.Price;
import com.capitole.useCase.price.PriceUseCase;

import java.time.LocalDateTime;

public class PriceService implements PriceUseCase {

    private final PriceUseCase priceUseCase;
    public PriceService(PriceUseCase priceUseCase){
        this.priceUseCase = priceUseCase;
    }

    @Override
    public Price getQueryPrice(Long idBrand, Long idProduct, LocalDateTime dateApplication) {
        return priceUseCase.getQueryPrice(idBrand,idProduct,dateApplication);
    }
}
