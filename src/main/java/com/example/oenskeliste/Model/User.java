package com.example.oenskeliste.Model;

public class User {
    private String email;
    private String name;
    private String password;

    //Constructor with password
    public User(String email, String name, String password) {

        this.email = email;
        this.name = name;
        this.password = password;
    }
    //Constructor without password
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }


    //Getters & Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}