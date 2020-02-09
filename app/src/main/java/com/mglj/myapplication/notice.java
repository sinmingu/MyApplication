package com.mglj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class notice extends AppCompatActivity {

    ListView notice_list;
    private NoticeListAdapter adapter;
    private List<DB_Notice> db_noticeList;

    ImageView notice_cancle, notice_can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        final Intent intent = getIntent();

        notice_list = (ListView)findViewById(R.id.notice_list);
        db_noticeList = new ArrayList<DB_Notice>();
        adapter = new NoticeListAdapter(getApplicationContext(), db_noticeList);
        notice_list.setAdapter(adapter);

        notice_cancle =(ImageView)findViewById(R.id.notice_cancle);
        notice_can = (ImageView)findViewById(R.id.notice_can);

        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(notice_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(notice_can);

        // JSON 데이터 받기
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("noticeList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count= 0;
            String noNUM, noTITLE, noCONTENT, noDATE, noSTATUS;

            while(count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                noNUM = object.getString("noNUM");
                noTITLE = object.getString("noTITLE");
                noCONTENT = object.getString("noCONTENT");
                noDATE = object.getString("noDATE");
                noSTATUS = object.getString("noSTATUS");

                DB_Notice db_notice = new DB_Notice(noNUM, noTITLE, noCONTENT, noDATE, noSTATUS);
                db_noticeList.add(db_notice);

                count++;
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        //



        notice_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent1 = new Intent(notice.this,Detail_notice.class);
                intent1.putExtra("content",db_noticeList.get(i).getNoCONTENT());
                intent1.putExtra("title",db_noticeList.get(i).getNoTITLE());
                intent1.putExtra("date",db_noticeList.get(i).getNoDATE());
                startActivity(intent1);




            }
        });


        notice_cancle.setOnClickListener(new View.OnClickListener() {
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
