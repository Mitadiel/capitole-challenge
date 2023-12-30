package com.capitole.service.product;

import com.capitole.entity.product.Product;
import com.capitole.useCase.product.CrudProductUseCase;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

public class CrudProductService implements CrudProductUseCase {

    private final CrudProductUseCase crudProductUseCase;
    private final Logger logger;

    public CrudProductService(CrudProductUseCase CrudProductUseCase, Logger logger){
        this.crudProductUseCase = CrudProductUseCase;
        this.logger = logger;
    }

    @Override
    public Product createProduct(Product product) {
        logger.trace(String.format("Creating product -> %s",product.toString()));
        return crudProductUseCase.createProduct(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        logger.trace(String.format("Updating product -> %d %s%n", id, updatedProduct.toString()));
       return crudProductUseCase.updateProduct(id,updatedProduct);
    }

    @Override
    public Product getProductById(Long id) {
        logger.trace(String.format("Getting info from product id -> %d",id));
        return crudProductUseCase.getProductById(id);
    }

    @Override
    public boolean deleteProduct(Long id) {
        logger.trace(String.format("Deleting product with id -> %d",id));
        return crudProductUseCase.deleteProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return crudProductUseCase.getAllProducts();
    }
}
