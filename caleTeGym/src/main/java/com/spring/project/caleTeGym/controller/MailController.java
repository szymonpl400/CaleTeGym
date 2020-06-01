package com.spring.project.caleTeGym.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.caleTeGym.entity.InComingMail;
import com.spring.project.caleTeGym.entity.OutComingMail;
import com.spring.project.caleTeGym.entity.User;
import com.spring.project.caleTeGym.service.InComingMailService;
import com.spring.project.caleTeGym.service.OutComingMailService;
import com.spring.project.caleTeGym.service.UserService;

@Controller
public class MailController 
{
	
	@Autowired
	UserService userService;
	
	@Autowired
	InComingMailService inComingMailService;
	
	@Autowired
	OutComingMailService outComingMailService;
	
	@RequestMapping(value={"/reply"}, method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam("mailId") int mailId) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(inComingMailService.getOne(mailId).getAddressee().equals(user))
		{
			InComingMail mail = inComingMailService.getOne(mailId);
			OutComingMail outComingMail = new OutComingMail();
			modelAndView.addObject("message", outComingMail);
			modelAndView.addObject("mail", mail);
			modelAndView.addObject("user", user);
			modelAndView.setViewName("reply_page");
		}
		else 
		{
			modelAndView.setViewName("redirect:/home");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/delete"}, method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("mailId") int mailId) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		InComingMail mail = inComingMailService.getOne(mailId);
		if(mail.getAddressee().equals(user))
		{
			inComingMailService.delete(mailId);
		}
		modelAndView.addObject("user", user);
		modelAndView.setViewName("home");

		return modelAndView;
	}
	
	@RequestMapping(value={"/delete_out_coming_mail"}, method = RequestMethod.GET)
	public ModelAndView deleteOutComingMail(@RequestParam("mailId") int mailId) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		OutComingMail mail = outComingMailService.getOne(mailId);
		if(mail.getSender().equals(user))
		{
			outComingMailService.delete(mailId);
		}
		modelAndView.addObject("user", user);
		modelAndView.setViewName("home");

		return modelAndView;
	}
	
	@RequestMapping(value={"/send_mail"}, method = RequestMethod.POST)
	public ModelAndView sendMail(@Valid OutComingMail outComingMail, BindingResult bindingResult) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		User sender = inComingMailService.getOne(outComingMail.getId()).getSender();
		InComingMail inComingMail = new InComingMail();
		Date date = new Date();
		outComingMail.setDate(date);
		outComingMail.setSender(user);
		outComingMail.setAddressee(sender);
		outComingMail.setId(0);
		
		inComingMail.setAddressee(sender);
		inComingMail.setSender(user);
		inComingMail.setDate(date);
		inComingMail.setContent(outComingMail.getContent());
		inComingMail.setTopic(outComingMail.getTopic());
		
		outComingMailService.save(outComingMail);
		inComingMailService.save(inComingMail);
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(outComingMail);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("home");

		return modelAndView;
	}
	
	@RequestMapping("/show_message_page")
	public ModelAndView sendMessage(@RequestParam("userId") int userId) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User sender = userService.findUserByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		User addressee = userService.getOne(userId);
		OutComingMail outComingMail = new OutComingMail();
		
		modelAndView.addObject("user", sender);
		modelAndView.addObject("message", outComingMail);
		modelAndView.addObject("addressee", addressee);
		modelAndView.addObject("sender", sender);
		modelAndView.setViewName("sending_page");
		return modelAndView;
	}
	
	@RequestMapping(value={"/send_message"}, method = RequestMethod.POST)
	public ModelAndView sendMessage(@Valid OutComingMail outComingMail, BindingResult bindingResult) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		InComingMail inComingMail = new InComingMail();
		Date date = new Date();
		outComingMail.setDate(date);
		outComingMail.setSender(user);
		outComingMail.setId(0);
		
		inComingMail.setAddressee(outComingMail.getAddressee());
		inComingMail.setSender(user);
		inComingMail.setDate(date);
		inComingMail.setContent(outComingMail.getContent());
		inComingMail.setTopic(outComingMail.getTopic());
		
		outComingMailService.save(outComingMail);
		inComingMailService.save(inComingMail);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("home");

		return modelAndView;
	}
}
