package com.spring.project.caleTeGym.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class GroupController 
{
	@Autowired
	UserService userService;
	
	@GetMapping("/show_group_form")
	public ModelAndView showGroupForm() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("group_creator");
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		return modelAndView;
	}
}
