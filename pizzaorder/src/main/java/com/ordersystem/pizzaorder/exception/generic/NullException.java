package com.ordersystem.pizzaorder.exception.generic;

public class NullException extends RuntimeException{
    public NullException(String message) {
        super(message);
    }

    public NullException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullException(Throwable cause) {
        super(cause);
    }
}
