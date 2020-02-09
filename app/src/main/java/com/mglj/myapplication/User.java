package com.mglj.myapplication;

public class User {

    String userID;
    String userPassword;
    String userName;
    String userAge;
    String userHeight;
    String userWeight;

    public User(String userID, String userPassword, String userName, String userAge, String userHeight, String userWeight) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAge = userAge;
        this.userHeight =  userHeight;
        this.userWeight = userWeight;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public String getUserWeight() {
        return userWeight;
    }
}
