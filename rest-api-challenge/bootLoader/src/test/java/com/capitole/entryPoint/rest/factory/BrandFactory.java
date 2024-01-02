package com.capitole.entryPoint.rest.factory;

import com.capitole.drivenAdapter.jpaRepository.mapper.BrandMapper;
import com.capitole.entity.brand.Brand;
import com.capitole.entryPoint.rest.dto.brand.request.BrandRequest;
import com.capitole.service.brand.CrudBrandService;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class BrandFactory {

    private BrandMapper brandMapper;
    private CrudBrandService crudBrandService;
    private final List<String> brandNames = Arrays.asList("Brand1", "Brand2", "Brand3", "Brand4", "Brand5");
    private final List<String> descriptions = Arrays.asList("Lorem ipsum dolor sit amet", "Consectetur adipiscing elit", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");

    public BrandFactory(CrudBrandService crudBrandService, BrandMapper brandMapper) {
        this.crudBrandService = crudBrandService;
        this.brandMapper = brandMapper;
    }

    public BrandRequest createBrandRequest() {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName(getRandomBrandName());
        brandRequest.setDescription(getRandomDescription());
        return brandRequest;
    }
    public Brand createBrand() {
        BrandRequest request = createBrandRequest();
        return crudBrandService.createBrand(brandMapper.toDomainEntity(request));
    }

    private String getRandomBrandName() {
        return brandNames.get(new Random().nextInt(brandNames.size()));
    }

    private String getRandomDescription() {
        return descriptions.get(new Random().nextInt(descriptions.size()));
    }
}
