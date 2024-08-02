package tech.java.workflowejbs.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.java.workflowejbs.entity.Book;
import tech.java.workflowejbs.projections.BookProjection;
import tech.java.workflowejbs.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{isbn}")
    @CrossOrigin(origins = "http://localhost", methods = RequestMethod.GET)
    @Operation (summary = "Get book by isbn", description = "Returns a book by isbn")
    @ApiResponse (responseCode = "200", description = "Successful retrieval of the book")
    @ApiResponse (responseCode = "404", description = "Book not found")
    public Book getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping("/projection")
    @CrossOrigin(origins = "http://localhost", methods = RequestMethod.GET)
    @Operation (summary = "Get all books", description = "Returns a list of all books")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of books")
    public List<BookProjection> findAllBook(){
        return bookService.findAllBook();
    }

    @GetMapping("/genre/{genre}")
    @CrossOrigin(origins = "http://localhost", methods = RequestMethod.GET)
    @Operation(summary = "Get books by genre", description = "Returns a list of all books by genre")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of books")
    @ApiResponse(responseCode = "404", description = "Books not found")
    public List<Book> getBooksByGenre(@PathVariable String genre){
        return bookService.getBooksByGenre(genre);
    }
}