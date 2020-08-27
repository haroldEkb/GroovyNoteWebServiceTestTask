package com.haroldekb.GroovyNoteWebServiceTestTask.repository

import com.haroldekb.GroovyNoteWebServiceTestTask.NoteTestConfiguration
import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@ContextConfiguration(classes = NoteTestConfiguration.class)
class NoteRepositoryTest {
    @Autowired
    NoteRepository repository

    @Autowired
    @Qualifier("testNotes")
    List<Note> testNotes

    @Autowired
    @Qualifier("searchNotes")
    List<Note> searchNotes

    @BeforeEach
    void setUp() {
        repository.saveAll(testNotes)
    }

    @Test
    void 'return not null or empty list'(){
        def notes = repository.findByNameContainingIgnoreCaseOrContentContainingIgnoreCase("123", "123")
        Assertions.assertNotNull(notes)
        Assertions.assertTrue(notes.size() != 0)
    }

    @Test
    void 'when search return list of correct size'(){
        def notes = repository.findByNameContainingIgnoreCaseOrContentContainingIgnoreCase("123", "123")
        Assertions.assertEquals(3, notes.size())
    }

    @Test
    void 'when search return correct list'(){
        def notes = repository.findByNameContainingIgnoreCaseOrContentContainingIgnoreCase("123", "123")
        Assertions.assertTrue(notes.containsAll(searchNotes))
    }
}
