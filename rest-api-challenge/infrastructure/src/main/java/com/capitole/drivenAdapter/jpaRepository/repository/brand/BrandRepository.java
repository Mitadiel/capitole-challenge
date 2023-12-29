package com.capitole.drivenAdapter.jpaRepository.repository.brand;

import com.capitole.drivenAdapter.jpaRepository.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
