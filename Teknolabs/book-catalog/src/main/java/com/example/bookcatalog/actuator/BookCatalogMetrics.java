package com.example.bookcatalog.actuator;

import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.service.BookService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

@Component
public class BookCatalogMetrics {

    private final BookService bookService;
    private final MeterRegistry meterRegistry;

    @Autowired
    public BookCatalogMetrics(BookService bookService, MeterRegistry meterRegistry) {
        this.bookService = bookService;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void registerMetrics() {
        // Total number of books
        Gauge.builder("bookcatalog.books.count",
                        () -> bookService.findAllBooks().size())
                .description("Total number of books in the catalog")
                .register(meterRegistry);

        // Number of books published in the last 10 years
        Gauge.builder("bookcatalog.books.recent",
                        () -> getRecentBooksCount())
                .description("Number of books published in the last 10 years")
                .register(meterRegistry);

        // Average page count
        Gauge.builder("bookcatalog.books.avgpages",
                        () -> getAveragePageCount())
                .description("Average number of pages per book")
                .register(meterRegistry);
    }

    private int getRecentBooksCount() {
        List<Book> allBooks = bookService.findAllBooks();
        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
        return (int) allBooks.stream()
                .filter(book -> book.getPublicationDate().isAfter(tenYearsAgo))
                .count();
    }

    private double getAveragePageCount() {
        List<Book> allBooks = bookService.findAllBooks();
        if (allBooks.isEmpty()) {
            return 0;
        }
        return allBooks.stream()
                .mapToInt(Book::getPageCount)
                .average()
                .orElse(0);
    }
}