package com.ferri.userapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDetail implements Serializable {

	@SerializedName("firstname")
	private String firstname;

	@SerializedName("gender")
	private String gender;

	@SerializedName("phone")
	private long phone;

	@SerializedName("email")
	private String email;

	@SerializedName("lastname")
	private String lastname;

	public String getFirstname(){
		return firstname;
	}

	public String getGender(){
		return gender;
	}

	public long getPhone(){
		return phone;
	}

	public String getEmail(){
		return email;
	}

	public String getLastname(){
		return lastname;
	}
}