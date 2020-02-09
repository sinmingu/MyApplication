package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class user_graph_UpdateRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/user_graph_Update.php";
    private Map<String, String> parameters;

    public user_graph_UpdateRequest(int TODAY, String graphID, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("TODAY", TODAY+" ");
        parameters.put("graphID", graphID);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
