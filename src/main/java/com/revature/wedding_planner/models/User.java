package com.revature.wedding_planner.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "users")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Serial in SQL
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_name", nullable = false)
	private String name;

	@Column(name = "user_email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "user_password", nullable = false)
	private String password;
	
	@JoinColumn(name = "user_type_id", nullable = false)
	private int typeID;
	
	// Constructors
	
	public User() {
		super();
	}
	
	public User(int id, String name, String email, String password, int typeID) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.typeID = typeID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", typeID="
				+ typeID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, password, typeID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && typeID == other.typeID;
	}
	
}
