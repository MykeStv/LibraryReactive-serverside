package com.myke.libraryreactive.repository;

import com.myke.libraryreactive.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findByName(String name);
}
