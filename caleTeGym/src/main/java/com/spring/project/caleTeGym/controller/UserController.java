package com.spring.project.caleTeGym.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class UserController 
{
	@Autowired
	UserService userService;

	
	@RequestMapping(value={"/find_user"}, method = RequestMethod.GET)
	 public ModelAndView acceptFriendRequest(@RequestParam("nameAndLastName") String nameAndLastName)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
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
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("friendRequests", friendRequests);
		modelAndView.addObject("user", user);
		
		if(users.isEmpty()) 
		{
			modelAndView.setViewName("redirect:/home");
		}
		else 
		{
			modelAndView.addObject("users", users);
			modelAndView.setViewName("searched_people");
		}
		return modelAndView;
	}
	@ResponseBody
	@PostMapping("/uploadImage")
	public ModelAndView uploadImage(@RequestParam("imageFile")MultipartFile imageFile) {
	    
		ModelAndView modelAndView = new ModelAndView();
		try {
			saveImage(imageFile);
			System.out.println();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/showMyProfile");
	    return modelAndView;
	}
	
	public void saveImage(MultipartFile imageFile) throws IOException{
		byte[] bytes = imageFile.getBytes();
		String path = "src/main/resources/static/images/";
		 
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		 
		System.out.println(absolutePath);
		Path filePath = Paths.get(absolutePath + "/" + imageFile.getOriginalFilename());
		Files.write(filePath, bytes);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		user.setPhoto("/images/" + imageFile.getOriginalFilename());
		userService.saveOnlyUser(user);
		
	}
}
