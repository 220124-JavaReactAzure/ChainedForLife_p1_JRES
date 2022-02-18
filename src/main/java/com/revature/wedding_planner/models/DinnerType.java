package com.revature.wedding_planner.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "dinner_types")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class DinnerType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Serial in SQL
	@Column(name = "dinner_type_id")
	private int id;
	
	@Column(name = "dinner_type_name", unique = true, nullable = false)
	private String name;
	
	// Constructors
	
	public DinnerType() {
		super();
	}

	public DinnerType(int id, String name) {
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
		return "DinnerType [id=" + id + ", name=" + name + "]";
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
		DinnerType other = (DinnerType) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
	
}
