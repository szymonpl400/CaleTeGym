package com.spring.project.caleTeGym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.MealService;
import com.spring.project.caleTeGym.service.ProductService;
import com.spring.project.caleTeGym.service.UserService;

@Controller 
public class TrainingController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MealService mealService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value={"/writeTraining"}, method = RequestMethod.GET)
	public ModelAndView writeDiet() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("currentUser", user);
		modelAndView.setViewName("/writeTraining");
		
		return modelAndView;
	}

}
