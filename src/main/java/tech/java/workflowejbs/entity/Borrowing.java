package tech.java.workflowejbs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowingId;
    private Date startDate;
    private Date dueDate;
    private Date returnedDate;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Member borrower;
}
