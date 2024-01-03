package org.myuquilima.currencyconversionspring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.myuquilima.currencyconversionspring.domain.exceptions.ExchangeRateNotFoundException;
import org.myuquilima.currencyconversionspring.domain.interfaces.IExchangeRateRepository;
import org.myuquilima.currencyconversionspring.domain.models.CurrencyExchangeRate;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CurrencyExchangeServiceTest {
    @Mock
    private IExchangeRateRepository currencyExchangeRateRepository;

    private CurrencyExchangeService currencyExchangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyExchangeService = new CurrencyExchangeService(currencyExchangeRateRepository);
    }

    @Test
    void testGetExchangeRateDirectConversion() {
        Currency sourceCurrency = new Currency("USD");
        Currency targetCurrency = new Currency("EUR");
        double expectedRate = 1.5;

        when(currencyExchangeRateRepository.getClosestExchangeRate(sourceCurrency, targetCurrency))
                .thenReturn(expectedRate);

        double result = currencyExchangeService.getExchangeRate(sourceCurrency, targetCurrency);
        assertEquals(expectedRate, result);
    }

    @Test
    void testGetExchangeRateTriangularConversion() {
        Currency sourceCurrency = new Currency("USD");
        Currency targetCurrency = new Currency("COP");
        Currency intermediateCurrency = new Currency("EUR");
        double rateToIntermediate = 1.2;
        double rateFromIntermediate = 0.75;
        double expectedRate = rateToIntermediate * rateFromIntermediate;

        when(currencyExchangeRateRepository.getClosestExchangeRate(sourceCurrency, targetCurrency))
                .thenReturn(null);

        // Setting conversion from USD to EUR
        ArrayList<CurrencyExchangeRate> targets = new ArrayList<>();
        targets.add(new CurrencyExchangeRate(sourceCurrency, intermediateCurrency, rateToIntermediate, LocalDate.now()));
        when(currencyExchangeRateRepository.getClosestExchangeRatesFromSource(sourceCurrency))
                .thenReturn(targets);

        // Setting conversion from EUR to COP
        ArrayList<CurrencyExchangeRate> destinations = new ArrayList<>();
        destinations.add(new CurrencyExchangeRate(intermediateCurrency, targetCurrency, rateFromIntermediate, LocalDate.now()));
        when(currencyExchangeRateRepository.getClosestExchangeRatesToTarget(targetCurrency))
                .thenReturn(destinations);

        double result = currencyExchangeService.getExchangeRate(sourceCurrency, targetCurrency);
        assertEquals(expectedRate, result);
    }

    @Test
    void testGetExchangeRateNotFound() {
        Currency sourceCurrency = new Currency("COP");
        Currency targetCurrency = new Currency("USD");

        when(currencyExchangeRateRepository.getClosestExchangeRate(sourceCurrency, targetCurrency))
                .thenReturn(null);
        when(currencyExchangeRateRepository.getClosestExchangeRatesFromSource(sourceCurrency))
                .thenReturn(new ArrayList<>());
        when(currencyExchangeRateRepository.getClosestExchangeRatesToTarget(targetCurrency))
                .thenReturn(new ArrayList<>());

        assertThrows(ExchangeRateNotFoundException.class, () -> {
            currencyExchangeService.getExchangeRate(sourceCurrency, targetCurrency);
        });
    }
}
