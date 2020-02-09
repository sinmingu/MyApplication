package com.mglj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class pet_photo extends AppCompatActivity {

    ImageView pet_cancle, pet_can, img_add_photo;
    ListView photo_list;
    private PhotoListAdapter adapter;
    private List<DB_photo> db_photos;
    Button btn_add_photo;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_photo);

        pet_cancle = (ImageView)findViewById(R.id.pet_cancle);
        pet_can = (ImageView)findViewById(R.id.pet_can);
        img_add_photo = (ImageView)findViewById(R.id.img_add_photo);
        btn_add_photo = (Button)findViewById(R.id.btn_add_photo);

        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pet_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pet_can);
        Glide.with(this).load(R.drawable.add).fitCenter().into(img_add_photo);

        final Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        photo_list = (ListView)findViewById(R.id.photo_list);
        db_photos = new ArrayList<DB_photo>();
        adapter = new PhotoListAdapter(getApplicationContext(), db_photos);
        photo_list.setAdapter(adapter);

        // JSON 데이터 받기
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("photoList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count= 0;
            String photoNUM, photoID, photoTITLE, photoCONTENT, photoDATE, photoNAME;

            while(count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                photoNUM = object.getString("photoNUM");
                photoID = object.getString("photoID");
                photoTITLE = object.getString("photoTITLE");
                photoCONTENT = object.getString("photoCONTENT");
                photoDATE = object.getString("photoDATE");
                photoNAME = object.getString("photoNAME");

                if(photoID.equals(userID)) {

                    DB_photo db_photo = new DB_photo(photoNUM, photoID, photoTITLE, photoCONTENT, photoDATE, photoNAME);
                    db_photos.add(db_photo);

                }

                count++;
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        //

        pet_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        img_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), add_photo.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
            }
        });

        photo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent1 = new Intent(pet_photo.this,detail_phpto.class);
                intent1.putExtra("userID",userID);
                intent1.putExtra("content",db_photos.get(i).getPhotoCONTENT());
                intent1.putExtra("title",db_photos.get(i).getPhotoTITLE());
                intent1.putExtra("date",db_photos.get(i).getPhotoDATE());
                intent1.putExtra("image",db_photos.get(i).getPhotoNAME());
                intent1.putExtra("number",db_photos.get(i).getPhotoNUM());
                startActivity(intent1);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
