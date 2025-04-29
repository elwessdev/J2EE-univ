package com.example.bookcatalog.repository;

import com.example.bookcatalog.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find book by ISBN
    Optional<Book> findByIsbn(String isbn);

    // Find books by title containing a string (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books by author containing a string (case-insensitive)
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Find books published after a certain date
    List<Book> findByPublicationDateAfter(LocalDate date);

    // Find books with page count greater than a value
    List<Book> findByPageCountGreaterThan(Integer pageCount);

    // Custom query to search books by title or author
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBooks(@Param("keyword") String keyword);

    // Custom query to find books published in a specific year
    @Query("SELECT b FROM Book b WHERE YEAR(b.publicationDate) = :year")
    List<Book> findBooksByPublicationYear(@Param("year") int year);

    // Paginated search
    Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title, String author, Pageable pageable);
}