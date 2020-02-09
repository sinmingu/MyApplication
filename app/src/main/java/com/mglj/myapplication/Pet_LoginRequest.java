package com.mglj.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Pet_LoginRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/pet_List.php";
    private Map<String, String> parameters;

    public Pet_LoginRequest(String petID, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put( "petID",petID);

    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
