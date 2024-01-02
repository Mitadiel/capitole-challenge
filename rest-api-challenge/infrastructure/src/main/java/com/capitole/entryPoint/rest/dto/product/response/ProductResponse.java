package com.capitole.entryPoint.rest.dto.product.response;

import com.capitole.entryPoint.rest.dto.abstracts.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse extends BaseDto {
    private String code;
    private String name;
    private String sku;
}