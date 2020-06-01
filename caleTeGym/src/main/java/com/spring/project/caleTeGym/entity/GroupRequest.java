package com.spring.project.caleTeGym.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="group_request")
public class GroupRequest 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToOne
	@JoinColumn(name="sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name="addressee_id")
	private User addressee;
	
	@Column(name="date")
	private Date date;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Group getGroup()
	{
		return group;
	}

	public void setGroup(Group group) 
	{
		this.group = group;
	}

	public User getSender() 
	{
		return sender;
	}

	public void setSender(User sender) 
	{
		this.sender = sender;
	}

	public User getAddressee()
	{
		return addressee;
	}

	public void setAddressee(User addressee) 
	{
		this.addressee = addressee;
	}

	public Date getDate() 
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public GroupRequest(Group group, User sender, User addressee, Date date) 
	{
		this.group = group;
		this.sender = sender;
		this.addressee = addressee;
		this.date = date;
	}

	public GroupRequest() 
	{
		
	}

	@Override
	public String toString() {
		return "GroupRequest [id=" + getId() + ", group=" + getGroup().toString() + ", sender=" + getSender().toString() + ", addressee=" + getAddressee().toString()
				+ ", date=" + getDate().toString() + "]";
	}
	
}
