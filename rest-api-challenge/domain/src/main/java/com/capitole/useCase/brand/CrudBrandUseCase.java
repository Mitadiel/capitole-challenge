package com.capitole.useCase.brand;

import com.capitole.entity.brand.Brand;
import java.util.List;

public interface CrudBrandUseCase {
    Brand createBrand(Brand brand);
    Brand updateBrand(Long id, Brand updatedBrand);
    Brand getBrandById(Long id);
    boolean deleteBrand(Long id);
    List<Brand> getAllBrands();
}
