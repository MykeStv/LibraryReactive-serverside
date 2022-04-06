package com.myke.libraryreactive.service.impl;

import com.myke.libraryreactive.model.Book;
import com.myke.libraryreactive.repository.BookRepository;
import com.myke.libraryreactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Flux<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Mono<Book> getBookById(String id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Mono<Book> saveBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Mono<Book> updateBook(String id, Book book) {
        return this.bookRepository.findById(id)
                .flatMap(b -> {
                    book.setId(id);
                    return saveBook(book);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Book> deleteBook(String id) {
        return this.bookRepository.findById(id)
                .flatMap(b -> this.bookRepository.deleteById(id).thenReturn(b));
    }
}
