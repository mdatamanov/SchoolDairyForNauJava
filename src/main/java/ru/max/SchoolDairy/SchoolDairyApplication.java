package ru.max.SchoolDairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SchoolDairyApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SchoolDairyApplication.class, args);

    }

}
