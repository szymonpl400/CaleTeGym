package com.spring.project.caleTeGym.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.Group;
import com.spring.project.caleTeGym.entity.GroupRequest;
import com.spring.project.caleTeGym.entity.Post;
import com.spring.project.caleTeGym.entity.TrainerRequest;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.GroupRequestService;
import com.spring.project.caleTeGym.service.GroupService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class GroupController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired 
	GroupRequestService groupRequestService;
	
	@GetMapping("/show_group_form")
	public ModelAndView showGroupForm() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		ModelAndView modelAndView = new ModelAndView();
		Group group = new Group();
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("group", group);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.setViewName("group_creator");
		return modelAndView;
	}
	
	@PostMapping("/create_group")
	public ModelAndView createGroup(Group group, BindingResult bindingResult)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		List<Post> posts = new ArrayList<Post>();
		group.setPosts(posts);
		List<Group> groups = user.getGroups();
		ModelAndView modelAndView = new ModelAndView();
		group.setOwner(user);
		user.addGroup(group);
		ArrayList<User> members = new ArrayList<User>();
		members.add(user);
		group.setMembers(members);
		groupService.save(group);
		List<GroupRequest> groupRequests = user.getGroupRequests();
		userService.saveOnlyUser(user);
		modelAndView.addObject("user", user);
		modelAndView.addObject("groups", groups);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("groupRequests", groupRequests);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value={"/show_group_page"}, method = RequestMethod.GET)
	 public ModelAndView showGroupPage(@RequestParam("groupId") int groupId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		List<Group> groups = user.getGroups();
		Group group = groupService.getOne(groupId);
		List<Post> posts = group.getPosts();
		ModelAndView modelAndView = new ModelAndView();
		List<GroupRequest> groupRequests = user.getGroupRequests();
		modelAndView.addObject("groups", groups);
		modelAndView.addObject("group", group);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("user", user);
		modelAndView.addObject("posts", posts);
		modelAndView.addObject("groupRequests", groupRequests);
		
		if(group.getMembers().contains(user))
		{
			modelAndView.setViewName("group_page");
		}
		else 
		{
			modelAndView.setViewName("home");
		}
		return modelAndView;
	}
	
	 @RequestMapping(value={"/show_new_member_form"}, method = RequestMethod.GET)
	 public ModelAndView showNewMemberForm(@RequestParam("groupId") int groupId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		List<Group> groups = user.getGroups();
		Group group = groupService.getOne(groupId);
		ModelAndView modelAndView = new ModelAndView();
		int theGroupId = 0;
		List<GroupRequest> groupRequests = user.getGroupRequests();
		modelAndView.addObject("groups", groups);
		modelAndView.addObject("group", group);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("user", user);
		modelAndView.addObject("group_id", theGroupId);
		modelAndView.addObject("groupRequests", groupRequests);
		if(group.getMembers().contains(user))
		{
			modelAndView.setViewName("new_group_member_page");
		}
		else 
		{
			modelAndView.setViewName("home");
		}
		return modelAndView;
	}
	
		@RequestMapping(value={"/find_potential_member"}, method = RequestMethod.GET)
		public ModelAndView acceptFriendRequest(@RequestParam("nameAndLastName") String nameAndLastName, @RequestParam("groupId") int groupId)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			Group group = groupService.getOne(groupId);
			List<Group> groups = user.getGroups();
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
			List<GroupRequest> groupRequests = user.getGroupRequests();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("friends", friends);
			modelAndView.addObject("friendRequests", friendRequests);
			modelAndView.addObject("groups", groups);
			modelAndView.addObject("groupRequests", groupRequests);

			
			if(users.isEmpty()) 
			{
				modelAndView.setViewName("redirect:/home");
			}
			else 
			{
				
				modelAndView.addObject("group", group);
				modelAndView.addObject("users", users);
				modelAndView.addObject("user", user);
				modelAndView.setViewName("potential_members");
			}
			return modelAndView;
		}
		
		@RequestMapping(value={"/add_member"}, method = RequestMethod.GET)
		public ModelAndView addMember(@RequestParam("userId") int userId, @RequestParam("groupId") int groupId)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			User member = userService.getOne(userId);
			Group group = groupService.getOne(groupId);
			List<Group> groups = user.getGroups();
			GroupRequest groupRequest = new GroupRequest();
			groupRequest.setAddressee(member);
			Date date = new Date();
			groupRequest.setDate(date);
			groupRequest.setGroup(group);
			groupRequest.setSender(user);
			Set<User> friends = user.getFriends();
			Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
			List<GroupRequest> groupRequests = member.getGroupRequests();
			groupRequests.add(groupRequest);
			member.setGroupRequests(groupRequests);
			
			groupRequestService.save(groupRequest);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("friends", friends);
			modelAndView.addObject("friendRequests", friendRequests);
			modelAndView.addObject("groups", groups);	
			modelAndView.addObject("group", group);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("potential_members");
			return modelAndView;
		}
		
		 @RequestMapping(value={"/accept_invitation"}, method = RequestMethod.GET)
		 public ModelAndView sendRequest(@RequestParam("invitationId") int invitationId)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			Set<User> friends = user.getFriends();
			Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
			List<Group> groups = user.getGroups();
			List<GroupRequest> groupRequests = user.getGroupRequests();
			GroupRequest groupRequest = groupRequestService.getOne(invitationId);
			if(!groupRequest.getGroup().getMembers().contains(user)) 
			{
				user.addGroup(groupRequest.getGroup());
				groupRequest.getGroup().addMember(user);
				groupRequestService.deleteRequest(groupRequest);
			}
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("friends", friends);
			modelAndView.addObject("friendRequests", friendRequests);
			modelAndView.addObject("groupRequests", groupRequests);
			modelAndView.addObject("groups", groups);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("home");
			
			
			return modelAndView;
		}
		 
		 @RequestMapping(value={"/show_group_members"}, method = RequestMethod.GET)
		 public ModelAndView showGroupMembers(@RequestParam("groupId") int groupId)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			Set<User> friends = user.getFriends();
			Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
			List<Group> groups = user.getGroups();
			List<GroupRequest> groupRequests = user.getGroupRequests();
			Group group = groupService.getOne(groupId);
			List<User> members = group.getMembers();

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("group", group);
			modelAndView.addObject("user", user);
			modelAndView.addObject("members", members);
			modelAndView.addObject("groups", groups);
			modelAndView.setViewName("group_members_page");
			modelAndView.addObject("friends", friends);
			modelAndView.addObject("friendRequests", friendRequests);
			modelAndView.addObject("groupRequests", groupRequests);
			
			return modelAndView;
		}
		 
		 @ResponseBody
		 @RequestMapping(value={"/nameAndLastname"}, method = RequestMethod.GET)
		 public String nameAndLastname(@RequestParam("userId") int userId)
		 {
		 	User user = userService.getOne(userId);
		       
		 	return user.getName() + ":" + user.getLastName();
		 }
}
