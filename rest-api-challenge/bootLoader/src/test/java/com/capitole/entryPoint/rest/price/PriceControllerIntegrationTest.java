package com.capitole.entryPoint.rest.price;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@Transactional
public class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final String url = "/v1/api/prices";

    @Test
    @Sql("/dataTest.sql")
    public void validateTest1() throws Exception{
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/query")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    @Sql("/dataTest.sql")
    public void validateTest2() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        mockMvc.perform(MockMvcRequestBuilders.get(url + "/query")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45));
    }

    @Test
    @Sql("/dataTest.sql")
    public void validateTest3() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        mockMvc.perform(MockMvcRequestBuilders.get(url + "/query")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value( 35.50));
    }

    @Test
    @Sql("/dataTest.sql")
    public void validateTest4() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        mockMvc.perform(MockMvcRequestBuilders.get(url + "/query")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value( 30.50));
    }

    @Test
    @Sql("/dataTest.sql")
    public void validateTest5() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        mockMvc.perform(MockMvcRequestBuilders.get(url + "/query")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand.name").value("ZARA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value( 38.95));
    }

    @Test
    @Sql("/dataTest.sql")
    public void whenNotMathWithFilterThenReturnNotExist() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2024, 1, 2, 21, 0);
        mockMvc.perform(MockMvcRequestBuilders.get(url + "/query")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").doesNotExist());
    }
}
