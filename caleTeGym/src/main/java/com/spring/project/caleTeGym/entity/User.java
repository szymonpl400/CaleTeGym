package com.spring.project.caleTeGym.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Length(min=5,message="*Your password must have at least 5 characters")
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name="last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(name="type")
	private String type;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="active")
	private int active;
	
	@Column(name="age", columnDefinition="INT(11) default '0'")
	private int age = 0;
	
	@Column(name="weight", columnDefinition="FLOAT default '0.00'")
	private float weight;
	
	@Column(name="growth", columnDefinition="FLOAT default '0.00'")
	private float growth;
	
	@Column(name="bmi", columnDefinition="FLOAT default '0.00'")
	private float bmi;
	
	@Column(name="caloricDemand", columnDefinition="FLOAT default '0.00'")
	private float caloricDemand;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="friends", joinColumns = 
		{@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = 
		{@JoinColumn(name="friend_id", referencedColumnName = "id", nullable = false)})
	private Set<User> friends;
	
	@OneToMany(mappedBy="addressee")
	private Set<InComingMail> inComingMails;
	
	@OneToMany(mappedBy="sender")
	private Set<OutComingMail> outComingMails;
	
	@OneToMany(mappedBy="addressee")
	private Set<FriendRequest> inComingFriendRequests;
	
	@OneToMany(mappedBy="sender")
	private	Set<FriendRequest> outComingFriendRequests;
	
	@OneToMany(mappedBy="addressee")
	private List<GroupRequest> groupRequests;
	
	@OneToMany(mappedBy="pupil")
	private List<TrainerRequest> trainerRequests;
	
	@ManyToMany
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<Group> groups;
	
	@ManyToMany
	@JoinTable(name = "pupil_trainer", joinColumns = @JoinColumn(name = "pupil_id"), inverseJoinColumns = @JoinColumn(name = "trainer_id"))
	private List<User> trainers;
	
	@ManyToMany
	@JoinTable(name = "trainer_pupil", joinColumns = @JoinColumn(name = "trainer_id"), inverseJoinColumns = @JoinColumn(name = "pupil_id"))
	private List<User> pupils;
	
	@ManyToMany
	@JoinTable(name = "pupil_breakfast", joinColumns = @JoinColumn(name = "pupil_id"), inverseJoinColumns = @JoinColumn(name = "breakfast_id"))
	private List<Meal> breakfast;
	
	@ManyToMany
	@JoinTable(name = "pupil_secondBreakfast", joinColumns = @JoinColumn(name = "pupil_id"), inverseJoinColumns = @JoinColumn(name = "secondBreakfast_id"))
	private List<Meal> secondBreakfast;
	
	@ManyToMany
	@JoinTable(name = "pupil_dinner", joinColumns = @JoinColumn(name = "pupil_id"), inverseJoinColumns = @JoinColumn(name = "dinner_id"))
	private List<Meal> dinner;
	
	@ManyToMany
	@JoinTable(name = "pupil_tea", joinColumns = @JoinColumn(name = "pupil_id"), inverseJoinColumns = @JoinColumn(name = "tea_id"))
	private List<Meal> tea;
	
	@ManyToMany
	@JoinTable(name = "pupil_supper", joinColumns = @JoinColumn(name = "pupil_id"), inverseJoinColumns = @JoinColumn(name = "supper_id"))
	private List<Meal> supper;
	
	@ManyToMany
	@JoinTable(name = "user_event", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	private List<Event> events;
	
	@ManyToMany
	@JoinTable(name = "user_remaining_target", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "remaining_target_id"))
	private List<Target> remainingTargets;
	
	@ManyToMany
	@JoinTable(name = "user_completed_target", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "completed_target_id"))
	private List<Target> completedTargets;

	
	public User() 
	{}

	public User(int id,
			@Email(message = "Please provide a valid Email") @NotEmpty(message = "Please provide an email") String email,
			@NotEmpty(message = "Please provide your password") @Length(min = 5, message = "*Your password ,ust have at least 5 characters") String password,
			@NotEmpty(message = "*Please provide your name") String name,
			@NotEmpty(message = "*Please provide your last name") String lastName,
			@NotEmpty(message = "*Please choose one") String type,
			int active, Set<Role> roles,
			Set<User> friends, Set<InComingMail> inComingMails, Set<OutComingMail> outComingMails,
			Set<FriendRequest> inComingFriendRequests, Set<FriendRequest> outComingFriendRequests, List<Group> groups, List<TrainerRequest> trainerRequest) {

		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.type = type;
		this.active = active;
		this.roles = roles;
		this.friends = friends;
		this.inComingMails = inComingMails;
		this.outComingMails = outComingMails;
		this.inComingFriendRequests = inComingFriendRequests;
		this.outComingFriendRequests = outComingFriendRequests;
		this.groups = groups;
		this.trainerRequests = trainerRequest;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getGrowth() {
		return growth;
	}

	public void setGrowth(float growth) {
		this.growth = growth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public User(@NotEmpty(message = "*Please provide your name") String name,
			@NotEmpty(message = "*Please provide your last name") String lastName) 
	{
		this.name = name;
		this.lastName = lastName;
	}
		
	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public List<GroupRequest> getGroupRequests()
	{
		return groupRequests;
	}

	public void setGroupRequests(List<GroupRequest> groupRequests) 
	{
		this.groupRequests = groupRequests;
	}

	public List<Group> getGroups()
	{
		return groups;
	}

	public void setGroups(List<Group> groups) 
	{
		this.groups = groups;
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

	public List<User> getTrainers() {
		return trainers;
	}

	public void setTrainers(List<User> trainers) {
		this.trainers = trainers;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public int getActive() 
	{
		return active;
	}

	public float getBmi() {
		return bmi;
	}

	public void setBmi(float bmi) {
		this.bmi = bmi;
	}

	public float getCaloricDemand() {
		return caloricDemand;
	}

	public void setCaloricDemand(float caloricDemand) {
		this.caloricDemand = caloricDemand;
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
	
	public List<TrainerRequest> getTrainerRequests() 
	{
		return trainerRequests;
	}

	public void setTrainerRequests(List<TrainerRequest> trainerRequests) 
	{
		this.trainerRequests = trainerRequests;
	}

	public List<User> getPupils() {
		return pupils;
	}

	public void setPupils(List<User> pupils) {
		this.pupils = pupils;
	}
	
	

	public List<Meal> getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(List<Meal> breakfast) {
		this.breakfast = breakfast;
	}

	public List<Meal> getSecondBreakfast() {
		return secondBreakfast;
	}

	public void setSecondBreakfast(List<Meal> secondBreakfast) {
		this.secondBreakfast = secondBreakfast;
	}

	public List<Meal> getDinner() {
		return dinner;
	}

	public void setDinner(List<Meal> dinner) {
		this.dinner = dinner;
	}

	public List<Meal> getTea() {
		return tea;
	}

	public void setTea(List<Meal> tea) {
		this.tea = tea;
	}

	public List<Meal> getSupper() {
		return supper;
	}

	public void setSupper(List<Meal> supper) {
		this.supper = supper;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Target> getRemainingTargets() {
		return remainingTargets;
	}

	public void setRemainingTargets(List<Target> remainingTargets) {
		this.remainingTargets = remainingTargets;
	}

	public List<Target> getCompletedTargets() {
		return completedTargets;
	}

	public void setCompletedTargets(List<Target> completedTargets) {
		this.completedTargets = completedTargets;
	}

	public void deleteFriendRequest(FriendRequest friendRequest) 
	{
		this.inComingFriendRequests.remove(friendRequest);
	}
	
	public void deleteMail(InComingMail mail) 
	{
		this.inComingMails.remove(mail);
	}
	
	public void addGroup(Group group) 
	{
		this.groups.add(group);
	}
	
	public boolean isMember(User user, Group group)
	{
		return group.getMembers().contains(user);
	}
	public boolean isOwner(Group group, User user) 
	{
		return group.getOwner().equals(user);
	}
	
	public boolean ageIsZero() 
	{
		return this.age == 0;
	}
	
	public boolean growthIsZero() 
	{
		return this.growth == 0;
	}
	
	public boolean weightIsZero() 
	{
		return this.weight == 0;
	}
	
	public boolean bmiIsZero() 
	{
		return this.bmi == 0;
	}
	
	public boolean caloricDemandIsZero() 
	{
		return this.caloricDemand == 0;
	}
	
	public boolean haveInComingFriendRequest(User currentUser)
	{
		for(FriendRequest friendRequest : this.getInComingFriendRequests()){
		  	if(friendRequest.getSender().getId() == currentUser.getId() && friendRequest.getAddressee().getId() == this.getId())
		  	{
		  		return true;
		  	}
		} 
		return false;
	}
	
	public boolean haveTrainRequest(User trainer)
	{
		for(TrainerRequest trainRequest : this.getTrainerRequests()){
		  	if(trainRequest.getTrainer().getId() == trainer.getId())
		  	{
		  		return true;
		  	}
		} 
		return false;
	}
	
	public boolean isFriend(User currentUser)
	{
		for(User user : this.getFriends()) {
			if(user.getId() == currentUser.getId()) 
			{
				return true;
			}
		}
		return false;
	}
	
	public List<Post> getAllPosts(){
		List<Post> allPosts = new ArrayList<Post>();
		for(Group group : this.getGroups()) {
			int size = group.getPosts().size();
			if(size > 0) {
				allPosts.addAll(group.getPosts());
			}
		}
		return allPosts;
	}
	
	public boolean isTrainer(User pupil)
	{
		for(User trainer : pupil.getTrainers()) {
			if(this.getId() == trainer.getId()) 
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + getEmail() + ", password=" + getPassword() + ", name=" + getName() + ", lastName="
				+ lastName + ", type=" + type + ", active=" + active +
				", age=" + getAge() +
				", weight=" + getWeight() +
				", growth=" + getGrowth() +
				", bmi=" + getBmi() +
				", sex=" + getSex() +
				"]";
	}
}