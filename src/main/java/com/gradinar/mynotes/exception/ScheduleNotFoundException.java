package com.gradinar.mynotes.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException() {
        super("Schedule with such date not found");
    }
}
