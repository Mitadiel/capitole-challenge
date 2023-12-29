package com.capitole.entryPoint.rest.config;
import com.capitole.drivenPort.repository.BrandRepositoryPort;
import com.capitole.drivenPort.repository.ProductRepositoryPort;
import com.capitole.service.brand.CrudBrandService;
import com.capitole.service.product.CrudProductService;
import com.capitole.useCase.brand.CrudBrandUseCaseImpl;
import com.capitole.useCase.product.CrudProductUseCaseImpl;
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
