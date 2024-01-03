package org.myuquilima.currencyconversionspring.adapters.handlers;

import org.myuquilima.currencyconversionspring.adapters.dtos.ResponseErrorDTO;
import org.myuquilima.currencyconversionspring.domain.exceptions.WrongCurrencyValueException;
import org.myuquilima.currencyconversionspring.domain.exceptions.WrongExchangeRateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ResponseErrorDTO> handleEmailAlreadyExistsException() {
        ResponseErrorDTO error = new ResponseErrorDTO();
        error.setMessage("Invalid date format");
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(WrongCurrencyValueException.class)
    public ResponseEntity<ResponseErrorDTO> handleWrongCurrencyValueException() {
        ResponseErrorDTO error = new ResponseErrorDTO();
        error.setMessage("Wrong currency value");
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(WrongExchangeRateException.class)
    public ResponseEntity<ResponseErrorDTO> handleWrongExchangeRateException() {
        ResponseErrorDTO error = new ResponseErrorDTO();
        error.setMessage("Exchange rate must be greater than 0");
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
