package com.mglj.myapplication;

public class DB_Notice {

    String noNUM;
    String noTITLE;
    String noCONTENT;
    String noDATE;
    String noSTATUS;

    public DB_Notice(String noNUM, String noTITLE, String noCONTENT, String noDATE, String noSTATUS) {
        this.noNUM = noNUM;
        this.noTITLE = noTITLE;
        this.noCONTENT = noCONTENT;
        this.noDATE = noDATE;
        this.noSTATUS = noSTATUS;
    }

    public void setNoNUM(String noNUM) {
        this.noNUM = noNUM;
    }

    public void setNoTITLE(String noTITLE) {
        this.noTITLE = noTITLE;
    }

    public void setNoCONTENT(String noCONTENT) {
        this.noCONTENT = noCONTENT;
    }

    public void setNoDATE(String noDATE) {
        this.noDATE = noDATE;
    }

    public void setNoSTATUS(String noSTATUS) {
        this.noSTATUS = noSTATUS;
    }

    public String getNoNUM() {
        return noNUM;
    }

    public String getNoTITLE() {
        return noTITLE;
    }

    public String getNoCONTENT() {
        return noCONTENT;
    }

    public String getNoDATE() {
        return noDATE;
    }

    public String getNoSTATUS() {
        return noSTATUS;
    }
}
