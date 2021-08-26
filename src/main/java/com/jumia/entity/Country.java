package com.jumia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Amr Elbassiouni
 *
 */
@Entity
@Table(name = "COUNTRY")
public class Country {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	private String name;
	private String abbreviation;
	private String code;
	@Column(name = "phone_number_regex")
	private String phoneNumberRegex;
	
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
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhoneNumberRegex() {
		return phoneNumberRegex;
	}
	public void setPhoneNumberRegex(String phoneNumberRegex) {
		this.phoneNumberRegex = phoneNumberRegex;
	}
}