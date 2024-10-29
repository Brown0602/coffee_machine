package com.tuaev.coffee_machine.exception;

public class ResourcesNotEqualIngredientsException extends RuntimeException{
    public ResourcesNotEqualIngredientsException() {
        super();
    }

    public ResourcesNotEqualIngredientsException(String message) {
        super(message);
    }
}
