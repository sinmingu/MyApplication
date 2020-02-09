package com.mglj.myapplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.mglj.myapplication.SessionCallback.kakao_ID;
import static com.mglj.myapplication.SessionCallback.kakao_status;

public class SettingActivity extends AppCompatActivity {

    ImageView img_setting_back;
    TextView setting_ok, userNick, userNick_change, userId, text_pet_add;
    TextView password_re;
    EditText edit_pass_re, edit_pass, edit_userNick, edit_petName, edit_petBir;
    String userID;
    LinearLayout pet_layout2, pet_layout3;

    int petNUM;

    ArrayList<Pet_Graph> pet_graphList;
    ArrayList<Pet_Graph> pet_graphList_speed;
    ArrayList<Pet_Graph> pet_graphList_time;

    int bir_status;

    Intent intent;

    TextView user_pet_name, user_pet_name_change, user_pet_bir_change, user_pet_bir, user_delete;
    TextView user_pet_name2, user_pet_name_change2, user_pet_bir_change2, user_pet_bir2;
    EditText edit_petName2, edit_petBir2;
    TextView user_pet_name3, user_pet_name_change3, user_pet_bir_change3, user_pet_bir3;
    EditText edit_petName3, edit_petBir3;
    TextView delete_pet2, delete_pet3;
    Button btn_logout;
    ArrayList<Pet> petList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        intent = getIntent();

        img_setting_back = (ImageView)findViewById(R.id.img_setting_back);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(img_setting_back);

        setting_ok = (TextView)findViewById(R.id.setting_ok);
        userNick = (TextView)findViewById(R.id.userNick);
        userNick_change = (TextView)findViewById(R.id.userNick_change);
        userId = (TextView)findViewById(R.id.userId);

        edit_pass_re = (EditText)findViewById(R.id.edit_pass_re);
        edit_pass = (EditText)findViewById(R.id.edit_pass);

        user_pet_name = (TextView)findViewById(R.id.user_petname);
        user_pet_name_change = (TextView)findViewById(R.id.user_pet_name_change);
        user_pet_bir = (TextView)findViewById(R.id.user_pet_bir);
        user_pet_bir_change = (TextView)findViewById(R.id.user_pet_bir_change);

        user_pet_name2 = (TextView)findViewById(R.id.user_petname2);
        user_pet_name_change2 = (TextView)findViewById(R.id.user_pet_name_change2);
        user_pet_bir2 = (TextView)findViewById(R.id.user_pet_bir2);
        user_pet_bir_change2 = (TextView)findViewById(R.id.user_pet_bir_change2);

        user_pet_name3 = (TextView)findViewById(R.id.user_petname3);
        user_pet_name_change3 = (TextView)findViewById(R.id.user_pet_name_change3);
        user_pet_bir3 = (TextView)findViewById(R.id.user_pet_bir3);
        user_pet_bir_change3 = (TextView)findViewById(R.id.user_pet_bir_change3);

        btn_logout = (Button)findViewById(R.id.btn_logout_user);
        user_delete = (TextView)findViewById(R.id.user_delete);

        userNick.setText(intent.getStringExtra("userName"));
        userId.setText(intent.getStringExtra("userID"));
        user_pet_name.setText(intent.getStringExtra("petName"));
        user_pet_bir.setText(intent.getStringExtra("petBir"));

        userID = intent.getStringExtra("userID");

        edit_userNick=(EditText)findViewById(R.id.edit_userNick);
        edit_petName = (EditText)findViewById(R.id.edit_petName);
        edit_petBir = (EditText)findViewById(R.id.edit_petBir);

        text_pet_add = (TextView)findViewById(R.id.text_pet_add);
        pet_layout2 = (LinearLayout)findViewById(R.id.pet_layout2);
        pet_layout3 = (LinearLayout)findViewById(R.id.pet_layout3);

        edit_petName2 = (EditText)findViewById(R.id.edit_petName2);
        edit_petName3 = (EditText)findViewById(R.id.edit_petName3);

        delete_pet2 = (TextView)findViewById(R.id.delete_pet2);
        delete_pet3 = (TextView)findViewById(R.id.delete_pet3);

