package com.recruitment_portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.recruitment_portal.entities.User;
import com.recruitment_portal.repo.UserRepository;

@EnableCaching
@SpringBootApplication
public class PayrollTaskApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PayrollTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = userRepository.findById(12).get();
		System.out.println(user);
	}
}