package com.capitole.useCase.brand;

import com.capitole.entity.brand.Brand;

import java.util.ArrayList;
import java.util.List;

public class BrandTestData {

    public Brand createBrand() {
        return new Brand(1L, "SampleBrand", "SampleDescription");
    }

    public List<Brand> createBrandList() {
        List<Brand> brandList = new ArrayList<>();
        brandList.add(createBrand());
        return brandList;
    }
}

