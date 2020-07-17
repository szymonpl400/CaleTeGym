package com.spring.project.caleTeGym.repository;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.Role;
import com.spring.project.caleTeGym.entity.Training;
import com.spring.project.caleTeGym.entity.User;
@Repository("trainingRepository")
public interface TrainingRepository extends JpaRepository<Training, Integer> {

	

}
