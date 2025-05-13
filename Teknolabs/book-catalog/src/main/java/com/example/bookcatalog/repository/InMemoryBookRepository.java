package com.example.bookcatalog.repository;

import com.example.bookcatalog.model.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public InMemoryBookRepository() {
        // Add some sample data
//        Book book1 = new Book(
//                idGenerator.getAndIncrement(),
//                "Clean Code",
//                "Robert C. Martin",
//                "9780132350884",
//                LocalDate.of(2008, 8, 1),
//                464
//        );
//
//        Book book2 = new Book(
//                idGenerator.getAndIncrement(),
//                "Design Patterns",
//                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides",
//                "9780201633610",
//                LocalDate.of(1994, 11, 10),
//                416
//        );
//
//        Book book3 = new Book(
//                idGenerator.getAndIncrement(),
//                "The Pragmatic Programmer",
//                "Andrew Hunt, David Thomas",
//                "9780201616224",
//                LocalDate.of(1999, 10, 30),
//                352
//        );
//
//        books.put(book1.getId(), book1);
//        books.put(book2.getId(), book2);
//        books.put(book3.getId(), book3);
    }

    @Override
    public <S extends Book> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public List<Book> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            // New book
            book.setId(idGenerator.getAndIncrement());
        }
        books.put(book.getId(), book);
        return book;
    }

//    @Override
//    public void delete(Long id) {
//        books.remove(id);
//    }

    @Override
    public boolean existsById(Long id) {
        return books.containsKey(id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<Book> findByTitleContainingIgnoreCase(String title) {
        return List.of();
    }

    @Override
    public List<Book> findByAuthorContainingIgnoreCase(String author) {
        return List.of();
    }

    @Override
    public List<Book> findByPublicationDateAfter(LocalDate date) {
        return List.of();
    }

    @Override
    public List<Book> findByPageCountGreaterThan(Integer pageCount) {
        return List.of();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return List.of();
    }

    @Override
    public List<Book> findBooksByPublicationYear(int year) {
        return List.of();
    }

    @Override
    public Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author, Pageable pageable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Book> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Book> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Book> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Book getOne(Long aLong) {
        return null;
    }

    @Override
    public Book getById(Long aLong) {
        return null;
    }

    @Override
    public Book getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Book> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Book> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Book> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Book, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Book> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }
}