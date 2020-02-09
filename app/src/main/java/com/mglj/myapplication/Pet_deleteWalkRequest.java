package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Pet_deleteWalkRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/delete_walk_date.php";
    private Map<String, String> parameters;

    public Pet_deleteWalkRequest(String walkID, int walkNUM, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("walkID", walkID);
        parameters.put("walkNUM", walkNUM+"");

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}