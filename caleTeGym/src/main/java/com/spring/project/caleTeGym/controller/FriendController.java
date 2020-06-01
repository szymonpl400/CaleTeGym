package com.spring.project.caleTeGym.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.Group;
import com.spring.project.caleTeGym.entity.Post;
import com.spring.project.caleTeGym.entity.TrainerRequest;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.FriendRequestService;
import com.spring.project.caleTeGym.service.TrainerRequestService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class FriendController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	FriendRequestService friendRequestService;
	
	@Autowired
	TrainerRequestService trainerRequestService;
	
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
		modelAndView.addObject("user", sender);
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
		modelAndView.setViewName("user_profile");
		modelAndView.addObject("user", user);
		modelAndView.addObject("currentUser", currentUser);
		
		return modelAndView;
	 }
	 
	 @RequestMapping(value={"/removeFriend"}, method = RequestMethod.GET)
	 @ResponseBody
	 public void removeFriend(@RequestParam("userId") int userId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.findUserByEmail(auth.getName());
		User user = userService.getOne(userId);
		if(currentUser.getFriends().contains(user)) 
		{
			currentUser.getFriends().remove(user);
			user.getFriends().remove(currentUser);
			userService.saveOnlyUser(user);
			userService.saveOnlyUser(currentUser);
		} 
		else
		{
			for(FriendRequest friendRequest : user.getInComingFriendRequests())
			{
			  	if(friendRequest.getSender().equals(currentUser) && friendRequest.getAddressee().equals(user)) 
			  	{
			  		friendRequestService.deleteRequest(friendRequest.getId());
			  		user.getInComingFriendRequests().remove(friendRequest);
			  		userService.saveOnlyUser(user);
			  	}
			}	
		}
	}
	 
	 @RequestMapping(value={"/show_group_of_friends"}, method = RequestMethod.GET)
	 public ModelAndView showFriends()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.setViewName("group_of_friends");
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}
}
