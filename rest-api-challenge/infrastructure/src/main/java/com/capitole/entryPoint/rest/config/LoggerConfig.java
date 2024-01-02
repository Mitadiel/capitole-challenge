package com.capitole.entryPoint.rest.config;

import com.capitole.service.brand.CrudBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(CrudBrandService.class);
    }
}
