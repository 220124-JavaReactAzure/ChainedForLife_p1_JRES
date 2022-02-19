package com.revature.wedding_planner.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "weddings")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Wedding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "wedding_id")
	private int id;
	
	@JoinColumn(name = "user_id", nullable = false)
	private int userID;
	
	@OneToMany(mappedBy="wedding", fetch=FetchType.EAGER)
	@JsonIgnoreProperties(value="wedding")
	private List<RentedResource> rentedResources;
	
	@OneToMany(mappedBy="wedding", fetch=FetchType.EAGER)
	@JsonIgnoreProperties(value="wedding")
	private List<Attendee> attendees;
	
	// Constructors
	
	public Wedding() {
		super();
	}
	
	public Wedding(int id, int userID, List<RentedResource> rentedResources, List<Attendee> attendees) {
		super();
		this.id = id;
		this.userID = userID;
		this.rentedResources = rentedResources;
		this.attendees = attendees;
	}
	
	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public List<RentedResource> getRentedResources() {
		return rentedResources;
	}

	public void setResources(List<RentedResource> rentedResources) {
		this.rentedResources = rentedResources;
	}

	public List<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}
	
	// Methods

	@Override
	public String toString() {
		return "Wedding [id=" + id + ", userID=" + userID + ", rentedResources=" + rentedResources + ", attendees=" + attendees
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attendees, id, rentedResources, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wedding other = (Wedding) obj;
		return Objects.equals(attendees, other.attendees) && id == other.id
				&& Objects.equals(rentedResources, other.rentedResources) && userID == other.userID;
	}
	
}
