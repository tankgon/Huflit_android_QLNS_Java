package com.example.login.Model;

import java.io.Serializable;

public class User implements Serializable {
    String name, email ;
    double latitude, longitude;
    public String userID;

    public User(){

    }

    public User(String name, String email, String userID) {
        this.name = name;
        this.email = email;
        this.userID = userID;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;

    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name +
                '}';
    }
}

