package tech.java.workflowejbs.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.java.workflowejbs.entity.Borrowing;
import tech.java.workflowejbs.service.BorrowingService;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing")
@CrossOrigin
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/book/{isbn}/member/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation (summary = "Borrow a book", description = "Create a new borrowing instance")
    @ApiResponse (responseCode = "201", description = "Book borrowed")
    public Borrowing borrowBook(@PathVariable String isbn, @PathVariable Long id){
        return borrowingService.borrowBook(id, isbn);
    }

    @PutMapping("/{borrowingId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation (summary = "Return book", description = "Update returned date of the borrowing")
    @ApiResponse(responseCode = "200", description = "Book returned")
    public Borrowing returnBook(@PathVariable Long borrowingId){
        return borrowingService.returnBook(borrowingId);
    }

    @GetMapping("/member/{id}")
    @Operation (
            summary = "Get borrowing by memberId",
            description = "Get all borrowed book list from a given member"
    )
    @ApiResponse(responseCode = "200", description = "List provided")
    public List<Borrowing> getAllBorrowingsByMemberId(@PathVariable Long id){
        return borrowingService.getAllBorrowingsByMemberId(id);
    }

}
