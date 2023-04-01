package com.gingermadfire.onlinestore.exception;

public class OrderLineNotFoundException extends RuntimeException {

    public OrderLineNotFoundException(String msg) {
        super(msg);
    }
}
