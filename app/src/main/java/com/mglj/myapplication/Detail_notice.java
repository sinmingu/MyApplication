package com.mglj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_notice extends AppCompatActivity {

    TextView detail_content, de_no_title, de_no_date;
    ImageView de_no_cancle, de_no_can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notice);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");

        de_no_date = (TextView)findViewById(R.id.de_no_date);
        de_no_title = (TextView)findViewById(R.id.de_no_title);

        de_no_title.setText(title);
        de_no_date.setText(date.substring(0,10));
        de_no_cancle = (ImageView)findViewById(R.id.de_no_cancle);
        de_no_can = (ImageView)findViewById(R.id.de_no_can);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(de_no_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(de_no_can);

        detail_content = (TextView)findViewById(R.id.detail_content);
        detail_content.setText(content);

        de_no_cancle.setOnClickListener(new View.OnClickListener() {
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
