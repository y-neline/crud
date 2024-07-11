package com.example.crud.repo;

import com.example.crud.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {



}
