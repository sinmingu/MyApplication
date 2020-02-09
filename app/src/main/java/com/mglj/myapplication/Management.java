package com.mglj.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Management extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;
    private List<User> saveList;

    Button server_graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        Intent intent = getIntent();

        listView = (ListView)findViewById(R.id.user_List);
        userList = new ArrayList<User>();
        saveList = new ArrayList<User>();

        server_graph = (Button)findViewById(R.id.server_graph);

        server_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"23:59에 서버 그래프 업데이트 예정",Toast.LENGTH_SHORT).show();
                alram();
            }
        });

        adapter = new UserListAdapter(getApplicationContext(),userList,this, saveList);
        listView.setAdapter(adapter);

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
                if(!userID.equals("admin")){
                    userList.add(user);
                    saveList.add(user);
                }

                count++;

            }
        }catch(Exception e){
            e.getStackTrace();
        }
        EditText search_user = (EditText)findViewById(R.id.search_user);
        search_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchUser(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void searchUser(String search){
        userList.clear();
        for(int i = 0; i<saveList.size(); i++){
            if(saveList.get(i).getUserID().contains(search)){
                userList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
    //알람 매니저
    public void alram(){

        Intent intent = new Intent(Management.this, AlarmReceiver.class);

        PendingIntent sender = PendingIntent.getBroadcast(Management.this, 0, intent, 0);


        // 알람을 받을 시간을 5초 뒤로 설정

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

//      calendar.add(Calendar.SECOND, 5);

        calendar.set(Calendar.HOUR_OF_DAY, 23); // calendar.set(Calendar.HOUR_OF_DAY, 시간(int)); 저녁10시->22시

        calendar.set(Calendar.MINUTE, 59); //calendar.set(Calendar.MINUTE, 분(int));

        calendar.set(Calendar.SECOND, 59);

//      calendar.set(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.DATE), 18, 57, 0);


        // 알람 매니저에 알람을 등록

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 86400000,sender);


    }
}
