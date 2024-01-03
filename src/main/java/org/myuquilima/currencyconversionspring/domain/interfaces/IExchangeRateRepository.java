package org.myuquilima.currencyconversionspring.domain.interfaces;

import org.myuquilima.currencyconversionspring.domain.models.CurrencyExchangeRate;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;

import java.util.ArrayList;

public interface IExchangeRateRepository {
    void setExchangeRate(CurrencyExchangeRate currencyExchangeRate);

    void setBulkExchangeRate(ArrayList<CurrencyExchangeRate> currencyExchangeRateList);

    Double getClosestExchangeRate(Currency sourceCurrency, Currency targetCurrency);

    ArrayList<CurrencyExchangeRate> getClosestExchangeRatesFromSource(Currency sourceCurrency);

    ArrayList<CurrencyExchangeRate> getClosestExchangeRatesToTarget(Currency targetCurrency);

}
