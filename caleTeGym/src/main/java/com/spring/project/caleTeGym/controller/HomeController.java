package com.spring.project.caleTeGym.controller;

import java.util.ArrayList;
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
import com.spring.project.caleTeGym.entity.InComingMail;
import com.spring.project.caleTeGym.entity.OutComingMail;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.FriendRequestService;
import com.spring.project.caleTeGym.service.InComingMailService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class HomeController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	FriendRequestService friendRequestService;
	
	@Autowired
	InComingMailService inComingMailService;
	
	@RequestMapping(value={"/show_friends"}, method = RequestMethod.GET)
	 public ModelAndView showFriends()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		List<User> users = new ArrayList<User>();
		users.addAll(user.getFriends());
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.setViewName("friends");
		modelAndView.addObject("users", users);
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/show_people"}, method = RequestMethod.GET)
	 public ModelAndView showPeople()
	{
		List<User> notFriends = userService.getNotFriends();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("friends");
		modelAndView.addObject("users", notFriends);
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/show_inbox"}, method = RequestMethod.GET)
	 public ModelAndView showInbox()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<InComingMail> inComingMails = user.getInComingMails();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("inbox");
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("inComingMails", inComingMails);
		return modelAndView;
	}
	
	@RequestMapping(value={"/showProfile"}, method = RequestMethod.GET)
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
	
	@RequestMapping(value={"/showMyProfile"}, method = RequestMethod.GET)
	 public ModelAndView showMyProfile()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("profile");
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/accept_friend_request"}, method = RequestMethod.GET)
	 public ModelAndView acceptFriendRequest(@RequestParam("friendRequestId") int friendRequestId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		FriendRequest friendRequest = friendRequestService.getRequest(friendRequestId);
		User sender = friendRequest.getSender();
		User addressee = friendRequest.getAddressee();
		sender.addFriend(addressee);
		user.addFriend(sender);
		friendRequestService.deleteRequest(friendRequestId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/home");
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/discard_friend_request"}, method = RequestMethod.GET)
	 public ModelAndView discardFriend_request(@RequestParam("friendRequestId") int friendRequestId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		FriendRequest friendRequest = friendRequestService.getRequest(friendRequestId);
		user.deleteFriendRequest(friendRequest);
		friendRequestService.deleteRequest(friendRequestId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/home");
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/show_out_coming_inbox"}, method = RequestMethod.GET)
	 public ModelAndView showOutComingInbox()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<OutComingMail> outComingMails = user.getOutComingMails();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.setViewName("out_coming_inbox");
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("outComingMails", outComingMails);
		return modelAndView;
	}
}
