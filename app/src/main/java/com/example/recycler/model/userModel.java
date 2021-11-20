package com.example.recycler.model;

public class userModel extends ModelID {

    String fullName;
    String lastName;
    String email;
    String cellular;

    public userModel() {
    }

    public userModel(String fullName, String lastName, String email, String cellular) {
        this.fullName = fullName;
        this.lastName = lastName;
        this.email = email;
        this.cellular = cellular;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellular() {
        return cellular;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }
}
