package com.capitole.useCase;

import com.capitole.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface CrudProductUseCase {
    Product createProduct(Product product);
    Optional<Product> updateProduct(Long id, Product updatedproduct);
    Optional<Product> getProductById(Long id);
    boolean deleteProduct(Long id);
    List<Product> getAllProducts();
}