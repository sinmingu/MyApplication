package com.mglj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_pre extends AppCompatActivity {

    TextView pre_detail_content, de_pre_title, de_pre_date;

    ImageView img_pre, pre_cancle, pre_can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pre);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String preNum = intent.getStringExtra("preNum");
        String preTITLE = intent.getStringExtra("preTITLE");
        String preDATE = intent.getStringExtra("preDATE");
        pre_detail_content = (TextView)findViewById(R.id.p_content);
        pre_cancle = (ImageView)findViewById(R.id.pre_cancle);
        pre_can = (ImageView)findViewById(R.id.pre_can);

        de_pre_title = (TextView)findViewById(R.id.de_pre_title);
        de_pre_date = (TextView)findViewById(R.id.de_pre_date);

        de_pre_title.setText(preTITLE);
        de_pre_date.setText(preDATE.substring(0,10));

        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pre_cancle);

        img_pre = (ImageView)findViewById(R.id.img_pre);

        if(preNum.equals("1")){
            Glide.with(this).load(R.drawable.pre01).fitCenter().into(img_pre);
        }
        else if(preNum.equals("2")){
            Glide.with(this).load(R.drawable.pre02).fitCenter().into(img_pre);
        }
        else if(preNum.equals("3")){
            Glide.with(this).load(R.drawable.pre03).fitCenter().into(img_pre);
        }
        else if(preNum.equals("4")){
            Glide.with(this).load(R.drawable.pre04).fitCenter().into(img_pre);
        }
        else if(preNum.equals("5")){
            Glide.with(this).load(R.drawable.pre05).fitCenter().into(img_pre);
        }
        else if(preNum.equals("6")){
            Glide.with(this).load(R.drawable.pre06).fitCenter().into(img_pre);
        }

        pre_detail_content.setText(content);

        pre_cancle.setOnClickListener(new View.OnClickListener() {
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
