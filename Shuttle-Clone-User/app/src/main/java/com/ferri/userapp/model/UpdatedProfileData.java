package com.ferri.userapp.model;

import com.google.gson.annotations.SerializedName;

public class UpdatedProfileData{

	@SerializedName("firstname")
	private String firstname;

	@SerializedName("gender")
	private String gender;

	@SerializedName("otp")
	private int otp;

	@SerializedName("office_lng")
	private double officeLng;

	@SerializedName("office_timing")
	private String officeTiming;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("home_lng")
	private double homeLng;

	@SerializedName("mode")
	private String mode;

	@SerializedName("social_id")
	private String socialId;

	@SerializedName("office_lat")
	private double officeLat;

	@SerializedName("office_address")
	private String officeAddress;

	@SerializedName("home_address")
	private String homeAddress;

	@SerializedName("phone")
	private String phone;

	@SerializedName("home_lat")
	private double homeLat;

	@SerializedName("refercode")
	private String refercode;

	@SerializedName("ProfilePic")
	private String profilePic;

	@SerializedName("_id")
	private String id;

	@SerializedName("home_timing")
	private String homeTiming;

	@SerializedName("email")
	private String email;

	public String getFirstname(){
		return firstname;
	}

	public String getGender(){
		return gender;
	}

	public int getOtp(){
		return otp;
	}

	public double getOfficeLng(){
		return officeLng;
	}

	public String getOfficeTiming(){
		return officeTiming;
	}

	public String getLastname(){
		return lastname;
	}

	public double getHomeLng(){
		return homeLng;
	}

	public String getMode(){
		return mode;
	}

	public String getSocialId(){
		return socialId;
	}

	public double getOfficeLat(){
		return officeLat;
	}

	public String getOfficeAddress(){
		return officeAddress;
	}

	public String getHomeAddress(){
		return homeAddress;
	}

	public String getPhone(){
		return phone;
	}

	public double getHomeLat(){
		return homeLat;
	}

	public String getRefercode(){
		return refercode;
	}

	public String getProfilePic(){
		return profilePic;
	}

	public String getId(){
		return id;
	}

	public String getHomeTiming(){
		return homeTiming;
	}

	public String getEmail(){
		return email;
	}
}