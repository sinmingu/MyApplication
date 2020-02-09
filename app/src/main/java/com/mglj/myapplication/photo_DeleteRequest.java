package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class photo_DeleteRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/delete_photo.php";
    private Map<String, String> parameters;

    public photo_DeleteRequest(String photo_path, int photoNUM, Response.Listener listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("photoNUM", photoNUM+"");
        parameters.put("photo_path", photo_path);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}