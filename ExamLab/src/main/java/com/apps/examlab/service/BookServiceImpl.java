package com.apps.examlab.service;

import com.apps.examlab.model.Book;
import com.apps.examlab.repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "book", key = "#isbn")
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @CachePut(value = "book", key = "#book.isbn")
    @CacheEvict(value = "books", allEntries = true)
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @CachePut(value = "book", key = "#isbn")
    @CacheEvict(value = "books", allEntries = true)
    public Book updateBookByIsbn(String isbn, Book book) {
        Book bookG = bookRepository.findByIsbn(isbn);
        bookG.setTitle(book.getTitle());
        bookG.setAuthor(book.getAuthor());
        bookRepository.save(bookG);
        return bookG;
    }

    @CacheEvict(value = {"book", "books"}, key = "#isbn", allEntries = true)
    public void deleteBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        bookRepository.delete(book);
    }
}
