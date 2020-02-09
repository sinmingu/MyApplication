package com.mglj.myapplication;

public class Location {

    String locationNUM;
    String locationLAT;
    String locationLON;
    String locationTITLE;
    String locationCONTENT;
    String locationTYPE;

    public Location(String locationNUM, String locationLAT, String locationLON, String locationTITLE, String locationCONTENT, String locationTYPE) {
        this.locationNUM = locationNUM;
        this.locationLAT = locationLAT;
        this.locationLON = locationLON;
        this.locationTITLE = locationTITLE;
        this.locationCONTENT = locationCONTENT;
        this.locationTYPE = locationTYPE;
    }

    public void setLocationNUM(String locationNUM) {
        this.locationNUM = locationNUM;
    }

    public void setLocationLAT(String locationLAT) {
        this.locationLAT = locationLAT;
    }

    public void setLocationLON(String locationLON) {
        this.locationLON = locationLON;
    }

    public void setLocationTITLE(String locationTITLE) {
        this.locationTITLE = locationTITLE;
    }

    public void setLocationCONTENT(String locationCONTENT) {
        this.locationCONTENT = locationCONTENT;
    }

    public void setLocationTYPE(String locationTYPE) {
        this.locationTYPE = locationTYPE;
    }

    public String getLocationNUM() {
        return locationNUM;
    }

    public String getLocationLAT() {
        return locationLAT;
    }

    public String getLocationLON() {
        return locationLON;
    }

    public String getLocationTITLE() {
        return locationTITLE;
    }

    public String getLocationCONTENT() {
        return locationCONTENT;
    }

    public String getLocationTYPE() {
        return locationTYPE;
    }
}
