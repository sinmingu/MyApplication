package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class date_DeleteRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/date_delete.php";
    private Map<String, String> parameters;

    public date_DeleteRequest(int dateNUM, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("dateNUM", dateNUM+"");


    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}

