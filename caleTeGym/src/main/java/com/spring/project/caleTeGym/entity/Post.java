package com.spring.project.caleTeGym.entity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne
    @JoinColumn(name="owner", referencedColumnName="id")
	private User owner;
	
	@Column(name="date")
	private Date date;
	
	
	@ManyToMany
	@JoinTable(name = "post_user", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> likes;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "post_comment", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
	private List<Comment> comments;
	//private ... photo

	public Post(int id, String content, Date date, int likes)
	{
		this.id = id;
		this.content = content;
		this.date = date;
	}
	
	
	public Post() {}

	public int getId() 
	{
		return id;
	}
	
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getContent() 
	{
		return content;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}

	public Date getDate() 
	{
		return date;
	}

	public void setDate(Date date) 
	{
		this.date = date;
	}

	public List<User> getLikes() 
	{
		return likes;
	}

	public void setLikes(List<User> likes) 
	{
		this.likes = likes;
	}

	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", owner=" + getOwner().toString() + ", date=" + date + ", likes=" + getLikes().toString()
				+ ", comments=" + getComments().toString() + "]";
	}
	
	public String justifyDate() {
		 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String formatedData = formatter.format(this.date);
		return formatedData;
	}
	
	public boolean isLiked(User user) {
		return likes.contains(user);
	}
	
	public List<Comment> sortByDate(List<Comment> comments) {
		for(int i=0; i<comments.size(); i++) {
			for(int j = i+1 ; j< comments.size();j++)
	        {
	            if(comments.get(i).getDate().compareTo(comments.get(j).getDate()) < 0)
	            {
	                Comment temp = comments.get(i);
	                comments.set(i, comments.get(j));
	                comments.set(j,temp);
	            }
	        }
		}
		return comments;
	}
	
	public int getGroupIdOfPost(User user) {
		
		int groupId = 0;
		for(Group group : user.getGroups()) {
			if(group.getPosts().contains(this)) {
				groupId = group.getId();
			}
		}
		return groupId;	
	}
	public boolean commentsAreEmpty() {
		System.out.println("wielkosc : " + this.comments.size());
		return true;
	}
}
