package com.capitole.useCase.brand.impl;

import com.capitole.drivenPort.repository.BrandRepositoryPort;
import com.capitole.entity.brand.Brand;
import com.capitole.exception.BusinessException;
import com.capitole.exception.EntityNotFoundException;
import com.capitole.exception.constant.BrandConstant;
import com.capitole.useCase.brand.CrudBrandUseCase;
import java.util.List;

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
    public Brand updateBrand(Long id, Brand updatedBrand) {
        updatedBrand.setId(id);
        return brandRepositoryPort.update(updatedBrand)
                .orElseThrow(() -> createBrandNotFoundException(id));
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepositoryPort.findById(id)
                .orElseThrow(() -> createBrandNotFoundException(id));
    }

    @Override
    public boolean deleteBrand(Long id) {
        return brandRepositoryPort.deleteById(id);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepositoryPort.findAll();
    }

    private EntityNotFoundException createBrandNotFoundException(Long id) {
        return new EntityNotFoundException(
                String.format(BrandConstant.BRAND_NOT_FOUND_MESSAGE_ERROR, id)
        );
    }
}
