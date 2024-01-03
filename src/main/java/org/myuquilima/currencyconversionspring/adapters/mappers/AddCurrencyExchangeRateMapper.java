package org.myuquilima.currencyconversionspring.adapters.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.myuquilima.currencyconversionspring.adapters.dtos.AddCurrencyExchangeRateDTO;
import org.myuquilima.currencyconversionspring.domain.models.CurrencyExchangeRate;
import org.myuquilima.currencyconversionspring.domain.valueobjects.Currency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AddCurrencyExchangeRateMapper {
    @Mapping(source = "sourceCurrency", target = "sourceCurrency", qualifiedByName = "stringToCurrency")
    @Mapping(source = "targetCurrency", target = "targetCurrency", qualifiedByName = "stringToCurrency")
    @Mapping(source = "effectiveStartDate", target = "effectiveStartDate", qualifiedByName = "stringToDate")
    CurrencyExchangeRate dtoToDomain(AddCurrencyExchangeRateDTO addCurrencyExchangeRateDTO);

    @Mapping(source = "sourceCurrency", target = "sourceCurrency", qualifiedByName = "stringToCurrency")
    @Mapping(source = "targetCurrency", target = "targetCurrency", qualifiedByName = "stringToCurrency")
    @Mapping(source = "effectiveStartDate", target = "effectiveStartDate", qualifiedByName = "stringToDate")
    ArrayList<CurrencyExchangeRate> dtoListToDomainList(ArrayList<AddCurrencyExchangeRateDTO> addCurrencyExchangeRateDTOList);

    @Named("stringToCurrency")
    default Currency stringToCurrency(String value) {
        return new Currency(value);
    }

    @Named("stringToDate")
    default LocalDate stringToDate(String value) {
        return LocalDate.parse(value);
    }
}
