package com.haroldekb.GroovyNoteWebServiceTestTask.service

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note

interface NoteService {

    List<Note> getAll()

    void save(Note note)

    List<Note> searchContaining(String search)

    boolean existsById(Integer integer)

    void deleteNoteById(Integer integer)
}