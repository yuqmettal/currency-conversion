package org.myuquilima.currencyconversionspring.adapters.repositories;

import org.myuquilima.currencyconversionspring.domain.interfaces.IExchangeRateRepository;
import org.myuquilima.currencyconversionspring.domain.models.CurrencyExchangeRate;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;


@Repository
public class LocalExchangeRateRepository implements IExchangeRateRepository {
    private final ArrayList<CurrencyExchangeRate> exchangeRates = new ArrayList<>();

    @Override
    public void setExchangeRate(CurrencyExchangeRate currencyExchangeRate) {
        exchangeRates.add(currencyExchangeRate);
    }

    @Override
    public void setBulkExchangeRate(ArrayList<CurrencyExchangeRate> currencyExchangeRateList) {
        exchangeRates.addAll(currencyExchangeRateList);
    }

    @Override
    public Double getClosestExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        return exchangeRates.stream()
                .filter(rate ->
                        rate.getSourceCurrency().equals(sourceCurrency) &&
                        rate.getTargetCurrency().equals(targetCurrency)
                )
                .sorted(Comparator.comparing(CurrencyExchangeRate::getEffectiveStartDate).reversed())
                .map(CurrencyExchangeRate::getExchangeRate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<CurrencyExchangeRate> getClosestExchangeRatesFromSource(Currency sourceCurrency) {
        return exchangeRates.stream()
                .filter(rate -> rate.getSourceCurrency().equals(sourceCurrency))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<CurrencyExchangeRate> getClosestExchangeRatesToTarget(Currency targetCurrency) {
        return exchangeRates.stream()
                .filter(rate -> rate.getTargetCurrency().equals(targetCurrency))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
