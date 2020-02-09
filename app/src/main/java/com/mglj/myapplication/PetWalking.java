package com.mglj.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static com.mglj.myapplication.MainActivity.testmsg;

public class PetWalking extends AppCompatActivity implements OnMapReadyCallback, com.google.android.gms.maps.GoogleMap.OnMarkerClickListener, com.google.android.gms.maps.GoogleMap.OnMapClickListener {

    ArrayList<Pet_Graph> pet_graphList;
    ArrayList<Pet_Graph> pet_graphList_speed;
    ArrayList<Pet_Graph> pet_graphList_time;

    ArrayList<User> userList;

    int flag_count=0;
    int pet_status;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    //GPS
    private GpsTracker gpsTracker;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    String userID;
    int TODAY;

    //GPS 변수
    double latitude;
    double longitude;

    int status = 0;

    TextView mEllapse;
    TextView distance_text, speed_text, cal_text;
    TextView mSplit;
    ImageView walk_image, top_camera;
    ImageButton imgbtn_camera, imgbtn_play, imgbtn_write;
    private com.google.android.gms.maps.GoogleMap mMap;
    Button mBtnStart;
    Button mBtnSplit;

    int cnt = 0;
    String listID;

    //스톱워치의 상태를 위한 상수
    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;//처음 상태는 IDLE
    long mBaseTime;
    long mPauseTime;
    int min_status = 0;
    int mSplitCount;

    private LocationManager lm;
    private LocationListener ll;
    double mySpeed, maxSpeed;
    double calo, meter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_walking);

        Intent intent = getIntent();

        userID = intent.getStringExtra("userID");
        pet_status = intent.getIntExtra("pet",0);
        listID = intent.getStringExtra("userID");
        new BackgroundTask_graph().execute();

        calo = 0;
        meter = 0;
        new BackgroundTask_graphList_speed().execute();
        new BackgroundTask_graphList_time().execute();
        new BackgroundTask_user_List().execute();


        // 스탑워치
        mEllapse = (TextView) findViewById(R.id.ellapse);

//        mSplit = (TextView) findViewById(R.id.split);

        distance_text = (TextView) findViewById(R.id.distance_text); //거리
        speed_text = (TextView) findViewById(R.id.speed_text); // 속도
        cal_text = (TextView) findViewById(R.id.cal_text); // 칼로리

        imgbtn_write = (ImageButton) findViewById(R.id.imgbtn_write);
        imgbtn_play = (ImageButton) findViewById(R.id.imgbtn_play);
        imgbtn_camera = (ImageButton) findViewById(R.id.imgbtn_camera);
        top_camera = (ImageView)findViewById(R.id.top_camera);

        Glide.with(this).load(R.drawable.play).fitCenter().into(imgbtn_play);
        Glide.with(this).load(R.drawable.reset).fitCenter().into(imgbtn_write);
        Glide.with(this).load(R.drawable.save_1).fitCenter().into(imgbtn_camera);
        Glide.with(this).load(R.drawable.camera).fitCenter().into(top_camera);

//        mBtnStart = (Button) findViewById(R.id.btnstart); // 시작버튼
//        mBtnSplit = (Button) findViewById(R.id.btnsplit); // 기록버튼

        walk_image = (ImageView)findViewById(R.id.walk_image);

        //------------------------ gps 시작

        maxSpeed = mySpeed = 0;

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        ll = new SpeedoActionListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);

        //------------------ gps 끝 --------------------

        imgbtn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                graph_update();
                date_walk_add();
            }
        });

        top_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);

            }
        });

        Glide.with(PetWalking.this).load(R.drawable.p1).fitCenter().into(walk_image);

