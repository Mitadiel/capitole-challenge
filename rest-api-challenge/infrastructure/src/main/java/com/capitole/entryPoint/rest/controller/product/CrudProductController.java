package com.capitole.entryPoint.rest.controller.product;

import com.capitole.entity.brand.Brand;
import com.capitole.entity.product.Product;
import com.capitole.entryPoint.rest.dto.brand.request.BrandRequest;
import com.capitole.entryPoint.rest.dto.brand.response.BrandResponse;
import com.capitole.entryPoint.rest.dto.product.request.ProductRequest;
import com.capitole.entryPoint.rest.dto.product.response.ProductResponse;
import com.capitole.entryPoint.rest.mapper.ProductApiMapper;
import com.capitole.service.product.CrudProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products")
public class CrudProductController {

    private final ProductApiMapper productApiMapper;
    private final CrudProductService crudProductService;

    public CrudProductController(CrudProductService crudProductService, ProductApiMapper productApiMapper ){
        this.productApiMapper = productApiMapper;
        this.crudProductService = crudProductService;

    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {
         Product request = productApiMapper.toModel(productRequest);
         ProductResponse productResponse = productApiMapper.toResponseDto(crudProductService.createProduct(request));
         return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long productId){
        return crudProductService.getProductById(productId)
                .map(product -> new ResponseEntity<>(productApiMapper.toResponseDto(product), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = crudProductService.getAllProducts().stream().map(productApiMapper::toResponseDto).collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        if (crudProductService.deleteProduct(productId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest,
            @PathVariable Long productId){
        Product product = productApiMapper.toModel(productRequest);
         return crudProductService.updateProduct(productId,product)
                 .map(productUpdated -> new ResponseEntity<>(productApiMapper.toResponseDto(productUpdated), HttpStatus.OK))
                 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
}
