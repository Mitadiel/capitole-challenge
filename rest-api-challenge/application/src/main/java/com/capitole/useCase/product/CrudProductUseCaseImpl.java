package com.capitole.useCase.product;

import com.capitole.drivenPort.repository.ProductRepositoryPort;
import com.capitole.entity.product.Product;
import com.capitole.useCase.CrudProductUseCase;

import java.util.List;
import java.util.Optional;

public class CrudProductUseCaseImpl implements CrudProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public CrudProductUseCaseImpl(ProductRepositoryPort productRepositoryPort){
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepositoryPort.update(updatedProduct);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepositoryPort.findById(id);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepositoryPort.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }
}
