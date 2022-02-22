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
		property = "id")
public class RentedResource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "rented_resource_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="resource_id")
	private Resource resourceID;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="wedding_id", nullable = false)
	private Wedding weddingID;
	
	@Column(name="date_rented")
	private Date dateRented;
	
	// Constructors

	public RentedResource() {
		super();
	}

	public RentedResource(Resource resourceID, Wedding weddingID, Date dateRented) {
		super();
		this.resourceID = resourceID;
		this.weddingID = weddingID;
		this.dateRented = dateRented;
	}
	
	// Getters and Setters

	public Resource getResourceID() {
		return resourceID;
	}

	public void setResourceID(Resource resourceID) {
		this.resourceID = resourceID;
	}

	public Wedding getWedding() {
		return weddingID;
	}

	public void setWedding(Wedding weddingID) {
		this.weddingID = weddingID;
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
		return "RentedResource [resourceID=" + resourceID + ", weddingID=" + weddingID + ", dateRented=" + dateRented + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateRented, resourceID, weddingID);
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
		return Objects.equals(dateRented, other.dateRented) && resourceID == other.resourceID
				&& Objects.equals(weddingID, other.weddingID);
	}
	
}
