package com.capitole.entryPoint.rest.controller.price;

import com.capitole.entity.price.Price;
import com.capitole.entryPoint.rest.dto.price.request.PriceRequest;
import com.capitole.entryPoint.rest.dto.price.response.PriceResponse;
import com.capitole.entryPoint.rest.mapper.PriceApiMapper;
import com.capitole.service.price.CrudPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/prices")
public class CrudPriceController {

    private final PriceApiMapper priceApiMapper;
    private final CrudPriceService crudPriceService;

    public CrudPriceController(CrudPriceService crudPriceService, PriceApiMapper priceApiMapper ){
        this.priceApiMapper = priceApiMapper;
        this.crudPriceService = crudPriceService;

    }

    @PostMapping
    public ResponseEntity<PriceResponse> create(@RequestBody PriceRequest priceRequest) {
         Price request = priceApiMapper.toModel(priceRequest);
         PriceResponse brandResponse = priceApiMapper.toResponseDto(crudPriceService.createPrice(request));
         return new ResponseEntity<>(brandResponse, HttpStatus.CREATED);
    }

    @GetMapping("{priceId}")
    public ResponseEntity<PriceResponse> getById(@PathVariable Long priceId){
        return new ResponseEntity<>(priceApiMapper.toResponseDto(crudPriceService.getPriceById(priceId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getAllPrices() {
        List<PriceResponse> prices = crudPriceService.getAllPrices().stream().map(priceApiMapper::toResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @DeleteMapping("/{priceId}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable Long priceId) {
        if (crudPriceService.deletePrice(priceId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{priceId}")
    public ResponseEntity<PriceResponse> updatePrice(
            @RequestBody PriceRequest priceRequest,
            @PathVariable Long priceId){
        Price price = priceApiMapper.toModel(priceRequest);
         return crudPriceService.updatePrice(priceId,price)
                 .map(priceUpdated -> new ResponseEntity<>(priceApiMapper.toResponseDto(priceUpdated), HttpStatus.OK))
                 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
}
