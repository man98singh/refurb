package com.houstondirectauto.refurb.model;

public class Verify2FA {
    private String username;  // Changed to "username"
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
