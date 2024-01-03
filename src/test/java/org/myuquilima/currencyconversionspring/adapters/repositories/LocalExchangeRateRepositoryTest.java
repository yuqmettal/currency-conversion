package org.myuquilima.currencyconversionspring.adapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myuquilima.currencyconversionspring.domain.models.CurrencyExchangeRate;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LocalExchangeRateRepositoryTest {
    private LocalExchangeRateRepository repository;

    @BeforeEach
    void setUp() {
        // Initialize repository with some data
        repository = new LocalExchangeRateRepository();
        repository.setExchangeRate(new CurrencyExchangeRate(
                new Currency("USD"),
                new Currency("EUR"),
                1.5,
                LocalDate.of(2021, 1, 1)
        ));
        repository.setExchangeRate(new CurrencyExchangeRate(
                new Currency("EUR"),
                new Currency("COP"),
                4000.0,
                LocalDate.of(2021, 1, 1)
        ));
    }

    @Test
    void testSetExchangeRate() {
        double exchangeRate = 0.00003;
        CurrencyExchangeRate rate = new CurrencyExchangeRate(
                new Currency("USD"),
                new Currency("COP"),
                exchangeRate,
                LocalDate.of(2021, 1, 1)
        );
        repository.setExchangeRate(rate);

        Double result = repository.getClosestExchangeRate(new Currency("USD"), new Currency("COP"));
        assertNotNull(result);
        assertEquals(result, exchangeRate);
    }

    @Test
    void testGetClosestExchangeRate() {
        repository.setExchangeRate(new CurrencyExchangeRate(
                new Currency("USD"),
                new Currency("COP"),
                0.0003,
                LocalDate.of(2021, 1, 1)
        ));

        Double rate = repository.getClosestExchangeRate(
                new Currency("USD"),
                new Currency("COP")
        );
        assertNotNull(rate);
    }

    @Test
    void testGetClosestExchangeRatesFromSource() {
        Currency sourceCurrency = new Currency("USD");
        ArrayList<CurrencyExchangeRate> rates = repository.getClosestExchangeRatesFromSource(sourceCurrency);
        assertFalse(rates.isEmpty());
    }

    @Test
    void testGetClosestExchangeRatesToTarget() {
        Currency targetCurrency = new Currency("EUR");
        ArrayList<CurrencyExchangeRate> rates = repository.getClosestExchangeRatesToTarget(targetCurrency);
        assertFalse(rates.isEmpty());
    }

}
