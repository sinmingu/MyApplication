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

public class preList extends AppCompatActivity {

    ListView pre_list;
    private preListAdapter adapter;
    private List<DB_preList> db_preLists;
    ImageView pre_cancle, pre_can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_list);

        final Intent intent = getIntent();

        pre_list = (ListView)findViewById(R.id.pre_list);
        db_preLists = new ArrayList<DB_preList>();
        adapter = new preListAdapter(getApplicationContext(), db_preLists);
        pre_list.setAdapter(adapter);
        pre_can = (ImageView)findViewById(R.id.pre_can);
        pre_cancle = (ImageView)findViewById(R.id.pre_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pre_can);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pre_cancle);

        // JSON 데이터 받기
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("preList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count= 0;
            String preNUM, preTITLE, preCONTENT, preDATE;

            while(count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                preNUM = object.getString("preNUM");
                preTITLE = object.getString("preTITLE");
                preCONTENT = object.getString("preCONTENT");
                preDATE = object.getString("preDATE");


                DB_preList db_preList = new DB_preList(preNUM, preTITLE, preCONTENT, preDATE);
                db_preLists.add(db_preList);

                count++;
            }
        }catch(Exception e){
            e.getStackTrace();
        }

        pre_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent1 = new Intent(preList.this,Detail_pre.class);
                intent1.putExtra("content",db_preLists.get(i).getPreCONTENT());
                intent1.putExtra("preNum", db_preLists.get(i).getPreNum());
                intent1.putExtra("preTITLE", db_preLists.get(i).getPreTITLE());
                intent1.putExtra("preDATE",db_preLists.get(i).getPreDATE());
                startActivity(intent1);

            }
        });

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

