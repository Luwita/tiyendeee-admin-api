package com.ferri.userapp.model;

import com.google.gson.annotations.SerializedName;

public class UserProfileUpdateResponse{

	@SerializedName("baseurl")
	private String baseurl;

	@SerializedName("data")
	private UpdatedProfileData data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getBaseurl(){
		return baseurl;
	}

	public UpdatedProfileData getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}