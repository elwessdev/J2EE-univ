package com.example.bookcatalog.service.impl;

import com.example.bookcatalog.exception.ResourceNotFoundException;
import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.repository.BookRepository;
import com.example.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        // Check if a book with the same ISBN already exists
        bookRepository.findByIsbn(book.getIsbn()).ifPresent(existingBook -> {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
        });

        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = findBookById(id);

        // If ISBN is being changed, check if the new ISBN already exists on another book
        if (!book.getIsbn().equals(bookDetails.getIsbn())) {
            bookRepository.findByIsbn(bookDetails.getIsbn()).ifPresent(existingBook -> {
                if (!existingBook.getId().equals(id)) {
                    throw new IllegalArgumentException("Book with ISBN " + bookDetails.getIsbn() + " already exists");
                }
            });
        }

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationDate(bookDetails.getPublicationDate());
        book.setDescription(bookDetails.getDescription());
        book.setPageCount(bookDetails.getPageCount());

        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findBooksPaginated(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByYear(int year) {
        return bookRepository.findBooksByPublicationYear(year);
    }
}