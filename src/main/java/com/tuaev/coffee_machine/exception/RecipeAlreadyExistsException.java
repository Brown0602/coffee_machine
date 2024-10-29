package com.tuaev.coffee_machine.exception;

public class RecipeAlreadyExistsException extends RuntimeException{
    public RecipeAlreadyExistsException() {
        super();
    }

    public RecipeAlreadyExistsException(String message) {
        super(message);
    }
}
