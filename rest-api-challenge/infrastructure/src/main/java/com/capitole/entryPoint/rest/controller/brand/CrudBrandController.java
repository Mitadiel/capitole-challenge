package com.capitole.entryPoint.rest.controller.brand;

import com.capitole.entity.brand.Brand;
import com.capitole.entryPoint.rest.dto.brand.request.BrandRequest;
import com.capitole.entryPoint.rest.dto.brand.response.BrandResponse;
import com.capitole.entryPoint.rest.mapper.BrandApiMapper;
import com.capitole.service.brand.CrudBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/brands")
public class CrudBrandController {

    private final BrandApiMapper brandApiMapper;
    private final CrudBrandService crudBrandService;

    public CrudBrandController(CrudBrandService crudBrandService,BrandApiMapper brandApiMapper ){
        this.brandApiMapper = brandApiMapper;
        this.crudBrandService = crudBrandService;

    }

    @PostMapping
    public ResponseEntity<BrandResponse> create(@RequestBody BrandRequest brandRequest) {
         Brand request = brandApiMapper.toBrandModel(brandRequest);
         BrandResponse brandResponse = brandApiMapper.toBrandResponseDto(crudBrandService.createBrand(request));
         return new ResponseEntity<>(brandResponse, HttpStatus.CREATED);
    }

    @GetMapping("{brandId}")
    public ResponseEntity<BrandResponse> getById(@PathVariable Long brandId){
        return new ResponseEntity<>(brandApiMapper.toBrandResponseDto(crudBrandService.getBrandById(brandId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrands() {
        List<BrandResponse> brands = crudBrandService.getAllBrands().stream().map(brandApiMapper::toBrandResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable Long brandId) {
        if (crudBrandService.deleteBrand(brandId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<BrandResponse> updateBrand(
            @RequestBody BrandRequest brandRequest,
            @PathVariable Long brandId){
        Brand brand = brandApiMapper.toBrandModel(brandRequest);
         return new ResponseEntity<>(brandApiMapper.toBrandResponseDto(crudBrandService.updateBrand(brandId,brand)), HttpStatus.OK);
        }
}
