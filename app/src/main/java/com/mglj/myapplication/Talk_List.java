package com.mglj.myapplication;

public class Talk_List {

    String talkNUM;
    String talkTITLE;
    String talkTITLE2;
    String talkTITLE3;
    String talkCONTENT;

    public Talk_List(String talkNUM, String talkTITLE, String talkTITLE2, String talkTITLE3, String talkCONTENT) {
        this.talkNUM = talkNUM;
        this.talkTITLE = talkTITLE;
        this.talkTITLE2 = talkTITLE2;
        this.talkTITLE3 = talkTITLE3;
        this.talkCONTENT = talkCONTENT;
    }

    public void setTalkNUM(String talkNUM) {
        this.talkNUM = talkNUM;
    }

    public void setTalkTITLE(String talkTITLE) {
        this.talkTITLE = talkTITLE;
    }

    public void setTalkTITLE2(String talkTITLE2) {
        this.talkTITLE2 = talkTITLE2;
    }

    public void setTalkTITLE3(String talkTITLE3) {
        this.talkTITLE3 = talkTITLE3;
    }

    public void setTalkCONTENT(String talkCONTENT) {
        this.talkCONTENT = talkCONTENT;
    }

    public String getTalkNUM() {
        return talkNUM;
    }

    public String getTalkTITLE() {
        return talkTITLE;
    }

    public String getTalkTITLE2() {
        return talkTITLE2;
    }

    public String getTalkTITLE3() {
        return talkTITLE3;
    }

    public String getTalkCONTENT() {
        return talkCONTENT;
    }
}
