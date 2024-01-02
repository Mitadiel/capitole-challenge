package com.capitole.useCase.brand;

import com.capitole.drivenPort.repository.BrandRepositoryPort;
import com.capitole.entity.brand.Brand;
import com.capitole.exception.EntityNotFoundException;
import com.capitole.useCase.brand.impl.CrudBrandUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class CrudBrandUseCaseTest extends BrandTestData {

    private final BrandRepositoryPort brandRepositoryPort;

    @InjectMocks
    private CrudBrandUseCaseImpl crudBrandUseCaseImpl;

    public CrudBrandUseCaseTest(@Mock BrandRepositoryPort brandRepositoryPort) {
        this.brandRepositoryPort = brandRepositoryPort;
        this.crudBrandUseCaseImpl = new CrudBrandUseCaseImpl(brandRepositoryPort);
    }

    @Test
    public void shouldCreateBrandAndSaveToRepository() {
        // given
        Brand brand = createBrand();
        when(brandRepositoryPort.save(brand)).thenReturn(brand);

        // when
        Brand createdBrand = crudBrandUseCaseImpl.createBrand(brand);

        // then
        verify(brandRepositoryPort, times(1)).save(any());
        assertEquals(brand, createdBrand, "The returned brand should be the same as the one saved.");
    }

    @Test
    public void shouldUpdateBrandAndReturnUpdatedBrand() {
        // given
        Long brandId = 1L;
        Brand updatedBrand = createBrand();
        updatedBrand.setId(brandId);
        when(brandRepositoryPort.update(updatedBrand)).thenReturn(Optional.of(updatedBrand));

        // when
        Brand result = crudBrandUseCaseImpl.updateBrand(brandId, updatedBrand);

        // then
        verify(brandRepositoryPort, times(1)).update(updatedBrand);
        assertEquals(updatedBrand, result, "The returned brand should be the updated brand.");
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenUpdatingNonExistingBrand() {
        // given
        Long brandId = 1L;
        Brand updatedBrand = createBrand();
        updatedBrand.setId(brandId);
        when(brandRepositoryPort.update(updatedBrand)).thenReturn(Optional.empty());

        // when, then
        assertThrows(EntityNotFoundException.class, () -> {
            crudBrandUseCaseImpl.updateBrand(brandId, updatedBrand);
        });

        verify(brandRepositoryPort, times(1)).update(updatedBrand);
    }

    @Test
    public void shouldGetBrandById() {
        // given
        Long brandId = 1L;
        Brand expectedBrand = createBrand();
        expectedBrand.setId(brandId);
        when(brandRepositoryPort.findById(brandId)).thenReturn(Optional.of(expectedBrand));

        // when
        Brand result = crudBrandUseCaseImpl.getBrandById(brandId);

        // then
        assertEquals(expectedBrand, result, "The returned brand should be the one with the specified ID.");
        verify(brandRepositoryPort, times(1)).findById(brandId);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenGettingNonExistingBrand() {
        // given
        Long brandId = 1L;
        when(brandRepositoryPort.findById(brandId)).thenReturn(Optional.empty());

        // when, then
        assertThrows(EntityNotFoundException.class, () -> {
            crudBrandUseCaseImpl.getBrandById(brandId);
        });

        verify(brandRepositoryPort, times(1)).findById(brandId);
    }

    @Test
    public void shouldDeleteBrand() {
        // given
        Long brandId = 1L;
        when(brandRepositoryPort.deleteById(brandId)).thenReturn(true);

        // when
        boolean result = crudBrandUseCaseImpl.deleteBrand(brandId);

        // then
        assertTrue(result, "The delete operation should be successful.");
        verify(brandRepositoryPort, times(1)).deleteById(brandId);
    }

    @Test
    public void shouldReturnFalseWhenDeletingNonExistingBrand() {
        // given
        Long brandId = 1L;
        when(brandRepositoryPort.deleteById(brandId)).thenReturn(false);

        // when
        boolean result = crudBrandUseCaseImpl.deleteBrand(brandId);

        // then
        assertFalse(result, "The delete operation should return false for a non-existing brand.");
        verify(brandRepositoryPort, times(1)).deleteById(brandId);
    }

    @Test
    public void shouldGetAllBrandsAndValidateSize() {
        // given
        List<Brand> expectedBrands = createBrandList();
        when(brandRepositoryPort.findAll()).thenReturn(expectedBrands);

        // when
        List<Brand> result = crudBrandUseCaseImpl.getAllBrands();

        // then
        assertEquals(expectedBrands, result, "The returned list of brands should match the expected list.");
        verify(brandRepositoryPort, times(1)).findAll();
        assertEquals(expectedBrands.size(), result.size(), "The size of the returned list should match the expected size.");
    }

    @Test
    public void shouldGetEmptyBrandListWhenNoBrandsCreated() {
        // given
        when(brandRepositoryPort.findAll()).thenReturn(Collections.emptyList());

        // when
        List<Brand> result = crudBrandUseCaseImpl.getAllBrands();

        // then
        assertNotNull(result, "The returned list should not be null.");
        assertTrue(result.isEmpty(), "The returned list should be empty when no brands are created.");
        verify(brandRepositoryPort, times(1)).findAll();
    }
}
