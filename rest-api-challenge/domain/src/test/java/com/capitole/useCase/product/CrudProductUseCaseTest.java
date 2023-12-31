package com.capitole.useCase.product;

import com.capitole.drivenPort.repository.ProductRepositoryPort;
import com.capitole.entity.product.Product;
import com.capitole.exception.EntityNotFoundException;
import com.capitole.useCase.product.impl.CrudProductUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrudProductUseCaseTest extends ProductTestData {

    private final ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private CrudProductUseCaseImpl crudProductUseCaseImpl;

    public CrudProductUseCaseTest(@Mock ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.crudProductUseCaseImpl = new CrudProductUseCaseImpl(productRepositoryPort);
    }

    @Test
    public void shouldCreateProductAndSaveToRepository() {
        // given
        Product product = createProduct();
        when(productRepositoryPort.save(product)).thenReturn(product);

        // when
        Product createdProduct = crudProductUseCaseImpl.createProduct(product);

        // then
        verify(productRepositoryPort, times(1)).save(any());
        assertEquals(product, createdProduct, "The returned product should be the same as the one saved.");
    }

    @Test
    public void shouldUpdateProductAndReturnUpdatedProduct() {
        // given
        Long productId = 1L;
        Product updatedProduct = createProduct();
        updatedProduct.setId(productId);
        when(productRepositoryPort.update(updatedProduct)).thenReturn(Optional.of(updatedProduct));

        // when
        Product result = crudProductUseCaseImpl.updateProduct(productId, updatedProduct);

        // then
        verify(productRepositoryPort, times(1)).update(updatedProduct);
        assertEquals(updatedProduct, result, "The returned product should be the updated product.");
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenUpdatingNonExistingProduct() {
        // given
        Long productId = 1L;
        Product updatedProduct = createProduct();
        updatedProduct.setId(productId);
        when(productRepositoryPort.update(updatedProduct)).thenReturn(Optional.empty());

        // when, then
        assertThrows(EntityNotFoundException.class, () -> {
            crudProductUseCaseImpl.updateProduct(productId, updatedProduct);
        });

        verify(productRepositoryPort, times(1)).update(updatedProduct);
    }

    @Test
    public void shouldGetProductById() {
        // given
        Long productId = 1L;
        Product expectedProduct = createProduct();
        expectedProduct.setId(productId);
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.of(expectedProduct));

        // when
        Product result = crudProductUseCaseImpl.getProductById(productId);

        // then
        assertEquals(expectedProduct, result, "The returned product should be the one with the specified ID.");
        verify(productRepositoryPort, times(1)).findById(productId);
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenGettingNonExistingProduct() {
        // given
        Long productId = 1L;
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.empty());

        // when, then
        assertThrows(EntityNotFoundException.class, () -> {
            crudProductUseCaseImpl.getProductById(productId);
        });

        verify(productRepositoryPort, times(1)).findById(productId);
    }

    @Test
    public void shouldDeleteProduct() {
        // given
        Long productId = 1L;
        when(productRepositoryPort.deleteById(productId)).thenReturn(true);

        // when
        boolean result = crudProductUseCaseImpl.deleteProduct(productId);

        // then
        assertTrue(result, "The delete operation should be successful.");
        verify(productRepositoryPort, times(1)).deleteById(productId);
    }

    @Test
    public void shouldReturnFalseWhenDeletingNonExistingProduct() {
        // given
        Long productId = 1L;
        when(productRepositoryPort.deleteById(productId)).thenReturn(false);

        // when
        boolean result = crudProductUseCaseImpl.deleteProduct(productId);

        // then
        assertFalse(result, "The delete operation should return false for a non-existing product.");
        verify(productRepositoryPort, times(1)).deleteById(productId);
    }

    @Test
    public void shouldGetAllProductsAndValidateSize() {
        // given
        List<Product> expectedProducts = createProductList();
        when(productRepositoryPort.findAll()).thenReturn(expectedProducts);

        // when
        List<Product> result = crudProductUseCaseImpl.getAllProducts();

        // then
        assertEquals(expectedProducts, result, "The returned list of products should match the expected list.");
        verify(productRepositoryPort, times(1)).findAll();
        assertEquals(expectedProducts.size(), result.size(), "The size of the returned list should match the expected size.");
    }

    @Test
    public void shouldGetEmptyProductListWhenNoProductsCreated() {
        // given
        when(productRepositoryPort.findAll()).thenReturn(Collections.emptyList());

        // when
        List<Product> result = crudProductUseCaseImpl.getAllProducts();

        // then
        assertNotNull(result, "The returned list should not be null.");
        assertTrue(result.isEmpty(), "The returned list should be empty when no products are created.");
        verify(productRepositoryPort, times(1)).findAll();
    }
}
