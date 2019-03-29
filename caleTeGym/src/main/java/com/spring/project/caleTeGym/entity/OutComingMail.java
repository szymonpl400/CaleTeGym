package com.spring.project.caleTeGym.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="out_coming_mails")
public class OutComingMail implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sender_id")
	private User sender;
	
	@Column(name="topic")
	private String topic;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne
    @JoinColumn(name="addressee_id", referencedColumnName="id")
	private User addressee;
	
	@Column(name="date")
	private Date date;
	
	public OutComingMail() {}	
	
	public OutComingMail(int id, User sender, String topic, String content, User addressee, Date date) 
	{
		this.id = id;
		this.sender = sender;
		this.topic = topic;
		this.content = content;
		this.addressee = addressee;
		this.date = date;
	}

	public Date getDate() 
	{
		return date;
	}

	public void setDate(Date date) 
	{
		this.date = date ;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
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

	public String getTopic()
	{
		return topic;
	}

	public void setTopic(String topic) 
	{
		this.topic = topic;
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
	public String toString() 
	{
		return "InComingMail [id=" + id + ", sender=" + sender + ", topic=" + topic + ", content=" + content
				+ ", addressee=" + addressee + ", date=" + date + "]";
	}
}
