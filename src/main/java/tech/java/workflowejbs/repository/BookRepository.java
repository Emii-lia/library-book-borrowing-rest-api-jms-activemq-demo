package tech.java.workflowejbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tech.java.workflowejbs.entity.Book;
import tech.java.workflowejbs.projections.BookProjection;

import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String isbn);
    List<Book> findByGenreIgnoreCase(String genre);

    @Query("SELECT b FROM Book b")
    List<BookProjection> findAllBook();
}
