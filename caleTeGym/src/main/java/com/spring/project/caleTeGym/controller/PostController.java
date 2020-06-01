package com.spring.project.caleTeGym.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.caleTeGym.entity.Comment;
import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.Group;
import com.spring.project.caleTeGym.entity.GroupRequest;
import com.spring.project.caleTeGym.entity.Post;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.GroupRequestService;
import com.spring.project.caleTeGym.service.GroupService;
import com.spring.project.caleTeGym.service.PostService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class PostController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired 
	GroupRequestService groupRequestService;
	
	@Autowired 
	PostService postService;
	
	@RequestMapping("/show_group_post_form")
	public ModelAndView showGroupPostForm(@RequestParam("groupId") int groupId) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		List<Group> groups = user.getGroups();
		List<GroupRequest> groupRequests = user.getGroupRequests();
		Group group = groupService.getOne(groupId);
		List<User> members = group.getMembers();
		Post post = new Post();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("group", group);
		modelAndView.addObject("user", user);
		modelAndView.addObject("members", members);
		modelAndView.addObject("groups", groups);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("groupRequests", groupRequests);
		modelAndView.addObject("post", post);
		modelAndView.setViewName("group_post_creator");
		return modelAndView;
	}
	
	@PostMapping("/add_post")
	public ModelAndView addPost(@RequestParam("groupId") int groupId, @Valid Post post, BindingResult bindingResult)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Post currentPost = new Post();
		currentPost.setComments(comments);
		currentPost.setContent(post.getContent());
		currentPost.setOwner(user);
		Date date = new Date();
		currentPost.setDate(date);
		ArrayList<User> likes = new ArrayList<User>();
		currentPost.setLikes(likes);
		ArrayList<User> likedPeople = new ArrayList<User>();
		currentPost.setLikes(likes);
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		List<Group> groups = user.getGroups();
		List<GroupRequest> groupRequests = user.getGroupRequests();
		postService.save(currentPost);
		Group group = groupService.getOne(groupId);
		List<Post> posts = group.getPosts();
		posts.add(currentPost);
		postService.save(currentPost);
		group.setPosts(posts);
		groupService.save(group);
		List<User> members = group.getMembers();
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("group", group);
		modelAndView.addObject("user", user);
		modelAndView.addObject("members", members);
		modelAndView.addObject("groups", groups);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("groupRequests", groupRequests);
		modelAndView.setViewName("group_page");
		return modelAndView;

	}
	
	@ResponseBody
	@RequestMapping(value={"/like"}, method = RequestMethod.POST)
	 public void like(@RequestBody String data, HttpServletRequest request)
	{	
		
		JSONObject jsonObject = new JSONObject();
		try {
			
			 jsonObject = new JSONObject(data);
		     Group group = groupService.getOne(Integer.parseInt(jsonObject.get("groupId").toString()));
		     Post post = postService.getPost(Integer.parseInt(jsonObject.get("postId").toString()));
		     if(!post.getLikes().contains(userService.getOne(Integer.parseInt(jsonObject.get("userId").toString())))) {
			     post.getLikes().add(userService.getOne(Integer.parseInt(jsonObject.get("userId").toString())));
			     
		     } else {
		    	 post.getLikes().remove(userService.getOne(Integer.parseInt(jsonObject.get("userId").toString())));
		     }
		     postService.save(post);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/comment"}, method = RequestMethod.POST)
	 public void comment(@RequestBody String data, HttpServletRequest request)
	{	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Comment comment = new Comment();
		
		JSONObject jsonObject = new JSONObject();
		try {
			
			Date date = new Date();
			jsonObject = new JSONObject(data);
			comment.setDate(date);
			comment.setOwner(user);
			comment.setContent(jsonObject.getString("comment"));
			
			Post post = postService.getPost(Integer.parseInt(jsonObject.get("postId").toString()));
			post.getComments().add(comment);
			postService.save(post);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}	
}

















