package com.capitole.useCase.price;

import com.capitole.entity.price.Price;
import com.capitole.useCase.brand.BrandTestData;
import com.capitole.useCase.product.ProductTestData;
import com.capitole.valueObject.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PriceTestData {

    private final BrandTestData brandTestData = new BrandTestData();
    private final ProductTestData productTestData = new ProductTestData();

    public Price createPrice() {
        return new Price(
                1L,
                brandTestData.createBrand().getId(),
                productTestData.createProduct().getId(),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                1L,
                1L,
                BigDecimal.valueOf(100.00),
                Currency.EUR
        );
    }

    public List<Price> createPriceList() {
        List<Price> priceList = new ArrayList<>();
        priceList.add(createPrice());
        return priceList;
    }

    public Price createCustomPrice(
            Long id, Long brandId, Long productId,
            LocalDateTime startDate, LocalDateTime endDate,
            Long priority, Long priceList, Double price) {

        return new Price(
                id, brandId, productId,
                startDate, endDate, priority,
                priceList, BigDecimal.valueOf(price),
                Currency.EUR
        );
    }

}
