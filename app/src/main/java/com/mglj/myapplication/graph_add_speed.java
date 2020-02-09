package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class graph_add_speed extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/graph_add_speed.php";
    private Map<String, String> parameters;

    public graph_add_speed(String graphID, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("graphID", graphID);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}

