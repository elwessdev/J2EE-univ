package com.example.bookcatalog.actuator;

import com.example.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookCatalogInfoContributor implements InfoContributor {

    private final BookService bookService;

    @Autowired
    public BookCatalogInfoContributor(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> bookDetails = new HashMap<>();
        bookDetails.put("totalBooks", bookService.findAllBooks().size());

        // Group books by genre
        Map<String, Long> booksByGenre = new HashMap<>();
        bookService.findAllBooks().forEach(book -> {
            String genre = "Uncategorized"; // Using default genre as the Book class doesn't have a genre field yet
            booksByGenre.put(genre, booksByGenre.getOrDefault(genre, 0L) + 1);
        });
        bookDetails.put("booksByGenre", booksByGenre);

        builder.withDetail("bookCatalog", bookDetails);
    }
}