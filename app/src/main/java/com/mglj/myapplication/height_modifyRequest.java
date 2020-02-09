package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class height_modifyRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/modify_height.php";
    private Map<String, String> parameters;

    public height_modifyRequest(String userID, int userHeight, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userHeight", userHeight+" ");

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
