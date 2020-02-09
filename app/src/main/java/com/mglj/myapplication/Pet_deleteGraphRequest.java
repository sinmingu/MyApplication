package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Pet_deleteGraphRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/delete_pet_graph.php";
    private Map<String, String> parameters;

    public Pet_deleteGraphRequest(int graphNUM, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("graphNUM", graphNUM+"");

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
