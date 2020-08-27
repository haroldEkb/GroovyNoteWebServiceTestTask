package com.haroldekb.GroovyNoteWebServiceTestTask.service

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import com.haroldekb.GroovyNoteWebServiceTestTask.repository.NoteRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@SpringBootTest(classes = NoteServiceImplTestConfiguration.class)
class NoteServiceImplTest {

    @MockBean
    private NoteRepository repository

    @Autowired
    @Qualifier("Note")
    private Note note

    @Autowired
    @Qualifier("testNotes")
    private List<Note> testNotes

    private NoteService service

    @BeforeEach
    void setUp() {
        service = new NoteServiceImpl(repository)
    }

    @Test
    void 'call repository findAll'() {
        service.getAll()
        verify(repository).findAll()
    }

    @Test
    void 'return correct list'() {
        when(repository.findAll()).thenReturn(testNotes)
        Assertions.assertSame(service.getAll(), testNotes)
    }

    @Test
    void 'call repository save'() {
        service.save(note)
        verify(repository).save(any())
    }

    @Test
    void 'call repository existsById'() {
        service.existsById(12)
        verify(repository).existsById(12)
    }

    @Test
    void 'return repository existsById result'() {
        when(repository.existsById(anyInt())).thenReturn(true)
        Assertions.assertTrue(service.existsById(12))
    }

    @Test
    void 'call repository delete'() {
        service.deleteNoteById(13)
        verify(repository).deleteById(anyInt())
    }

    @Test
    void 'call repository searchContaining'() {
        service.searchContaining("123")
        verify(repository).findByNameContainingIgnoreCaseOrContentContainingIgnoreCase("123", "123")
    }

    @Test
    void 'return correct note when search'() {
        when(repository.findByNameContainingIgnoreCaseOrContentContainingIgnoreCase(anyString(), anyString())).thenReturn(testNotes)
        Assertions.assertSame(service.searchContaining("123"), testNotes)
    }
}
