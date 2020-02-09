package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class weight_modifyRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/modify_weight.php";
    private Map<String, String> parameters;

    public weight_modifyRequest(String userID, int userWeight, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userWeight", userWeight+"");

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
