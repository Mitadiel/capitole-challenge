package com.capitole.service.brand;

import com.capitole.entity.brand.Brand;
import com.capitole.useCase.brand.CrudBrandUseCase;

import java.util.List;
import java.util.Optional;

public class CrudBrandService implements CrudBrandUseCase {

    private final CrudBrandUseCase crudBrandUseCase;

    public CrudBrandService(CrudBrandUseCase crudBrandUseCase){
        this.crudBrandUseCase = crudBrandUseCase;
    }

    @Override
    public Brand createBrand(Brand brandEntity) {
        return crudBrandUseCase.createBrand(brandEntity);
    }

    @Override
    public Optional<Brand> updateBrand(Long id, Brand updatedBrand) {
       return crudBrandUseCase.updateBrand(id,updatedBrand);
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return crudBrandUseCase.getBrandById(id);
    }

    @Override
    public boolean deleteBrand(Long id) {
        return crudBrandUseCase.deleteBrand(id);
    }

    @Override
    public List<Brand> getAllBrands() {
        return crudBrandUseCase.getAllBrands();
    }
}
