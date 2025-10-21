package com.spotevent.rest.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_event")
public class Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	
	@Column
	@JsonFormat(pattern="HH:mm")
	private LocalTime time;
	
	@Column
	private Double longitude;
	
	@Column
	private Double latitude;
	
	@Column(name = "private_event")
	private boolean privateEvent = false;
	
	@ManyToOne
	@JoinColumn(name="fk_creator_id", nullable=false)
	private User creator;
	
	@ManyToMany
	@JoinTable( name = "tj_events_interests",
				joinColumns = @JoinColumn( name = "fk_event_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn( name = "fk_interest_id", referencedColumnName="id"))
	private List<KeyWord> interests;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable( name = "tj_events_users",
    			joinColumns = @JoinColumn( name = "fk_event_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn( name = "fk_user_id", referencedColumnName="id"))
	private List<User> users;
	
	public Event() {}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public boolean isPrivateEvent() {
		return privateEvent;
	}
	
	public void setPrivateEvent(boolean privateEvent) {
		this.privateEvent = privateEvent;
	}
	
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public List<KeyWord> getInterests(){
		return interests;
	}
	
	public void setInterests(List<KeyWord> interests){
		this.interests = interests;
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
