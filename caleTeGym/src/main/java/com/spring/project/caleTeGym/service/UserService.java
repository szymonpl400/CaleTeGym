package com.spring.project.caleTeGym.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	public List<User> findAll()
	{
		return userRepository.findAll();
	}
	public User getOne(Integer id) 
	{
		return userRepository.getOne(id);
	}
	
	public void saveUser(User user) 
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	public void saveOnlyUser(User user) 
	{
		userRepository.save(user);
	}
	
	public List<User> getNotFriends()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = findUserByEmail(auth.getName());
		
		List<User> allUsers = new ArrayList<User>();
		List<User> friends = new ArrayList<User>();
		List<User> notFriends = new ArrayList<User>();
		
		allUsers.addAll(findAll());
		
		for(User theUser : allUsers) 
		{
			if(user.getFriends().contains(theUser)) 
			{
				friends.add(theUser);
			}
			else 
			{
				if(theUser.equals(user))
				{
					
				}
				else 
				{
					notFriends.add(theUser);
				}
			}
		}
		return notFriends;
	}

	public List<User> findByNameAndLastName(String name, String lastName)
	{
		return userRepository.findByNameAndLastName(name, lastName);
	}
	
	public List<User> findByName(String name)
	{
		return userRepository.findByName(name);
	}
}
