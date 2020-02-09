package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class manager_pet_graph_speed_updateRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/manager_pet_speed_update.php";
    private Map<String, String> parameters;

    public manager_pet_graph_speed_updateRequest(Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();


    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
