package com.example.bookcatalog.actuator;

import com.example.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BookCatalogHealthIndicator implements HealthIndicator {

    private final BookService bookService;

    @Autowired
    public BookCatalogHealthIndicator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Health health() {
        try {
            // Check if we can access the book repository
            int bookCount = bookService.findAllBooks().size();
            return Health.up()
                    .withDetail("bookCount", bookCount)
                    .withDetail("message", "Book catalog service is running normally")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .withDetail("message", "Book catalog service is not available")
                    .build();
        }
    }
}