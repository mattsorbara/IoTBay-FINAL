/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.model;

import static com.sun.corba.se.spi.activation.ServerHelper.type;

/**
 *
 * @author saniyakhanna
 */
public class UserAdmin {
    
    private String name;
    private String email;
    private String password;
    private String phone;
    private String userType;
    private boolean userActive;
    
    public User(String name, String email, String password, String phone, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userType = type;
        this.userActive = true;
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

    public String getType() {
        return userType;
    }

    public void setType(String userType) {
        this.userType = userType;
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
    
}
