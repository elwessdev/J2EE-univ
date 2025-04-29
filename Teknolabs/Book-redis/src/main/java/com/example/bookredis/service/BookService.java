package com.example.bookredis.service;

import com.example.bookredis.model.Book;
import com.example.bookredis.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add caching annotation - books will be cached by id
    @Cacheable(value = "books", key = "#id")
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    // Update cache when a book is updated
    @CachePut(value = "books", key = "#book.id")
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    // Remove from cache when a book is deleted
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    // Clear entire cache
    @CacheEvict(value = "books", allEntries = true)
    public void clearAllCaches() {
        System.out.println("Clearing all books from cache");
    }
}