package com.spring.project.caleTeGym.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.caleTeGym.entity.Event;
import com.spring.project.caleTeGym.entity.Target;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.TargetService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class TargetController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TargetService targetService;
	
	@ResponseBody
	@RequestMapping(value={"/save_target"}, method = RequestMethod.POST)
	public void save_target(@RequestBody String data, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		try {
			jsonObject = new JSONObject(data);
			String content = jsonObject.get("content").toString();

			Target target = new Target();
			target.setContent(content);
			user.getRemainingTargets().add(target);
			
			targetService.save(target);
			userService.saveOnlyUser(user);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	
	
	@ResponseBody
	@RequestMapping(value={"/show_remaining_targets"}, method = RequestMethod.GET)
	public String show_remaining_targets() throws JsonProcessingException 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		List<Target> remainingTargets = user.getRemainingTargets();

		String data = "";
		ObjectMapper mapper = new ObjectMapper();
	    
		//Converting the Object to JSONString
		data = mapper.writeValueAsString(remainingTargets);
		
		return data;
	}
	
	@ResponseBody
	@RequestMapping(value={"/show_completed_targets"}, method = RequestMethod.GET)
	public String show_completed_targets() throws JsonProcessingException 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		List<Target> completedTargets = user.getCompletedTargets();

		String data = "";
		ObjectMapper mapper = new ObjectMapper();
	    
		//Converting the Object to JSONString
		data = mapper.writeValueAsString(completedTargets);
		
		return data;
	}

	
	@ResponseBody
	@RequestMapping(value={"/complete_target"}, method = RequestMethod.GET)
	public void complete_target(@RequestParam("targetId") String targetId)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Target target = targetService.getTarget(Integer.parseInt(targetId));
		
		user.getCompletedTargets().add(target);
		user.getRemainingTargets().remove(target);
		userService.saveOnlyUser(user);
	}
}
