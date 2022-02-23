package com.revature.wedding_planner.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "attendees")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope=Attendee.class)
public class Attendee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "attendee_id")
	private int id;
	
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="wedding_id", nullable = false)
	private Wedding wedding;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="dinner_type_id", nullable = false)
	private DinnerType dinnerType;
	
	@Column(name="is_going")
	private boolean isGoing;
	
	@Column(name="is_bringing_plus_one")
	private boolean isBringingPlusOne;
	
	// Constructors

	public Attendee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendee(int id, User user, Wedding wedding, DinnerType dinnerType, boolean isGoing, boolean isBringingPlusOne) {
		super();
		this.id = id;
		this.user = user;
		this.wedding = wedding;
		this.dinnerType = dinnerType;
		this.isGoing = isGoing;
		this.isBringingPlusOne = isBringingPlusOne;
	}
	
	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Wedding getWedding() {
		return wedding;
	}

	public void setWedding(Wedding wedding) {
		this.wedding = wedding;
	}

	public DinnerType getDinnerType() {
		return dinnerType;
	}

	public void setDinnerType(DinnerType dinnerType) {
		this.dinnerType = dinnerType;
	}

	public boolean isGoing() {
		return isGoing;
	}

	public void setGoing(boolean isGoing) {
		this.isGoing = isGoing;
	}

	public boolean isBringingPlusOne() {
		return isBringingPlusOne;
	}

	public void setBringingPlusOne(boolean isBringingPlusOne) {
		this.isBringingPlusOne = isBringingPlusOne;
	}
	
	// Methods

	@Override
	public String toString() {
		return "Attendee [id=" + id + ", user=" + user + ", wedding=" + wedding + ", dinnerType=" + dinnerType
				+ ", isGoing=" + isGoing + ", isBringingPlusOne=" + isBringingPlusOne + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dinnerType, id, isBringingPlusOne, isGoing, user, wedding);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attendee other = (Attendee) obj;
		return dinnerType == other.dinnerType && id == other.id && isBringingPlusOne == other.isBringingPlusOne
				&& isGoing == other.isGoing && user == other.user && Objects.equals(wedding, other.wedding);
	}

	
	
	
}
