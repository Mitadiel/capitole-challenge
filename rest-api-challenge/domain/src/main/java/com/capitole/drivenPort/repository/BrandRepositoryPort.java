package com.capitole.drivenPort.repository;

import com.capitole.entity.brand.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepositoryPort {

    Brand save(Brand brand);
    Optional<Brand> findById(Long id);
    List<Brand> findAll();
    Optional<Brand> update(Brand brand);
    boolean deleteById(Long id);
}
