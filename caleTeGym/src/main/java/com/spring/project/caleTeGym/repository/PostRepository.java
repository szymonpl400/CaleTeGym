package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.Post;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer> 
{

}
