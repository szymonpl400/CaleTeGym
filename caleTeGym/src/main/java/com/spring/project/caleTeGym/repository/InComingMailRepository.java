package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.InComingMail;

@Repository("inComingMailRepository")
public interface InComingMailRepository extends JpaRepository<InComingMail, Integer> 
{
	InComingMail findById(int id);
}



