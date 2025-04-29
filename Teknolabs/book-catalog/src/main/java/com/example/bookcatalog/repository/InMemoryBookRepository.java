package com.example.bookcatalog.repository;

import com.example.bookcatalog.model.Book;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public InMemoryBookRepository() {
        // Add some sample data
        Book book1 = new Book(
                idGenerator.getAndIncrement(),
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                LocalDate.of(2008, 8, 1),
                464
        );

        Book book2 = new Book(
                idGenerator.getAndIncrement(),
                "Design Patterns",
                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides",
                "9780201633610",
                LocalDate.of(1994, 11, 10),
                416
        );

        Book book3 = new Book(
                idGenerator.getAndIncrement(),
                "The Pragmatic Programmer",
                "Andrew Hunt, David Thomas",
                "9780201616224",
                LocalDate.of(1999, 10, 30),
                352
        );

        books.put(book1.getId(), book1);
        books.put(book2.getId(), book2);
        books.put(book3.getId(), book3);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            // New book
            book.setId(idGenerator.getAndIncrement());
        }
        books.put(book.getId(), book);
        return book;
    }

    @Override
    public void delete(Long id) {
        books.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return books.containsKey(id);
    }
}