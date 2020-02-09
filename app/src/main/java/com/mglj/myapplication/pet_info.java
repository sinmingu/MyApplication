package com.mglj.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class pet_info extends AppCompatActivity {

    ImageView info_no_cancle, info_no_can;
    String userID;
    TextView title_text;
    String info_ID;
    ImageView info_pet_image1;

    TextView info_petname, info_petbir, info_petsex, info_petweight, info_petjoong, info_petwater, info_petfood;
    TextView info_username, info_userheight, info_userweight, modify_userweight, modify_userheight;

    EditText edit_userweight, edit_userheight;

    ArrayList<Pet> petList;
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        info_ID = intent.getStringExtra("userID");


        title_text = (TextView)findViewById(R.id.title_text);

        info_pet_image1 = (ImageView)findViewById(R.id.info_pet_image1);
        Glide.with(this).load(R.drawable.sub_img).fitCenter().into(info_pet_image1);


        info_petname = (TextView)findViewById(R.id.info_petname);
        info_petbir = (TextView)findViewById(R.id.info_petbir);
        info_petsex = (TextView)findViewById(R.id.info_petsex);
        info_petweight = (TextView)findViewById(R.id.info_petweight);
        info_petjoong = (TextView)findViewById(R.id.info_petjoong);
        info_petwater = (TextView)findViewById(R.id.info_petwater);
        info_petfood = (TextView)findViewById(R.id.info_petfood);

        info_username = (TextView)findViewById(R.id.info_username);
        info_userheight = (TextView)findViewById(R.id.info_userheight);
        info_userweight = (TextView)findViewById(R.id.info_userweight);

        modify_userweight = (TextView)findViewById(R.id.modify_userweight);
        modify_userheight = (TextView)findViewById(R.id.modify_userheight);

        info_no_cancle = (ImageView)findViewById(R.id.info_no_cancle);
        info_no_can = (ImageView)findViewById(R.id.info_no_can);

        edit_userweight = (EditText)findViewById(R.id.edit_userweight);
        edit_userheight = (EditText)findViewById(R.id.edit_userheight);

        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(info_no_can);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(info_no_cancle);

        new BackgroundTask_user_pet().execute();
        new BackgroundTask_user_screen().execute();

        modify_userheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(modify_userheight.getText().toString().equals("수정")){
                    modify_userheight.setText("확인");
                    edit_userheight.setVisibility(View.VISIBLE);
                    info_userheight.setVisibility(View.GONE);
                }
                else{
                    if(edit_userheight.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        modify_height();
                        modify_userheight.setText("수정");
                        edit_userheight.setVisibility(View.GONE);
                        info_userheight.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        modify_userweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(modify_userweight.getText().toString().equals("수정")){
                    modify_userweight.setText("확인");
                    edit_userweight.setVisibility(View.VISIBLE);
                    info_userweight.setVisibility(View.GONE);
                }
                else{
                    if(edit_userweight.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        modify_weight();
                        modify_userweight.setText("수정");
                        edit_userweight.setVisibility(View.GONE);
                        info_userweight.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        info_no_cancle.setOnClickListener(new View.OnClickListener() {
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

    // 사용자 펫 아이디 불러오기
    class BackgroundTask_user_pet extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/pet_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!= null){
                    stringBuilder.append((temp + "\n"));
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }
            catch(Exception e){
                e.getStackTrace();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            petList = new ArrayList<Pet>();

            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String petNum, petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus;

                while(count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);
                    petNum = object.getString("petNum");
                    petID = object.getString("petID");
                    petName = object.getString("petName");
                    petType = object.getString("petType");
                    petAge = object.getString("petAge");
                    petSex = object.getString("petSex");
                    petBir = object.getString("petBir");
                    petWeight = object.getString("petWeight");
                    petStatus = object.getString("petStatus");

                    if (petID.equals(userID)) {
                        Pet pet = new Pet(petNum, petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus);
                        petList.add(pet);
                    }

                    count++;

                }
                Intent intent = getIntent();
                int pet_status = intent.getIntExtra("pet",0);

                if(pet_status==1) {
                    info_petname.setText(petList.get(0).getPetName());
                    info_petweight.setText(petList.get(0).getPetWeight());
                    info_petsex.setText(petList.get(0).getPetSex());
                    info_petbir.setText(petList.get(0).getPetBir());
                    info_petjoong.setText(petList.get(0).getPetStatus());
                    int water = (int) (132*(Math.pow(Double.parseDouble(petList.get(0).getPetWeight()),0.75)));
                    info_petwater.setText(water+"ml");
                    info_petfood.setText(Integer.parseInt(petList.get(0).getPetWeight())*20+"g");
                }
                else if(pet_status==2){
                    info_petname.setText(petList.get(1).getPetName());
                    info_petweight.setText(petList.get(1).getPetWeight());
                    info_petsex.setText(petList.get(1).getPetSex());
                    info_petbir.setText(petList.get(1).getPetBir());
                    info_petjoong.setText(petList.get(1).getPetStatus());
                    int water = (int) (132*(Math.pow(Double.parseDouble(petList.get(1).getPetWeight()),0.75)));
                    info_petwater.setText(water+"ml");
                    info_petfood.setText(Integer.parseInt(petList.get(1).getPetWeight())*20+"g");
                }
                else if(pet_status==3){
                    info_petname.setText(petList.get(2).getPetName());
                    info_petweight.setText(petList.get(2).getPetWeight());
                    info_petsex.setText(petList.get(2).getPetSex());
                    info_petbir.setText(petList.get(2).getPetBir());
                    info_petjoong.setText(petList.get(2).getPetStatus());
                    int water = (int) (132*(Math.pow(Double.parseDouble(petList.get(2).getPetWeight()),0.75)));
                    info_petwater.setText(water+"ml");
                    info_petfood.setText(Integer.parseInt(petList.get(2).getPetWeight())*20+"g");
                }


            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

    // 유저 정보 불러오기 // 네이버 로그인
    class BackgroundTask_user_screen extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!= null){
                    stringBuilder.append((temp + "\n"));
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }
            catch(Exception e){
                e.getStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            userList = new ArrayList<User>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String userID, userPassword, userName, userAge, userHeight, userWeight;

                while(count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);
                    userID = object.getString("userID");
                    userPassword = object.getString("userPassword");
                    userName = object.getString("userName");
                    userAge = object.getString("userAge");
                    userHeight = object.getString("userHeight");
                    userWeight = object.getString("userWeight");

                    if (info_ID.equals(userID)) {
                        User user = new User(userID, userPassword, userName, userAge, userHeight, userWeight);
                        userList.add(user);
                    }

                    count++;

                }

                info_userheight.setText(userList.get(0).getUserHeight()+"cm");
                info_userweight.setText(userList.get(0).getUserWeight()+"kg");

            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

    public void modify_height(){
        // -------------------- 반려견 DB등록 시작 ----------------
        String ID = userID;
        int userHeight = Integer.parseInt(edit_userheight.getText().toString());

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        Response.Listener<String> pet_responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(pet_info.this);
                        builder.setMessage("수정 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new BackgroundTask_user_screen().execute();
                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(pet_info.this);
                        builder.setMessage("반려견 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }

            }
        };

        height_modifyRequest pet_registerRequest = new height_modifyRequest(ID, userHeight, pet_responseListener);
        RequestQueue pet_queue = Volley.newRequestQueue(pet_info.this);
        pet_queue.add(pet_registerRequest);


        // ------------------ 반려견 DB등록 끝 ---------------------
    }

    public void modify_weight(){
        // -------------------- 반려견 DB등록 시작 ----------------
        String ID = userID;
        int userWeight = Integer.parseInt(edit_userweight.getText().toString());

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        Response.Listener<String> pet_responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(pet_info.this);
                        builder.setMessage("수정 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new BackgroundTask_user_screen().execute();
                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(pet_info.this);
                        builder.setMessage("반려견 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }

            }
        };

        weight_modifyRequest pet_registerRequest = new weight_modifyRequest(ID, userWeight, pet_responseListener);
        RequestQueue pet_queue = Volley.newRequestQueue(pet_info.this);
        pet_queue.add(pet_registerRequest);


        // ------------------ 반려견 DB등록 끝 ---------------------
    }

}
