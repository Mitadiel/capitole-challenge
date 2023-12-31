package com.capitole.drivenPort.repository;

import com.capitole.entity.price.Price;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepositoryPort {
    Price save(Price price);
    Optional<Price> findById(Long id);
    List<Price> findAll();
    Optional<Price> update(Price price);
    boolean deleteById(Long id);

    List<Price> getQueryPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
