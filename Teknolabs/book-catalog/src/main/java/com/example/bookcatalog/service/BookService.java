package com.example.bookcatalog.service;

import com.example.bookcatalog.model.Book;
import org.springframework.data.domain.Page;
import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
    Book findBookById(Long id);
    Book findBookByIsbn(String isbn);
    Book createBook(Book book);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
    List<Book> searchBooks(String keyword);
    Page<Book> findBooksPaginated(int page, int size, String sortBy, String direction);
    List<Book> findBooksByYear(int year);
}