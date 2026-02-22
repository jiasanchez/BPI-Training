package bpi.m8activity1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class M8activity1Application {

	public static void main(String[] args) {
		SpringApplication.run(M8activity1Application.class, args);
	}
		//Activity 1
		@Bean
		CommandLineRunner run(BookService bookService) {
			return args -> {
				bookService.createBook("Test Demo");
			};
		
	}

}
