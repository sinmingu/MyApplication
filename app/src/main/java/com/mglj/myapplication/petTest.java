package com.mglj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class petTest extends AppCompatActivity {

    private ListView pet_list;
    private PetListAdapter adapter;
    private List<Pet> petList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_test);

        Intent intent = getIntent();

        pet_list = (ListView)findViewById(R.id.pet_list);
        petList = new ArrayList<Pet>();
        adapter = new PetListAdapter(getApplicationContext(), petList);
        pet_list.setAdapter(adapter);


        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("pet_List"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count= 0;
            String petNum, petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus;

            while(count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                petNum = object.getString("petNum");
                petID = object.getString("petID");
                petName = object.getString("petName");
                petType = object.getString("petType");
                petAge = object.getString("petAge");
                petSex = object.getString("petSex");
                petBir = object.getString("petBir");
                petWeight = object.getString("petWeight");
                petStatus = object.getString("petStatus");


                Pet pet = new Pet(petNum, petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus);
                petList.add(pet);



                count++;

            }
        }catch(Exception e){
            e.getStackTrace();
        }



    }
}
