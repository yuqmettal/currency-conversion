package org.myuquilima.currencyconversionspring.domain.exceptions;

public class WrongExchangeRateException extends RuntimeException{
    public WrongExchangeRateException(String message) {
        super(message);
    }

    public WrongExchangeRateException(String message, Throwable cause) {
        super(message, cause);
    }
}
