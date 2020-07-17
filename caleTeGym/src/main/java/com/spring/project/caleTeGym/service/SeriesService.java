package com.spring.project.caleTeGym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Series;
import com.spring.project.caleTeGym.repository.SeriesRepository;

@Service
public class SeriesService {
	@Autowired
	SeriesRepository seriesRepository;
	
	public void saveOneSeries(Series series) {
		seriesRepository.save(series);
	}
	
	public void saveAllSeries(List<Series> series) {
		seriesRepository.saveAll(series);
	}
}
