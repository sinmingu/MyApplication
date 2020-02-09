package com.mglj.myapplication;

public class pet_TalkList {

    String talkContent;
    String talk_user;
    String talk_date;

    public pet_TalkList(String talkContent, String talk_user, String talk_date) {
        this.talkContent = talkContent;
        this.talk_user = talk_user;
        this.talk_date = talk_date;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public void setTalk_user(String talk_user) {
        this.talk_user = talk_user;
    }

    public void setTalk_date(String talk_date) {
        this.talk_date = talk_date;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public String getTalk_user() {
        return talk_user;
    }

    public String getTalk_date() {
        return talk_date;
    }

}