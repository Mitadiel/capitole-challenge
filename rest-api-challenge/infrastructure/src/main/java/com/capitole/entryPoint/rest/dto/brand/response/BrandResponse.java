package com.capitole.entryPoint.rest.dto.brand.response;

import com.capitole.entryPoint.rest.dto.abstracts.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandResponse extends BaseDto {
    private String name;
    private String description;
}