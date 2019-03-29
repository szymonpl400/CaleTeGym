package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.FriendRequest;

import com.spring.project.caleTeGym.repository.FriendRequestRepository;

@Service("FriendRequestService")
public class FriendRequestService 
{
	@Autowired
	private FriendRequestRepository friendRequestRepository;
	
	public void saveRequest(FriendRequest friendRequest) 
	{
		friendRequestRepository.save(friendRequest);
	}
	
	public FriendRequest getRequest(int id) 
	{
		return friendRequestRepository.getOne(id);
	}
	
	public void deleteRequest(int id) 
	{
		friendRequestRepository.deleteById(id);
	}
}
