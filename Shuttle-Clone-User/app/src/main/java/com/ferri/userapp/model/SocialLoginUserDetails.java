package com.ferri.userapp.model;

import java.io.Serializable;

public class SocialLoginUserDetails implements Serializable {

    private String fname;
    private String lname;
    private String socialId;
    private String photo;
    private String email;
    private String mode;

    public SocialLoginUserDetails(String fname, String lname, String socialId, String photo, String email, String mode) {
        this.fname = fname;
        this.lname = lname;
        this.socialId = socialId;
        this.photo = photo;
        this.email = email;
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
