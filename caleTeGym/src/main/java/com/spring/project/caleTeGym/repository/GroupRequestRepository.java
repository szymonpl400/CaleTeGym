package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.GroupRequest;
@Repository("groupRequestRepository")
public interface GroupRequestRepository extends JpaRepository<GroupRequest, Integer> 
{

}
