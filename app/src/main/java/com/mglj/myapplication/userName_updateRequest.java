package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class userName_updateRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/user_name_update.php";
    private Map<String, String> parameters;

    public userName_updateRequest(String userName, String userID, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userName", userName);
        parameters.put("userID", userID);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
