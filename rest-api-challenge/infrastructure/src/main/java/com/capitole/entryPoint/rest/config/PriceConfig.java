package com.capitole.entryPoint.rest.config;

import com.capitole.drivenPort.repository.PriceRepositoryPort;
import com.capitole.service.price.CrudPriceService;
import com.capitole.service.price.PriceService;
import com.capitole.useCase.price.impl.CrudPriceUseCaseImpl;
import com.capitole.useCase.price.impl.PriceUseCaseImpl;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfig {
    @Bean
    public CrudPriceService crudPriceService(PriceRepositoryPort priceRepositoryPort, Logger logger) {
        return new CrudPriceService(
                new CrudPriceUseCaseImpl(priceRepositoryPort),logger);
    }

    @Bean
    public PriceService priceService(PriceRepositoryPort priceRepositoryPort){
        return new PriceService(
                new PriceUseCaseImpl(priceRepositoryPort));
    }
}
