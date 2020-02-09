package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Date_AddRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/Date_Add.php";
    private Map<String, String> parameters;

    public Date_AddRequest(String dateID, String dateDAY, String dateContent, int petNUM, Response.Listener<String> listener){


        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put( "dateID",dateID);
        parameters.put( "dateDAY",dateDAY);
        parameters.put( "dateContent",dateContent);
        parameters.put( "petNUM",petNUM+"");

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
