package com.haroldekb.GroovyNoteWebServiceTestTask.controller

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import com.haroldekb.GroovyNoteWebServiceTestTask.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam

import javax.annotation.PostConstruct

@Controller
class NoteController {
    final NoteService service

    @Autowired
    NoteController(NoteService service){
        this.service = service
    }

    @PostConstruct
    void init(){
        service.save(new Note(1, '123', '123213'))
    }

    @GetMapping("/")
    String showNotes(@RequestParam(required = false, value = "search") String search, Model model, @ModelAttribute("message") String message){
        def notes
        if (search != null && search != "") {
            notes = service.searchContaining(search)
        } else {
            notes = service.getAll()
        }
        model.addAttribute("notes", notes)
        if (message != null) model.addAttribute("message", message)
        "index"
    }
}
