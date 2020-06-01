package com.spring.project.caleTeGym.entity;

import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "groups")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group
{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany
	@JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id") )
	private List<User> members;
	
	@OneToOne
    @JoinColumn(name="owner", referencedColumnName="id")
	private User owner;
	
	@Column(name="status")
	private String status;
	
	@ManyToMany
	@JoinTable(name = "group_post", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private List<Post> posts;

	public Group() 
	{
		
	}
	public Group(int id, String name, List<User> members, User owner, String status) 
	{
		this.id = id;
		this.name = name;
		this.members = members;
		this.owner = owner;
		this.status = status;
	}

	
	public List<Post> getPosts() 
	{
		return posts;
	}

	public void setPosts(List<Post> posts) 
	{
		this.posts = posts;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
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
	
	public void addMember(User user) 
	{
			this.members.add(user);
	}

	@Override
	public String toString() 
	{
		return "Group [id=" + getId() + ", name=" + getName() + ", members=" + getMembers().toString() + ", owner=" + getOwner().toString() + ", status=" + getStatus()
				+ "]";
	}
	
	public boolean isOwner(User user) 
	{
		return this.owner.equals(user);
	}
	
	public void addPost(Post post) 
	{
		this.posts.add(post);
	}
}
