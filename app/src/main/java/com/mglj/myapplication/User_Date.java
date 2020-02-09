package com.mglj.myapplication;

public class User_Date {

    String dateNUM;
    String dateID;
    String dateDAY;
    String dateContent;
    String petNUM;

    public User_Date(String dateNUM, String dateID, String dateDAY, String dateContent, String petNUM) {
        this.dateNUM = dateNUM;
        this.dateID = dateID;
        this.dateDAY = dateDAY;
        this.dateContent = dateContent;
        this.petNUM = petNUM;
    }

    public void setDateNUM(String dateNUM) {
        this.dateNUM = dateNUM;
    }

    public void setDateID(String dateID) {
        this.dateID = dateID;
    }

    public void setDateDAY(String dateDAY) {
        this.dateDAY = dateDAY;
    }

    public void setDateContent(String dateContent) {
        this.dateContent = dateContent;
    }

    public void setPetNUM(String petNUM) {
        this.petNUM = petNUM;
    }

    public String getDateNUM() {
        return dateNUM;
    }

    public String getDateID() {
        return dateID;
    }

    public String getDateDAY() {
        return dateDAY;
    }

    public String getDateContent() {
        return dateContent;
    }

    public String getPetNUM() {
        return petNUM;
    }
}
