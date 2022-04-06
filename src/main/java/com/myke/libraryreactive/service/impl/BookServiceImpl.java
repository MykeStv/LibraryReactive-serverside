package com.myke.libraryreactive.service.impl;

import com.myke.libraryreactive.model.Book;
import com.myke.libraryreactive.repository.BookRepository;
import com.myke.libraryreactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

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

    @Override
    public Mono<Book> findBookName(String name) {
        return this.bookRepository.findByName(name).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<String> borrowBook(String id) {

        return this.bookRepository.findById(id)
                .flatMap(book -> {
                    if (!book.getBorrowed()) {
                        book.setBorrowed(true);
                        book.setDateBorrewed(LocalDate.now());
                        return saveBook(book).flatMap(book1 -> Mono.just("Libro prestado!"));
                    } else {
                        return Mono.just("No se puede prestar, el libro fue prestado el dia: " +
                                book.getDateBorrewed());
                    }
                });

    }

    @Override
    public Mono<String> returnBook(String id) {
        return this.bookRepository.findById(id)
                .flatMap(book -> {
                    if (book.getBorrowed()) {
                        book.setBorrowed(false);
                        book.setDateBorrewed(null);
                        return saveBook(book).flatMap(book1 -> Mono.just("Libro Devuelto!"));
                    } else {
                        return Mono.just("No se puede devolver, el libro no esta prestado");
                    }
                });
    }

    @Override
    public Flux<Book> findBooksCategory(String category) {
        return this.bookRepository.findByCategory(category);
    }
}
