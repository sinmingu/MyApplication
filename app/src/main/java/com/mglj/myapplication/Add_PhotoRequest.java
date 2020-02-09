package com.mglj.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Add_PhotoRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/photo_add.php";
    private Map<String, String> parameters;

    public Add_PhotoRequest(String photoID, String photoTITLE, String photoCONTENT, String photoNAME, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put( "photoID",photoID);
        parameters.put( "photoTITLE",photoTITLE);
        parameters.put( "photoCONTENT",photoCONTENT);
        parameters.put( "photoNAME",photoNAME);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
