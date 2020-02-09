package com.mglj.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class advertise_screen_four extends Fragment {

    public advertise_screen_four(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.advertise_screen_four, container, false);
        AdView adBanner;

        MobileAds.initialize(getContext(),"ca-app-pub-5784293657699097~3465156383");

        adBanner = layout.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adBanner.loadAd(adRequest);

        return layout;
    }



}
