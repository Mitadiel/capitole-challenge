package com.capitole.drivenAdapter.jpaRepository.repository.price;

import com.capitole.drivenAdapter.jpaRepository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity,Long> {
}
