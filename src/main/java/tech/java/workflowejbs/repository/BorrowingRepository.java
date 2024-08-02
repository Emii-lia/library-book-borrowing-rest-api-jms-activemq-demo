package tech.java.workflowejbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import tech.java.workflowejbs.entity.Borrowing;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RepositoryRestResource
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByBorrower_MemberId(Long memberId);
    List<Borrowing> findByDueDateLessThanEqualAndReturnedDateIsNull(Date dueDate);
}
