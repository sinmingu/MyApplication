package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class User_DeleteRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/delete_user.php";
    private Map<String, String> parameters;

    public User_DeleteRequest(String userID, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}

