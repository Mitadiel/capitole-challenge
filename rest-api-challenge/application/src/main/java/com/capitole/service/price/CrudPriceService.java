package com.capitole.service.price;

import com.capitole.entity.price.Price;
import com.capitole.useCase.price.CrudPriceUseCase;
import org.slf4j.Logger;
import java.util.List;
import java.util.Optional;

public class CrudPriceService implements CrudPriceUseCase {

    private final CrudPriceUseCase crudPriceUseCase;

    private final Logger logger;

    public CrudPriceService(CrudPriceUseCase crudPriceUseCase,Logger logger){
        this.crudPriceUseCase = crudPriceUseCase;
        this.logger = logger;
    }

    @Override
    public Price createPrice(Price price) {
        logger.trace(String.format("Creating price -> %s",price.toString()));
        return crudPriceUseCase.createPrice(price);
    }

    @Override
    public Optional<Price> updatePrice(Long id, Price updatedPrice) {
        logger.trace(String.format("Updating price -> %d %s%n", id, updatedPrice.toString()));
       return crudPriceUseCase.updatePrice(id,updatedPrice);
    }

    @Override
    public Price getPriceById(Long id) {
        logger.trace(String.format("Getting info from price id -> %d",id));
        return crudPriceUseCase.getPriceById(id);
    }

    @Override
    public boolean deletePrice(Long id) {
        logger.trace(String.format("Deleting price with id -> %d",id));
        return crudPriceUseCase.deletePrice(id);
    }

    @Override
    public List<Price> getAllPrices() {
        return crudPriceUseCase.getAllPrices();
    }
}
