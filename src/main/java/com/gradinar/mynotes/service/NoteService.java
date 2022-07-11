package com.gradinar.mynotes.service;

import com.gradinar.mynotes.entity.Note;
import com.gradinar.mynotes.entity.User;
import com.gradinar.mynotes.exception.NoteNotFoundException;
import com.gradinar.mynotes.repository.NoteRepository;
import com.gradinar.mynotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public ResponseEntity<Note> saveNote(Note note) throws URISyntaxException {
        User user = userRepository.findCurrentByEmail();
        note.setUser(user);
        Note savedNote = noteRepository.save(note);
        return ResponseEntity.created(new URI("/notes/"+savedNote.getId())).body(savedNote);
    }

    public ResponseEntity<String> deleteNote(Long id) {
        noteRepository.findById(id)
                .orElseThrow(()->new NoteNotFoundException(id));
        noteRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<Note> update(Long id, Note note) {
        Note updatedNote = noteRepository.findById(id)
                .orElseThrow(()->new NoteNotFoundException(id));
        updatedNote.setId(note.getId());
        updatedNote.setCaption(note.getCaption());
        updatedNote.setContent(note.getContent());
        noteRepository.save(updatedNote);
        return ResponseEntity.ok(updatedNote);
    }

}
