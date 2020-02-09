package com.mglj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class introduction extends AppCompatActivity {

    ImageView intro_cancle, intro_foot, intro_sub, intro_can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        intro_cancle = (ImageView)findViewById(R.id.intro_cancle);
        intro_foot = (ImageView)findViewById(R.id.intro_foot);
        intro_sub = (ImageView)findViewById(R.id.intro_sub);
        intro_can = (ImageView)findViewById(R.id.intro_can);

        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(intro_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(intro_can);
        Glide.with(this).load(R.drawable.foot).fitCenter().into(intro_foot);
        Glide.with(this).load(R.drawable.sub_img).fitCenter().into(intro_sub);

        intro_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
