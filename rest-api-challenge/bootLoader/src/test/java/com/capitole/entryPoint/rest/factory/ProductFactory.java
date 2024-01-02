package com.capitole.entryPoint.rest.factory;

import com.capitole.drivenAdapter.jpaRepository.mapper.ProductMapper;
import com.capitole.entity.product.Product;
import com.capitole.entryPoint.rest.dto.product.request.ProductRequest;
import com.capitole.service.product.CrudProductService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class ProductFactory {

    private final CrudProductService crudProductService;
    private final List<String> productNames = Arrays.asList("Product1", "Product2", "Product3", "Product4", "Product5");
    private final List<String> skus = Arrays.asList("SKU001", "SKU002", "SKU003", "SKU004", "SKU005");

    private final ProductMapper productMapper;

    public ProductFactory(CrudProductService crudProductService, ProductMapper productMapper) {
        this.crudProductService = crudProductService;
        this.productMapper = productMapper;
    }

    public ProductRequest createProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(getRandomProductName());
        productRequest.setSku(getRandomSku());
        return productRequest;
    }

    public Product createProduct() {
        ProductRequest request = createProductRequest();
        return crudProductService.createProduct(productMapper.toDomainEntity(request));
    }

    private String getRandomProductName() {
        return productNames.get(new Random().nextInt(productNames.size()));
    }

    private String getRandomSku() {
        return skus.get(new Random().nextInt(skus.size()));
    }
}
