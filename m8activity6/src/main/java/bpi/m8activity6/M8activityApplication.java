package bpi.m8activity6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class M8activityApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(M8activityApplication.class, args);
		
		//Get Singleton Beans
		SingletonService s1 = context.getBean(SingletonService.class);
		SingletonService s2 = context.getBean(SingletonService.class);
		
		//Get Prototype Beans
		PrototypeService p1 = context.getBean(PrototypeService.class);
		PrototypeService p2 = context.getBean(PrototypeService.class);
		
		System.out.println("Singleton Same?"+ (s1 == s2));
		System.out.println("Prototype Same?" + (p1 == p2));
	}

}
