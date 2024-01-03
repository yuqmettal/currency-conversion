package org.myuquilima.currencyconversionspring.adapters.dtos;

import lombok.Data;

@Data
public class ConvertCurrencyDTO {
    private String sourceCurrency;
    private String targetCurrency;
}
