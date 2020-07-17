package com.spring.project.caleTeGym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Series {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int seriesId;
	
	@Column(name="reps")
	private int reps;
	
	@Column(name="weight")
	private float weight;
	
	@Column(name="time")
	private int time;

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public Series() {
	}

	public Series(int reps, float weight, int time) {
		this.reps = reps;
		this.weight = weight;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Series [seriesId=" + seriesId + ", reps=" + reps + ", weight=" + weight + ", time="
				+ time + "]";
	}
	
}
