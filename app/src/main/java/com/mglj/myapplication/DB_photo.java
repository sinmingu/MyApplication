package com.mglj.myapplication;

public class DB_photo {

    String photoNUM;
    String photoID;
    String photoTITLE;
    String photoCONTENT;
    String photoDATE;
    String photoNAME;

    public DB_photo(String photoNUM, String photoID, String photoTITLE, String photoCONTENT, String photoDATE, String photoNAME) {
        this.photoNUM = photoNUM;
        this.photoID = photoID;
        this.photoTITLE = photoTITLE;
        this.photoCONTENT = photoCONTENT;
        this.photoDATE = photoDATE;
        this.photoNAME = photoNAME;
    }

    public String getPhotoNUM() {
        return photoNUM;
    }

    public String getPhotoID() {
        return photoID;
    }

    public String getPhotoTITLE() {
        return photoTITLE;
    }

    public String getPhotoCONTENT() {
        return photoCONTENT;
    }

    public String getPhotoDATE() {
        return photoDATE;
    }

    public String getPhotoNAME() {
        return photoNAME;
    }

    public void setPhotoNUM(String photoNUM) {
        this.photoNUM = photoNUM;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public void setPhotoTITLE(String photoTITLE) {
        this.photoTITLE = photoTITLE;
    }

    public void setPhotoCONTENT(String photoCONTENT) {
        this.photoCONTENT = photoCONTENT;
    }

    public void setPhotoDATE(String photoDATE) {
        this.photoDATE = photoDATE;
    }

    public void setPhotoNAME(String photoNAME) {
        this.photoNAME = photoNAME;
    }
}
