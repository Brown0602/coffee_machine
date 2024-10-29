package com.tuaev.coffee_machine.exception;

public class NotRecipeByNameException extends RuntimeException{
    public NotRecipeByNameException() {
        super();
    }

    public NotRecipeByNameException(String message) {
        super(message);
    }
}
