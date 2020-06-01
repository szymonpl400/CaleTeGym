package com.spring.project.caleTeGym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int id;
	
	@Column(name="role")
	private String role;
	
	Role()
	{
		
	}

	public Role(String role) 
	{
		this.role = role;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getRole() 
	{
		return role;
	}

	public void setRole(String role) 
	{
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + getRole().toString() + "]";
	}
	
}
