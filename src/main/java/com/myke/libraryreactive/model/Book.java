package com.myke.libraryreactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "booksR")
public class Book {

    // ATTRIBUTES
    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String isbn;
    private String name;
    private String author;
    private String category;
    private String type;
    private Boolean borrowed = false;
    private LocalDate dateBorrewed = null;


    // GETTER && SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }

    public LocalDate getDateBorrewed() {
        return dateBorrewed;
    }

    public void setDateBorrewed(LocalDate dateBorrewed) {
        this.dateBorrewed = dateBorrewed;
    }
}
