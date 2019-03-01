package com.spring.project.caleTeGym.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Role;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.repository.RoleRepository;
import com.spring.project.caleTeGym.repository.UserRepository;

@Service("userService")
public class UserService 
{
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) 
	{
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) 
	{
		return userRepository.findByEmail(email);
	}
	
	public void saveUser(User user) 
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		System.out.println("ROLEEEEEEEEEEE:" + userRole);
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
}
