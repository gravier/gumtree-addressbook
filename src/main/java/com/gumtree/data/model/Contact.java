package com.gumtree.data.model;

import java.util.Date;
import java.util.Objects;

import org.joda.time.DateTime;
import org.joda.time.Years;

public class Contact {
	public static final String ENTITY_NAME = "contact"; 
	
	private String firstname;
	private String lastname;
	private Gender gender;
	private DateTime birthday;	
	
	public Contact(String firstname, String lastname, Gender gender, DateTime birthday) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthday = birthday;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public Gender getGender() {
		return gender;
	}
	public DateTime getBirthday() {
		return birthday;
	}
	public int getAge() {
		return Years.yearsBetween(birthday, DateTime.now()).getYears();
	}
	
	public int hashCode() {
		return Objects.hash(this.firstname, this.lastname, this.gender, this.birthday);
	}
	
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}

	@Override
	public String toString() {
		return "Contact [firstname=" + firstname + ", lastname=" + lastname
				+ ", gender=" + gender + ", birthday=" + birthday.toString("dd/MM/yy") 
				+ ", age=" + getAge() + "]";
	}
	
	
}


