package com.capitole.entryPoint.rest.config;
import com.capitole.drivenPort.repository.BrandRepositoryPort;
import com.capitole.service.brand.CrudBrandService;
import com.capitole.useCase.brand.impl.CrudBrandUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandConfig {
    @Bean
    public CrudBrandService crudBrandService(BrandRepositoryPort  brandRepositoryPort) {
        return new CrudBrandService(
                new CrudBrandUseCaseImpl(brandRepositoryPort));
    }
}
