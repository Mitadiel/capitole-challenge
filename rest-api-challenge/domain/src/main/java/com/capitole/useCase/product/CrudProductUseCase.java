package com.capitole.useCase.product;

import com.capitole.entity.product.Product;
import java.util.List;

public interface CrudProductUseCase {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product updatedproduct);
    Product getProductById(Long id);
    boolean deleteProduct(Long id);
    List<Product> getAllProducts();
}
