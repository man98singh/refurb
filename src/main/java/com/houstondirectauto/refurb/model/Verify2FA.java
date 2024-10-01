package com.houstondirectauto.refurb.model;

public class Verify2FA {
    private Integer userId;  // Change this to Integer for user ID
    private String code;

    // Getter and setter for userId
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Getter and setter for code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
