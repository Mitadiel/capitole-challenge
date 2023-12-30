package com.capitole.entryPoint.rest.controller.price;

import com.capitole.entity.price.Price;
import com.capitole.entryPoint.rest.dto.price.response.PriceResponse;
import com.capitole.entryPoint.rest.mapper.PriceApiMapper;
import com.capitole.service.price.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/api/prices")
public class PriceController {

        private final PriceService priceService;
        private final PriceApiMapper priceApiMapper;

        PriceController(PriceService priceService,PriceApiMapper priceApiMapper){
            this.priceService = priceService;
            this.priceApiMapper = priceApiMapper;
        }

    @GetMapping("/query")
    public ResponseEntity<PriceResponse> queryPrice(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        Price price = priceService.getQueryPrice(brandId,productId,applicationDate);
        return new ResponseEntity<>(priceApiMapper.toResponseDto(price), HttpStatus.OK);
    }
}
