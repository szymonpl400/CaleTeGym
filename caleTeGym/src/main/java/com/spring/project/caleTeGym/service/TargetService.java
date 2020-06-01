package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Event;
import com.spring.project.caleTeGym.entity.Target;
import com.spring.project.caleTeGym.repository.EventRepository;
import com.spring.project.caleTeGym.repository.TargetRepository;


@Service
public class TargetService 
{
	@Autowired
	TargetRepository targetRepository;
	
	public void save(Target target) 
	{
		targetRepository.save(target);
	}
	
	public Target getTarget(int id) 
	{
		return targetRepository.getOne(id);
	}
}
