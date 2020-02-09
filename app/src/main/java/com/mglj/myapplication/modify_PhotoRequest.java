package com.mglj.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class modify_PhotoRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/photo_update.php";
    private Map<String, String> parameters;

    public modify_PhotoRequest(int photoNUM, String photoTITLE, String photoCONTENT, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put( "photoNUM",photoNUM+"");
        parameters.put( "photoTITLE",photoTITLE);
        parameters.put( "photoCONTENT",photoCONTENT);


    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