        password_re = (TextView)findViewById(R.id.password_re);

        img_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        new BackgroundTask_user_pet().execute();
        new BackgroundTask_graphList().execute();
        new BackgroundTask_graphList_speed().execute();
        new BackgroundTask_graphList_time().execute();

        // 아이디 수정
        userNick_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userNick_change.getText().toString().equals("확인")){
                        if(edit_userNick.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"아이디를 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        userNick.setText(edit_userNick.getText().toString());
                        userNick_change.setText("수정");
                        edit_userNick.setVisibility(View.GONE);
                        userNick.setVisibility(View.VISIBLE);
                        user_update_name();
                        return;
                    }

                }
                userNick_change.setText("확인");
                edit_userNick.setVisibility(View.VISIBLE);
                userNick.setVisibility(View.GONE);
            }
        });

        // 펫 이름 수정 ( 1 )
        user_pet_name_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_pet_name_change.getText().toString().equals("확인")){
                    if(edit_petName.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"아이디를 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        user_pet_name.setText(edit_petName.getText().toString());
                        user_pet_update_name(Integer.parseInt(petList.get(0).getPetNum()));
                        user_pet_name_change.setText("수정");
                        edit_petName.setVisibility(View.GONE);
                        user_pet_name.setVisibility(View.VISIBLE);
                        return;
                    }

                }
                user_pet_name_change.setText("확인");
                edit_petName.setVisibility(View.VISIBLE);
                user_pet_name.setVisibility(View.GONE);
            }
        });

        // 펫 이름 수정 ( 2 )
        user_pet_name_change2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_pet_name_change2.getText().toString().equals("확인")){
                    if(edit_petName2.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"아이디를 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        user_pet_name2.setText(edit_petName2.getText().toString());
                        user_pet_update_name(Integer.parseInt(petList.get(1).getPetNum()));
                        user_pet_name_change2.setText("수정");
                        edit_petName2.setVisibility(View.GONE);
                        user_pet_name2.setVisibility(View.VISIBLE);
                        return;
                    }

                }
                user_pet_name_change2.setText("확인");
                edit_petName2.setVisibility(View.VISIBLE);
                user_pet_name2.setVisibility(View.GONE);
            }
        });

        // 펫 이름 수정 ( 3 )
        user_pet_name_change3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_pet_name_change3.getText().toString().equals("확인")){
                    if(edit_petName3.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"아이디를 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        user_pet_name3.setText(edit_petName3.getText().toString());
                        user_pet_update_name(Integer.parseInt(petList.get(2).getPetNum()));
                        user_pet_name_change3.setText("수정");
                        edit_petName3.setVisibility(View.GONE);
                        user_pet_name3.setVisibility(View.VISIBLE);
                        return;
                    }

                }
                user_pet_name_change3.setText("확인");
                edit_petName3.setVisibility(View.VISIBLE);
                user_pet_name3.setVisibility(View.GONE);
            }
        });

        password_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edit_pass.getText().toString().equals("")||edit_pass_re.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "공백이 있습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(!edit_pass.getText().toString().equals(edit_pass_re.getText().toString())){
                    Toast.makeText(getApplicationContext(),"비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    user_update_password();
                    edit_pass_re.setText("");
                    edit_pass.setText("");
                }

            }
        });

        //펫 생년월일 수정 ( 1 )
        user_pet_bir_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_pet_bir_change.getText().toString().equals("확인")){
                    user_pet_update_bir(Integer.parseInt(petList.get(0).getPetNum()));
                    user_pet_bir_change.setText("수정");
                }
                else{
                    bir_status = 1;
                    DatePickerDialog dialog = new DatePickerDialog(SettingActivity.this, listener, 2013, 10, 22);
                    dialog.show();
                    user_pet_bir_change.setText("확인");

                }

            }
        });

        //펫 생년월일 수정 ( 2 )
        user_pet_bir_change2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_pet_bir_change2.getText().toString().equals("확인")){
                    user_pet_update_bir(Integer.parseInt(petList.get(1).getPetNum()));
                    user_pet_bir_change2.setText("수정");
                }
                else{
                    bir_status = 2;
                    DatePickerDialog dialog = new DatePickerDialog(SettingActivity.this, listener, 2013, 10, 22);
                    dialog.show();
                    user_pet_bir_change2.setText("확인");

                }

            }
        });


        //펫 생년월일 수정 ( 3 )
        user_pet_bir_change3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_pet_bir_change3.getText().toString().equals("확인")){
                    user_pet_update_bir(Integer.parseInt(petList.get(2).getPetNum()));
                    user_pet_bir_change3.setText("수정");
                }
                else{
                    bir_status = 3;
                    DatePickerDialog dialog = new DatePickerDialog(SettingActivity.this, listener, 2013, 10, 22);
                    dialog.show();
                    user_pet_bir_change3.setText("확인");

                }

            }
        });


        // 로그아웃
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        UserManagement.requestLogout(new LogoutResponseCallback() {

                            @Override

                            public void onCompleteLogout() {
                                kakao_ID = "";
                                kakao_status = 0 ;
                            }

                        });

                        SharedPreferences sharedPreferences = getSharedPreferences("LoginUser",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userID",""); // key, value를 이용하여 저장하는 형태
                        editor.putString("userPassword","");
                        editor.putString("userName","");
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                    }
                })
                        .create()
                        .show();


            }
        });

        // 회원탈퇴
        user_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("정말 탈퇴하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SharedPreferences sharedPreferences = getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(userID+"text",""); // key, value를 이용하여 저장하는 형태
                        editor.putString(userID+"text2",""); // key, value를 이용하여 저장하는 형태
                        editor.putString(userID+"text3",""); // key, value를 이용하여 저장하는 형태
                        editor.commit();

                        user_delete();
//                        Intent intent = new Intent(getApplicationContext(),MainScreen.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);


                    }
                })
                        .create()
                        .show();

            }
        });

        // 펫 삭제
        delete_pet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }

                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete_pet(Integer.parseInt(petList.get(1).getPetNum()));
                        delete_pet_graph(Integer.parseInt(pet_graphList.get(1).getGraphNUM()));
                        delete_pet_graph_speed(Integer.parseInt(pet_graphList_speed.get(1).getGraphNUM()));
                        delete_pet_graph_time(Integer.parseInt(pet_graphList_speed.get(1).getGraphNUM()));
                        petNUM = 2;
                        delete_date();
                        delete_date_walk();

                        SharedPreferences sharedPreferences = getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(userID+"text2",""); // key, value를 이용하여 저장하는 형태
                        editor.commit();

                    }
                })
                        .create()
                        .show();



            }
        });

        delete_pet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete_pet(Integer.parseInt(petList.get(2).getPetNum()));
                        delete_pet_graph(Integer.parseInt(pet_graphList.get(2).getGraphNUM()));
                        delete_pet_graph_speed(Integer.parseInt(pet_graphList_speed.get(2).getGraphNUM()));
                        delete_pet_graph_time(Integer.parseInt(pet_graphList_speed.get(2).getGraphNUM()));
                        petNUM = 3;
                        delete_date();
                        delete_date_walk();

                        SharedPreferences sharedPreferences = getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(userID+"text3",""); // key, value를 이용하여 저장하는 형태
                        editor.commit();

                    }
                })
                        .create()
                        .show();


            }
        });

        // 확인버튼
        setting_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(!edit_pass.getText().toString().equals("")&&!edit_pass_re.getText().toString().equals("")&&edit_pass.getText().toString().equals(edit_pass_re.getText().toString())){
//                    user_update_name();
//                    user_pet_update_name();
//                    user_pet_update_bir();
//                    onBackPressed();
//                    user_update_password();
//                }
//                else {
//                    Toast.makeText(SettingActivity.this, "비밀번호가 서로 다릅니다", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        text_pet_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), add_pet.class);
                intent.putExtra("userID",userID);
                startActivity(intent);

            }
        });

    }

    // 뒤로가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // 유저 이름 업데이트
    private void user_update_name(){

        String userID = intent.getStringExtra("userID");
        String userName = userNick.getText().toString();
        userNick.setText(userName);

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        userName_updateRequest userName_update = new userName_updateRequest(userName, userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(userName_update);

    }

    // 유저 비밀번호 업데이트
    private void user_update_password(){

        String userID = intent.getStringExtra("userID");
        String userPassword = edit_pass.getText().toString();

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("비밀번호가 변경되었습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        User_PasswordUpdateRequest user_passwordUpdateRequest = new User_PasswordUpdateRequest(userPassword, userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(user_passwordUpdateRequest);

    }

    // 펫 이름 수정
    private void user_pet_update_name(int preNum){

        int petNUM = preNum;
//        Toast.makeText(getApplicationContext(), petNUM+"", Toast.LENGTH_SHORT).show();
//        String petID = userID;
        String petName="";
        if(preNum == Integer.parseInt(petList.get(0).getPetNum())){
            petName = user_pet_name.getText().toString();
        }
        else if(preNum == Integer.parseInt(petList.get(1).getPetNum())){
            petName = user_pet_name2.getText().toString();
        }
        else if(preNum == Integer.parseInt(petList.get(2).getPetNum())){
            petName = user_pet_name3.getText().toString();
        }


        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        petName_UpdateRequest petName_updateRequest = new petName_UpdateRequest(petName, petNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(petName_updateRequest);

    }

    // 펫 생년월일 수정
    private void user_pet_update_bir(int preNum){

        int petNUM = preNum;
        String petBir="";
        if(preNum == Integer.parseInt(petList.get(0).getPetNum())){
            petBir = user_pet_bir.getText().toString();
        }else if(preNum == Integer.parseInt(petList.get(1).getPetNum())){
            petBir = user_pet_bir2.getText().toString();
        }else if(preNum == Integer.parseInt(petList.get(2).getPetNum())){
            petBir = user_pet_bir3.getText().toString();
        }

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        petBir_UpdateRequest petBir_updateRequest = new petBir_UpdateRequest(petBir, petNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(petBir_updateRequest);

    }

    // 회원탈퇴
    private void user_delete(){

        String userID = intent.getStringExtra("userID");

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("회원탈퇴 되었습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(getApplicationContext(),MainScreen.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        User_DeleteRequest user_deleteRequest = new User_DeleteRequest(userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(user_deleteRequest);

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
                pet_layout2.setVisibility(View.GONE);

                if(petList.size()>=2) {
                    pet_layout2.setVisibility(View.VISIBLE);
                    pet_layout3.setVisibility(View.GONE);
                    user_pet_name2.setText(petList.get(1).getPetName());
                    user_pet_bir2.setText(petList.get(1).getPetBir());
                }
                if(petList.size()>=3) {
                    pet_layout3.setVisibility(View.VISIBLE);
                    user_pet_name3.setText(petList.get(2).getPetName());
                    user_pet_bir3.setText(petList.get(2).getPetBir());
                }

            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            if(bir_status==1){
                user_pet_bir.setText(year + "," + monthOfYear + "," + dayOfMonth);
                user_pet_bir.setTextColor(Color.BLACK);
            }
            else if(bir_status==2){
                user_pet_bir2.setText(year + "," + monthOfYear + "," + dayOfMonth);
                user_pet_bir2.setTextColor(Color.BLACK);
            }
            else if(bir_status==3){
                user_pet_bir3.setText(year + "," + monthOfYear + "," + dayOfMonth);
                user_pet_bir3.setTextColor(Color.BLACK);
            }

        }

    };

    // 펫삭제
    public void delete_pet(int preNum){

        int petNUM = preNum;

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("펫을 삭제했습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new BackgroundTask_user_pet().execute();
                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        Pet_deleteRequest pet_deleteRequest = new Pet_deleteRequest(petNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(pet_deleteRequest);

    }

    // 펫 그래프 삭제
    public void delete_pet_graph(int preNum){

        int graphNUM = preNum;

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        Pet_deleteGraphRequest pet_deleteGraphRequest = new Pet_deleteGraphRequest(graphNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(pet_deleteGraphRequest);

    }

    // 펫 그래프 삭제
    public void delete_pet_graph_speed(int preNum){

        int graphNUM = preNum;

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        Pet_deleteGraphSpeedRequest pet_deleteGraphRequest = new Pet_deleteGraphSpeedRequest(graphNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(pet_deleteGraphRequest);

    }

    // 펫 그래프 삭제
    public void delete_pet_graph_time(int preNum){

        int graphNUM = preNum;

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        Pet_deleteGraphTimeRequest pet_deleteGraphRequest = new Pet_deleteGraphTimeRequest(graphNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(pet_deleteGraphRequest);

    }

    // 그래프 보기 시작(펫)
    class BackgroundTask_graphList extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/graph_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            pet_graphList = new ArrayList<Pet_Graph>();
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

            pet_graphList = new ArrayList<Pet_Graph>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    graphNUM = object.getString("graphNUM");
                    graphID = object.getString("graphID");
                    TODAY = object.getString("TODAY");
                    TODAY1 = object.getString("TODAY1");
                    TODAY2 = object.getString("TODAY2");
                    TODAY3 = object.getString("TODAY3");
                    TODAY4 = object.getString("TODAY4");
                    TODAY5 = object.getString("TODAY5");
                    TODAY6 = object.getString("TODAY6");



                    if(userID.equals(graphID)){

                        Pet_Graph pet_graph = new Pet_Graph(graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6);
                        pet_graphList.add(pet_graph);
                    }

                    count++;

                }
            }catch(Exception e){

                e.getStackTrace();

            }

        }

    }

    // 그래프 보기 시작(펫)
    class BackgroundTask_graphList_speed extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/graph_speed_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            pet_graphList_speed = new ArrayList<Pet_Graph>();
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

            pet_graphList_speed = new ArrayList<Pet_Graph>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    graphNUM = object.getString("graphNUM");
                    graphID = object.getString("graphID");
                    TODAY = object.getString("TODAY");
                    TODAY1 = object.getString("TODAY1");
                    TODAY2 = object.getString("TODAY2");
                    TODAY3 = object.getString("TODAY3");
                    TODAY4 = object.getString("TODAY4");
                    TODAY5 = object.getString("TODAY5");
                    TODAY6 = object.getString("TODAY6");



                    if(userID.equals(graphID)){

                        Pet_Graph pet_graph = new Pet_Graph(graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6);
                        pet_graphList_speed.add(pet_graph);
                    }

                    count++;

                }
            }catch(Exception e){

                e.getStackTrace();

            }

        }

    }

    // 그래프 보기 시작(펫)
    class BackgroundTask_graphList_time extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/graph_time_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            pet_graphList_time = new ArrayList<Pet_Graph>();
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

            pet_graphList_time = new ArrayList<Pet_Graph>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    graphNUM = object.getString("graphNUM");
                    graphID = object.getString("graphID");
                    TODAY = object.getString("TODAY");
                    TODAY1 = object.getString("TODAY1");
                    TODAY2 = object.getString("TODAY2");
                    TODAY3 = object.getString("TODAY3");
                    TODAY4 = object.getString("TODAY4");
                    TODAY5 = object.getString("TODAY5");
                    TODAY6 = object.getString("TODAY6");

                    if(userID.equals(graphID)){

                        Pet_Graph pet_graph = new Pet_Graph(graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6);
                        pet_graphList.add(pet_graph);

                    }

                    count++;

                }
            }catch(Exception e){

                e.getStackTrace();

            }

        }

    }

    // 일정 삭제
    public void delete_date(){

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        Pet_deleteDateRequest pet_deleteDateRequest = new Pet_deleteDateRequest(userID, petNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(pet_deleteDateRequest);

    }

    // 산책 일수 삭제
    public void delete_date_walk(){

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                        builder.setMessage("수정 실패")
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

        Pet_deleteWalkRequest pet_deleteWalkRequest = new Pet_deleteWalkRequest(userID, petNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
        queue.add(pet_deleteWalkRequest);

    }

    // 재시작
    @Override
    protected void onRestart() {

        super.onRestart();
        new BackgroundTask_user_pet().execute();


    }

}
