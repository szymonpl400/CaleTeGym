package com.spring.project.caleTeGym.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.caleTeGym.entity.Event;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.EventService;
import com.spring.project.caleTeGym.service.UserService;

@Controller 
public class EventController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	EventService eventService;
	
	@ResponseBody
	@RequestMapping(value={"/save_event"}, method = RequestMethod.POST)
	public void save_event(@RequestBody String data, HttpServletRequest request)
	{
		JSONObject jsonObject = new JSONObject();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		try {
			jsonObject = new JSONObject(data);
			
			String content = jsonObject.getString("content").toString();
			
			String[] arrOfStr = jsonObject.get("date").toString().split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(arrOfStr[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(arrOfStr[1]) -1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arrOfStr[2]));
			Date date = cal.getTime();
			
			Event event = new Event();
			event.setContent(content);
			event.setDate(date);
			user.getEvents().add(event);
			
			eventService.save(event);
			userService.saveOnlyUser(user);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"/show_events"}, method = RequestMethod.GET)
	public String show_events() throws JsonProcessingException 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		user.getEvents();
		List<Event> events = new ArrayList<Event>();
		for(Event event : user.getEvents()) {
			if(isToday(event.getDate())) {
				events.add(event);
			}
		}
		String dietJson = "";
		ObjectMapper mapper = new ObjectMapper();
	    
		//Converting the Object to JSONString
		dietJson = mapper.writeValueAsString(events);
	    System.out.println(events);
		
		return dietJson;
	}
	
	public boolean isToday(Date date) {
		 
		 Date todaysDate = new Date();
		 
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(todaysDate);
		 int todayYear = calendar.get(Calendar.YEAR);
		 int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
		 int todayMonth = calendar.get(Calendar.MONTH);
		 
		 calendar.setTime(date);
		 
		 int choosenDateYear = calendar.get(Calendar.YEAR);
		 int choosenDateDay = calendar.get(Calendar.DAY_OF_MONTH);
		 int choosenDateMonth = calendar.get(Calendar.MONTH);
		 
		 return todayYear == choosenDateYear && todayDay == choosenDateDay && todayMonth == choosenDateMonth;				 
	 }
}
