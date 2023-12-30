package com.capitole.drivenAdapter.jpaRepository.repository.price;

import com.capitole.drivenAdapter.jpaRepository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PriceRepository extends JpaRepository<PriceEntity,Long> {

    List<PriceEntity> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(LocalDateTime startDate, LocalDateTime endDate,Long brandId,Long productId);
}
