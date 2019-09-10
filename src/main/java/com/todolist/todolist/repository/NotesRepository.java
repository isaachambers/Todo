package com.todolist.todolist.repository;

import com.todolist.todolist.domain.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotesRepository extends JpaRepository<Note,Long> {
    Page<Note> findByUserId(Long id, Pageable pageable);
    Optional<Note> findByIdAndUserId(Long noteId, Long userId);
}
