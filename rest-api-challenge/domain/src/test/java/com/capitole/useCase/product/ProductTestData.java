package com.capitole.useCase.product;

import com.capitole.entity.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductTestData {

    public Product createProduct() {
        return new Product(1L, "P-001", "SampleProduct", "SKU-001");
    }

    public List<Product> createProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(createProduct());
        return productList;
    }

}

