package com.revature.wedding_planner.models;

import java.sql.Date;
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
@Table(name= "rented_resources")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope=RentedResource.class)
public class RentedResource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "rented_resource_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="resource_id")
	private Resource resource;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="wedding_id", nullable = false)
	private Wedding wedding;
	
	@Column(name="date_rented")
	private Date dateRented;
	
	// Constructors

	public RentedResource() {
		super();
	}

	public RentedResource(int id, Resource resource, Wedding wedding, Date dateRented) {
		super();
		this.id = id;
		this.resource = resource;
		this.wedding = wedding;
		this.dateRented = dateRented;
	}
	
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Wedding getWedding() {
		return wedding;
	}

	public void setWedding(Wedding wedding) {
		this.wedding = wedding;
	}

	public Date getDateRented() {
		return dateRented;
	}

	public void setDateRented(Date dateRented) {
		this.dateRented = dateRented;
	}
	
	// Methods

	@Override
	public String toString() {
		return "RentedResource [id=" + id + ", resource=" + resource + ", wedding=" + wedding + ", dateRented="
				+ dateRented + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateRented, id, resource, wedding);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentedResource other = (RentedResource) obj;
		return Objects.equals(dateRented, other.dateRented) && id == other.id
				&& Objects.equals(resource, other.resource) && Objects.equals(wedding, other.wedding);
	}
	
}
