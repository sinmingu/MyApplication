package com.mglj.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class detail_phpto extends AppCompatActivity {

    ImageView pre_cancle, pre_can, img_photo, pre_can2, pre_can3, pre_can4, pre_can5;
    TextView photo_content, de_photo_date, de_photo_title;
    int photoNum;
    String userID;
    Bitmap bitmap;
    Handler handler = new Handler();  // 외부쓰레드 에서 메인 UI화면을

    String photo_path;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phpto);

        intent = getIntent();

        pre_cancle = (ImageView)findViewById(R.id.pre_cancle);
        pre_can = (ImageView)findViewById(R.id.pre_can);
        pre_can2 = (ImageView)findViewById(R.id.pre_can2);
        pre_can3 = (ImageView)findViewById(R.id.pre_can3);
        pre_can4 = (ImageView)findViewById(R.id.pre_can4);
        pre_can5 = (ImageView)findViewById(R.id.pre_can5);

        photo_content = (TextView)findViewById(R.id.photo_content);
        de_photo_date = (TextView)findViewById(R.id.de_photo_date);
        de_photo_title = (TextView)findViewById(R.id.de_photo_title);
        img_photo = (ImageView)findViewById(R.id.img_photo);

        de_photo_title.setText(intent.getStringExtra("title"));
        de_photo_date.setText(intent.getStringExtra("date").substring(0,10));
        photo_content.setText(intent.getStringExtra("content"));
        userID = intent.getStringExtra("userID");

        final String url2 = intent.getStringExtra("image");

        photo_path = intent.getStringExtra("image");

        photoNum = Integer.parseInt(intent.getStringExtra("number"));

//        byte[] decodedByteArray = Base64.decode(intent.getStringExtra("image"), Base64.NO_WRAP);
//        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
//        Glide.with(this).load(decodedBitmap).fitCenter().into(img_photo);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한다
                // TODO Auto-generated method stub
                try{
                    // 걍 외우는게 좋다 -_-;
                    final ImageView iv = (ImageView)findViewById(R.id.img_photo);
                    URL url = new URL(url2);
                    InputStream is = url.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    handler.post(new Runnable() {

                        @Override
                        public void run() {  // 화면에 그려줄 작업
                            Glide.with(getApplicationContext()).load(bm).fitCenter().into(iv);
//                            iv.setImageBitmap(bm);
                        }
                    });
//                    iv.setImageBitmap(bm); //비트맵 객체로 보여주기
                } catch(Exception e){

                }

            }
        });

        t.start();

//        Glide.with(getApplicationContext()).load(bitmap).fitCenter().into(img_photo);
//        img_photo.setImageBitmap(bitmap);

        Glide.with(this).load(R.drawable.arrow_white).fitCenter().into(pre_cancle);
        Glide.with(this).load(R.drawable.recyclebin).fitCenter().into(pre_can);
        Glide.with(this).load(R.drawable.modify).fitCenter().into(pre_can2);
        Glide.with(this).load(R.drawable.modify).fitCenter().into(pre_can3);
        Glide.with(this).load(R.drawable.modify).fitCenter().into(pre_can3);
        Glide.with(this).load(R.drawable.sharing).fitCenter().into(pre_can5);

        pre_can2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),modify_photo.class);
                intent1.putExtra("title",de_photo_title.getText().toString());
                intent1.putExtra("date",de_photo_date.getText().toString());
                intent1.putExtra("content",photo_content.getText().toString());
                intent1.putExtra("image",photo_path);
                intent1.putExtra("photoNum",photoNum);
                intent1.putExtra("userID",userID);
                startActivity(intent1);
            }
        });

        pre_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        pre_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(detail_phpto.this);
                builder.setMessage("해당 글을 삭제하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete_photo();
//                        Intent intent = new Intent(getApplicationContext(),MainScreen.class);
//                        startActivity(intent);


                    }
                })
                        .create()
                        .show();




            }
        });

        pre_can5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });

    }

    // 공유시작
    public void sendMmsIntent(String number, Uri imgUri){

        try{
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra("address", number);
            sendIntent.putExtra("subject", "MMS Test");
            sendIntent.setType("image/*");
            sendIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
            startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.app_name)));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void getImage(){

        try{
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Image Choose"), 1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //-- gps
        super.onActivityResult(requestCode, resultCode, data);




        // ------ gps
        if(requestCode == 1){
            if(resultCode==RESULT_OK){
                Uri imgUri = data.getData();

                sendMmsIntent("010-1234-1234", imgUri);
            }
        }
        else if(resultCode == RESULT_CANCELED)
        {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // 포토 사진 리스트 불러오기
    class BackgroundTask_photo extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/photo_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!= null){
                    stringBuilder.append((temp + "\n"));
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }
            catch(Exception e){
                e.getStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            Intent intent = new Intent(detail_phpto.this,pet_photo.class);
            intent.putExtra("photoList",result);
            intent.putExtra("userID",userID);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            detail_phpto.this.startActivity(intent);

        }

    }

    public void delete_photo(){
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(detail_phpto.this);
                        builder.setMessage("수정 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new BackgroundTask_photo().execute();
                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(detail_phpto.this);
                        builder.setMessage("수정 실패")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }
            }
        };

        photo_DeleteRequest photo_deleteRequest = new photo_DeleteRequest(photo_path, photoNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(detail_phpto.this);
        queue.add(photo_deleteRequest);
    }

}
