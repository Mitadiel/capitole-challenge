package com.capitole.service.product;

import com.capitole.entity.product.Product;
import com.capitole.useCase.CrudProductUseCase;

import java.util.List;
import java.util.Optional;

public class CrudProductService implements CrudProductUseCase {

    private final CrudProductUseCase crudProductUseCase;

    public CrudProductService(CrudProductUseCase CrudProductUseCase){
        this.crudProductUseCase = CrudProductUseCase;
    }

    @Override
    public Product createProduct(Product product) {
        return crudProductUseCase.createProduct(product);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
       return crudProductUseCase.updateProduct(id,updatedProduct);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return crudProductUseCase.getProductById(id);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return crudProductUseCase.deleteProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return crudProductUseCase.getAllProducts();
    }
}
