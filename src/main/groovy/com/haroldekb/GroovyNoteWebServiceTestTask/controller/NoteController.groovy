package com.haroldekb.GroovyNoteWebServiceTestTask.controller

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import com.haroldekb.GroovyNoteWebServiceTestTask.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

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
    String showAll(Model model){
        model.addAttribute("notes", service.getAll())
        "index"
    }
}
