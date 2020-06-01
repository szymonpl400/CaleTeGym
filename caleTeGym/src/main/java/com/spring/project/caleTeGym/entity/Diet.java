package com.spring.project.caleTeGym.entity;

import java.util.List;

public class Diet {

	private List<Product> breakfast;
	private List<Product> secondBreakfast;
	private List<Product> dinner;
	private List<Product> tea;
	private List<Product> supper;
	
	
	public Diet(List<Product> breakfast, List<Product> secondBreakfast, List<Product> dinner, List<Product> tea,
			List<Product> supper) {
		super();
		this.breakfast = breakfast;
		this.secondBreakfast = secondBreakfast;
		this.dinner = dinner;
		this.tea = tea;
		this.supper = supper;
	}


	public Diet() {
		super();
	}


	public List<Product> getBreakfast() {
		return breakfast;
	}


	public void setBreakfast(List<Product> breakfast) {
		this.breakfast = breakfast;
	}


	public List<Product> getSecondBreakfast() {
		return secondBreakfast;
	}


	public void setSecondBreakfast(List<Product> secondBreakfast) {
		this.secondBreakfast = secondBreakfast;
	}


	public List<Product> getDinner() {
		return dinner;
	}


	public void setDinner(List<Product> dinner) {
		this.dinner = dinner;
	}


	public List<Product> getTea() {
		return tea;
	}


	public void setTea(List<Product> tea) {
		this.tea = tea;
	}


	public List<Product> getSupper() {
		return supper;
	}


	public void setSupper(List<Product> supper) {
		this.supper = supper;
	}


	@Override
	public String toString() {
		return "Diet [breakfast=" + breakfast + ", secondBreakfast=" + secondBreakfast + ", dinner=" + dinner + ", tea="
				+ tea + ", supper=" + supper + "]";
	}
	
	
}
