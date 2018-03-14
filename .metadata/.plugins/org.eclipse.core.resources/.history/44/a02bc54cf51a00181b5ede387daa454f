package com.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quiz.dao.QuestionRepository;
import com.quiz.dao.UsersRepository;
import com.quiz.entity.Question;
import com.quiz.entity.Users;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private QuestionRepository qrep ; 
	
	@Autowired
	private UsersRepository userep ; 
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Question q = qrep.save(new Question("23", "23"));
		Users u = userep.save(new Users("amine","psw"));
	}
}
