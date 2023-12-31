package com.capitole.useCase.price;

import com.capitole.drivenPort.repository.PriceRepositoryPort;
import com.capitole.entity.price.Price;
import com.capitole.useCase.price.impl.PriceUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PriceUseCaseTest extends PriceTestData{

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceUseCaseImpl priceUseCase;

    @Test
    public void shouldReturnNullWhenNoPricesFound() {
        // given
        Long idBrand = 1L;
        Long idProduct = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(priceRepositoryPort.getQueryPrice(idBrand, idProduct, applicationDate))
                .thenReturn(Collections.emptyList());

        // when
        Price result = priceUseCase.getQueryPrice(idBrand, idProduct, applicationDate);

        // then
        assertNull(result, "The result should be null when no prices are found.");
        verify(priceRepositoryPort, times(1)).getQueryPrice(idBrand, idProduct, applicationDate);
    }

    @Test
    public void shouldReturnMaxPriorityPrice() {
        // given
        Long idBrand = 1L;
        Long idProduct = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        List<Price> prices = Collections.singletonList(
                createCustomPrice(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 10L, 1L, 100.0)
        );

        when(priceRepositoryPort.getQueryPrice(idBrand, idProduct, applicationDate))
                .thenReturn(prices);

        // when
        Price result = priceUseCase.getQueryPrice(idBrand, idProduct, applicationDate);

        // then
        assertEquals(prices.get(0), result, "The result should be the only price available.");
        verify(priceRepositoryPort, times(1)).getQueryPrice(idBrand, idProduct, applicationDate);
    }

    @Test
    public void shouldReturnMaxPriorityPriceWhenMultiplePricesExist() {
        // given
        Long idBrand = 1L;
        Long idProduct = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        List<Price> prices = List.of(
                createCustomPrice(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 10L, 1L, 100.0),
                createCustomPrice(2L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 20L, 2L, 150.0),
                createCustomPrice(3L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 15L, 3L, 120.0)
        );

        when(priceRepositoryPort.getQueryPrice(idBrand, idProduct, applicationDate))
                .thenReturn(prices);

        // when
        Price result = priceUseCase.getQueryPrice(idBrand, idProduct, applicationDate);

        // then
        assertEquals(prices.get(2), result, "The result should be the price with the max priority.");
        verify(priceRepositoryPort, times(1)).getQueryPrice(idBrand, idProduct, applicationDate);
    }

    @Test
    public void shouldReturnNullWhenPricesExistButNotForGivenProductAndBrand() {
        // given
        Long idBrand = 1L;
        Long idProduct = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        List<Price> prices = List.of(
                createCustomPrice(2L, 2L, 1L, LocalDateTime.now(), LocalDateTime.now(), 20L, 2L, 150.0),
                createCustomPrice(3L, 3L, 1L, LocalDateTime.now(), LocalDateTime.now(), 15L, 3L, 120.0)
        );

        // when
        Price result = priceUseCase.getQueryPrice(idBrand, idProduct, applicationDate);

        // then
        assertNull(result, "The result should be null when no prices match the given product and brand.");
        verify(priceRepositoryPort, times(1)).getQueryPrice(idBrand, idProduct, applicationDate);
    }
}
