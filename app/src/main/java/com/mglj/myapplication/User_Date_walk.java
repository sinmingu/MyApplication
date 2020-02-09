package com.mglj.myapplication;

public class User_Date_walk {

    String walkID;
    String walkDAY;
    String walkNUM;

    public User_Date_walk( String walkID, String walkDAY, String walkNUM) {

        this.walkID = walkID;
        this.walkDAY = walkDAY;
        this.walkNUM = walkNUM;
    }

    public String getWalkID() {
        return walkID;
    }

    public String getWalkDAY() {
        return walkDAY;
    }

    public String getWalkNUM() {
        return walkNUM;
    }

    public void setWalkID(String walkID) {
        this.walkID = walkID;
    }

    public void setWalkDAY(String walkDAY) {
        this.walkDAY = walkDAY;
    }

    public void setWalkNUM(String walkNUM) {
        this.walkNUM = walkNUM;
    }

}
