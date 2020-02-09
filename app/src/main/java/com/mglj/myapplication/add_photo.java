package com.mglj.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class add_photo extends AppCompatActivity {

    ImageView pet_cancle, pet_can, img_add;
    EditText Edit_photo_title, Edit_photo_content;
    Button btn_add_photo;
    String userID;
    String photoNAME;
    TextView today_date;

    private final int GET_GALLERY_IMAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_photo);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        pet_cancle = (ImageView)findViewById(R.id.pet_cancle);
        pet_can = (ImageView)findViewById(R.id.pet_can);
        img_add = (ImageView)findViewById(R.id.img_add);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pet_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(pet_can);
        Glide.with(this).load(R.drawable.img_bg).fitCenter().into(img_add);

        Edit_photo_title = (EditText)findViewById(R.id.Edit_photo_title);
        Edit_photo_content = (EditText)findViewById(R.id.Edit_photo_content);

        today_date = (TextView)findViewById(R.id.today_date);

        btn_add_photo = (Button)findViewById(R.id.btn_add_photo);

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 0); //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷
        String today = sdf.format(calendar.getTime()); // String으로 저장

        today_date.setText(today);

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);

            }
        });

        pet_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                final ProgressDialog progressDialog = new ProgressDialog(add_photo.this);
                                progressDialog.setIndeterminate(true);
                                progressDialog.setMessage("사진을 업로드 중입니다.");
                                progressDialog.show();

                                add_pet();
                            }
                        }, 300);




            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void add_pet(){

        // -------------------- 반려견 DB등록 시작 ----------------
        String photoID = userID;
        String photoTITLE = Edit_photo_title.getText().toString();
        String photoCONTENT = Edit_photo_content.getText().toString();


        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        Response.Listener<String> pet_responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){

                        new BackgroundTask_photo().execute();


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(add_photo.this);
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

        Add_PhotoRequest add_photoRequest = new Add_PhotoRequest(photoID, photoTITLE, photoCONTENT, photoNAME, pet_responseListener);
        RequestQueue pet_queue = Volley.newRequestQueue(add_photo.this);
        pet_queue.add(add_photoRequest);


        // ------------------ 반려견 DB등록 끝 ---------------------
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

                Glide.with(this).load(img).fitCenter().into(img_add);

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

    public int exifOrientationToDegrees(int exifOrientation){

        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {

            return 90;

        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {

            return 180;

        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {

            return 270;

        } return 0;

    }

    public Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try
            {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted)
                {
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex)
            {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }


    // 비트맵을 문자열로
    public String getBase64String(Bitmap bitmap)
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);

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

            Intent intent = new Intent(add_photo.this,pet_photo.class);
            intent.putExtra("photoList",result);
            intent.putExtra("userID",userID);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            add_photo.this.startActivity(intent);

        }

    }


}
