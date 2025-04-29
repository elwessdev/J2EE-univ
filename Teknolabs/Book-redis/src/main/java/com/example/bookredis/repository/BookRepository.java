package com.example.bookredis.repository;

import com.example.bookredis.model.Book;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {

    private final Map<Long, Book> bookStore = new HashMap<>();

    @PostConstruct
    public void init() {
        // Populate with sample data
        bookStore.put(1L, new Book(1L, "To Kill a Mockingbird", "Harper Lee", "978-0061120084", 14.99));
        bookStore.put(2L, new Book(2L, "1984", "George Orwell", "978-0451524935", 12.99));
        bookStore.put(3L, new Book(3L, "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 11.99));
        bookStore.put(4L, new Book(4L, "Pride and Prejudice", "Jane Austen", "978-0141439518", 9.99));
        bookStore.put(5L, new Book(5L, "The Catcher in the Rye", "J.D. Salinger", "978-0316769488", 10.99));
    }

    public Book findById(Long id) {
        // Simulate database latency
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Fetching book with ID: " + id + " from database");
        return bookStore.get(id);
    }

    public Book save(Book book) {
        // Simulate database latency
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        bookStore.put(book.getId(), book);
        return book;
    }

    public void delete(Long id) {
        bookStore.remove(id);
    }
}