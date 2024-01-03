package org.myuquilima.currencyconversionspring.adapters.controllers;

import org.myuquilima.currencyconversionspring.adapters.dtos.AddCurrencyExchangeRateDTO;
import org.myuquilima.currencyconversionspring.adapters.mappers.AddCurrencyExchangeRateMapper;
import org.myuquilima.currencyconversionspring.adapters.repositories.LocalExchangeRateRepository;
import org.myuquilima.currencyconversionspring.domain.models.CurrencyExchangeRate;
import org.myuquilima.currencyconversionspring.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/currency-exchange-rates")
public class CurrencyExchangeRateController {
    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    private AddCurrencyExchangeRateMapper addCurrencyExchangeRateMapper;

    public CurrencyExchangeRateController(LocalExchangeRateRepository localExchangeRateRepository) {
        currencyExchangeService = new CurrencyExchangeService(localExchangeRateRepository);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody AddCurrencyExchangeRateDTO currencyExchangeRate) {
        CurrencyExchangeRate currencyExchangeRateDomain = addCurrencyExchangeRateMapper.dtoToDomain(currencyExchangeRate);
        currencyExchangeService.setExchangeRate(currencyExchangeRateDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<Void> addBulk(@RequestBody ArrayList<AddCurrencyExchangeRateDTO> currencyExchangeRateList) {
        ArrayList<CurrencyExchangeRate> currencyExchangeRateDomain = addCurrencyExchangeRateMapper.dtoListToDomainList(currencyExchangeRateList);
        currencyExchangeService.setBulkExchangeRate(currencyExchangeRateDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
