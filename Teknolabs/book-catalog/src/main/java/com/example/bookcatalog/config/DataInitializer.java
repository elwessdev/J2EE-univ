package com.example.bookcatalog.config;

import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    @Profile("!prod") // This bean will not be loaded in production
    public CommandLineRunner loadData(BookRepository bookRepository) {
        return args -> {
            // Only initialize if the database is empty
            if (bookRepository.count() == 0) {
                bookRepository.save(new Book(
                        "To Kill a Mockingbird",
                        "Harper Lee",
                        "9780061120084",
                        LocalDate.of(1960, 7, 11),
                        "A novel about racial injustice in a small Alabama town during the Great Depression.",
                        281
                ));

                bookRepository.save(new Book(
                        "1984",
                        "George Orwell",
                        "9780451524935",
                        LocalDate.of(1949, 6, 8),
                        "A dystopian novel set in a totalitarian society ruled by the Party, which has total control over every action and thought of the people.",
                        328
                ));

                bookRepository.save(new Book(
                        "The Great Gatsby",
                        "F. Scott Fitzgerald",
                        "9780743273565",
                        LocalDate.of(1925, 4, 10),
                        "A novel that explores themes of decadence, idealism, resistance to change, social upheaval, and excess.",
                        180
                ));

                bookRepository.save(new Book(
                        "The Hobbit",
                        "J.R.R. Tolkien",
                        "9780345339683",
                        LocalDate.of(1937, 9, 21),
                        "A fantasy novel about the adventures of the hobbit Bilbo Baggins.",
                        310
                ));

                bookRepository.save(new Book(
                        "Pride and Prejudice",
                        "Jane Austen",
                        "9780141439518",
                        LocalDate.of(1813, 1, 28),
                        "A romantic novel of manners that follows the character development of Elizabeth Bennet.",
                        432
                ));

                System.out.println("Sample books loaded into database!");
            }
        };
    }
}