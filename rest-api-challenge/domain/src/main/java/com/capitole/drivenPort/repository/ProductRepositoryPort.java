package com.capitole.drivenPort.repository;

import com.capitole.entity.product.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    Optional<Product> update(Product product);
    boolean deleteById(Long id);
}
