package com.capitole.entryPoint.rest.price;

import com.capitole.entity.price.Price;
import com.capitole.entryPoint.rest.dto.price.request.PriceRequest;
import com.capitole.entryPoint.rest.factory.PriceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrudPriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceFactory priceFactory;

    @Autowired
    private ObjectMapper objectMapper;

    private final String url = "/v1/prices";


    @Test
    public void createPrice() throws Exception {
        PriceRequest request = priceFactory.createPriceRequest();
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(request.getBrandId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(request.getProductId()));
    }

    @Test
    public void getPriceById() throws Exception {
        Price price = priceFactory.createPrice();
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/{priceId}", price.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(price.getBrand().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(price.getProduct().getId()));
    }

    @Test
    public void getAllPrices() throws Exception {
        priceFactory.createPrice();
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updatePrice() throws Exception {
        Price priceUpdate = priceFactory.createPrice();
        priceUpdate.setPrice(BigDecimal.valueOf(22.5));
        mockMvc.perform(MockMvcRequestBuilders.put(url+"/{priceId}", priceUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(priceUpdate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(priceUpdate.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(priceUpdate.getPrice()));
    }

    @Test
    public void deletePriceById() throws Exception {
        Price priceToDelete = priceFactory.createPrice();
        mockMvc.perform(MockMvcRequestBuilders.delete(url+"/{priceId}", priceToDelete.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void getPriceByIdWhenNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/{priceId}",50L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deletePriceWhenNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(url+"/{priceId}",50L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updatePrICEWhenNotExist() throws Exception {
        Price priceToUpdate = priceFactory.createPrice();
        mockMvc.perform(MockMvcRequestBuilders.put(url+"/{priceId}",50L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(priceToUpdate)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
