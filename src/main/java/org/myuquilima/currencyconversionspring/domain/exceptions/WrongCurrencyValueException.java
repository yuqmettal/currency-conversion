package org.myuquilima.currencyconversionspring.domain.exceptions;

public class WrongCurrencyValueException extends RuntimeException{
    public WrongCurrencyValueException(String message) {
        super(message);
    }

    public WrongCurrencyValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
