package com.gradinar.mynotes.exception;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(Long id) {
        super("Note with Id " + id + "note found");
    }
}
