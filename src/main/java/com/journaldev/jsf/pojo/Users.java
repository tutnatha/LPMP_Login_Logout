package com.journaldev.jsf.pojo;

import javax.persistence.Column;

public class Users {
    private String username;
    //    @Column(name="password")
    private String password;
    //    @Column(name="full_name")
    private String fullName;
    //    @Column(name="role")
    private String role;
    //    @Column(name="country")
    private String country;
    //    @Column(name="enabled")
//    private int enabled;
    private Boolean enabled;

    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}
