package com.capitole.entryPoint.rest.product;

import com.capitole.entity.product.Product;
import com.capitole.entryPoint.rest.dto.product.request.ProductRequest;
import com.capitole.entryPoint.rest.factory.ProductFactory;
import com.capitole.entryPoint.rest.mapper.ProductApiMapper;
import com.capitole.entryPoint.rest.util.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class CrudProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductApiMapper productApiMapper;

    private final String url = "/v1/products";

    @Test
    public void createProduct() throws Exception {
        ProductRequest request = productFactory.createProductRequest();
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.convertObjectToJsonBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(request.getSku()));
    }

    @Test
    public void getProductById() throws Exception {
        Product product = productFactory.createProduct();
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/{productId}", product.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(product.getSku()));
    }

    @Test
    public void getAllProducts() throws Exception {
        productFactory.createProduct();
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

     @Test
    public void updateProduct() throws Exception {
         Product productUpdate = productFactory.createProduct();
         productUpdate.setName("martillo");
        mockMvc.perform(MockMvcRequestBuilders.put(url+"/{productId}", productUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.convertObjectToJsonBytes(productApiMapper.toResponseDto(productUpdate))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productUpdate.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(productUpdate.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(productUpdate.getSku()));
    }

    @Test
    public void deleteProductById() throws Exception {
        Product productToDelete = productFactory.createProduct();
        mockMvc.perform(MockMvcRequestBuilders.delete(url+"/{productId}", productToDelete.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void getProductByIdWhenNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/{productId}",50L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteProductWhenNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(url+"/{productId}",50L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateProductWhenNotExist() throws Exception {
        Product productToUpdate = productFactory.createProduct();
        mockMvc.perform(MockMvcRequestBuilders.put(url+"/{productId}",50L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.convertObjectToJsonBytes(productApiMapper.toResponseDto(productToUpdate))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
