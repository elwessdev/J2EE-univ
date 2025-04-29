package com.example.bookcatalog.health;

import com.example.bookcatalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BookCatalogHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Autowired
    public BookCatalogHealthIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        try {
            long bookCount = bookRepository.count();
            return Health.up()
                    .withDetail("book_count", bookCount)
                    .withDetail("message", "Book catalog is operational")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .withDetail("message", "Book catalog is not operational")
                    .build();
        }
    }
}