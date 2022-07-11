package com.gradinar.mynotes.controller;

import com.gradinar.mynotes.entity.Note;
import com.gradinar.mynotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @PostMapping
    public ResponseEntity<Note> save(@Valid @RequestBody Note note) throws URISyntaxException {
        return noteService.saveNote(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return noteService.deleteNote(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @Valid @RequestBody Note note) {
        return noteService.update(id, note);
    }
}
