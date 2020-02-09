package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class petBir_UpdateRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/pet_bir_update.php";
    private Map<String, String> parameters;

    public petBir_UpdateRequest(String petBir, int petNUM, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("petBir", petBir);
        parameters.put("petNUM", petNUM+"");

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}

