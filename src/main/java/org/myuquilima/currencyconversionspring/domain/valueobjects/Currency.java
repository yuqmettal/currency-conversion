package org.myuquilima.currencyconversionspring.domain.valueobjects;

import org.myuquilima.currencyconversionspring.domain.exceptions.WrongCurrencyValueException;

import java.util.Objects;

public final class Currency {

    private final String value;

    public Currency(String currency) {
        if (currency == null || currency.isEmpty())
            throw new WrongCurrencyValueException("Currency cannot be null or empty");
        this.value = currency;
    }

    public String getCurrency() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Currency that = (Currency) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Currency{value='" + value + "'}";
    }
}

