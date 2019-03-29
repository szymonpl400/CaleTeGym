package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.OutComingMail;

@Repository("OutComingMailRepository")
public interface OutComingMailRepository extends JpaRepository<OutComingMail, Integer> 
{
	OutComingMail findById(int id);
}



