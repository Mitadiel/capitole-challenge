package com.capitole.service.price;

import com.capitole.entity.price.Price;
import com.capitole.useCase.price.CrudPriceUseCase;
import java.util.List;
import java.util.Optional;

public class CrudPriceService implements CrudPriceUseCase {

    private final CrudPriceUseCase crudPriceUseCase;

    public CrudPriceService(CrudPriceUseCase crudPriceUseCase){
        this.crudPriceUseCase = crudPriceUseCase;
    }

    @Override
    public Price createPrice(Price price) {
        return crudPriceUseCase.createPrice(price);
    }

    @Override
    public Optional<Price> updatePrice(Long id, Price updatedPrice) {
       return crudPriceUseCase.updatePrice(id,updatedPrice);
    }

    @Override
    public Optional<Price> getPriceById(Long id) {
        return crudPriceUseCase.getPriceById(id);
    }

    @Override
    public boolean deletePrice(Long id) {
        return crudPriceUseCase.deletePrice(id);
    }

    @Override
    public List<Price> getAllPrices() {
        return crudPriceUseCase.getAllPrices();
    }
}
