package com.mglj.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class advertise_screen_first extends Fragment {

    ImageView adver02_img;

    public advertise_screen_first(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_advertise_screen_first, container, false);
        ImageView imageView = layout.findViewById(R.id.adver02_img);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),introduction.class);
                startActivity(intent);
            }
        });

        return layout;






    }


}
