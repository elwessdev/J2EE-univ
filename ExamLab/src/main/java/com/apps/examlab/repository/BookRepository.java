package com.apps.examlab.repository;

import com.apps.examlab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    public Book findByIsbn(String isbn);
}
