package org.myuquilima.currencyconversionspring.services;

import org.myuquilima.currencyconversionspring.domain.exceptions.ExchangeRateNotFoundException;
import org.myuquilima.currencyconversionspring.domain.interfaces.IExchangeRateRepository;
import org.myuquilima.currencyconversionspring.domain.models.CurrencyExchangeRate;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class CurrencyExchangeService {
    private final IExchangeRateRepository currencyExchangeRateRepository;

    public CurrencyExchangeService(IExchangeRateRepository currencyExchangeRateRepository) {
        this.currencyExchangeRateRepository = currencyExchangeRateRepository;
    }

    public void setExchangeRate(CurrencyExchangeRate currencyExchangeRate) {
        currencyExchangeRateRepository.setExchangeRate(currencyExchangeRate);
    }

    public void setBulkExchangeRate(ArrayList<CurrencyExchangeRate> currencyExchangeRateList) {
        currencyExchangeRateRepository.setBulkExchangeRate(currencyExchangeRateList);
    }

    public double getExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        Double exchangeRate = currencyExchangeRateRepository.getClosestExchangeRate(sourceCurrency, targetCurrency);

        if (exchangeRate != null) {
            return exchangeRate;
        }

        ArrayList<CurrencyExchangeRate> targets = currencyExchangeRateRepository.getClosestExchangeRatesFromSource(sourceCurrency);
        ArrayList<CurrencyExchangeRate> sources = currencyExchangeRateRepository.getClosestExchangeRatesToTarget(targetCurrency);

        if (targets.isEmpty() || sources.isEmpty()) {
            throw new ExchangeRateNotFoundException("Exchange rate not found");
        }

        for (CurrencyExchangeRate target : targets) {
            for (CurrencyExchangeRate source : sources) {
                if (target.getTargetCurrency().equals(source.getSourceCurrency())) {
                    return target.getExchangeRate() * source.getExchangeRate();
                }
            }
        }

        throw new ExchangeRateNotFoundException("Exchange rate not found");
    }
}
