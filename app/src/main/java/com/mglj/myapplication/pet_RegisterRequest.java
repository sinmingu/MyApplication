package com.mglj.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class pet_RegisterRequest extends StringRequest {

    final static private String URL = "https://tlsalsrn1.cafe24.com/pet_Register.php";
    private Map<String, String> parameters;

    public pet_RegisterRequest(String petID, String petName, int petType, int petAge, String petSex,String petBir,int petWeight, String petStatus, Response.Listener<String> listener){

        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put( "petID",petID);
        parameters.put( "petName",petName);
        parameters.put( "petType",petType+" ");
        parameters.put( "petAge",petAge+" ");
        parameters.put( "petSex",petSex);
        parameters.put( "petBir",petBir);
        parameters.put( "petWeight",petWeight+" ");
        parameters.put( "petStatus", petStatus);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
