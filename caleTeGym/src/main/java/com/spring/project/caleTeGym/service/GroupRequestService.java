package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.GroupRequest;
import com.spring.project.caleTeGym.repository.GroupRequestRepository;


@Service("GroupRequestService")
public class GroupRequestService 
{
	@Autowired
	GroupRequestRepository groupRequestRepository;

	public void save(GroupRequest groupRequest) 
	{
		groupRequestRepository.save(groupRequest);
	}
	
	public GroupRequest getOne(int id) 
	{
		return groupRequestRepository.getOne(id);
	}
	
	public void deleteRequest(GroupRequest groupRequest) 
	{
		groupRequestRepository.delete(groupRequest);
	}
}
