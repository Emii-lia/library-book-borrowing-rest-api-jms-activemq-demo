package tech.java.workflowejbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class WorkflowEjbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowEjbsApplication.class, args);
    }

}
