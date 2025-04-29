package com.example.bookcatalog.controller;

import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Controller", description = "Operations pertaining to books in the catalog")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books", description = "Returns a list of all books in the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved books",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) })
    })
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get paginated books", description = "Returns a paginated list of books with sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated books",
                    content = { @Content(mediaType = "application/json") })
    })
    @GetMapping("/paginated")
    public ResponseEntity<Page<Book>> getPaginatedBooks(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field") @RequestParam(defaultValue = "id") String sort,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "asc") String direction) {

        Page<Book> books = bookService.findBooksPaginated(page, size, sort, direction);
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get a book by ID", description = "Returns a single book identified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "ID of the book to be retrieved") @PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @Operation(summary = "Get a book by ISBN", description = "Returns a single book identified by its ISBN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(
            @Parameter(description = "ISBN of the book to be retrieved") @PathVariable String isbn) {
        Book book = bookService.findBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @Operation(summary = "Search books", description = "Returns books matching the search keyword in title or author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved books",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) })
    })
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @Parameter(description = "Search keyword") @RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Find books by publication year", description = "Returns books published in a specific year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved books",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) })
    })
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Book>> getBooksByYear(
            @Parameter(description = "Publication year") @PathVariable int year) {
        List<Book> books = bookService.findBooksByYear(year);
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Create a new book", description = "Adds a new book to the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Book> createBook(
            @Parameter(description = "Book object to be added") @Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a book", description = "Updates an existing book in the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "ID of the book to be updated") @PathVariable Long id,
            @Parameter(description = "Updated book object") @Valid @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @Operation(summary = "Delete a book", description = "Removes a book from the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to be deleted") @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}