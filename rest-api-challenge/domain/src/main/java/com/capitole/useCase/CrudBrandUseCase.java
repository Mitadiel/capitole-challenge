package com.capitole.useCase;

import com.capitole.entity.brand.Brand;

import java.util.List;
import java.util.Optional;

public interface CrudBrandUseCase {
    Brand crateBrand(Brand brand);
    Optional<Brand> updateBrand(Long id, Brand updatedBrand);
    Optional<Brand> getBrandById(Long id);
    boolean deleteBrand(Long id);
    List<Brand> getAllBrands();
}
