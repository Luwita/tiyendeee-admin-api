package com.ferri.userapp.model;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("otp")
	private int otp;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public int getOtp(){
		return otp;
	}
}