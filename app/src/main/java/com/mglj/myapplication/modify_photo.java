package com.mglj.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class modify_photo extends AppCompatActivity {

    ImageView pet_cancle;
    int photoNUM;
    EditText modify_Edit_photo_title, modify_Edit_photo_content;
    ImageView img_modify;
    Button btn_modify_photo;
    Intent intent;
    Handler handler = new Handler();
    TextView today_date;
    String userID;
    String photoNAME;
    String photo_path;

    private final int GET_GALLERY_IMAGE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_photo);

        intent = getIntent();

        modify_Edit_photo_title = (EditText)findViewById(R.id.modify_Edit_photo_title);
        modify_Edit_photo_content = (EditText)findViewById(R.id.modify_Edit_photo_content);
        img_modify = (ImageView)findViewById(R.id.img_modify);
        btn_modify_photo = (Button)findViewById(R.id.btn_modify_photo);
        today_date = (TextView)findViewById(R.id.today_date);

        userID = intent.getStringExtra("userID");
        modify_Edit_photo_title.setText(intent.getStringExtra("title"));
        modify_Edit_photo_content.setText(intent.getStringExtra("content"));
        today_date.setText(intent.getStringExtra("date"));

        photoNUM = intent.getIntExtra("photoNum",0);
        photo_path = intent.getStringExtra("image");


        final String url2 = intent.getStringExtra("image");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한다
                // TODO Auto-generated method stub
                try{
                    // 걍 외우는게 좋다 -_-;
                    final ImageView iv = (ImageView)findViewById(R.id.img_modify);
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


        pet_cancle = (ImageView)findViewById(R.id.pet_cancle);

        pet_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        btn_modify_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                final ProgressDialog progressDialog = new ProgressDialog(modify_photo.this);
                                progressDialog.setIndeterminate(true);
                                progressDialog.setMessage("사진을 수정중 입니다.");
                                progressDialog.show();

                                modify_pet();
                            }
                        }, 300);

//                new BackgroundTask_photo().execute();

            }
        });


        img_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //-- gps
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try{

                // 비트맵 이미지로 가져온다
                InputStream in = getContentResolver().openInputStream(data.getData());
//
                Bitmap img = BitmapFactory.decodeStream(in);

                if(img.getHeight() >= 2000 ){

                    img = imgRotate(img);

                }

                in.close();
//
//                // 이미지를 상황에 맞게 회전시킨다
//                ExifInterface exif = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                    exif = new ExifInterface(in);
//                }
//                int exifOrientation = exif.getAttributeInt(
//                        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//                int exifDegree = exifOrientationToDegrees(exifOrientation);
//                img = rotate(img, exifDegree);


//                InputStream in = getContentResolver().openInputStream(data.getData());
//
//                Bitmap img = BitmapFactory.decodeStream(in);
//                in.close();
//                Toast.makeText(getApplicationContext(),img.getHeight()+" : "+img.getWidth(),Toast.LENGTH_SHORT).show();

//                img = Bitmap.createScaledBitmap(img, 640, height/(width/640), true);

                Glide.with(this).load(img).fitCenter().into(img_modify);

                photoNAME = getBase64String(img);

//                pet_imgbtn.setImageBitmap(img);
            }catch(Exception e) {

            }


        }else if(resultCode == RESULT_CANCELED) {

            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();

        }

    }

    private Bitmap imgRotate(Bitmap bmp){

        int width = bmp.getWidth();

        int height = bmp.getHeight();

        Matrix matrix = new Matrix();

        matrix.postRotate(90);




        Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);

        bmp.recycle();


        return resizedBitmap;

    }

    // 비트맵을 문자열로
    public String getBase64String(Bitmap bitmap)
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void modify_pet(){

        // -------------------- 반려견 DB등록 시작 ----------------
        String photoTITLE = modify_Edit_photo_title.getText().toString();
        String photoCONTENT = modify_Edit_photo_content.getText().toString();

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        Response.Listener<String> pet_responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Toast.makeText(getApplicationContext(),photoNUM+"3", Toast.LENGTH_SHORT).show();
                        new BackgroundTask_photo().execute();


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(modify_photo.this);
                        builder.setMessage("반려견 등록에 실패했습니다.")
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

        modify_PhotoRequest modify_photoRequest = new modify_PhotoRequest(photoNUM, photoTITLE, photoCONTENT, pet_responseListener);
        RequestQueue pet_queue = Volley.newRequestQueue(modify_photo.this);
        pet_queue.add(modify_photoRequest);


        // ------------------ 반려견 DB등록 끝 ---------------------
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

            Intent intent = new Intent(modify_photo.this,pet_photo.class);
            intent.putExtra("photoList",result);
            intent.putExtra("userID",userID);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            modify_photo.this.startActivity(intent);

        }

    }
}
