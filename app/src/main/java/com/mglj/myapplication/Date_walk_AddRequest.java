package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Date_walk_AddRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/Date_walk_Add.php";
    private Map<String, String> parameters;

    public Date_walk_AddRequest(String walkID, String walkDAY, int walkNUM, Response.Listener<String> listener) {

        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("walkID", walkID);
        parameters.put("walkDAY", walkDAY);
        parameters.put("walkNUM", walkNUM+"");

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
