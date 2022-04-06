package com.myke.libraryreactive.controller;

import com.myke.libraryreactive.model.Book;
import com.myke.libraryreactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Flux<Book> getBooks() {
        return this.bookService.getBooks();
    }

    @GetMapping(path = "/{id}")
    private Mono<Book> findBookId(@PathVariable("id") String id) {
        return this.bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> findBookId(@RequestBody Book book) {
        return this.bookService.saveBook(book);
    }

    @PutMapping(path = "/{id}")
    private Mono<ResponseEntity<Book>> updateBook(@PathVariable("id") String id, @RequestBody Book book) {
        return this.bookService.updateBook(id, book)
                .flatMap(b -> Mono.just(ResponseEntity.ok(b)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping(path = "/{id}/delete")
    private Mono<ResponseEntity<Book>> deleteBook(@PathVariable("id") String id) {
        return this.bookService.deleteBook(id)
                .flatMap(b -> Mono.just(ResponseEntity.ok(b)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping(path = "/{id}/borrow")
    private Mono<String> borrowBook(@PathVariable("id") String id) {
        return this.bookService.borrowBook(id);

    }

    @PutMapping(path = "/{id}/return")
    private Mono<String> returnBook(@PathVariable("id") String id) {
        return this.bookService.returnBook(id);

    }

    @GetMapping(path = "/search")
    private Mono<ResponseEntity<Book>> findBookName(@RequestParam("name") String name) {
        return this.bookService.findBookName(name)
                .flatMap(b -> Mono.just(ResponseEntity.ok(b)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping(path = "/category/{category}")
    private Flux<Book> categoryRecommendations(@PathVariable("category") String category) {
        return this.bookService.findBooksCategory(category);
    }

}
