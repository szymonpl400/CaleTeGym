package com.spring.project.caleTeGym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.caleTeGym.entity.Meal;
import com.spring.project.caleTeGym.entity.Product;
import com.spring.project.caleTeGym.repository.MealRepository;
import com.spring.project.caleTeGym.repository.ProductRepository;


@Service
public class ProductService 
{
	@Autowired
	ProductRepository productRepository;
	
	public void save(Product product) 
	{
		productRepository.save(product);
	}
	
	public Product getMeal(int id) 
	{
		return productRepository.getOne(id);
	}
}
