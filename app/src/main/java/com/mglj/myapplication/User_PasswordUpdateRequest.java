package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class User_PasswordUpdateRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/user_password_update.php";
    private Map<String, String> parameters;

    public User_PasswordUpdateRequest(String userPassword, String userID, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userPassword", userPassword);
        parameters.put("userID", userID);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
