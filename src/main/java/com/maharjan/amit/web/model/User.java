/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maharjan.amit.web.model;

import java.util.Date;

/**
 *
 * @author Student99
 */
public class User {
    private String username;
    private String email;
    private String password;
    private Date createdDate;
    private Date modifiedDate;
    
    public User() {
        
    }
    
    public User(String username, String email, String password, Date createdDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
