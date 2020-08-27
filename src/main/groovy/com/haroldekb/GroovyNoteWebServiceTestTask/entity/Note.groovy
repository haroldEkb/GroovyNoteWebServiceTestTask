package com.haroldekb.GroovyNoteWebServiceTestTask.entity

import groovy.transform.Canonical

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Canonical
@Entity
@Table(name = "notes")
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Column(name = 'name')
    String name

    @Column(name = 'content')
    String content
}
