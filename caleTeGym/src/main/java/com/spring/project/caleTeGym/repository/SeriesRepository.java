package com.spring.project.caleTeGym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.project.caleTeGym.entity.Series;

@Repository("seriesRepository")
public interface SeriesRepository extends JpaRepository<Series, Integer> {
	
	Series findById(int id);
	List<Series> findAll();

}
