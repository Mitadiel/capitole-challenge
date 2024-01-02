package com.capitole.entryPoint.rest.factory;

import com.capitole.drivenAdapter.jpaRepository.mapper.PriceMapper;
import com.capitole.entity.brand.Brand;
import com.capitole.entity.price.Price;
import com.capitole.entity.product.Product;
import com.capitole.entryPoint.rest.dto.price.request.PriceRequest;
import com.capitole.service.price.CrudPriceService;
import com.capitole.valueObject.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class PriceFactory {

    private final CrudPriceService crudPriceService;
    private final PriceMapper priceMapper;

    private final ProductFactory productFactory;

    private final BrandFactory brandFactory;

    private final List<Long> priceLists = Arrays.asList(1L, 2L, 3L);

    public PriceFactory(CrudPriceService crudPriceService, PriceMapper priceMapper, ProductFactory productFactory, BrandFactory brandFactory) {
        this.crudPriceService = crudPriceService;
        this.priceMapper = priceMapper;
        this.productFactory = productFactory;
        this.brandFactory = brandFactory;
    }

    public PriceRequest createPriceRequest() {
        Brand brand = brandFactory.createBrand();
        Product product = productFactory.createProduct();
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setBrandId(brand.getId());
        priceRequest.setProductId(product.getId());
        priceRequest.setStartDate(LocalDateTime.now());
        priceRequest.setEndDate(LocalDateTime.now().plusDays(30));
        priceRequest.setPriceList(getRandomPriceList());
        priceRequest.setPriority(generateRandomPriority());
        priceRequest.setPrice(generateRandomPrice());
        priceRequest.setCurrency(Currency.EUR);
        return priceRequest;
    }

    public Price createPrice() {
        PriceRequest request = createPriceRequest();
        return crudPriceService.createPrice(priceMapper.toPriceDomainEntity(request));
    }

    private Long generateRandomPriority() {
        return new Random().nextLong();
    }

    private BigDecimal generateRandomPrice() {
        return BigDecimal.valueOf(new Random().nextDouble() * 100);
    }

    private Long getRandomPriceList() {
        return priceLists.get(new Random().nextInt(priceLists.size()));
    }
}
