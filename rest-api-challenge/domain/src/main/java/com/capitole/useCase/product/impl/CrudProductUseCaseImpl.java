package com.capitole.useCase.product.impl;

import com.capitole.drivenPort.repository.ProductRepositoryPort;
import com.capitole.entity.product.Product;
import com.capitole.exception.EntityNotFoundException;
import com.capitole.exception.constant.ProductConstant;
import com.capitole.useCase.product.CrudProductUseCase;
import java.util.List;
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
    public Product updateProduct(Long id, Product updatedProduct) {
        updatedProduct.setId(id);
        return productRepositoryPort.update(updatedProduct)
                .orElseThrow(() -> createProductNotFoundException(id));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepositoryPort.findById(id)
                .orElseThrow(() -> createProductNotFoundException(id));
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepositoryPort.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }

    private EntityNotFoundException createProductNotFoundException(Long id) {
        return new EntityNotFoundException(
                String.format(ProductConstant.PRODUCT_NOT_FOUND_MESSAGE_ERROR, id)
        );
    }
}
