package org.myuquilima.currencyconversionspring.adapters.controllers;

import org.myuquilima.currencyconversionspring.adapters.dtos.ConvertCurrencyDTO;
import org.myuquilima.currencyconversionspring.adapters.mappers.AddCurrencyExchangeRateMapper;
import org.myuquilima.currencyconversionspring.adapters.repositories.LocalExchangeRateRepository;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;
import org.myuquilima.currencyconversionspring.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert-currency")
public class ConvertCurrencyController {
    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    private AddCurrencyExchangeRateMapper addCurrencyExchangeRateMapper;

    public ConvertCurrencyController(LocalExchangeRateRepository localExchangeRateRepository) {
        currencyExchangeService = new CurrencyExchangeService(localExchangeRateRepository);
    }

    @PostMapping
    public ResponseEntity<Double> convertCurrency(@RequestBody ConvertCurrencyDTO convertCurrencyDTO) {
        Currency sourceCurrency = new Currency(convertCurrencyDTO.getSourceCurrency());
        Currency targetCurrency = new Currency(convertCurrencyDTO.getTargetCurrency());
        return new ResponseEntity<>(currencyExchangeService.getExchangeRate(sourceCurrency, targetCurrency), HttpStatus.OK);
    }
}
