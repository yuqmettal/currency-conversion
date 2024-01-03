package org.myuquilima.currencyconversionspring.adapters.dtos;

import lombok.Data;

@Data
public class AddCurrencyExchangeRateDTO {
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
    private String effectiveStartDate;
}
