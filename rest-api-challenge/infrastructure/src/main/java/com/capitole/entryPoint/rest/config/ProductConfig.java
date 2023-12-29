package com.capitole.entryPoint.rest.config;
import com.capitole.drivenPort.repository.ProductRepositoryPort;
import com.capitole.service.product.CrudProductService;
import com.capitole.useCase.product.impl.CrudProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    public CrudProductService crudProductService(ProductRepositoryPort productRepositoryPort) {
        return new CrudProductService(
                new CrudProductUseCaseImpl(productRepositoryPort));
    }
}
