package com.mglj.myapplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
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

public class add_pet extends AppCompatActivity {

    ImageView pre_cancle, pre_can;
    Button btn_pet_add;
    RadioGroup radioGroup1;
    int petNumber;
    EditText pet_name, pet_age, pet_weight;
    TextView pet_bir, pet_sex, pet_sex_status;
    String userID;
    ArrayList<Pet> petList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_pet);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        pet_name = (EditText)findViewById(R.id.pet_name);
        pet_age = (EditText)findViewById(R.id.pet_age);
        pet_sex = (TextView)findViewById(R.id.pet_sex);
        pet_weight = (EditText)findViewById(R.id.pet_weight);
        pet_bir = (TextView)findViewById(R.id.pet_bir) ;
        pre_cancle = (ImageView)findViewById(R.id.pre_cancle);
        pre_can = (ImageView)findViewById(R.id.pre_can);
        btn_pet_add = (Button)findViewById(R.id.btn_pet_add);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        pet_sex_status = (TextView)findViewById(R.id.pet_sex_status);

        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pre_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pre_can);

        //반려견 생일 입력
        pet_bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(add_pet.this, listener, 2013, 10, 22);
                dialog.show();

            }
        });

        pet_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CustomDialogSex customDialog = new CustomDialogSex(add_pet.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(pet_sex);
            }
        });

        pet_sex_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CustomDialogSexStatus customDialog = new CustomDialogSexStatus(add_pet.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(pet_sex_status);
            }
        });

        pre_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_pet_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pet_name.getText().toString().equals("")||pet_age.getText().toString().equals("")||pet_sex.getText().toString().equals("")||
                        pet_weight.getText().toString().equals("")||pet_bir.getText().toString().equals("")){
                    Toast.makeText(add_pet.this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else {

                    new BackgroundTask_user_pet().execute();
                    add_graph();
                    add_graph_speed();
                    add_graph_time();

                }
            }
        });

        // 소형, 중형, 대형 라디오 이벤트
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(i){
                    case R.id.radio0:
                        petNumber = 1;
                        break;
                    case R.id.radio1:
                        petNumber = 2;
                        break;
                    case R.id.radio2:
                        petNumber = 3;
                        break;

                }


            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void pet_add(){

        // -------------------- 반려견 DB등록 시작 ----------------
        String petID = userID;
        String petName = pet_name.getText().toString();
        int petType = petNumber;
        int petAge = Integer.parseInt(pet_age.getText().toString());
        String petSex = pet_sex.getText().toString();
        String petBir = pet_bir.getText().toString();
        int petWeight = Integer.parseInt(pet_weight.getText().toString());
        String petStatus = pet_sex_status.getText().toString();




        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        Response.Listener<String> pet_responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(add_pet.this);
                        builder.setMessage("새로운 펫을 추가했습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        pet_name.setText("");
                                        pet_age.setText("");
                                        pet_sex.setText("");
                                        pet_weight.setText("");
                                        pet_bir.setText("");


                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(add_pet.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
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

        pet_RegisterRequest pet_registerRequest = new pet_RegisterRequest(petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus, pet_responseListener);
        RequestQueue pet_queue = Volley.newRequestQueue(add_pet.this);
        pet_queue.add(pet_registerRequest);
        // ------------------ 반려견 DB등록 끝 ---------------------

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
                        Pet pet = new Pet(petNum, petID, petName, petType, petAge, petSex, petBir, petWeight,petStatus);
                        petList.add(pet);
                    }

                    count++;


                }
                if(petList.size()>=3){
                    Toast.makeText(add_pet.this, "펫은 3명까지 등록 가능합니다", Toast.LENGTH_SHORT).show();
                }else {
                    pet_add();
                }


            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            pet_bir.setText(year + "," + monthOfYear + "," + dayOfMonth);
            pet_bir.setTextColor(Color.BLACK);

        }

    };

    // graph 테이블 등록(펫)
    private void add_graph(){

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(add_pet.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        graph_add graph_add = new graph_add(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(add_pet.this);
        queue.add(graph_add);

    }

    // graph 테이블 등록(펫 달린 속도)
    private void add_graph_speed(){

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(add_pet.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        graph_add_speed graph_add = new graph_add_speed(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(add_pet.this);
        queue.add(graph_add);


    }

    // graph 테이블 등록(펫 달린 시간)
    private void add_graph_time(){

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(add_pet.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        graph_add_time graph_add = new graph_add_time(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(add_pet.this);
        queue.add(graph_add);


    }

}
