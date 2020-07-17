package com.spring.project.caleTeGym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Exercise;
import com.spring.project.caleTeGym.repository.ExerciseRepository;

@Service
public class ExerciseService {
	
	@Autowired
	ExerciseRepository exerciseRepository;

	public void saveExercise(Exercise exercise) {
		exerciseRepository.save(exercise);
	}
	
	public void saveAllExercise(List<Exercise> exercises) {
		exerciseRepository.saveAll(exercises);
	}
}
