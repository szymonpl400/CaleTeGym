package com.spring.project.caleTeGym.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="users")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Email(message="Please provide a valid Email")
	@NotEmpty(message="Please provide an email")
	@Column(name="email")
	private String email;
	
	@NotEmpty(message="Please provide your password")
	@Length(min=5,message="*Your password ,ust have at least 5 characters")
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name="last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(name="active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	

	@JoinTable(name="friends", joinColumns = 
		{@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = 
		{@JoinColumn(name="friend_id", referencedColumnName = "id", nullable = false)})
	@ManyToMany
	private Set<User> friends;
	
	@OneToMany(mappedBy="addressee")
	private Set<InComingMail> inComingMails;
	
	@OneToMany(mappedBy="sender")
	private Set<OutComingMail> outComingMails;
	
	@OneToMany(mappedBy="addressee")
	private Set<FriendRequest> inComingFriendRequests;
	
	@OneToMany(mappedBy="sender")
	private	Set<FriendRequest> outComingFriendRequests;
	
	@ManyToMany
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<Group> groups;

	public User() 
	{}



	public User(int id,
			@Email(message = "Please provide a valid Email") @NotEmpty(message = "Please provide an email") String email,
			@NotEmpty(message = "Please provide your password") @Length(min = 5, message = "*Your password ,ust have at least 5 characters") String password,
			@NotEmpty(message = "*Please provide your name") String name,
			@NotEmpty(message = "*Please provide your last name") String lastName, int active, Set<Role> roles,
			Set<User> friends, Set<InComingMail> inComingMails, Set<OutComingMail> outComingMails,
			Set<FriendRequest> inComingFriendRequests, Set<FriendRequest> outComingFriendRequests, Set<Group> groups) {

		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roles = roles;
		this.friends = friends;
		this.inComingMails = inComingMails;
		this.outComingMails = outComingMails;
		this.inComingFriendRequests = inComingFriendRequests;
		this.outComingFriendRequests = outComingFriendRequests;
		this.groups = groups;
	}



	public User(@NotEmpty(message = "*Please provide your name") String name,
			@NotEmpty(message = "*Please provide your last name") String lastName) 
	{
		this.name = name;
		this.lastName = lastName;
	}


	public Set<FriendRequest> getInComingFriendRequests()
	{
		return inComingFriendRequests;
	}


	public void setInComingFriendRequests(Set<FriendRequest> inComingFriendRequests)
	{
		this.inComingFriendRequests = inComingFriendRequests;
	}


	public Set<FriendRequest> getOutComingFriendRequests()
	{
		return outComingFriendRequests;
	}


	public void setOutComingFriendRequests(Set<FriendRequest> outComingFriendRequests) 
	{
		this.outComingFriendRequests = outComingFriendRequests;
	}

	public Set<InComingMail> getInComingMails() 
	{
		return inComingMails;
	}

	public void setInComingMails(Set<InComingMail> inComingMails) 
	{
		this.inComingMails = inComingMails;
	}

	
	public Set<OutComingMail> getOutComingMails() 
	{
		return outComingMails;
	}

	public void setOutComingMails(Set<OutComingMail> outComingMails) 
	{
		this.outComingMails = outComingMails;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getName()
{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public int getActive() 
	{
		return active;
	}

	public void setActive(int active) 
	{
		this.active = active;
	}

	public Set<Role> getRoles() 
	{
		return roles;
	}

	public void setRoles(Set<Role> roles) 
	{
		this.roles = roles;
	}

	public Set<User> getFriends() 
	{
		return friends;
	}

	public void setFriends(Set<User> friends) 
	{
		this.friends = friends;
	}
	
	public void addFriend(User user) 
	{
		this.friends.add(user);
	}
	
	public void deleteFriendRequest(FriendRequest friendRequest) 
	{
		this.inComingFriendRequests.remove(friendRequest);
	}
	
	public boolean isFriend(User user)
	{
		return this.friends.contains(user);
	}
	
	public void deleteMail(InComingMail mail) 
	{
		this.inComingMails.remove(mail);
	}
}
