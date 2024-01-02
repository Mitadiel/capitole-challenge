package com.capitole.drivenAdapter.jpaRepository.repository.product;

import com.capitole.drivenAdapter.jpaRepository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
