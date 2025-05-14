package com.apps.examlab.controller;

import com.apps.examlab.model.Book;
import com.apps.examlab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("message", "Books retrieved successfully");
        response.put("data", books);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("message", "Book added successfully");
        response.put("data", savedBook);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Map<String, Object>> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        Map<String, Object> response = new HashMap<>();

        if (book != null) {
            response.put("status", "success");
            response.put("message", "Book retrieved successfully");
            response.put("data", book);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Book with ISBN " + isbn + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Map<String, Object>> updateBookByIsbn(@PathVariable String isbn, @RequestBody Book book) {
        Book existingBook = bookService.getBookByIsbn(isbn);
        Map<String, Object> response = new HashMap<>();

        if (existingBook != null) {
            Book updatedBook = bookService.updateBookByIsbn(isbn, book);
            response.put("status", "success");
            response.put("message", "Book updated successfully");
            response.put("data", updatedBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Book with ISBN " + isbn + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Map<String, Object>> deleteBookByIsbn(@PathVariable String isbn) {
        Book existingBook = bookService.getBookByIsbn(isbn);
        Map<String, Object> response = new HashMap<>();

        if (existingBook != null) {
            bookService.deleteBookByIsbn(isbn);
            response.put("status", "success");
            response.put("message", "Book with ISBN " + isbn + " deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Book with ISBN " + isbn + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
