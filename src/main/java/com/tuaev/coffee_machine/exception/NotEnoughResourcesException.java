package com.tuaev.coffee_machine.exception;

public class NotEnoughResourcesException extends RuntimeException{

    public NotEnoughResourcesException() {
        super();
    }

    public NotEnoughResourcesException(String message) {
        super(message);
    }
}
