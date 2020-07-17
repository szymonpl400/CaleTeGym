package com.spring.project.caleTeGym.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeId;

@Entity
public class Training {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="trainer", referencedColumnName="id")
	@JsonIgnore
	private User trainer;
	
	@OneToOne
	@JoinColumn(name="pupil", referencedColumnName="id")
	@JsonIgnore
	private User pupil;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name="date")
	private Date date;
	
	@Column(name="trainerName")
	private String trainerName;
	
	@ManyToMany
	@JoinTable(name = "training_exercise", joinColumns = @JoinColumn(name = "training_id"), inverseJoinColumns = @JoinColumn(name = "exercise_id"))
	private List<Exercise> exercises;

	public Training() {
	}

	public Training(User trainer, User pupil, Date date, String trainerName, List<Exercise> exercises) {
		this.trainer = trainer;
		this.pupil = pupil;
		this.date = date;
		this.trainerName = trainerName;
		this.exercises = exercises;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public User getPupil() {
		return pupil;
	}

	public void setPupil(User pupil) {
		this.pupil = pupil;
	}
	

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", trainer=" + trainer + ", exercises=" + exercises + "]";
	}

	
	
}
