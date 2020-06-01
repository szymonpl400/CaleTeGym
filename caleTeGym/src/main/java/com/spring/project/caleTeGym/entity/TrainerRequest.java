package com.spring.project.caleTeGym.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TrainerRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pupil_id")
	private User pupil;
	
	@OneToOne
    @JoinColumn(name="trainer_id", referencedColumnName="id")
	private User trainer;
	
	@Column(name="date")
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getPupil() {
		return pupil;
	}

	public void setPupil(User pupil) {
		this.pupil = pupil;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TrainerRequest(int id, User pupil, User trainer, Date date) {
		this.id = id;
		this.pupil = pupil;
		this.trainer = trainer;
		this.date = date;
	}
	
	public TrainerRequest() {}

	@Override
	public String toString() {
		return "TrainerRequest [id=" + id + ", pupil=" + pupil + ", trainer=" + trainer + ", date=" + date + "]";
	}
	
	

}
