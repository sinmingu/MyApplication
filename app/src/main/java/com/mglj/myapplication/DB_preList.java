package com.mglj.myapplication;

public class DB_preList {

    String preNum;
    String preTITLE;
    String preCONTENT;
    String preDATE;

    public DB_preList(String preNum, String preTITLE, String preCONTENT, String preDATE) {
        this.preNum = preNum;
        this.preTITLE = preTITLE;
        this.preCONTENT = preCONTENT;
        this.preDATE = preDATE;
    }

    public void setPreNum(String preNum) {
        this.preNum = preNum;
    }

    public void setPreTITLE(String preTITLE) {
        this.preTITLE = preTITLE;
    }

    public void setPreCONTENT(String preCONTENT) {
        this.preCONTENT = preCONTENT;
    }

    public void setPreDATE(String preDATE) {
        this.preDATE = preDATE;
    }

    public String getPreNum() {
        return preNum;
    }

    public String getPreTITLE() {
        return preTITLE;
    }

    public String getPreCONTENT() {
        return preCONTENT;
    }

    public String getPreDATE() {
        return preDATE;
    }

}
