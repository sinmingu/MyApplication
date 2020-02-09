package com.mglj.myapplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mglj.myapplication.MainScreen.Register_status;

public class RegisterActivity extends AppCompatActivity {

    SmsManager mSMSManager;

    //소형 , 중형, 대형 라디오
    RadioGroup radioGroup1;
    int petNumber;
    ImageView img_test;

    private List<User> userList;

    // 뷰 페이저(광고)
    ViewPager reg_vp;

    TabHost reg_tabHost;
    TextView pet_bir;

    EditText Register_id, Register_pw, Register_name, Register_age, Register_weight, Register_height;
    EditText pet_name, pet_age, pet_weight;
    TextView pet_sex, pet_sex_status;

    ImageView register_cancle, register_page1, register_page2, register_page3, register_page4, register_page5, register_page6,
    register_page7, register_page8, register_page9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");;
        mSMSManager = SmsManager.getDefault();

        img_test = (ImageView)findViewById(R.id.img_test);
        Glide.with(this).load(R.drawable.puppy9).fitCenter().into(img_test);
        userList = new ArrayList<User>();

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count= 0;
            String userID, userPassword, userName, userAge, userHeight, userWeight;
            while(count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPassword = object.getString("userPassword");
                userName = object.getString("userName");
                userAge = object.getString("userAge");
                userHeight = object.getString("userHeight");
                userWeight = object.getString("userWeight");

                User user = new User(userID, userPassword, userName, userAge, userHeight, userWeight);

                userList.add(user);


                count++;

            }
        }catch(Exception e){
            e.getStackTrace();
        }

        //-----------------------탭 호스트 시작--------------------------------
        reg_tabHost = (TabHost)findViewById(R.id.reg_tab_host);

        reg_tabHost.setup();

        // 홈 탭
        TabHost.TabSpec reg_tabSpec1 = reg_tabHost.newTabSpec("Tab1");
        reg_tabSpec1.setIndicator("",getResources().getDrawable(R.drawable.notebook)); // Tab Subject
        reg_tabSpec1.setContent(new Intent(this,RegisterActivity.class));
        reg_tabSpec1.setContent(R.id.reg_tab_view1); // Tab Content
        reg_tabHost.addTab(reg_tabSpec1);

        // 일정 탭
        final TabHost.TabSpec reg_tabSpec2 = reg_tabHost.newTabSpec("Tab2");
        reg_tabSpec2.setIndicator("",getResources().getDrawable(R.drawable.group)); // Tab Subject
        reg_tabSpec2.setContent(new Intent(this,RegisterActivity.class));
        reg_tabSpec2.setContent(R.id.reg_tab_view2); // Tab Content
        reg_tabHost.addTab(reg_tabSpec2);

        // 그래프 탭
        TabHost.TabSpec reg_tabSpec3 = reg_tabHost.newTabSpec("Tab3");
        reg_tabSpec3.setIndicator("",getResources().getDrawable(R.drawable.dog)); // Tab Subject
        reg_tabSpec3.setContent(new Intent(this,RegisterActivity.class));
        reg_tabSpec3.setContent(R.id.reg_tab_view3); // Tab Content
        reg_tabHost.addTab(reg_tabSpec3);


        reg_tabHost.getTabWidget().getChildAt(0)

                .setBackgroundColor(Color.parseColor("#ffffff"));


        reg_tabHost.getTabWidget().getChildAt(1)

                .setBackgroundColor(Color.parseColor("#ffffff"));

        reg_tabHost.getTabWidget().getChildAt(2)

                .setBackgroundColor(Color.parseColor("#ffffff"));

        reg_tabHost.setCurrentTab(0); // 처음 열리는 탭

        //-----------------------탭 호스트 끝--------------------------------

        // 유저
        Register_id = findViewById(R.id.Register_id);
        Register_pw = findViewById(R.id.Register_pw);
        Register_name = findViewById(R.id.Register_name);
        Register_age = findViewById(R.id.Register_age);
        Register_weight = (EditText)findViewById(R.id.Register_weight);
        Register_height = (EditText)findViewById(R.id.Register_height);

        if(Register_status==1){
            Register_id.setText(email);
            Register_id.setFocusable(false);
            Register_id.setClickable(false);
        }

        // 펫 반려견
        pet_bir = (TextView) findViewById(R.id.pet_bir);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);

        pet_name = (EditText)findViewById(R.id.pet_name);
        pet_age = (EditText)findViewById(R.id.pet_age);
        pet_sex = (TextView) findViewById(R.id.pet_sex);
        pet_weight = (EditText)findViewById(R.id.pet_weight);

        register_cancle = (ImageView)findViewById(R.id.register_cancle);
        register_page1 = (ImageView)findViewById(R.id.register_page1);
        register_page2 = (ImageView)findViewById(R.id.register_page2);
        register_page3 = (ImageView)findViewById(R.id.register_page3);
        register_page4 = (ImageView)findViewById(R.id.register_page4);
        register_page5 = (ImageView)findViewById(R.id.register_page5);
        register_page6 = (ImageView)findViewById(R.id.register_page6);
        register_page7 = (ImageView)findViewById(R.id.register_page7);
        register_page8 = (ImageView)findViewById(R.id.register_page8);
        register_page9 = (ImageView)findViewById(R.id.register_page9);

        Glide.with(this).load(R.drawable.cancel).fitCenter().into(register_cancle);
        Glide.with(this).load(R.drawable.page_on).fitCenter().into(register_page1);
        Glide.with(this).load(R.drawable.page_off).fitCenter().into(register_page2);
        Glide.with(this).load(R.drawable.page_off).fitCenter().into(register_page3);

        // 뷰페이저