//        pref3 = getSharedPreferences("pref", MODE_PRIVATE);
//        editor3 = pref3.edit();
//
//        if(pref3.getInt("flag_count",0)!=1) {
//            editor3.putInt("flag_count", 0);
//            editor3.commit();
//        }


    }

    //스톱워치를 위한 핸들러
    Handler mTimer = new Handler() {

        //핸들러는 기본적으로 handleMessage에서 처리한다.

        public void handleMessage(android.os.Message msg) {
            //텍스트뷰를 수정해준다.
            mEllapse.setText(getEllapse().substring(0,5));

            // 강아지 달리기
            cnt++;



            if(cnt == 10){
//                walk_image.setImageResource(R.drawable.p1);
                Glide.with(PetWalking.this).load(R.drawable.p1).fitCenter().into(walk_image);
            }
            else if(cnt ==20){
//                walk_image.setImageResource(R.drawable.p2);
                Glide.with(PetWalking.this).load(R.drawable.p2).fitCenter().into(walk_image);
            }
            else if(cnt == 30){
//                walk_image.setImageResource(R.drawable.p4);
                Glide.with(PetWalking.this).load(R.drawable.p4).fitCenter().into(walk_image);
                cnt = 0;
            }


            //메시지를 다시 보낸다.

            mTimer.sendEmptyMessage(0);//0은 메시지를 구분하기 위한 것

        }

        ;

    };


    @Override
    protected void onDestroy() {

        // TODO Auto-generated method stub
        mTimer.removeMessages(0);//메시지를 지워서 메모리릭 방지
        super.onDestroy();

    }

    public void mOnClick(View v) {

        switch (v.getId()) {

            //시작 버튼이 눌리면
            case R.id.imgbtn_play:

                switch (mStatus) {

                    //IDLE상태이면 .. 산책시작 버튼 클릭시
                    case IDLE:
                        //현재 값을 세팅해주고
                        mBaseTime = SystemClock.elapsedRealtime();
                        //핸드러로 메시지를 보낸다
                        mTimer.sendEmptyMessage(0);
                        //시작을 중지로 바꾸고

                        Glide.with(this).load(R.drawable.stop).fitCenter().into(imgbtn_play);

                        imgbtn_write.setVisibility(View.INVISIBLE);
                        imgbtn_camera.setVisibility(View.INVISIBLE);
////                        mBtnStart.setText("산책중지");
                        //옆버튼의 Enable을 푼 다음
//                        mBtnSplit.setEnabled(true);
                        //상태를 RUNNING으로 바꾼다.
                        mStatus = RUNNING;
//                        min_status=1;
//                        mBtnSplit.setVisibility(View.GONE);

                        status = 1;
                        break;

                    //버튼이 실행상태이면 ....... 산책중지 버튼 클릭
                    case RUNNING:
                        //핸들러 메시지를 없애고
                        mTimer.removeMessages(0);
                        //멈춘 시간을 파악
                        mPauseTime = SystemClock.elapsedRealtime();
                        //버튼 텍스트를 바꿔줌
//                        mBtnStart.setText("산책시작");
//                        mBtnSplit.setText("다시산책");
                        Glide.with(this).load(R.drawable.play).fitCenter().into(imgbtn_play);

                        imgbtn_write.setVisibility(View.VISIBLE);
                        imgbtn_camera.setVisibility(View.VISIBLE);

                        mStatus = PAUSE;//상태를 멈춤으로 표시
//                        min_status=0;
//                        mBtnSplit.setVisibility(View.VISIBLE);

                        status = 0;
                        break;

                    //멈춤이면 .. 산책시작 중 중지 했다가 다시 산책시작 눌렀을때
                    case PAUSE:

                        //현재값 가져옴
                        long now = SystemClock.elapsedRealtime();
                        //베이스타임 = 베이스타임 + (now - mPauseTime)
                        //잠깐 스톱워치를 멈췄다가 다시 시작하면 기준점이 변하게 되므로..
                        mBaseTime += (now - mPauseTime);
                        mTimer.sendEmptyMessage(0);
                        //텍스트 수정
//                        mBtnStart.setText("산책중지");
//                        mBtnSplit.setText("산책기록");
//                        min_status=0;
                        Glide.with(this).load(R.drawable.stop).fitCenter().into(imgbtn_play);

                        imgbtn_write.setVisibility(View.INVISIBLE);
                        imgbtn_camera.setVisibility(View.INVISIBLE);

                        status =1;
                        mStatus = RUNNING;
//                        mBtnSplit.setVisibility(View.GONE);
                        break;

                }

                break;

            // 기록버튼
            case R.id.imgbtn_write:

                switch (mStatus) {
                    //RUNNING 상태일 때.
                    case RUNNING:
//                        //기존의 값을 가져온뒤 이어붙이기 위해서
//                        String sSplit = mSplit.getText().toString();
//                        //+연산자로 이어붙임
//                        sSplit += String.format("%d => %s\n", mSplitCount, getEllapse());
//                        //텍스트뷰의 값을 바꿔줌
//                        mSplit.setText(sSplit);
//                        mSplitCount++;
//                        mBtnSplit.setVisibility(View.VISIBLE);
                        break;

                    case PAUSE://여기서는 초기화버튼이 됨 .. 다시산책하는 리셋버튼

                        //핸들러를 없애고
                        mTimer.removeMessages(0);
                        //처음상태로 원상복귀시킴
//                        mBtnStartg.setText("산책시작");

//                        mBtnSplit.setText("산책기록");
                        mEllapse.setText("00:00");
                        cnt = 0;
                        distance_text.setText("0");
                        speed_text.setText("0");
                        cal_text.setText("0");

                        mStatus = IDLE;

//                        user_update_today_graph();
//                        update_today_graph();


//                        mSplit.setText("");
//                        mBtnSplit.setEnabled(false);
//                        mBtnSplit.setVisibility(View.VISIBLE);
                        break;

                }
                break;

        }

    }

    String getEllapse() {

        long now = SystemClock.elapsedRealtime();
        long ell = now - mBaseTime;//현재 시간과 지난 시간을 빼서 ell값을 구하고
        //아래에서 포맷을 예쁘게 바꾼다음 리턴해준다.
        String sEll = String.format("%02d:%02d:%02d", ell / 1000 / 60, (ell / 1000) % 60, (ell % 1000) / 10);
        return sEll;

    }

    private class SpeedoActionListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            if (location != null) {
                mySpeed = location.getSpeed();
                if (mySpeed > maxSpeed) {
                    maxSpeed = mySpeed;
                }

                if(status == 1) {
                    String speed_format = String.format("%.2f" , mySpeed);
                    speed_text.setText(speed_format);

                    meter += (Double.parseDouble(speed_text.getText().toString()) *1000)/900;
                    String meter_format = String.format("%.2f" , meter);
                    distance_text.setText(String.valueOf(meter_format));
//                    calo += Math.round(Double.parseDouble(distance_text.getText().toString()) * 10 * 100.0) / 100.0;

                    if(!speed_format.equals("0.00")) {

                        calo += ((3 * (3.5 * Double.parseDouble(userList.get(0).getUserHeight()) * Double.parseDouble(userList.get(0).getUserWeight())) * 5) / 3600)/1000;
                        String calo_format = String.format("%.2f", calo);

                        cal_text.setText(String.valueOf(calo_format));
                    }
                }
//                onMapReady(mMap);

//                Toast.makeText(getApplicationContext(), "위치 재 탐색", Toast.LENGTH_SHORT).show();

//                }
//                else
//                    Toast.makeText(PetWalking.this, "비활성", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onProviderDisabled(String provider) {

            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

            // TODO Auto-generated method stub

        }
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {

        // GPS
        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        } else {

            checkRunTimePermission();
        }
        gpsTracker = new GpsTracker(PetWalking.this);

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();



    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(getApplicationContext(), "하이", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }


    //-------------------------- gps 함수 --------------------------------------

    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(PetWalking.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(PetWalking.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(PetWalking.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(PetWalking.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(PetWalking.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(PetWalking.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(PetWalking.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(PetWalking.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress(double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //----------------------------- gps 함수 끝 -------------------------

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        testmsg = speed_text.getText().toString();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Bitmap photo = (Bitmap) data.getExtras().get("data");
        Bundle extras = data.getExtras();
        Bitmap mImageBitmap = (Bitmap) extras.get("data");
//        String re = getBase64String(mImageBitmap);

    }

    // 비트맵을 문자열로
    public String getBase64String(Bitmap bitmap)
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);

    }

    // 문자열을 비트맵으로로
    byte[] decodedByteArray = Base64.decode("변환된 문자열", Base64.NO_WRAP);
    Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);

    // 오늘 그래프 업데이트 (사람)
    private void user_update_today_graph(){

        String graphID = userID;
        TODAY = (int) calo;
//        int TODAY = (int) Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(testmsg))));

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
                        builder.setMessage("산책 등록을 하였습니다")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
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

        user_graph_UpdateRequest user_graph_updateRequest = new user_graph_UpdateRequest(TODAY, graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PetWalking.this);
        queue.add(user_graph_updateRequest);

    }

    // 오늘 그래프 업데이트(펫) 거리
    private void update_today_graph(){
        
        int graphNUM = 0;
        
        if(pet_status==1){
            graphNUM = Integer.parseInt(pet_graphList.get(0).getGraphNUM()); 
        }
        else if(pet_status==2){
            graphNUM = Integer.parseInt(pet_graphList.get(1).getGraphNUM());
        }
        else if(pet_status==3){
            graphNUM = Integer.parseInt(pet_graphList.get(2).getGraphNUM());
        }
        
        TODAY = (int) meter;
//        int TODAY = (int) Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(testmsg))));

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
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

        graph_UpdateRequest graph_updateRequest = new graph_UpdateRequest(TODAY, graphNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PetWalking.this);
        queue.add(graph_updateRequest);

    }

    // 오늘 그래프 업데이트(펫) 속도
    private void update_today_graph_speed(){

        int graphNUM = 0;

        if(pet_status==1){
            graphNUM = Integer.parseInt(pet_graphList_speed.get(0).getGraphNUM());
        }
        else if(pet_status==2){
            graphNUM = Integer.parseInt(pet_graphList_speed.get(1).getGraphNUM());
        }
        else if(pet_status==3){
            graphNUM = Integer.parseInt(pet_graphList_speed.get(2).getGraphNUM());
        }

        TODAY = (int) meter;
//        int TODAY = (int) Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(testmsg))));

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
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

        graph_speed_UpdateRequest graph_updateRequest = new graph_speed_UpdateRequest(TODAY, graphNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PetWalking.this);
        queue.add(graph_updateRequest);

    }

    // 오늘 그래프 업데이트(펫) 시간
    private void update_today_graph_time(){

        int graphNUM = 0;

        if(pet_status==1){
            graphNUM = Integer.parseInt(pet_graphList_time.get(0).getGraphNUM());
        }
        else if(pet_status==2){
            graphNUM = Integer.parseInt(pet_graphList_time.get(1).getGraphNUM());
        }
        else if(pet_status==3){
            graphNUM = Integer.parseInt(pet_graphList_time.get(2).getGraphNUM());
        }

        TODAY = Integer.parseInt(mEllapse.getText().toString().substring(0,2));
//        int TODAY = (int) Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(testmsg))));

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
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

        graph_time_UpdateRequest graph_updateRequest = new graph_time_UpdateRequest(TODAY, graphNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PetWalking.this);
        queue.add(graph_updateRequest);

    }

    // 업데이트 한칸씩 뒤로 하기(사람)
    private void user_update_graph(){
        // 수정 시작

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
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

        user_graph_all_update user_graph_all_update = new user_graph_all_update(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PetWalking.this);
        queue.add(user_graph_all_update);
        // 수정 끝

    }

    //업데이트 한칸씩 뒤로 하기(펫)
    private void update_graph(){

        // 수정 시작
        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(PetWalking.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
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

        graph_all_update graph_all_update = new graph_all_update(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PetWalking.this);
        queue.add(graph_all_update);
        // 수정 끝

    }

    // 오늘 거리 업데이트
    public void graph_update(){

        // 매일 오늘 날짜를 불러옴
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 0); //
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd"); // 날짜 포맷
        String today = sdf.format(calendar.getTime()); // String으로 저장
        // 어플 첫 실행시 실행되는 부분 ( 오늘 날짜를 저장 )
//        if(pref3.getInt("flag_count",0)==0){
//            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putString("today", today);
//            editor.commit();
//            editor3.putInt("flag_count", 1);
//            editor3.commit();
//
//        }
//        SharedPreferences pref2 = getSharedPreferences("pref", MODE_PRIVATE);

        update_today_graph();
        update_today_graph_speed();
        update_today_graph_time();
        user_update_today_graph();

//        // 오늘 날짜이면
//        if(today.equals( pref2.getString("today", ""))){
//
//            Toast.makeText(getApplicationContext(), "오늘 : "+today +" 다음 : "+pref2.getString("today",""), Toast.LENGTH_SHORT).show();
//            update_today_graph();
//            user_update_today_graph();
//
//        }
//        // 날짜가 변동되면
//        else{
//
//            update_graph();
//            user_update_graph();
//            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putString("today", today);
//            editor.commit();
//            Toast.makeText(getApplicationContext(), "else 오늘 : "+today +" 다음 : "+pref.getString("today",""), Toast.LENGTH_SHORT).show();
//
//        }

    }

    // 오늘 산책 일수 추가
    public void date_walk_add(){

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH)+1;
        String today = cal.get(Calendar.YEAR)+","+month+","+cal.get(Calendar.DATE);

        // -------------------- 날짜 DB등록 시작 ----------------
        String walkID = userID;
        String walkDAY = today;

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> date_responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){


                    }
                    else{


                    }
                }
                catch(JSONException e ){
                    e.getStackTrace();
                }
            }
        };
        Date_walk_AddRequest date_walk_addRequest = new Date_walk_AddRequest(walkID, walkDAY, pet_status, date_responseListener);
        RequestQueue date_queue = Volley.newRequestQueue(PetWalking.this);
        date_queue.add(date_walk_addRequest);

        // -------------------- 날짜 DB등록 끝 ------------------

    }

    // 그래프 보기 시작(펫)
    class BackgroundTask_graph extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/graph_List.php";
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


            pet_graphList = new ArrayList<Pet_Graph>();

            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    graphNUM = object.getString("graphNUM");
                    graphID = object.getString("graphID");
                    TODAY = object.getString("TODAY");
                    TODAY1 = object.getString("TODAY1");
                    TODAY2 = object.getString("TODAY2");
                    TODAY3 = object.getString("TODAY3");
                    TODAY4 = object.getString("TODAY4");
                    TODAY5 = object.getString("TODAY5");
                    TODAY6 = object.getString("TODAY6");

                    if(userID.equals(graphID)){

                        Pet_Graph pet_graph = new Pet_Graph(graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6);
                        pet_graphList.add(pet_graph);
                    }

                    count++;

                }
            }catch(Exception e){

                e.getStackTrace();

            }

        }

    }

    // 그래프 보기 시작(펫)
    class BackgroundTask_graphList_speed extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/graph_speed_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            pet_graphList_speed = new ArrayList<Pet_Graph>();
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

            pet_graphList_speed = new ArrayList<Pet_Graph>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    graphNUM = object.getString("graphNUM");
                    graphID = object.getString("graphID");
                    TODAY = object.getString("TODAY");
                    TODAY1 = object.getString("TODAY1");
                    TODAY2 = object.getString("TODAY2");
                    TODAY3 = object.getString("TODAY3");
                    TODAY4 = object.getString("TODAY4");
                    TODAY5 = object.getString("TODAY5");
                    TODAY6 = object.getString("TODAY6");



                    if(userID.equals(graphID)){

                        Pet_Graph pet_graph = new Pet_Graph(graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6);
                        pet_graphList_speed.add(pet_graph);
                    }

                    count++;

                }
            }catch(Exception e){

                e.getStackTrace();

            }

        }

    }

    // 그래프 보기 시작(펫)
    class BackgroundTask_graphList_time extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/graph_time_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            pet_graphList_time = new ArrayList<Pet_Graph>();
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

            pet_graphList_time = new ArrayList<Pet_Graph>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    graphNUM = object.getString("graphNUM");
                    graphID = object.getString("graphID");
                    TODAY = object.getString("TODAY");
                    TODAY1 = object.getString("TODAY1");
                    TODAY2 = object.getString("TODAY2");
                    TODAY3 = object.getString("TODAY3");
                    TODAY4 = object.getString("TODAY4");
                    TODAY5 = object.getString("TODAY5");
                    TODAY6 = object.getString("TODAY6");

                    if(userID.equals(graphID)){

                        Pet_Graph pet_graph = new Pet_Graph(graphNUM, graphID, TODAY, TODAY1,TODAY2,TODAY3,TODAY4,TODAY5,TODAY6);
                        pet_graphList_time.add(pet_graph);

                    }

                    count++;

                }
            }catch(Exception e){

                e.getStackTrace();

            }

        }

    }

    // 유저 정보 불러오기
    class BackgroundTask_user_List extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/List.php";
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
            userList = new ArrayList<User>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String userID, userPassword, userName, userAge, userHeight, userWeight;

                while(count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);
                    userID = object.getString("userID");
                    userPassword = object.getString("userPassword");
                    userName = object.getString("userName");
                    userAge = object.getString("userAge");
                    userHeight = object.getString("userHeight");
                    userWeight = object.getString("userWeight");

                    if (userID.equals(listID)) {
                        User user = new User(userID, userPassword, userName, userAge, userHeight, userWeight);
                        userList.add(user);
                    }

                    count++;

                }


            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

}