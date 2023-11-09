package com.ferri.userapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRegisterResponseModel implements Serializable {

	@SerializedName("flag")
	private int flag;

	@SerializedName("csrfToken")
	private String csrfToken;

	@SerializedName("userDetail")
	private UserDetail userDetail;

	@SerializedName("otp")
	private int otp;

	@SerializedName("title")
	private String title;

	@SerializedName("status")
	private boolean status;

	@SerializedName("token")
	private String token;

	public int getFlag(){
		return flag;
	}

	public String getCsrfToken(){
		return csrfToken;
	}

	public UserDetail getUserDetail(){
		return userDetail;
	}

	public int getOtp(){
		return otp;
	}

	public String getTitle(){
		return title;
	}

	public boolean isStatus(){
		return status;
	}

	public String getToken(){
		return token;
	}
}