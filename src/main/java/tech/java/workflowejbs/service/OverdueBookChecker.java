package tech.java.workflowejbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.java.workflowejbs.entity.Borrowing;
import tech.java.workflowejbs.entity.Member;
import tech.java.workflowejbs.repository.BorrowingRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OverdueBookChecker {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 1 * * *")
    public void checkForOverdueBooks() {
        List<Borrowing> overdueBorrowings = borrowingRepository
                .findByDueDateLessThanEqualAndReturnedDateIsNull(new Date());
        for (Borrowing b : overdueBorrowings) {
            Map<String, Object> borrowingMap = new HashMap<>();

            borrowingMap.put("borrowingId", b.getBorrowingId());
            borrowingMap.put("borrowerId", b.getBorrower().getMemberId());
            borrowingMap.put("email", b.getBorrower().getEmail());
            borrowingMap.put("isbn", b.getBook().getIsbn());
            borrowingMap.put("dueDate", b.getDueDate().toString());
            emailService.sendBorrowingNotification(borrowingMap);
        }
    }
}
