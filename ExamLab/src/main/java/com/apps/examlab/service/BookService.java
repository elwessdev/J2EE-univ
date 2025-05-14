package com.apps.examlab.service;

import com.apps.examlab.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getAllBooks();
    Book getBookByIsbn(String isbn);
    Book addBook(Book book);
    Book updateBookByIsbn(String isbn, Book book);
    void deleteBookByIsbn(String isbn);
}
