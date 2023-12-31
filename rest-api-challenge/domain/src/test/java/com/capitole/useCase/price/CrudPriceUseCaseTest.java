package com.capitole.useCase.price;

import com.capitole.drivenPort.repository.PriceRepositoryPort;
import com.capitole.entity.price.Price;
import com.capitole.exception.EntityNotFoundException;
import com.capitole.useCase.price.impl.CrudPriceUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CrudPriceUseCaseTest extends PriceTestData {

    private final PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private  CrudPriceUseCaseImpl crudPriceUseCaseImpl;

    public CrudPriceUseCaseTest(@Mock PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
        this.crudPriceUseCaseImpl = new CrudPriceUseCaseImpl(priceRepositoryPort);
    }

    @Test
    public void shouldCreatePriceAndSaveToRepository() {
        // given
        Price price = createPrice();
        when(priceRepositoryPort.save(price)).thenReturn(price);

        // when
        Price createdPrice = crudPriceUseCaseImpl.createPrice(price);

        // then
        verify(priceRepositoryPort, times(1)).save(price);
        assertEquals(price, createdPrice, "The returned price should be the same as the one saved.");
    }

    @Test
    public void shouldUpdatePrice() {
        // given
        Price price = createPrice();
        when(priceRepositoryPort.update(price)).thenReturn(Optional.of(price));

        // when
        Optional<Price> updatedPrice = crudPriceUseCaseImpl.updatePrice(price.getId(), price);

        // then
        assertTrue(updatedPrice.isPresent(), "The updated price should be present.");
        verify(priceRepositoryPort, times(1)).update(price);
    }

    @Test
    public void shouldReturnEmptyWhenUpdatingNonExistingPrice() {
        // given
        Price price = createPrice();
        // when
        Optional<Price> optionalPrice = crudPriceUseCaseImpl.updatePrice(price.getId(), price);
        assertTrue(optionalPrice.isEmpty());
        verify(priceRepositoryPort, times(1)).update(price);
    }

    @Test
    public void shouldGetPriceById() {
        // given
        Price price = createPrice();
        when(priceRepositoryPort.findById(price.getId())).thenReturn(Optional.of(price));

        // when
        Price retrievedPrice = crudPriceUseCaseImpl.getPriceById(price.getId());

        // then
        assertEquals(price, retrievedPrice, "The retrieved price should match the expected price.");
        verify(priceRepositoryPort, times(1)).findById(price.getId());
    }

    @Test
    public void shouldThrowExceptionWhenGettingNonExistingPriceById() {
        // given
        Price price = createPrice();
        when(priceRepositoryPort.findById(price.getId())).thenReturn(Optional.empty());

        // when, then
        assertThrows(EntityNotFoundException.class,
                () -> crudPriceUseCaseImpl.getPriceById(price.getId()),
                "Should throw EntityNotFoundException for getting non-existing price by ID.");
        verify(priceRepositoryPort, times(1)).findById(price.getId());
    }

    @Test
    public void shouldDeletePrice() {
        // given
        Price price = createPrice();
        when(priceRepositoryPort.deleteById(price.getId())).thenReturn(true);

        // when
        boolean result = crudPriceUseCaseImpl.deletePrice(price.getId());

        // then
        assertTrue(result, "The result of deleting the price should be true.");
        verify(priceRepositoryPort, times(1)).deleteById(price.getId());
    }

    @Test
    public void shouldReturnFalseWhenDeletingNonExistingPrice() {
        // given
        Price price = createPrice();
        when(priceRepositoryPort.deleteById(price.getId())).thenReturn(false);

        // when
        boolean result = crudPriceUseCaseImpl.deletePrice(price.getId());

        // then
        assertFalse(result, "The result of deleting the non-existing price should be false.");
        verify(priceRepositoryPort, times(1)).deleteById(price.getId());
    }

    @Test
    public void shouldGetAllPrices() {
        // given
        List<Price> expectedPrices = createPriceList();
        when(priceRepositoryPort.findAll()).thenReturn(expectedPrices);

        // when
        List<Price> result = crudPriceUseCaseImpl.getAllPrices();

        // then
        assertEquals(expectedPrices, result, "The returned list of prices should match the expected list.");
        verify(priceRepositoryPort, times(1)).findAll();
    }
}
