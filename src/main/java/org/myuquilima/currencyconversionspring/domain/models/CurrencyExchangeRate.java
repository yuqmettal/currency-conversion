package org.myuquilima.currencyconversionspring.domain.models;

import org.myuquilima.currencyconversionspring.domain.exceptions.WrongExchangeRateException;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;

import java.time.LocalDate;

public class CurrencyExchangeRate {
    private final Currency sourceCurrency;
    private final Currency targetCurrency;
    private final Double exchangeRate;
    private final LocalDate effectiveStartDate;

    public CurrencyExchangeRate(Currency sourceCurrency, Currency targetCurrency, Double exchangeRate, LocalDate effectiveStartDate) {
        if (exchangeRate <= 0) throw new WrongExchangeRateException("Exchange rate must be greater than 0");
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.effectiveStartDate = effectiveStartDate;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public LocalDate getEffectiveStartDate() {
        return effectiveStartDate;
    }
}
