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

@Entity
public class FriendRequest 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addressee_id")
	private User addressee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sender_id")
	private User sender;
	
	@Column(name="date")
	private Date date;

	public FriendRequest(User addressee, User sender, Date date) 
	{
		this.addressee = addressee;
		this.sender = sender;
		this.date = date;
	}
	
	public FriendRequest() 
	{
		
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public User getAddressee() 
	{
		return addressee;
	}

	public void setAddressee(User addressee) 
	{
		this.addressee = addressee;
	}

	public User getSender() 
	{
		return sender;
	}

	public void setSender(User sender) 
	{
		this.sender = sender;
	}

	public Date getDate() 
	{
		return date;
	}

	public void setDate(Date date) 
	{
		this.date = date;
	}

	@Override
	public String toString() {
		return "FriendRequest [id=" + getId() +
				", sender=" + getSender().toString() +
				", addressee=" + getAddressee().toString() +
				", date=" + getDate().toString() + "]";
	}
	
	
}
