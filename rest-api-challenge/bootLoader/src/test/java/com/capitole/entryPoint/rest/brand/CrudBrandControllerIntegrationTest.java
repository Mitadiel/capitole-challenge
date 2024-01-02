package com.capitole.entryPoint.rest.brand;

import com.capitole.entity.brand.Brand;
import com.capitole.entryPoint.rest.dto.brand.request.BrandRequest;
import com.capitole.entryPoint.rest.factory.BrandFactory;
import com.capitole.entryPoint.rest.mapper.BrandApiMapper;
import com.capitole.entryPoint.rest.util.JsonUtils;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import javax.transaction.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class CrudBrandControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandFactory brandFactory;

    @Autowired
    private BrandApiMapper brandApiMapper;

    private final String url = "/v1/brands";

    @Test
    public void createBrand() throws Exception {
        BrandRequest  request = brandFactory.createBrandRequest();
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.convertObjectToJsonBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(request.getDescription()));
    }

    @Test
    public void getBrandById() throws Exception {
        Brand brand = brandFactory.createBrand();
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/"+ brand.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(brand.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(brand.getDescription()));
    }

    @Test
    public void getAllBrands() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

     @Test
    public void updateBrand() throws Exception {
         Brand brandToUpdate = brandFactory.createBrand();
         brandToUpdate.setName("UpdatedBrand");
        mockMvc.perform(MockMvcRequestBuilders.put(url+"/{brandId}", brandToUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.convertObjectToJsonBytes(brandApiMapper.toBrandResponseDto(brandToUpdate))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(brandToUpdate.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UpdatedBrand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(brandToUpdate.getDescription()));
    }

    @Test
    public void deleteBrandById() throws Exception {
        Brand brandToDelete = brandFactory.createBrand();
        mockMvc.perform(MockMvcRequestBuilders.delete(url+"/{brandId}", brandToDelete.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void getBrandByIdWhenNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url+"/{brandId}",2L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteBrandWhenNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(url+"/{brandId}",2L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateBrandWhenNotExist() throws Exception {
        Brand brandToUpdate = brandFactory.createBrand();
        mockMvc.perform(MockMvcRequestBuilders.put(url+"/{brandId}",50L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.convertObjectToJsonBytes(brandApiMapper.toBrandResponseDto(brandToUpdate))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
