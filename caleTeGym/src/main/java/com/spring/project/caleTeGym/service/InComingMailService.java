package com.spring.project.caleTeGym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.InComingMail;
import com.spring.project.caleTeGym.repository.InComingMailRepository;

@Service
public class InComingMailService 
{
	@Autowired
	InComingMailRepository inComingMailRepository;
	
	public InComingMail getOne(int id)
	{
		return inComingMailRepository.getOne(id);
	}
	
	public List<InComingMail> findAll()
	{
		return inComingMailRepository.findAll();
	}
	
	public void delete(int id) 
	{
		inComingMailRepository.deleteById(id);
	}
	
	public void save(InComingMail inComingMail) 
	{
		inComingMailRepository.save(inComingMail);
	}
}
