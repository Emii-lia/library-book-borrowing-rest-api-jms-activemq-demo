package tech.java.workflowejbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BorrowingListener {
    @Autowired
    private EmailService emailService;

    @JmsListener(destination = "borrowingQueue")
    public void onBorrowing(Map<String, Object> borrowing){

        emailService.sendBorrowingNotification(borrowing);
    }
}