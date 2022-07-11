package com.gradinar.mynotes.exception;

public class EventIsEmptyException extends RuntimeException {
    public EventIsEmptyException() {
        super("Event is empty");
    }
}
