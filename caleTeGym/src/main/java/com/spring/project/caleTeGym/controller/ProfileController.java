package com.spring.project.caleTeGym.controller;

import java.util.Date;

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

import com.spring.project.caleTeGym.entity.Group;
import com.spring.project.caleTeGym.entity.Post;
import com.spring.project.caleTeGym.entity.TrainerRequest;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.TrainerRequestService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TrainerRequestService trainerRequestService;
	
	@ResponseBody
	@RequestMapping(value={"/setUserInfo"}, method = RequestMethod.POST)
	 public void like(@RequestBody String data, HttpServletRequest request)
	{	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		JSONObject jsonObject = new JSONObject();
		try {
			 jsonObject = new JSONObject(data);
		     int age =(Integer.parseInt(jsonObject.get("age").toString()));
		     int weight =(Integer.parseInt(jsonObject.get("weight").toString()));
		     int growth =(Integer.parseInt(jsonObject.get("growth").toString()));
		     float bmi =(Float.parseFloat(jsonObject.get("bmi").toString()));
		     String sex =jsonObject.get("sex").toString();
		     int caloricDemain =(Integer.parseInt(jsonObject.get("caloricDemand").toString()));
		     
		     user.setAge(age);
		     user.setWeight(weight);
		     user.setGrowth(growth);
		     user.setBmi(bmi);
		     user.setSex(sex);
		     user.setCaloricDemand(caloricDemain);
		     userService.saveOnlyUser(user);
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
	
 	@ResponseBody
	@RequestMapping(value={"/createTrainerRequest"}, method = RequestMethod.POST)
	public void createTrainerRequest(@RequestBody String data, HttpServletRequest request)
	{	
		
		JSONObject jsonObject = new JSONObject();
		try {
			
			 jsonObject = new JSONObject(data);
		     int trainerId = Integer.parseInt(jsonObject.get("trainer").toString());
		     int pupilId = Integer.parseInt(jsonObject.get("pupil").toString());
		     User trainer = userService.getOne(trainerId);
		     User pupil = userService.getOne(pupilId);
		     Date date = new Date();
		     TrainerRequest trainerRequest = new TrainerRequest();
		     
		     trainerRequest.setDate(date);
		     trainerRequest.setPupil(pupil);
		     trainerRequest.setTrainer(trainer);
		     pupil.getTrainerRequests().add(trainerRequest);
		     userService.saveOnlyUser(pupil);
		     trainerRequestService.saveRequest(trainerRequest);
		     
		}catch (JSONException err){
		    System.out.println(err);
		}
	}
 	
 	@ResponseBody
	@RequestMapping(value={"/stopTraining"}, method = RequestMethod.POST)
	public void stopTraining(@RequestBody String data, HttpServletRequest request)
	{			
		JSONObject jsonObject = new JSONObject();
		try {
			
			 jsonObject = new JSONObject(data);
		     int trainerId = Integer.parseInt(jsonObject.get("trainer").toString());
		     int pupilId = Integer.parseInt(jsonObject.get("pupil").toString());
		     User trainer = userService.getOne(trainerId);
		     User pupil = userService.getOne(pupilId);
		     trainer.getPupils().remove(pupil);
		     pupil.getTrainers().remove(trainer);
		     userService.saveOnlyUser(pupil);
		     userService.saveOnlyUser(trainer);
		     
		}catch (JSONException err){
		    System.out.println(err);
		}
	}

 	@ResponseBody
	@RequestMapping(value={"/show_calendar"}, method = RequestMethod.GET)
	public ModelAndView show_calendar()
	{			
 		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("calendar");
		
		return modelAndView;
	}
 	
 	@ResponseBody
	@RequestMapping(value={"/show_target"}, method = RequestMethod.GET)
	public ModelAndView show_target()
	{			
 		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("target");
		
		return modelAndView;
	}
}
