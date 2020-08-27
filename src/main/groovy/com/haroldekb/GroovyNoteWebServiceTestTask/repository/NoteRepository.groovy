package com.haroldekb.GroovyNoteWebServiceTestTask.repository

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByNameContainingIgnoreCaseOrContentContainingIgnoreCase(String s1, String s2)
}