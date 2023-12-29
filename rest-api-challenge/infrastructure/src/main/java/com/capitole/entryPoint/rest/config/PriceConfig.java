package com.capitole.entryPoint.rest.config;

import com.capitole.drivenPort.repository.PriceRepositoryPort;
import com.capitole.service.price.CrudPriceService;
import com.capitole.useCase.price.impl.CrudPriceUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfig {
    @Bean
    public CrudPriceService crudPriceService(PriceRepositoryPort priceRepositoryPort) {
        return new CrudPriceService(
                new CrudPriceUseCaseImpl(priceRepositoryPort));
    }
}
