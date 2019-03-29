package com.spring.project.caleTeGym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.OutComingMail;
import com.spring.project.caleTeGym.repository.OutComingMailRepository;

@Service
public class OutComingMailService 
{
	@Autowired
	OutComingMailRepository outComingMailRepository;
	
	public OutComingMail getOne(int id)
	{
		return outComingMailRepository.getOne(id);
	}
	
	public List<OutComingMail> findAll()
	{
		return outComingMailRepository.findAll();
	}
	
	public void delete(int id) 
	{
		outComingMailRepository.deleteById(id);
	}
	public void save(OutComingMail outComingMail) 
	{
		outComingMailRepository.save(outComingMail);
	}
}
