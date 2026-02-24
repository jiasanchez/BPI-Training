package bpi.m8activity6;

import org.springframework.stereotype.Component;

@Component
public class SingletonService {
	public SingletonService() {
		System.out.println("SingletonService Created");
	}
}
