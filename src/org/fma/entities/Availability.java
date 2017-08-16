package org.fma.entities;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Availability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public int id;
	
	@Column(name="user_id")
	public int userId;
	
	@Column(name="monday_start")
	public Time mondayStart;
	@Column(name="monday_end")
	public Time mondayEnd;
	
	@Column(name="tuesday_start")
	public Time tuesdayStart;
	
	@Column(name="tuesday_end")
	public Time tuesdayEnd;
	
	@Column(name="wednesday_start")
	public Time wednesdayStart;
	
	@Column(name="wednesday_end")
	public Time wednesdayEnd;
	
	@Column(name="thursday_start")
	public Time thursdayStart;
	
	@Column(name="thursday_end")
	public Time thursdayEnd;

	@Column(name="friday_start")
	public Time fridayStart;
	
	@Column(name="friday_end")
	public Time fridayEnd;

	@Column(name="saturday_start")
	public Time saturdayStart;
	
	@Column(name="saturday_end")
	public Time saturdayEnd;
	
	@Column(name="sunday_start")
	public Time sundayStart;
	
	@Column(name="sunday_end")
	public Time sundayEnd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getMondayStart() {
		return mondayStart;
	}
	public void setMondayStart(Time mondayStart) {
		this.mondayStart = mondayStart;
	}
	public Time getMondayEnd() {
		return mondayEnd;
	}
	public void setMondayEnd(Time mondayEnd) {
		this.mondayEnd = mondayEnd;
	}
	public Time getTuesdayStart() {
		return tuesdayStart;
	}
	public void setTuesdayStart(Time tuesdayStart) {
		this.tuesdayStart = tuesdayStart;
	}
	public Time getTuesdayEnd() {
		return tuesdayEnd;
	}
	public void setTuesdayEnd(Time tuesdayEnd) {
		this.tuesdayEnd = tuesdayEnd;
	}

	public Time getWednesdayStart() {
		return wednesdayStart;
	}
	public void setWednesdayStart(Time wednesdayStart) {
		this.wednesdayStart = wednesdayStart;
	}
	public Time getWednesdayEnd() {
		return wednesdayEnd;
	}
	public void setWednesdayEnd(Time wednesdayEnd) {
		this.wednesdayEnd = wednesdayEnd;
	}
	public Time getThursdayStart() {
		return thursdayStart;
	}
	public void setThursdayStart(Time thursdayStart) {
		this.thursdayStart = thursdayStart;
	}
	public Time getThursdayEnd() {
		return thursdayEnd;
	}
	public void setThursdayEnd(Time thursdayEnd) {
		this.thursdayEnd = thursdayEnd;
	}
	public Time getFridayStart() {
		return fridayStart;
	}
	public void setFridayStart(Time fridayStart) {
		this.fridayStart = fridayStart;
	}
	public Time getFridayEnd() {
		return fridayEnd;
	}
	public void setFridayEnd(Time fridayEnd) {
		this.fridayEnd = fridayEnd;
	}
	public Time getSaturdayStart() {
		return saturdayStart;
	}
	public void setSaturdayStart(Time saturdayStart) {
		this.saturdayStart = saturdayStart;
	}
	public Time getSaturdayEnd() {
		return saturdayEnd;
	}
	public void setSaturdayEnd(Time saturdayEnd) {
		this.saturdayEnd = saturdayEnd;
	}
	public Time getSundayStart() {
		return sundayStart;
	}
	public void setSundayStart(Time sundayStart) {
		this.sundayStart = sundayStart;
	}
	public Time getSundayEnd() {
		return sundayEnd;
	}
	public void setSundayEnd(Time sundayEnd) {
		this.sundayEnd = sundayEnd;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
