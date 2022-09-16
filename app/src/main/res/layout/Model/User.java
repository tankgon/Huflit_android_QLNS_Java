package com.example.login.Model;

import java.io.Serializable;

public class User implements Serializable {
    String name, email ;
    double latitude, longitude;

    public User(){

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;

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
}

