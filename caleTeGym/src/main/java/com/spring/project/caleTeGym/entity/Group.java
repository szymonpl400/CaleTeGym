package com.spring.project.caleTeGym.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Group 
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<User> members;
	
	@OneToOne
    @JoinColumn(name="owner", referencedColumnName="id")
	private User owner;

	public Group() 
	{
		
	}
	
	public Group(int id, String name, List<User> members, User owner) 
	{
		this.id = id;
		this.name = name;
		this.members = members;
		this.owner = owner;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public List<User> getMembers()
	{
		return members;
	}

	public void setMembers(List<User> members)
	{
		this.members = members;
	}

	public User getOwner() 
	{
		return owner;
	}

	public void setOwner(User owner) 
	{
		this.owner = owner;
	}
	
	
	//private List<Post> posts;

	
}
