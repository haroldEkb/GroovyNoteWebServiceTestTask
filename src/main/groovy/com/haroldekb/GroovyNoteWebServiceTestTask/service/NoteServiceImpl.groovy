package com.haroldekb.GroovyNoteWebServiceTestTask.service

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import com.haroldekb.GroovyNoteWebServiceTestTask.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl implements NoteService {

    final NoteRepository repository

    @Autowired
    NoteServiceImpl(NoteRepository repository) {
        this.repository = repository
    }

    @Override
    List<Note> getAll() {
        repository.findAll()
    }

    @Override
    void save(Note note) {
        repository.save(note)
    }

    @Override
    List<Note> searchContaining(String search) {
        repository.findByNameContainingIgnoreCaseOrContentContainingIgnoreCase(search, search)
    }

    @Override
    void deleteNoteById(Integer id) {
        repository.deleteById(id)
    }

    @Override
    boolean doExistById(Integer id) {
        return repository.existsById(id)
    }
}
