/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.model;

/**
 *
 * @author matthewsorbara
 */
public class User {
    
    private String name;
    private String email;
    private String password;
    private String phone;
    
    private String userType;
    private boolean userActive;
    
    // constructor for 5 fields
    public User(String name, String email, String password, String phone, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userType = type;
        this.userActive = true;
    }
    
    // constructor for 6 fields
    public User(String name, String email, String password, String phone, String type, boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userType = type;
        this.userActive = active;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String type) {
        this.userType = type;
    }

    public boolean getUserActive() {
        return this.userActive;
    }

    public void setUserActive(boolean activeStatus) {
        this.userActive = activeStatus;
    }
     
}
