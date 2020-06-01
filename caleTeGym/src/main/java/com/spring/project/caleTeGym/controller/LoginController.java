package com.spring.project.caleTeGym.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.Group;
import com.spring.project.caleTeGym.entity.GroupRequest;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class LoginController 
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error){
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) 
        {
          modelAndView.setViewName("error page");
        } 
        else
        {
        	modelAndView.setViewName("login");
        }

        return modelAndView;
    }
	
	@RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login.html";
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
		System.out.println(bindingResult);
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
	
	@GetMapping("/home")
	public ModelAndView home() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		Set<User> friends = user.getFriends();
		Set<FriendRequest> friendRequests = user.getInComingFriendRequests();
		List<Group> groups = user.getGroups();
		List<GroupRequest> groupRequests = user.getGroupRequests();
		for(GroupRequest request: groupRequests) 
		{
			System.out.println(request);
		}
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("groups", groups);
		modelAndView.setViewName("home");
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("groupRequests", groupRequests);
		return modelAndView;
	}
	
}
