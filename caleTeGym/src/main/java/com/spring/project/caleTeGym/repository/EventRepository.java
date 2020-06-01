package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.Event;
import com.spring.project.caleTeGym.entity.Product;
@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event, Integer> {

}
