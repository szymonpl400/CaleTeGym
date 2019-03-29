package com.spring.project.caleTeGym.controller;

import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class UserController 
{
	@Autowired
	UserService userService;

	
	@RequestMapping(value={"/find_user"}, method = RequestMethod.GET)
	 public ModelAndView acceptFriendRequest(@RequestParam("nameAndLastName") String nameAndLastName)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<User> users=null;
		try 
		{
			int firstSpace = nameAndLastName.indexOf(" ");
			String firstName = nameAndLastName.substring(0, firstSpace);
			String lastName = nameAndLastName.substring(firstSpace).trim();
			users = userService.findByNameAndLastName(firstName, lastName);
		}
		catch(Exception ex)
		{
			users = userService.findByName(nameAndLastName);
		}
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);

		
		if(users.isEmpty()) 
		{
			modelAndView.setViewName("redirect:/home");
		}
		else 
		{
			modelAndView.addObject("users", users);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("searched_people");
		}
		return modelAndView;
	}
}
