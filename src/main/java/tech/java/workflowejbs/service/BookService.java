package tech.java.workflowejbs.service;

import tech.java.workflowejbs.entity.Book;
import tech.java.workflowejbs.projections.BookProjection;

import java.util.List;

public interface BookService {
    Book getBookByIsbn(String isbn);
    List<Book> getBooksByGenre(String genre);
    List<BookProjection> findAllBook();
}
