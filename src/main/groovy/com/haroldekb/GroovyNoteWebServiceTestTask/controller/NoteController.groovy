package com.haroldekb.GroovyNoteWebServiceTestTask.controller

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import com.haroldekb.GroovyNoteWebServiceTestTask.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletResponse

@Controller
class NoteController {
    final NoteService service

    @Autowired
    NoteController(NoteService service){
        this.service = service
    }

    @GetMapping('/')
    String showNotes(@RequestParam(required = false, value = 'search') String search, Model model, @ModelAttribute('message') String message){
        def notes
        if (search) {
            notes = service.searchContaining(search)
        } else {
            notes = service.getAll()
        }
        model.addAttribute('notes', notes)
        if (message) model.addAttribute('message', message)
        'index'
    }

    @GetMapping('/add')
    String newNoteForm(Model model){
        model.addAttribute('note', new Note())
        'add-note'
    }

    @PostMapping("/add")
    String addNote(@ModelAttribute(name = 'note') Note newNote, RedirectAttributes attributes){
        if (newNote == null || !newNote) {
            throw new NullPointerException('Note is empty')
        }
        service.save(newNote)
        attributes.addAttribute('message', 'New note is successfully added')
        'redirect:/'
    }

    @GetMapping("/delete")
    String deleteNote(@RequestParam('id')  Integer id, RedirectAttributes attributes){
        if (!service.existsById(id)){
            throw new NoSuchElementException("There is no note with id = ${id}")
        }
        service.deleteNoteById(id)
        attributes.addAttribute('message', 'Note is successfully deleted')
        'error-page'
    }

    @ExceptionHandler([NoSuchElementException.class, NullPointerException.class])
    String handleError(Model model, HttpServletResponse response, Exception ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
        model.addAttribute('message', ex.getMessage())
        'error-page'
    }
}
