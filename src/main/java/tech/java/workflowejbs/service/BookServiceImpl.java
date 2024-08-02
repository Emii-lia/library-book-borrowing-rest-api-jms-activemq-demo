package tech.java.workflowejbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.java.workflowejbs.entity.Book;
import tech.java.workflowejbs.projections.BookProjection;
import tech.java.workflowejbs.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }

    @Override
    public List<BookProjection> findAllBook() {
        return bookRepository.findAllBook();
    }
}