//        reg_vp = (ViewPager)findViewById(R.id.reg_vp);
//        reg_vp.setAdapter(new RegisterActivity.pagerAdapter(getSupportFragmentManager()));
//        reg_vp.setCurrentItem(0);


        //반려견 생일 입력
        pet_bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, listener, 2013, 10, 22);
                dialog.show();

            }
        });

        // 유저 입력완료 버튼
        Button Register_btn = (Button)findViewById(R.id.Register_btn);
        //회원가입 버튼
        Button Register_btn_next = (Button)findViewById(R.id.Register_btn_next);
        // 시작하기 버튼
        Button btn_reg_start = (Button)findViewById(R.id.btn_reg_start);

        // 시작하기 버튼
        btn_reg_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Glide.with(RegisterActivity.this).load(R.drawable.page_off).fitCenter().into(register_page4);
                Glide.with(RegisterActivity.this).load(R.drawable.page_on).fitCenter().into(register_page5);
                Glide.with(RegisterActivity.this).load(R.drawable.page_off).fitCenter().into(register_page6);
                reg_tabHost.setCurrentTab(1);


            }
        });

        // 유저 입력완료 버튼
        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0; i<userList.size(); i++){

                    if(Register_id.getText().toString().equals(userList.get(i).getUserID())){
                        Toast.makeText(getApplicationContext(), "중복된 아이디가 있습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }

                if(Register_id.getText().toString().equals("")||Register_pw.getText().toString().equals("")||
                        Register_name.getText().toString().equals("")||Register_age.getText().toString().equals("")||
                        Register_weight.getText().toString().equals("")||Register_height.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "추가 정보 입력", Toast.LENGTH_SHORT).show();
                }
                else {

                    Glide.with(RegisterActivity.this).load(R.drawable.page_off).fitCenter().into(register_page7);
                    Glide.with(RegisterActivity.this).load(R.drawable.page_off).fitCenter().into(register_page8);
                    Glide.with(RegisterActivity.this).load(R.drawable.page_on).fitCenter().into(register_page9);
                    reg_tabHost.setCurrentTab(2);



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

        pet_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CustomDialogSex customDialog = new CustomDialogSex(RegisterActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(pet_sex);
            }
        });

        // 취소 버튼
        register_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //회원가입 버튼
        Register_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pet_name.getText().toString().equals("")||pet_age.getText().toString().equals("")||pet_sex.getText().toString().equals("")||
                pet_weight.getText().toString().equals("")||pet_bir.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else{

                // --------------------유저 DB 등록 시작--------------------
                String userID = Register_id.getText().toString();
                String userPassword = Register_pw.getText().toString();
                String userName = Register_name.getText().toString();
                int userAge = Integer.parseInt(Register_age.getText().toString());
                int userHeight = Integer.parseInt(Register_height.getText().toString());
                int userWeight = Integer.parseInt(Register_weight.getText().toString());

                // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 가입을 성공했습니다")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                add_pet();

                                                Intent intent = new Intent(RegisterActivity.this, MainScreen.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                RegisterActivity.this.startActivity(intent);
                                            }
                                        })
                                        .create()
                                        .show();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, userHeight,userWeight,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

                // --------------------유저 DB 등록 끝--------------------

                }

            }
        });

        pet_sex_status = (TextView)findViewById(R.id.pet_sex_status);

        pet_sex_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CustomDialogSexStatus customDialog = new CustomDialogSexStatus(RegisterActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(pet_sex_status);
            }
        });

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            pet_bir.setText(year + "," + monthOfYear + "," + dayOfMonth);
            pet_bir.setTextColor(Color.BLACK);


        }

    };

    // 뷰페이저

    private class pagerAdapter extends FragmentStatePagerAdapter
    {

        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Register_Sceen_first();
                case 1:
                    return new Register_screen_two();
                case 2:
                    return new Register_Screen_three();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }

    // 뷰페이저 끝

    // 뒤로가기
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Register_status = 0 ;

    }

    public void add_pet(){
        // -------------------- 반려견 DB등록 시작 ----------------
        String petID = Register_id.getText().toString();
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("반려견 등록에 성공했습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        RegisterActivity.this.startActivity(intent);
                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

        pet_RegisterRequest pet_registerRequest = new pet_RegisterRequest(petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus, pet_responseListener);
        RequestQueue pet_queue = Volley.newRequestQueue(RegisterActivity.this);
        pet_queue.add(pet_registerRequest);
        // ------------------ 반려견 DB등록 끝 ---------------------
    }

}
