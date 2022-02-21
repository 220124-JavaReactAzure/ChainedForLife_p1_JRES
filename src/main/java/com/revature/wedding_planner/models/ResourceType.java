package com.revature.wedding_planner.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "resource_types")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class ResourceType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Serial in sql
	@Column(name = "resource_type_id")
	private int id;
	
	@Column(name = "resource_type_name", unique = true, nullable = false)
	private String name;
	
	@OneToMany(mappedBy="type", cascade= CascadeType.ALL)
	@JsonIgnoreProperties(value="resource")
	private List<Resource> resources;
	
	// Constructors
	
	public ResourceType() {
		super();
	}
	
	public ResourceType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// Methods

	@Override
	public String toString() {
		return "ResourceType [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceType other = (ResourceType) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
}
