package tech.java.workflowejbs.service;

import jakarta.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.java.workflowejbs.entity.Book;
import tech.java.workflowejbs.entity.Borrowing;
import tech.java.workflowejbs.entity.Member;
import tech.java.workflowejbs.repository.BookRepository;
import tech.java.workflowejbs.repository.BorrowingRepository;
import tech.java.workflowejbs.repository.MemberRepository;

import java.util.*;

@Service
@Transactional
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    public Queue borrowingQueue;

    @Override
    public Borrowing borrowBook(Long memberId, String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        Member member = memberRepository.findById(memberId).orElseThrow();

        assert book != null;

        if (book.getAvailableCopies() > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 7);

            Borrowing borrowing = Borrowing.builder()
                    .borrower(member)
                    .book(book)
                    .startDate(new Date())
                    .dueDate(calendar.getTime())
                    .build();
            Borrowing newBorrowing =  borrowingRepository.save(borrowing);
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            Map<String, Object> borrowingMap = new HashMap<>();

            borrowingMap.put("borrowingId", newBorrowing.getBorrowingId());
            borrowingMap.put("borrowerId", newBorrowing.getBorrower().getMemberId());
            borrowingMap.put("email", newBorrowing.getBorrower().getEmail());
            borrowingMap.put("isbn", newBorrowing.getBook().getIsbn());
            borrowingMap.put("dueDate", newBorrowing.getDueDate().toString());

            jmsTemplate.convertAndSend(borrowingQueue, borrowingMap);
            return newBorrowing;
        }
        return null;
    }

    @Override
    public Borrowing returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId).orElseThrow();
        Book book = bookRepository.findById(borrowing.getBook().getIsbn()).orElseThrow();
        borrowing.setReturnedDate(new Date());
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);
        return borrowingRepository.save(borrowing);
    }

    @Override
    public List<Borrowing> getAllBorrowingsByMemberId(Long memberId) {
        return borrowingRepository.findByBorrower_MemberId(memberId);
    }
}
