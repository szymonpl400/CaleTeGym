package com.spring.project.caleTeGym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> 
{
	User findByEmail(String email);
	List<User> findAll();
	List<User> findByNameAndLastName(String name, String lastName);
	List<User> findByName(String name);
}
