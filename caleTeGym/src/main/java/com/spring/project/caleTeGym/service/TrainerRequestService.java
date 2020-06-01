package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.project.caleTeGym.entity.TrainerRequest;
import com.spring.project.caleTeGym.repository.TrainerRequestRepository;

@Service("TrainerRequestService")
public class TrainerRequestService {

	@Autowired
	private TrainerRequestRepository trainerRequestRepository;
	
	
	public void saveRequest(TrainerRequest trainerRequest) 
	{
		trainerRequestRepository.save(trainerRequest);
	}
	
	public TrainerRequest getRequest(int id) 
	{
		return trainerRequestRepository.getOne(id);
	}
	
	public void deleteRequest(int id) 
	{
		trainerRequestRepository.deleteById(id);
	}
}
