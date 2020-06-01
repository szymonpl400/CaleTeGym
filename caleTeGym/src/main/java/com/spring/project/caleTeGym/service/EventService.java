package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Event;
import com.spring.project.caleTeGym.repository.EventRepository;


@Service
public class EventService 
{
	@Autowired
	EventRepository eventRepository;
	
	public void save(Event event) 
	{
		eventRepository.save(event);
	}
	
	public Event getEvent(int id) 
	{
		return eventRepository.getOne(id);
	}
}
