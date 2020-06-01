package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.Group;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, Integer> 
{

}
