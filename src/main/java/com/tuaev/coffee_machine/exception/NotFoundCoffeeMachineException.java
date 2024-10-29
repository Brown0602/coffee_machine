package com.tuaev.coffee_machine.exception;

public class NotFoundCoffeeMachineException extends RuntimeException{
    public NotFoundCoffeeMachineException(String message) {
        super(message);
    }

    public NotFoundCoffeeMachineException() {
        super();
    }
}
