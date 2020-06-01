package com.spring.project.caleTeGym.entity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Comment 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="owner", referencedColumnName="id")
	private User owner;

	@Column(name="date")
	private Date date;
	
	@Column(name="content")
	private String content;

	
	public Comment() {}

	public Comment(User owner, Date date, String content) {
		super();
		this.owner = owner;
		this.date = date;
		this.content = content;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public User getOwner()
	{
		return owner;
	}

	public void setOwner(User owner) 
	{
		this.owner = owner;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getContent() 
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + getId() + ", owner=" + getOwner().toString() + ", date=" + getDate().toString() + ", content=" + getContent() + "]";
	}
	
	public String justifyDate() {
		 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String formatedData = formatter.format(this.date);
		return formatedData;
	}
	
	
}
