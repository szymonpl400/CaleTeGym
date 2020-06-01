package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.Meal;
@Repository("mealRepository")
public interface MealRepository extends JpaRepository<Meal, Integer> {

}
