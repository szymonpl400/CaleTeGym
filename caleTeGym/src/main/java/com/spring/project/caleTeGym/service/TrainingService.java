package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Training;
import com.spring.project.caleTeGym.repository.TrainingRepository;



@Service
public class TrainingService {
	
	@Autowired
	TrainingRepository trainingRepository;

	public void saveTraining(Training training) 
	{
		trainingRepository.save(training);
	}
}
