package com.example.bookcatalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @Size(max = 200, message = "Author name cannot exceed 200 characters")
    @Column(length = 200)
    private String author;

    @Pattern(regexp = "^\\d{10}|\\d{13}$", message = "ISBN must be 10 or 13 digits")
    @Column(length = 20, unique = true)
    private String isbn;

    @Past(message = "Publication date must be in the past")
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(value = 1, message = "Page count must be at least 1")
    @Column(name = "page_count")
    private Integer pageCount;

    // Default constructor (required by JPA)
    public Book() {
    }

    // Constructor with fields
    public Book(String title, String author, String isbn, LocalDate publicationDate,
                String description, Integer pageCount) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.description = description;
        this.pageCount = pageCount;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    // equals, hashCode, toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", pageCount=" + pageCount +
                '}';
    }
}