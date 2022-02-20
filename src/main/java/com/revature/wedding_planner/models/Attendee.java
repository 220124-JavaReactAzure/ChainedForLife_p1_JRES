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
		property = "id")
public class Attendee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "attendee_id")
	private int id;
	
	@JoinColumn(name = "user_id")
	private int userID;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="wedding_id", nullable = false)
	private Wedding wedding;
	
	@JoinColumn(name="dinner_type_id", nullable = false)
	private int dinnerTypeID;
	
	@Column(name="is_going")
	private boolean isGoing;
	
	@Column(name="is_bringing_plus_one")
	private boolean isBringingPlusOne;
	
	// Constructors

	public Attendee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendee(int id, int userID, Wedding wedding, int dinnerTypeID, boolean isGoing, boolean isBringingPlusOne) {
		super();
		this.id = id;
		this.userID = userID;
		this.wedding = wedding;
		this.dinnerTypeID = dinnerTypeID;
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Wedding getWedding() {
		return wedding;
	}

	public void setWedding(Wedding wedding) {
		this.wedding = wedding;
	}

	public int getDinnerTypeID() {
		return dinnerTypeID;
	}

	public void setDinnerTypeID(int dinnerTypeID) {
		this.dinnerTypeID = dinnerTypeID;
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
		return "Attendee [id=" + id + ", userID=" + userID + ", wedding=" + wedding + ", dinnerTypeID=" + dinnerTypeID
				+ ", isGoing=" + isGoing + ", isBringingPlusOne=" + isBringingPlusOne + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dinnerTypeID, id, isBringingPlusOne, isGoing, userID, wedding);
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
		return dinnerTypeID == other.dinnerTypeID && id == other.id && isBringingPlusOne == other.isBringingPlusOne
				&& isGoing == other.isGoing && userID == other.userID && Objects.equals(wedding, other.wedding);
	}
	
	
}
