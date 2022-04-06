package com.myke.libraryreactive.route;

import com.myke.libraryreactive.model.Book;
import com.myke.libraryreactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class BookRouter {

    @Autowired
    private BookService bookService;

    @Bean
    public RouterFunction<ServerResponse> bookRoute(){
        return RouterFunctions.route(GET("/book"),
                request -> {
                    Flux<Book> books = bookService.getBooks();
                    return ServerResponse.ok()
                            .body(BodyInserters.fromPublisher(books, Book.class));
                });
    }

}
