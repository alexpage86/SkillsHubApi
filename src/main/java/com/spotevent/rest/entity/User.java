package com.spotevent.rest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="t_user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@JsonView
	private String firstName;
	
	@Column
	@JsonView
	private String lastName;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String eMail;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@OneToMany(mappedBy = "creator")
	@JsonBackReference
	private List<Event> createdEvents;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable( name = "tj_events_users",
    			joinColumns = @JoinColumn( name = "fk_user_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn( name = "fk_event_id", referencedColumnName="id" ))
	private List<Event> joinedEvents;
	
	@ManyToMany
	@JoinTable( name = "tj_users_interests",
				joinColumns = @JoinColumn( name = "fk_user_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn( name = "fk_interest_id", referencedColumnName="id"))
	@JsonView
	private List<KeyWord> interests;
	
	// Id du document Mongo (Photo)
    private String photoProfileId;
	
	public User() {}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getEMail(){
		return eMail;
	}
	
	public void setEMail(String eMail){
		this.eMail = eMail;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<Event> getCreatedEvents(){
		return createdEvents;
	}
	
	public void setCreatedEvents(List<Event> createdEvents){
		this.createdEvents = createdEvents;
	}
	
	public List<Event> getJoinedEvents(){
		return joinedEvents;
	}
	
	public void setJoinedEvents(List<Event> joinedEvents){
		this.joinedEvents = joinedEvents;
	}
	
	public List<KeyWord> getInterests(){
		return interests;
	}
	
	public void setInterests(List<KeyWord> interests){
		this.interests = interests;
	}
	
	public String getPhotoProfileId() {
		return this.photoProfileId;
	}
	
	public void setPhotoProfileId(String photoProfileId) {
		this.photoProfileId = photoProfileId;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate='" + birthDate + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", eMail='" + eMail + '\'' +
				'}';
	}

}
