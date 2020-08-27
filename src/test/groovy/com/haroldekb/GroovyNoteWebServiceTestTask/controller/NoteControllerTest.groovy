package com.haroldekb.GroovyNoteWebServiceTestTask.controller

import com.haroldekb.GroovyNoteWebServiceTestTask.NoteTestConfiguration
import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import com.haroldekb.GroovyNoteWebServiceTestTask.service.NoteService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.ui.Model

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(NoteController.class)
@MockBean(NoteService.class)
@MockBean(Model.class)
@ContextConfiguration(classes = NoteTestConfiguration.class)
class NoteControllerTest {
    @Autowired
    private MockMvc mvc

    @MockBean
    private NoteService service

    @Autowired
    @Qualifier("testNotes")
    private List<Note> testNotes

    @Autowired
    @Qualifier("searchNotes")
    private List<Note> searchNotes

    @Test
    void 'call service getAll'() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
        verify(service).getAll()
    }

    @Test
    void 'return all notes'() throws Exception {
        when(service.getAll()).thenReturn(testNotes)
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("notes"))
                .andExpect(model().attribute("notes", testNotes))
    }

    @Test
    void 'call service searchContaining'() throws Exception {
        mvc.perform(get("/?search=eqweqwe"))
                .andExpect(status().isOk())
        verify(service).searchContaining(anyString())
    }

    @Test
    void 'return correct list of searched notes '() throws Exception {
        when(service.searchContaining(anyString())).thenReturn(searchNotes)
        mvc.perform(get("/?search=qweqwe"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("notes"))
                .andExpect(model().attribute("notes", searchNotes))
    }

    @Test
    void 'call service delete'() throws Exception {
        when(service.existsById(anyInt())).thenReturn(true)
        mvc.perform(get("/delete?id=12"))
                .andExpect(status().isOk())
        verify(service).deleteNoteById(12)
    }

    @Test
    void 'return error page when try to delete nonexistent id'() throws Exception {
        when(service.existsById(anyInt())).thenReturn(false)
        mvc.perform(get("/delete?id=12"))
                .andExpect(status().isBadRequest())
                .andExpect(model().attribute("message", "There is no note with id = 12"))
    }

    @Test
    void 'return new Note when get request to add note'() throws Exception {
        mvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("note"))
    }

    @Test
    void 'call service save'() throws Exception {
        mvc.perform(post("/add")
                .flashAttr("note", testNotes[0]))
        verify(service).save(any())
    }

    @Test
    void 'redirect to main page when successfully add note'() throws Exception {
        mvc.perform(post("/add")
                .flashAttr("note", testNotes[0]))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "New note is successfully added"))
    }

    @Test
    void 'return error page when try to save empty note'() throws Exception {
        mvc.perform(post("/add")
                .flashAttr("note", new Note()))
                .andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("message", "Note is empty"))
    }
}
