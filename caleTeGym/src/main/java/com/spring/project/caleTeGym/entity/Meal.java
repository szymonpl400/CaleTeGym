package com.spring.project.caleTeGym.entity;


import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
@Entity
public class Meal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date date;
	
	@ManyToMany
	@JoinTable(name = "meal_product", joinColumns = @JoinColumn(name = "meal_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
	
	@OneToOne
	@JoinColumn(name="trainer", referencedColumnName="id")
	private User trainer;

	public Meal(int id, Date date, List<Product> products, User trainer) {
		super();
		this.id = id;
		this.date = date;
		this.products = products;
		this.trainer = trainer;
	}

	public Meal() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	
}
