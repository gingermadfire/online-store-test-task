package com.gingermadfire.onlinestore.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String msg) {
        super(msg);
    }

}
