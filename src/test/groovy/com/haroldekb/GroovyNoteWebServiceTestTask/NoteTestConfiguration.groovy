package com.haroldekb.GroovyNoteWebServiceTestTask

import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class NoteTestConfiguration {

    @Bean("testNotes")
    List<Note> testNotes(){
        [new Note(1, "xvxcv123tgttg", "thh123qweqwe"),
         new Note(2, "456", "asdsafd"),
         new Note(3, "wqe123qwe", "34435xvcxcxvvcx"),
         new Note(4, "", "123xvcxcxvvcx")]
    }

    @Bean("searchNotes")
    List<Note> searchNotes(){
        [new Note(1, "xvxcv123tgttg", "thh123qweqwe"),
         new Note(3, "wqe123qwe", "34435xvcxcxvvcx"),
         new Note(4, "", "123xvcxcxvvcx")]
    }
}
