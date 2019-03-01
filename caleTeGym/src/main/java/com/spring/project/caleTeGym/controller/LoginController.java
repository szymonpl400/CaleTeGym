package com.spring.project.caleTeGym.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class LoginController 
{
	@Autowired
	private UserService userService;
	
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
	}
	
	@GetMapping("/registration")
	public ModelAndView showRegistrationPage() 
	{
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@PostMapping("/registration")
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) 
	{
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		
		if(userExists != null) 
		{
			bindingResult
				.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		
		if(bindingResult.hasErrors()) 
		{
			modelAndView.setViewName("registration");
		}
		else 
		{
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered succesfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/admin/home")
	public ModelAndView home() 
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userMessage", "Welcome" + user.getName() + " " + user.getLastName() + " " + "(" + user.getEmail()+")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role ;)");
		modelAndView.setViewName("admin_home");
		return modelAndView;
	}
}
