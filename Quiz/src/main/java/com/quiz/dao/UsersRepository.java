package com.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	
}
