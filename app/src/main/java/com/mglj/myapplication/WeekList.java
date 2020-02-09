package com.mglj.myapplication;

import java.util.ArrayList;

public class WeekList {

    int dt;
    String dt_txt;

    WeekMain main;
    WeekWind wind;
    ArrayList<WeekWeather> weather;
//
    public WeekMain getMain() {
        return main;
    }

    public int getDt() {
        return dt;
    }

    //
    public ArrayList<WeekWeather> getWeather() {
        return weather;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public WeekWind getWind() {
        return wind;
    }

    public void setWind(WeekWind wind) {
        this.wind = wind;
    }
}
