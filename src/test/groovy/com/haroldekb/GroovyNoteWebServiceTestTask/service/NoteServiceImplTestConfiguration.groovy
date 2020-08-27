package com.haroldekb.GroovyNoteWebServiceTestTask.service

import com.haroldekb.GroovyNoteWebServiceTestTask.NoteTestConfiguration
import com.haroldekb.GroovyNoteWebServiceTestTask.entity.Note
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(NoteTestConfiguration.class)
class NoteServiceImplTestConfiguration {

    @Bean("Note")
    Note note(){
        new Note(1, "xvxcv123tgttg", "thh123qweqwe")
    }
}
