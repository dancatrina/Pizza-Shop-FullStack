package com.ordersystem.pizzaorder.exception.generic;

public class NotFoundEntity extends RuntimeException{

    public NotFoundEntity(String message) {
        super(message);
    }

    public NotFoundEntity(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundEntity(Throwable cause) {
        super(cause);
    }
}
