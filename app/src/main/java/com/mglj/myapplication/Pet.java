package com.mglj.myapplication;

public class Pet {

    String petNum, petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus;




    public Pet(String petNum, String petID, String petName, String petType, String petAge, String petSex, String petBir, String petWeight, String petStatus) {
        this.petNum = petNum;
        this.petID = petID;
        this.petName = petName;
        this.petType = petType;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petBir = petBir;
        this.petWeight = petWeight;
        this.petStatus = petStatus;
    }

    public String getPetNum() {
        return petNum;
    }

    public String getPetID() {
        return petID;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetType() {
        return petType;
    }

    public String getPetAge() {
        return petAge;
    }

    public String getPetSex() {
        return petSex;
    }

    public String getPetBir() {
        return petBir;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetNum(String petNum) {
        this.petNum = petNum;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public void setPetBir(String petBir) {
        this.petBir = petBir;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(String petStatus) {
        this.petStatus = petStatus;
    }

}
