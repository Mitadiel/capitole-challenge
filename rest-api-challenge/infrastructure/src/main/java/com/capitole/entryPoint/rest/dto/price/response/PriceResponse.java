package com.capitole.entryPoint.rest.dto.price.response;

import com.capitole.entity.brand.Brand;
import com.capitole.entity.product.Product;
import com.capitole.entryPoint.rest.dto.abstracts.BaseDto;
import com.capitole.valueObject.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceResponse extends BaseDto {
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Product product;
    private Long priority;
    private BigDecimal price;
    private Currency currency;
}