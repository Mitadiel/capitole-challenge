package com.capitole.useCase.brand.impl;

import com.capitole.drivenPort.repository.BrandRepositoryPort;
import com.capitole.entity.brand.Brand;
import com.capitole.useCase.brand.CrudBrandUseCase;
import java.util.List;
import java.util.Optional;

public class CrudBrandUseCaseImpl implements CrudBrandUseCase {

    private final BrandRepositoryPort brandRepositoryPort;

    public CrudBrandUseCaseImpl(BrandRepositoryPort brandRepositoryPort){
        this.brandRepositoryPort = brandRepositoryPort;
    }

    @Override
    public Brand createBrand(Brand brandEntity) {
       return  brandRepositoryPort.save(brandEntity);
    }

    @Override
    public Optional<Brand> updateBrand(Long id, Brand updatedBrand) {
        updatedBrand.setId(id);
        return brandRepositoryPort.update(updatedBrand);
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return brandRepositoryPort.findById(id);
    }

    @Override
    public boolean deleteBrand(Long id) {
        return brandRepositoryPort.deleteById(id);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepositoryPort.findAll();
    }
}
