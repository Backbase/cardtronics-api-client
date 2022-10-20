package com.backbase.accelerators.cardtronics.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardtronicsApiClientException extends RuntimeException {

    private int statusCode;

    public CardtronicsApiClientException(String message) {
        super(message);
    }
}
