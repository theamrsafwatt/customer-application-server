package com.jumia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	private String identifier;
	private String name;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "valid_phone_number")
	private Boolean validPhoneNumber;
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getValidPhoneNumber() {
		return validPhoneNumber;
	}
	public void setValidPhoneNumber(Boolean validPhoneNumber) {
		this.validPhoneNumber = validPhoneNumber;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}