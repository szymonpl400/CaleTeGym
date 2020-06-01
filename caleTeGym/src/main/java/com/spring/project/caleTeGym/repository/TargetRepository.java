package com.spring.project.caleTeGym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.project.caleTeGym.entity.Target;
@Repository("targetRepository")
public interface TargetRepository extends JpaRepository<Target, Integer> {

}
