package tech.java.workflowejbs.service;

import tech.java.workflowejbs.entity.Borrowing;

import java.util.List;

public interface BorrowingService {
    Borrowing borrowBook(Long memberId, String isbn);
    Borrowing returnBook(Long borrowingId);
    List<Borrowing> getAllBorrowingsByMemberId(Long memberId);
}
