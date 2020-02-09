package com.mglj.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

//            String graphID = intent.getStringExtra("userID");
//            // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
//            com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//
//                    try {
//                        JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
//                        boolean success = jsonResponse.getBoolean("success");
//                        if(success){
//                            Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
//                        }
//                        else{
//
//                        }
//                    }
//                    catch(JSONException e ){
//                        e.getStackTrace();
//                    }
//                }
//            };
//
//            graph_all_update graph_all_update = new graph_all_update(graphID, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(context);
//            queue.add(graph_all_update);
//            // 수정 끝
//
//        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
//        com.android.volley.Response.Listener<String> responseListener2 = new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
//                    boolean success = jsonResponse.getBoolean("success");
//                    if(success){
//                        Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//
//                    }
//                }
//                catch(JSONException e ){
//                    e.getStackTrace();
//                }
//            }
//        };
//
//        graph_all_speed_update graph_all_speed_update = new graph_all_speed_update(graphID, responseListener2);
//        RequestQueue queue2 = Volley.newRequestQueue(context);
//        queue2.add(graph_all_speed_update);
//        // 수정 끝
//
//        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
//        com.android.volley.Response.Listener<String> responseListener3 = new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
//                    boolean success = jsonResponse.getBoolean("success");
//                    if(success){
//                        Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//
//                    }
//                }
//                catch(JSONException e ){
//                    e.getStackTrace();
//                }
//            }
//        };
//
//        graph_all_time_update graph_all_time_update = new graph_all_time_update(graphID, responseListener3);
//        RequestQueue queue3 = Volley.newRequestQueue(context);
//        queue3.add(graph_all_time_update);
//        // 수정 끝
//
//        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
//        com.android.volley.Response.Listener<String> responseListener4 = new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
//                    boolean success = jsonResponse.getBoolean("success");
//                    if(success){
//                        Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
//
//                    }
//                    else{
//
//
//                    }
//                }
//                catch(JSONException e ){
//                    e.getStackTrace();
//                }
//            }
//        };
//
//        user_graph_all_update user_graph_all_update = new user_graph_all_update(graphID, responseListener4);
//        RequestQueue queue4 = Volley.newRequestQueue(context);
//        queue4.add(user_graph_all_update);
//        // 수정 끝

        // 매니저 전체 수정

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener5 = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
                    }
                    else{
                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }
            }
        };

        manager_pet_graph_updateRequest manager_pet_graph_updateRequest = new manager_pet_graph_updateRequest(responseListener5);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(manager_pet_graph_updateRequest);
        // 수정 끝

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener6 = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
                    }
                    else{
                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }
            }
        };

        manager_pet_graph_speed_updateRequest manager_pet_graph_speed_updateRequest = new manager_pet_graph_speed_updateRequest(responseListener6);
        RequestQueue queue2 = Volley.newRequestQueue(context);
        queue2.add(manager_pet_graph_speed_updateRequest);
        // 수정 끝

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener7 = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
                    }
                    else{
                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }
            }
        };

        manager_pet_time_updateRequest manager_pet_time_updateRequest= new manager_pet_time_updateRequest(responseListener7);
        RequestQueue queue3 = Volley.newRequestQueue(context);
        queue3.add(manager_pet_time_updateRequest);
        // 수정 끝

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener8 = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Toast.makeText(context, "날짜가 변경되었습니다.", Toast.LENGTH_LONG).show();
                    }
                    else{
                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }
            }
        };

        manager_user_graph_updateRequest manager_user_graph_updateRequest = new manager_user_graph_updateRequest(responseListener8);
        RequestQueue queue4 = Volley.newRequestQueue(context);
        queue4.add(manager_user_graph_updateRequest);
        // 수정 끝

        }

}

