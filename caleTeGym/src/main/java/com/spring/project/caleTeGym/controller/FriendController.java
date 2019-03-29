package com.spring.project.caleTeGym.controller;

import java.util.Date;  
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
import com.spring.project.caleTeGym.service.FriendRequestService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class FriendController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	FriendRequestService friendRequestService;
	
	@RequestMapping(value={"/add_friend"}, method = RequestMethod.GET)
	 public ModelAndView sendRequest(@RequestParam("userId") int addresseeId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User sender = userService.findUserByEmail(auth.getName());
		User addressee = userService.getOne(addresseeId);
		FriendRequest friendRequest = new FriendRequest();
		friendRequest.setAddressee(addressee);
		friendRequest.setSender(sender);
		Date date = new Date();
		friendRequest.setDate(date);
		Set<FriendRequest> outComingRequests = sender.getOutComingFriendRequests();
		outComingRequests.add(friendRequest);
		sender.setOutComingFriendRequests(outComingRequests);
		friendRequestService.saveRequest(friendRequest);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("friends");
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/friend"}, method = RequestMethod.GET)
	 public ModelAndView showProfile(@RequestParam("userId") int userId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.findUserByEmail(auth.getName());
		User user = userService.getOne(userId);
		
		Set<FriendRequest> friendRequests = currentUser.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.setViewName("profile");
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}
}
