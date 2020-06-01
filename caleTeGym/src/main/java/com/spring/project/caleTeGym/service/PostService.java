package com.spring.project.caleTeGym.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.FriendRequest;
import com.spring.project.caleTeGym.entity.Post;
import com.spring.project.caleTeGym.repository.PostRepository;

@Service
public class PostService 
{
	@Autowired
	PostRepository postRepository;
	
	public void save(Post post) 
	{
		postRepository.save(post);
	}
	
	public Post getPost(int id) 
	{
		return postRepository.getOne(id);
	}
}
