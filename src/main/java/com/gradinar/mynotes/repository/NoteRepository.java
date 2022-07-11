package com.gradinar.mynotes.repository;

import com.gradinar.mynotes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Override
    @Query("SELECT n FROM note n WHERE n.user.email = ?#{principal?.username}")
    List<Note> findAll();

}
