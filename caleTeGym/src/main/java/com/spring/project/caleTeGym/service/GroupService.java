package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Group;
import com.spring.project.caleTeGym.repository.GroupRepository;

@Service
public class GroupService
{
	@Autowired
	GroupRepository groupRepository;
	
	public void save(Group group) 
	{
		groupRepository.save(group);
	}
	
	public Group getOne(int id) 
	{
		return groupRepository.getOne(id);
	}
}
