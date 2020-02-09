package com.mglj.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    Button login_btn, pet_btn;
    EditText userId, userPw;

    CheckBox auto_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = (EditText)findViewById(R.id.userId);
        userPw = (EditText)findViewById(R.id.userPw);

        login_btn = (Button)findViewById(R.id.login_btn);
        pet_btn = (Button)findViewById(R.id.pet_btn);

        auto_login = (CheckBox)findViewById(R.id.auto_login);

        // 로그인 버튼
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userID = userId.getText().toString();
                String userPassword = userPw.getText().toString();


                Response.Listener<String>  responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonReponse = new JSONObject(response);
                            boolean success = jsonReponse.getBoolean("success");
                            if(success){

                                // 관리자 아이디면 관리자 페이지로 이동
                                if(userID.equals("admin")){

                                    new BackgroundTask().execute();
//                                    Intent intent_admin = new Intent(getApplicationContext(), Management.class);
//                                    startActivity(intent_admin);

                                }
                                // 로그인 페이지로 이동
                                else {


                                    if(auto_login.isChecked()==true){

                                        String userID = jsonReponse.getString("userID");
                                        String userPassword = jsonReponse.getString("userPassword");
                                        String userName = jsonReponse.getString("userName");
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("userID", userID);
                                        intent.putExtra("userPassword", userPassword);
                                        intent.putExtra("userName", userName);

                                        SharedPreferences sharedPreferences = getSharedPreferences("LoginUser",MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("userID",userID); // key, value를 이용하여 저장하는 형태
                                        editor.putString("userPassword",userPassword);
                                        editor.putString("userName",userName);
                                        editor.commit();
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                        LoginActivity.this.startActivity(intent);

                                    }
                                    else {
                                        String userID = jsonReponse.getString("userID");
                                        String userPassword = jsonReponse.getString("userPassword");
                                        String userName = jsonReponse.getString("userName");
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("userID", userID);
                                        intent.putExtra("userPassword", userPassword);
                                        intent.putExtra("userName", userName);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        LoginActivity.this.startActivity(intent);
                                    }


                                }

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패했습니다.")
                                        .setNegativeButton("다시시도",null)
                                        .create()
                                        .show();

                            }

                        }
                        catch(Exception e){
                            e.getStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID,userPassword,responseListener);
                RequestQueue queue  = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);

            }
        });


        pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BackgroundTask_pet().execute();

            }
        });



    }


    // 회원 목록 불러오기
    class BackgroundTask extends AsyncTask<Void, Void, String> {

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
            Intent intent = new Intent(LoginActivity.this,Management.class);
            intent.putExtra("userList",result);
            LoginActivity.this.startActivity(intent);
        }
    }
    // 회원 목록 불러오기 끝

    // 로그인한 펫의 목록 불러오기
    class BackgroundTask_pet extends AsyncTask<Void, Void, String> {

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
            Intent intent = new Intent(LoginActivity.this,petTest.class);
            intent.putExtra("pet_List",result);
            LoginActivity.this.startActivity(intent);
        }
    }

    // 회원 목록 불러오기 끝
}
