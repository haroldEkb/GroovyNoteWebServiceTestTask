package com.haroldekb.GroovyNoteWebServiceTestTask.service

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note

interface NoteService {

    List<Note> getAll()

    void save(Note note)

    List<Note> searchContaining(String search)

    boolean doExistById(Integer integer)

    void deleteNoteById(Integer integer)
}