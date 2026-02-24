package bpi.m8activity3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class M8activityApplication {

	public static void main(String[] args) {
		SpringApplication.run(M8activityApplication.class, args);
	}
		//Activity 3
		@Bean
		CommandLineRunner run(BookService bookService) {
			return args -> {
				bookService.createBook("Test Demo");
			};
		
	}

}
