package ru.max.SchoolDairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.max.SchoolDairy.config.ConsoleInterface;

@SpringBootApplication
public class SchoolDairyApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SchoolDairyApplication.class, args);

        ConsoleInterface consoleInterface = context.getBean(ConsoleInterface.class);

        consoleInterface.start();
	}

}
