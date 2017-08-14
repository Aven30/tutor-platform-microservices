package org.fma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name="BasicInfo")
public class BasicInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public int id;
	
	@Column(name="user_id")
	public int userId;
	
	@Column
	public String firstName;
	@Column
	public String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserid(int userId) {
		this.userId = userId;
	}
}
