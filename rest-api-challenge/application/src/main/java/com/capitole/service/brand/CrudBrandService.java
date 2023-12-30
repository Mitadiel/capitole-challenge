package com.capitole.service.brand;

import com.capitole.entity.brand.Brand;
import com.capitole.useCase.brand.CrudBrandUseCase;
import org.slf4j.Logger;
import java.util.List;
import java.util.Optional;

public class CrudBrandService implements CrudBrandUseCase {

    private final CrudBrandUseCase crudBrandUseCase;

    private final Logger logger;

    public CrudBrandService(CrudBrandUseCase crudBrandUseCase, Logger logger){
        this.crudBrandUseCase = crudBrandUseCase;
        this.logger = logger;
    }

    @Override
    public Brand createBrand(Brand brandEntity) {
        logger.trace(String.format("Creating brand -> %s",brandEntity.toString()));
        return crudBrandUseCase.createBrand(brandEntity);
    }

    @Override
    public Optional<Brand> updateBrand(Long id, Brand updatedBrand) {
        logger.trace(String.format("Updating brand -> %d %s%n", id, updatedBrand.toString()));
        return crudBrandUseCase.updateBrand(id,updatedBrand);
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        logger.trace(String.format("Getting info from brand id -> %d",id));
        return crudBrandUseCase.getBrandById(id);
    }

    @Override
    public boolean deleteBrand(Long id) {
        logger.trace(String.format("Deleting brand with id -> %d",id));
        return crudBrandUseCase.deleteBrand(id);
    }

    @Override
    public List<Brand> getAllBrands() {
        return crudBrandUseCase.getAllBrands();
    }
}
