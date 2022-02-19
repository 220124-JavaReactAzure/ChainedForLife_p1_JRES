package com.revature.wedding_planner.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "weddings")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class PlusOne {
	@Id
	@JoinColumn(name = "attendee_id", nullable = false)
	private int attendeeID;
	
	@Id
	@JoinColumn(name="Wedding_id", nullable = false)
	private int weddingID;
	
	@JoinColumn(name="dinner_type_id", nullable = false)
	private int dinnerTypeID;
	
	// Constructors

	public PlusOne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlusOne(int attendeeID, int weddingID, int dinnerTypeID) {
		super();
		this.attendeeID = attendeeID;
		this.weddingID = weddingID;
		this.dinnerTypeID = dinnerTypeID;
	}
	
	// Getters and Setters

	public int getAttendeeID() {
		return attendeeID;
	}

	public void setAttendeeID(int attendeeID) {
		this.attendeeID = attendeeID;
	}

	public int getWeddingID() {
		return weddingID;
	}

	public void setWeddingID(int weddingID) {
		this.weddingID = weddingID;
	}

	public int getDinnerTypeID() {
		return dinnerTypeID;
	}

	public void setDinnerTypeID(int dinnerTypeID) {
		this.dinnerTypeID = dinnerTypeID;
	}
	
	// Methods

	@Override
	public String toString() {
		return "PlusOne [attendeeID=" + attendeeID + ", weddingID=" + weddingID + ", dinnerTypeID=" + dinnerTypeID
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attendeeID, dinnerTypeID, weddingID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlusOne other = (PlusOne) obj;
		return attendeeID == other.attendeeID && dinnerTypeID == other.dinnerTypeID && weddingID == other.weddingID;
	}
	
	
}
