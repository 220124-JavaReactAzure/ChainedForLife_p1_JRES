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
@Table(name= "weddings")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope=PlusOne.class)
public class PlusOne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "plus_one_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "attendee_id", nullable = false)
	private Attendee attendee;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="Wedding_id", nullable = false)
	private Wedding wedding;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="dinner_type_id", nullable = false)
	private DinnerType dinnerType;
	
	// Constructors

	public PlusOne() {
		super();
	}

	public PlusOne(int id, Attendee attendee, Wedding wedding, DinnerType dinnerType) {
		super();
		this.id = id;
		this.attendee = attendee;
		this.wedding = wedding;
		this.dinnerType = dinnerType;
	}
	
	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Attendee getAttendee() {
		return attendee;
	}

	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
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
	
	// Methods

	@Override
	public String toString() {
		return "PlusOne [id=" + id + ", attendee=" + attendee + ", wedding=" + wedding + ", dinnerType=" + dinnerType
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attendee, dinnerType, id, wedding);
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
		return Objects.equals(attendee, other.attendee) && Objects.equals(dinnerType, other.dinnerType)
				&& id == other.id && Objects.equals(wedding, other.wedding);
	}

	
}
