package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Meal;
import com.spring.project.caleTeGym.repository.MealRepository;


@Service
public class MealService 
{
	@Autowired
	MealRepository mealRepository;
	
	public void save(Meal meal) 
	{
		mealRepository.save(meal);
	}
	
	public Meal getMeal(int id) 
	{
		return mealRepository.getOne(id);
	}
}
