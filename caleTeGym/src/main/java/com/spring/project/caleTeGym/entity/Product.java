package com.spring.project.caleTeGym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="calories")
	private int calories;
	
	@Column(name="protein")
	private int protein;
	
	@Column(name="fat")
	private int fat;
	
	@Column(name="carbohydrates")
	private int carbohydrates;
	
	@Column(name="image")
	private String image;

	public Product(int id, String name, int calories, int protein, int fat, int carbohydrates, String image) {
		super();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.protein = protein;
		this.fat = fat;
		this.carbohydrates = carbohydrates;
		this.image = image;
	}

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", calories=" + calories + ", protein=" + protein + ", fat="
				+ fat + ", carbohydrates=" + carbohydrates + ", image=" + image + "]";
	}

	
}
