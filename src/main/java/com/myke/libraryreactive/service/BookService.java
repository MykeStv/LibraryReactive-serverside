package com.myke.libraryreactive.service;

import com.myke.libraryreactive.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    Flux<Book> getBooks();

    Mono<Book> getBookById(String id);

    Mono<Book> saveBook(Book book);

    Mono<Book> updateBook(String id, Book book);

    Mono<Book> deleteBook(String id);

    Mono<Book> findBookName(String name);

    Mono<String> borrowBook(String id);

    Mono<String> returnBook(String id);

    Flux<Book> findBooksCategory(String category);

}
