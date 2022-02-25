package com.revature.wedding_planner.models;

import java.sql.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "resources")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope=Resource.class)
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "resource_id")
	private int id;
	
	@ManyToOne(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name = "resource_type_id", nullable = false)
	private ResourceType type;
	
	@Column(name = "resource_date_available_start")
	private Date dateAvailableStart;
	
	@Column(name = "resource_date_available_end")
	private Date dateAvailableEnd;
	
	@Column(name = "resource_cost")
	private int cost;
	
	@OneToMany(mappedBy="resource", fetch=FetchType.EAGER)
	private List<RentedResource> rentedResourceIDs;
	
	// Constructors
	
	public Resource() {
		super();
	}
	
	public Resource(int id, ResourceType type, Date dateAvailableStart, Date dateAvailableEnd, int cost) {
		super();
		this.id = id;
		this.type = type;
		this.dateAvailableStart = dateAvailableStart;
		this.dateAvailableEnd = dateAvailableEnd;
		this.cost = cost;
	}
	
	public Resource(ResourceType type, Date dateAvailableStart, Date dateAvailableEnd, int cost) {
		super();
		this.type = type;
		this.dateAvailableStart = dateAvailableStart;
		this.dateAvailableEnd = dateAvailableEnd;
		this.cost = cost;
	}
	
	
	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public Date getDateAvailableStart() {
		return dateAvailableStart;
	}

	public void setDateAvailableStart(Date dateAvailableStart) {
		this.dateAvailableStart = dateAvailableStart;
	}

	public Date getDateAvailableEnd() {
		return dateAvailableEnd;
	}

	public void setDateAvailableEnd(Date dateAvailableEnd) {
		this.dateAvailableEnd = dateAvailableEnd;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	// Methods

	@Override
	public String toString() {
		return "Resource [id=" + id + ", type=" + type + ", dateAvailableStart=" + dateAvailableStart
				+ ", dateAvailableEnd=" + dateAvailableEnd + ", cost=" + cost + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, dateAvailableEnd, dateAvailableStart, id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		return cost == other.cost && Objects.equals(dateAvailableEnd, other.dateAvailableEnd)
				&& Objects.equals(dateAvailableStart, other.dateAvailableStart) && id == other.id
				&& type == other.type;
	}
	
}
