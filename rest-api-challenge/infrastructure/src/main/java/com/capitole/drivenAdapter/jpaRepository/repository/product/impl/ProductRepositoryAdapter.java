package com.capitole.drivenAdapter.jpaRepository.repository.product.impl;

import com.capitole.drivenAdapter.jpaRepository.entity.ProductEntity;
import com.capitole.drivenAdapter.jpaRepository.mapper.ProductMapper;
import com.capitole.drivenAdapter.jpaRepository.repository.product.ProductRepository;
import com.capitole.drivenPort.repository.ProductRepositoryPort;
import com.capitole.entity.product.Product;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductRepositoryAdapter(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Override
    @Transactional
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        productRepository.save(productEntity);
        return productMapper.toDomainEntity(productEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(productMapper::toDomainEntity);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<Product> update(Product product) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            ProductEntity current = optionalProduct.get();
            productMapper.updateEntity(product,current);
            ProductEntity updatedBrandEntity = productRepository.save(current);
            return Optional.of(productMapper.toDomainEntity(updatedBrandEntity));
            }else{
                return Optional.empty();
            }
    }

    @Override
    public boolean deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
