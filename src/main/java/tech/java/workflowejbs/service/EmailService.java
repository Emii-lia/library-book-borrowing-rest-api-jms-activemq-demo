package tech.java.workflowejbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendBorrowingNotification(Map<String, Object> borrowing){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(borrowing.get("email").toString());
            message.setSubject("Book borrowed");
            message.setText("You have borrowed a book with ISBN: "
                    + borrowing.get("isbn").toString() + " due date:"
                    + borrowing.get("dueDate").toString());

            javaMailSender.send(message);
        } catch (Exception e) {
            // Log the exception and handle it appropriately (e.g., retry)
            System.out.println("Error sending email notification: "+ e);
        }
    }
    public void sendOverdueNotification(Map<String, Object> borrowing){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(borrowing.get("email").toString());
            message.setSubject("Overdue Notification");
            message.setText("The book with ISBN: "
                    +borrowing.get("isbn").toString()+" that is due to: "
                    +borrowing.get("dueDate").toString()
                    +" has not been returned yet \n Please return the book to the library"
                    + "in three days as it may cancel your membership in the library");
            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println("Error sending email notification" + e.getMessage());
        }
    }
}
