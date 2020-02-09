package com.mglj.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GoogleMap extends AppCompatActivity implements OnMapReadyCallback, com.google.android.gms.maps.GoogleMap.OnMarkerClickListener, com.google.android.gms.maps.GoogleMap.OnMapClickListener {

    private com.google.android.gms.maps.GoogleMap mMap;

    //    Marker selectedMarker;
//    View marker_root_view;
//    TextView tv_marker;

    //GPS
    private GpsTracker gpsTracker;

    int set_mode = 0;
    int view_mode = 0;
    double mySpeed, maxSpeed;
    private LocationListener ll;
    private LocationManager lm;

    ImageView img_map_all, img_map_cafe, img_map_tent, img_map_hotel, img_map_my, img_map_test2, img_map_test3, img_map_test4;
    ImageView map_cancle, test_img;
    CheckBox map_can;

    public static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        img_map_all = (ImageView) findViewById(R.id.img_map_all);
        img_map_cafe = (ImageView) findViewById(R.id.img_map_cafe);
        img_map_tent = (ImageView) findViewById(R.id.img_map_tent);
        img_map_hotel = (ImageView) findViewById(R.id.img_map_hotel);
        img_map_my = (ImageView) findViewById(R.id.img_map_my);
        img_map_test2 = (ImageView) findViewById(R.id.img_map_test2);
        img_map_test3 = (ImageView) findViewById(R.id.img_map_test3);
        img_map_test4 = (ImageView) findViewById(R.id.img_map_test4);

        map_cancle = (ImageView) findViewById(R.id.map_cancle);
        map_can = (CheckBox) findViewById(R.id.map_can);

        test_img = (ImageView)findViewById(R.id.test_img);

        // GPS 시작
        maxSpeed = mySpeed = 0;

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        ll = new SpeedoActionListener_2();


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

        // GPS 끝

        Glide.with(this).load(R.drawable.all).fitCenter().into(img_map_all);
        Glide.with(this).load(R.drawable.cafe_1).fitCenter().into(img_map_cafe);
        Glide.with(this).load(R.drawable.camp).fitCenter().into(img_map_tent);
        Glide.with(this).load(R.drawable.hotel).fitCenter().into(img_map_hotel);

        Glide.with(this).load(R.drawable.my).fitCenter().into(img_map_my);
        Glide.with(this).load(R.drawable.park_1).fitCenter().into(img_map_test2);
        Glide.with(this).load(R.drawable.toy).fitCenter().into(img_map_test3);
        Glide.with(this).load(R.drawable.hospital).fitCenter().into(img_map_test4);

        Glide.with(this).load(R.drawable.arrow_white).fitCenter().into(map_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(test_img);

        img_map_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 0;
                view_mode = 0;
                map_can.setChecked(false);
                map_can.setVisibility(View.VISIBLE);
                onMapReady(mMap);
            }
        });

        img_map_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 1;
                view_mode = 1;
                map_can.setVisibility(View.INVISIBLE);
                onMapReady(mMap);
            }
        });

        img_map_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 2;
                view_mode = 1;
                onMapReady(mMap);
                map_can.setVisibility(View.INVISIBLE);
            }
        });

        img_map_tent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 3;
                view_mode = 1;
                onMapReady(mMap);
                map_can.setVisibility(View.INVISIBLE);
            }
        });

        img_map_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 4;
                view_mode = 1;
                onMapReady(mMap);
                map_can.setVisibility(View.INVISIBLE);
            }
        });

        img_map_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 5;
                view_mode = 1;
                onMapReady(mMap);
                map_can.setVisibility(View.INVISIBLE);
            }
        });

        img_map_test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 6;
                view_mode = 1;
                onMapReady(mMap);
                map_can.setVisibility(View.INVISIBLE);
            }
        });


        img_map_test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_mode = 7;
                view_mode = 1;
                onMapReady(mMap);
                map_can.setVisibility(View.INVISIBLE);
            }
        });

        map_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        map_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (map_can.isChecked() == true) {
                    view_mode = 1;
                } else {
                    view_mode = 0;
                }

            }
        });


    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {


//        Intent intent = getIntent();
//        double latitude = intent.getDoubleExtra("latitude",0);
//        double longitude= intent.getDoubleExtra("longitude",0);

        gpsTracker = new GpsTracker(GoogleMap.this);

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        final String address = getCurrentAddress(latitude, longitude);

        mMap = googleMap;
        mMap.clear();

        // -------------------- 내 위치 -----------------------
        LatLng Mylocation = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(Mylocation);
        markerOptions.title("내 위치");
//        markerOptions.alpha((float) 0.5);
        markerOptions.snippet(address);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.my);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 70, 70, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        mMap.addMarker(markerOptions);


//         반경 1KM원
        CircleOptions circle1KM = new CircleOptions().center(Mylocation) //원점
                .radius(200)      //반지름 단위 : m
                .strokeWidth(0f)  //선너비 0f : 선없음
                .fillColor(Color.parseColor("#263995d3")); //배경색

        mMap.addCircle(circle1KM);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);


        //내위치
        if(set_mode == 0){


            view_cafe();

            view_camp();

            view_hotel();

            view_park();

            view_hospital();

            view_goods();

            view_mode = 0;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mylocation, 16));
        }
        // 모두 표시
        else if(set_mode == 1) {


            view_cafe();

            view_camp();

            view_hotel();

            view_park();

            view_hospital();

            view_goods();




            view_mode = 1;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.485893, 127.981778), 7));
        }

        //애견 카페
        else if(set_mode == 2){

            view_cafe();

            view_mode = 1;

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.485893, 127.981778), 7));
        }

        // 애견 캠핑장
        else if(set_mode == 3){

            view_camp();

           view_mode = 1;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.485893, 127.981778), 7));

        }

        // 애견 호텔
        else if(set_mode == 4){

            // -------------------- 애견 호텔 ---------------------

            view_hotel();

          view_mode = 1;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.485893, 127.981778), 7));

        }
        // 애견 공원
        else if(set_mode == 5){

            // -------------------- 애견 공원 ---------------------
            view_park();


            view_mode = 1;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.485893, 127.981778), 7));

        }

        // 애견용품
        else if(set_mode == 6){

            // -------------------- 애견용품 ---------------------
            view_goods();

            view_mode = 1;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.485893, 127.981778), 7));

        }

        // 애견병원
        else if(set_mode == 7){

            view_hospital();

            view_mode = 1;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.485893, 127.981778), 7));

        }


        //정보창 클릭 리스너

        mMap.setOnInfoWindowClickListener(infoWindowClickListener);

    }

    //정보창 클릭 리스너
    com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(final Marker marker) {

            if(!marker.getTitle().equals("내 위치")) {

                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CustomDialogMap customDialog = new CustomDialogMap(GoogleMap.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                url = marker.getSnippet();
                customDialog.callFunction(marker.getTitle(), marker.getSnippet());

            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(GoogleMap.this);
                builder.setMessage(marker.getTitle())
                        .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create()
                        .show();
            }


        }
    };

    @Override
    public boolean onMarkerClick(final Marker marker) {

        if(!marker.getTitle().equals("내 위치")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(GoogleMap.this);
            builder.setMessage(marker.getTitle())
                    .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setNegativeButton("자세히", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent intent = new Intent(GoogleMap.this, CafeWeb_View.class);
                    intent.putExtra("url", marker.getSnippet());
                    startActivity(intent);


                }
            })
                    .create()
                    .show();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(GoogleMap.this);
            builder.setMessage(marker.getTitle())
                    .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .create()
                    .show();
        }

        return true;
    }



    @Override
    public void onMapClick(LatLng latLng) {


    }


    private class SpeedoActionListener_2 implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            if (location != null) {
                mySpeed = location.getSpeed();
                if (mySpeed > maxSpeed) {
                    maxSpeed = mySpeed;
                }

//                if(min_status == 1) {
                if(view_mode == 0) {
                    onMapReady(mMap);
                }


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
    public void onBackPressed() {
        super.onBackPressed();
    }

    public String getCurrentAddress( double latitude, double longitude) {

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
        return address.getAddressLine(0).toString()+"\n";

    }

    public void view_goods(){
        // -------------------- 애견용품 ---------------------
        // 굿애견용품할인매장
        LatLng goods1 = new LatLng(37.6969289,126.7781925);
        MarkerOptions markerOptions_goods1 = new MarkerOptions();
        markerOptions_goods1.position(goods1);
        markerOptions_goods1.title("굿애견용품할인매장");
        markerOptions_goods1.snippet("경기도 고양시 일산서구 탄현동 고봉로 419");
        BitmapDrawable good_bitmapdraw2=(BitmapDrawable)getResources().getDrawable(R.drawable.toy);
        Bitmap goods_b2=good_bitmapdraw2.getBitmap();
        Bitmap good_smallMarker2 = Bitmap.createScaledBitmap(goods_b2, 60, 60, false);
        markerOptions_goods1.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods1);

        // 퍼피하우스
        LatLng goods2 = new LatLng(37.642102,127.0221233);
        MarkerOptions markerOptions_goods2 = new MarkerOptions();
        markerOptions_goods2.position(goods2);
        markerOptions_goods2.title("퍼피하우스");
        markerOptions_goods2.snippet("서울특별시 강북구 수유동 39-3");
        markerOptions_goods2.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods2);

        //(주)펫그라운드 애견용품 아울렛
        LatLng goods3 = new LatLng(37.6464516,127.0357704);
        MarkerOptions markerOptions_goods3 = new MarkerOptions();
        markerOptions_goods3.position(goods3);
        markerOptions_goods3.title("(주)펫그라운드 애견용품 아울렛");
        markerOptions_goods3.snippet("서울특별시 도봉구 창동 2층");
        markerOptions_goods3.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods3);

        //좋은나라애견본부
        LatLng goods4 = new LatLng(37.6188546,127.0523357);
        MarkerOptions markerOptions_goods4 = new MarkerOptions();
        markerOptions_goods4.position(goods4);
        markerOptions_goods4.title("좋은나라애견본부");
        markerOptions_goods4.snippet("서울특별시 성북구 장위동 173-119");
        markerOptions_goods4.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods4);

        //아마존애견용품할인마트
        LatLng goods5 = new LatLng(37.6006322,127.1294117);
        MarkerOptions markerOptions_goods5 = new MarkerOptions();
        markerOptions_goods5.position(goods5);
        markerOptions_goods5.title("아마존애견용품할인마트");
        markerOptions_goods5.snippet("경기도 구리시 교문동 270-2");
        markerOptions_goods5.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods5);

        //애견마트
        LatLng goods6 = new LatLng(37.5858743,127.1479511);
        MarkerOptions markerOptions_goods6 = new MarkerOptions();
        markerOptions_goods6.position(goods6);
        markerOptions_goods6.title("애견마트");
        markerOptions_goods6.snippet("경기도 구리시 토평동 617-3");
        markerOptions_goods6.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods6);

        //갤럭시펫 petshop
        LatLng goods7 = new LatLng(37.5136747,127.0618629);
        MarkerOptions markerOptions_goods7 = new MarkerOptions();
        markerOptions_goods7.position(goods7);
        markerOptions_goods7.title("갤럭시펫 petshop");
        markerOptions_goods7.snippet("서울특별시 강남구 삼성1동 봉은사로104길 10");
        markerOptions_goods7.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods7);

        //펫마트 양천점
        LatLng goods8 = new LatLng(37.5165341,126.838789);
        MarkerOptions markerOptions_goods8 = new MarkerOptions();
        markerOptions_goods8.position(goods8);
        markerOptions_goods8.title("펫마트 양천점");
        markerOptions_goods8.snippet("신월동 1001-2번지 1층 양천구 서울특별시 KR");
        markerOptions_goods8.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods8);

        //스토어봄 부천지사
        LatLng goods9 = new LatLng(37.5054364,126.7804241);
        MarkerOptions markerOptions_goods9 = new MarkerOptions();
        markerOptions_goods9.position(goods9);
        markerOptions_goods9.title("스토어봄 부천지사");
        markerOptions_goods9.snippet("경기도 부천시 춘의동 옥산로 136");
        markerOptions_goods9.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods9);

        //하비러브 애견용품
        LatLng goods10 = new LatLng(37.4591214,126.6921902);
        MarkerOptions markerOptions_goods10 = new MarkerOptions();
        markerOptions_goods10.position(goods10);
        markerOptions_goods10.title("하비러브 애견용품");
        markerOptions_goods10.snippet("인천광역시 남구 주안6동 920-8");
        markerOptions_goods10.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods10);

        //이지애견용품
        LatLng goods11 = new LatLng(37.4438584,126.7921829);
        MarkerOptions markerOptions_goods11 = new MarkerOptions();
        markerOptions_goods11.position(goods11);
        markerOptions_goods11.title("이지애견용품");
        markerOptions_goods11.snippet("경기도 시흥시 대야동 562-5");
        markerOptions_goods11.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods11);

        //애견용품 창고형 할인매장
        LatLng goods12 = new LatLng(37.3529657,126.7418003);
        MarkerOptions markerOptions_goods12 = new MarkerOptions();
        markerOptions_goods12.position(goods12);
        markerOptions_goods12.title("애견용품 창고형 할인매장");
        markerOptions_goods12.snippet("경기도 시흥시 정왕본동 2320-2");
        markerOptions_goods12.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods12);

        //산본팻애견용품할인마트
        LatLng goods13 = new LatLng(37.3747955,126.9413567);
        MarkerOptions markerOptions_goods13 = new MarkerOptions();
        markerOptions_goods13.position(goods13);
        markerOptions_goods13.title("산본팻애견용품할인마트");
        markerOptions_goods13.snippet("경기도 군포시 산본1동 79-69");
        markerOptions_goods13.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods13);

        //안양펫애견용품할인마트
        LatLng goods14 = new LatLng(37.3795699,126.9539738);
        MarkerOptions markerOptions_goods14 = new MarkerOptions();
        markerOptions_goods14.position(goods14);
        markerOptions_goods14.title("안양펫애견용품할인마트");
        markerOptions_goods14.snippet("경기도 안양시 동안구 호계동 934-26");
        markerOptions_goods14.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods14);

        //관양펫 애견용품할인마트
        LatLng goods15 = new LatLng(37.4043238,126.9671917);
        MarkerOptions markerOptions_goods15 = new MarkerOptions();
        markerOptions_goods15.position(goods15);
        markerOptions_goods15.title("관양펫 애견용품할인마트");
        markerOptions_goods15.snippet("경기도 안양시 동안구 관악대로 400");
        markerOptions_goods15.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods15);

        //루루애견용품할인매장
        LatLng goods16 = new LatLng(37.2706378,127.1059799);
        MarkerOptions markerOptions_goods16 = new MarkerOptions();
        markerOptions_goods16.position(goods16);
        markerOptions_goods16.title("루루애견용품할인매장");
        markerOptions_goods16.snippet("경기도 용인시 기흥구 신갈동 70-6");
        markerOptions_goods16.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods16);

        //가람펫애견용품할인점
        LatLng goods17 = new LatLng(37.4162545,127.259531);
        MarkerOptions markerOptions_goods17 = new MarkerOptions();
        markerOptions_goods17.position(goods17);
        markerOptions_goods17.title("가람펫애견용품할인점");
        markerOptions_goods17.snippet("경기도 광주시 송정동 58-9");
        markerOptions_goods17.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods17);

        //애견하우스
        LatLng goods18 = new LatLng(36.9870599,126.9286537);
        MarkerOptions markerOptions_goods18 = new MarkerOptions();
        markerOptions_goods18.position(goods18);
        markerOptions_goods18.title("애견하우스");
        markerOptions_goods18.snippet("경기도 평택시 안중읍 안중리 266-13");
        markerOptions_goods18.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods18);

        //로또애견용품할인매장
        LatLng goods19 = new LatLng(37.0076248,127.0784283);
        MarkerOptions markerOptions_goods19 = new MarkerOptions();
        markerOptions_goods19.position(goods19);
        markerOptions_goods19.title("로또애견용품할인매장");
        markerOptions_goods19.snippet("경기도 평택시 세교동");
        markerOptions_goods19.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods19);

        //코리아애견용품전문점
        LatLng goods20 = new LatLng(37.0470254,127.0976543);
        MarkerOptions markerOptions_goods20 = new MarkerOptions();
        markerOptions_goods20.position(goods20);
        markerOptions_goods20.title("코리아애견용품전문점");
        markerOptions_goods20.snippet("장안동 27-1번지 평택시 경기도 KR");
        markerOptions_goods20.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods20);

        //장보는강아지와고양이용인포곡점
        LatLng goods21 = new LatLng(37.2747358,127.2121096);
        MarkerOptions markerOptions_goods21 = new MarkerOptions();
        markerOptions_goods21.position(goods21);
        markerOptions_goods21.title("장보는강아지와고양이용인포곡점");
        markerOptions_goods21.snippet("경기도 용인시 처인구 영문리 113-6 KR");
        markerOptions_goods21.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods21);

        //강아지나라
        LatLng goods22 = new LatLng(37.2821459,127.4430799);
        MarkerOptions markerOptions_goods22 = new MarkerOptions();
        markerOptions_goods22.position(goods22);
        markerOptions_goods22.title("강아지나라");
        markerOptions_goods22.snippet("경기도 이천시 창전동 87-8");
        markerOptions_goods22.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods22);

        //아이펫2000
        LatLng goods23 = new LatLng(37.270467,127.455225);
        MarkerOptions markerOptions_goods23 = new MarkerOptions();
        markerOptions_goods23.position(goods23);
        markerOptions_goods23.title("아이펫2000");
        markerOptions_goods23.snippet("경기도 이천시 진리동 경충대로2481번길 7");
        markerOptions_goods23.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods23);

        //애견나라
        LatLng goods24 = new LatLng(37.29553,127.6398897);
        MarkerOptions markerOptions_goods24 = new MarkerOptions();
        markerOptions_goods24.position(goods24);
        markerOptions_goods24.title("애견나라");
        markerOptions_goods24.snippet("경기도 여주군 여주읍 상리 283-7");
        markerOptions_goods24.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods24);

        //해피애견 무극리
        LatLng goods25 = new LatLng(36.9908648,127.585516);
        MarkerOptions markerOptions_goods25 = new MarkerOptions();
        markerOptions_goods25.position(goods25);
        markerOptions_goods25.title("해피애견 무극리");
        markerOptions_goods25.snippet("충청북도 음성군 금왕읍 무극리 663-2");
        markerOptions_goods25.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods25);

        //해피애견 평곡리
        LatLng goods26 = new LatLng(36.9296886,127.6962805);
        MarkerOptions markerOptions_goods26 = new MarkerOptions();
        markerOptions_goods26.position(goods26);
        markerOptions_goods26.title("해피애견 평곡리");
        markerOptions_goods26.snippet("충청북도 음성군 음성읍 평곡리 963-17");
        markerOptions_goods26.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods26);

        //나라애견
        LatLng goods27 = new LatLng(36.9701239,127.9300833);
        MarkerOptions markerOptions_goods27 = new MarkerOptions();
        markerOptions_goods27.position(goods27);
        markerOptions_goods27.title("나라애견");
        markerOptions_goods27.snippet("충청북도 충주시 지현동 90");
        markerOptions_goods27.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods27);

        //동물나라애견
        LatLng goods28 = new LatLng(36.982192,127.9321003);
        MarkerOptions markerOptions_goods28 = new MarkerOptions();
        markerOptions_goods28.position(goods28);
        markerOptions_goods28.title("동물나라애견");
        markerOptions_goods28.snippet("충청북도 충주시 교현동 1087-1");
        markerOptions_goods28.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods28);

        //퍼피클럽
        LatLng goods29 = new LatLng(37.1308293,128.207016);
        MarkerOptions markerOptions_goods29 = new MarkerOptions();
        markerOptions_goods29.position(goods29);
        markerOptions_goods29.title("퍼피클럽");
        markerOptions_goods29.snippet("충청북도 제천시 화산동 205-1");
        markerOptions_goods29.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods29);

        //토토애견
        LatLng goods30 = new LatLng(37.1348323,128.2091188);
        MarkerOptions markerOptions_goods30 = new MarkerOptions();
        markerOptions_goods30.position(goods30);
        markerOptions_goods30.title("토토애견");
        markerOptions_goods30.snippet("충청북도 제천시 중앙로1가 116-9");
        markerOptions_goods30.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods30);

        //러브애견
        LatLng goods31 = new LatLng(37.1411956,128.210578);
        MarkerOptions markerOptions_goods31 = new MarkerOptions();
        markerOptions_goods31.position(goods31);
        markerOptions_goods31.title("러브애견");
        markerOptions_goods31.snippet("충청북도 제천시 중앙로2가 22-11");
        markerOptions_goods31.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods31);

        //아라애견
        LatLng goods32 = new LatLng(36.8258785,128.6195612);
        MarkerOptions markerOptions_goods32 = new MarkerOptions();
        markerOptions_goods32.position(goods32);
        markerOptions_goods32.title("아라애견");
        markerOptions_goods32.snippet("경상북도 영주시 영주2동 505-7");
        markerOptions_goods32.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods32);

        //복이네애견미용실
        LatLng goods33 = new LatLng(36.8217905,128.6232948);
        MarkerOptions markerOptions_goods33 = new MarkerOptions();
        markerOptions_goods33.position(goods33);
        markerOptions_goods33.title("복이네애견미용실");
        markerOptions_goods33.snippet("경상북도 영주시 영주2동 534-19");
        markerOptions_goods33.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods33);

        //애견용품전문매장
        LatLng goods34 = new LatLng(36.8150225,128.6190033);
        MarkerOptions markerOptions_goods34 = new MarkerOptions();
        markerOptions_goods34.position(goods34);
        markerOptions_goods34.title("애견용품전문매장");
        markerOptions_goods34.snippet("경상북도 영주시 가흥1동 1454-19");
        markerOptions_goods34.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods34);

        //영심이애견샵
        LatLng goods35 = new LatLng(36.5626345,128.7137175);
        MarkerOptions markerOptions_goods35 = new MarkerOptions();
        markerOptions_goods35.position(goods35);
        markerOptions_goods35.title("영심이애견샵");
        markerOptions_goods35.snippet("경상북도 안동시 태화동");
        markerOptions_goods35.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods35);

        //밍키애견마트
        LatLng goods36 = new LatLng(36.5642546,128.7210131);
        MarkerOptions markerOptions_goods36 = new MarkerOptions();
        markerOptions_goods36.position(goods36);
        markerOptions_goods36.title("밍키애견마트");
        markerOptions_goods36.snippet("경상북도 안동시 옥야동 91-12");
        markerOptions_goods36.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods36);

        //스마일퍼피
        LatLng goods37 = new LatLng(36.5655989,128.7184167);
        MarkerOptions markerOptions_goods37 = new MarkerOptions();
        markerOptions_goods37.position(goods37);
        markerOptions_goods37.title("스마일퍼피");
        markerOptions_goods37.snippet("경상북도 안동시 당북동 91-1");
        markerOptions_goods37.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods37);

        //알프스애견샵
        LatLng goods38 = new LatLng(36.5666157,128.7110567);
        MarkerOptions markerOptions_goods38 = new MarkerOptions();
        markerOptions_goods38.position(goods38);
        markerOptions_goods38.title("알프스애견샵");
        markerOptions_goods38.snippet("경상북도 안동시 태화동 206-1");
        markerOptions_goods38.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods38);

        //BOB애견용품백화점
        LatLng goods39 = new LatLng(35.5301308,129.315691);
        MarkerOptions markerOptions_goods39 = new MarkerOptions();
        markerOptions_goods39.position(goods39);
        markerOptions_goods39.title("BOB애견용품백화점");
        markerOptions_goods39.snippet("울산광역시 남구 신정4동 810-4");
        markerOptions_goods39.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods39);

        //펫마트울산삼산점
        LatLng goods40 = new LatLng(35.5440995,129.3413544);
        MarkerOptions markerOptions_goods40 = new MarkerOptions();
        markerOptions_goods40.position(goods40);
        markerOptions_goods40.title("펫마트울산삼산점");
        markerOptions_goods40.snippet("울산광역시 남구 삼산동 1541-15");
        markerOptions_goods40.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods40);

        //펫마트양산물금점
        LatLng goods41 = new LatLng(35.3226888,128.9966583);
        MarkerOptions markerOptions_goods41 = new MarkerOptions();
        markerOptions_goods41.position(goods41);
        markerOptions_goods41.title("펫마트양산물금점");
        markerOptions_goods41.snippet("경상남도 양산시 물금읍 가촌리 906-1");
        markerOptions_goods41.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods41);

        //범어애견
        LatLng goods42 = new LatLng(35.3335602,129.0040612);
        MarkerOptions markerOptions_goods42 = new MarkerOptions();
        markerOptions_goods42.position(goods42);
        markerOptions_goods42.title("범어애견");
        markerOptions_goods42.snippet("경상남도 양산시 물금읍 범어리 590-1");
        markerOptions_goods42.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods42);

        //동양애견용품사료할인마트
        LatLng goods43 = new LatLng(35.2553619,129.087553);
        MarkerOptions markerOptions_goods43 = new MarkerOptions();
        markerOptions_goods43.position(goods43);
        markerOptions_goods43.title("동양애견용품사료할인마트");
        markerOptions_goods43.snippet("부산광역시 금정구 구서2동 742-9");
        markerOptions_goods43.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods43);

        //백평애견용품백화점
        LatLng goods44 = new LatLng(35.2324398,129.007988);
        MarkerOptions markerOptions_goods44 = new MarkerOptions();
        markerOptions_goods44.position(goods44);
        markerOptions_goods44.title("백평애견용품백화점");
        markerOptions_goods44.snippet("부산광역시 북구 화명3동 2292-3");
        markerOptions_goods44.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods44);

        //펫마트남천점
        LatLng goods45 = new LatLng(35.1406165,129.105835);
        MarkerOptions markerOptions_goods45 = new MarkerOptions();
        markerOptions_goods45.position(goods45);
        markerOptions_goods45.title("펫마트남천점");
        markerOptions_goods45.snippet("부산광역시 수영구 남천1동 340-1");
        markerOptions_goods45.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods45);

        //핫독애견용품
        LatLng goods46 = new LatLng(35.1202596,129.1118431);
        MarkerOptions markerOptions_goods46 = new MarkerOptions();
        markerOptions_goods46.position(goods46);
        markerOptions_goods46.title("핫독애견용품");
        markerOptions_goods46.snippet("부산광역시 남구 용호동 370-32");
        markerOptions_goods46.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods46);

        //애완동물메카마트
        LatLng goods47 = new LatLng(35.1655993,129.1652298);
        MarkerOptions markerOptions_goods47 = new MarkerOptions();
        markerOptions_goods47.position(goods47);
        markerOptions_goods47.title("애완동물메카마트");
        markerOptions_goods47.snippet("부산광역시 해운대구 중동 1360");
        markerOptions_goods47.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods47);

        //사림누리애견
        LatLng goods48 = new LatLng(35.2437966,128.6847496);
        MarkerOptions markerOptions_goods48 = new MarkerOptions();
        markerOptions_goods48.position(goods48);
        markerOptions_goods48.title("사림누리애견");
        markerOptions_goods48.snippet("경상남도 창원시 의창구 사림동 43-15");
        markerOptions_goods48.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods48);

        //프렌드애견
        LatLng goods49 = new LatLng(34.8858605,128.6202908);
        MarkerOptions markerOptions_goods49 = new MarkerOptions();
        markerOptions_goods49.position(goods49);
        markerOptions_goods49.title("프렌드애견");
        markerOptions_goods49.snippet("경상남도 거제시 고현동 760-8");
        markerOptions_goods49.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods49);

        //복남이네애견
        LatLng goods50 = new LatLng(34.8586088,128.4251976);
        MarkerOptions markerOptions_goods50 = new MarkerOptions();
        markerOptions_goods50.position(goods50);
        markerOptions_goods50.title("복남이네애견");
        markerOptions_goods50.snippet("경상남도 통영시 무전동 1026-3");
        markerOptions_goods50.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods50);

        //명품애견
        LatLng goods51 = new LatLng(35.1853837,128.0851364);
        MarkerOptions markerOptions_goods51 = new MarkerOptions();
        markerOptions_goods51.position(goods51);
        markerOptions_goods51.title("명품애견");
        markerOptions_goods51.snippet("경상남도 진주시 망경동 41-2");
        markerOptions_goods51.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods51);

        //행복애견샵
        LatLng goods52 = new LatLng(35.1977991,128.0866814);
        MarkerOptions markerOptions_goods52 = new MarkerOptions();
        markerOptions_goods52.position(goods52);
        markerOptions_goods52.title("행복애견샵");
        markerOptions_goods52.snippet("경상남도 진주시 수정동 21-15");
        markerOptions_goods52.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods52);

        //도그몰 애견종합할인마트 본점
        LatLng goods53 = new LatLng(34.9397741,127.69907);
        MarkerOptions markerOptions_goods53 = new MarkerOptions();
        markerOptions_goods53.position(goods53);
        markerOptions_goods53.title("도그몰 애견종합할인마트 본점");
        markerOptions_goods53.snippet("전라남도 광양시 중동 1308-13 KR");
        markerOptions_goods53.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods53);

        //카니네펫샵
        LatLng goods54 = new LatLng(34.9428699,127.688942);
        MarkerOptions markerOptions_goods54 = new MarkerOptions();
        markerOptions_goods54.position(goods54);
        markerOptions_goods54.title("카니네펫샵");
        markerOptions_goods54.snippet("전라남도 광양시 중동 1393-3");
        markerOptions_goods54.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods54);

        //펫마트 순천법원
        LatLng goods55 = new LatLng(34.971641,127.5226021);
        MarkerOptions markerOptions_goods55 = new MarkerOptions();
        markerOptions_goods55.position(goods55);
        markerOptions_goods55.title("펫마트 순천법원");
        markerOptions_goods55.snippet("전라남도 순천시 왕지동 849-2");
        markerOptions_goods55.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods55);

        //애견풀하우스
        LatLng goods56 = new LatLng(34.9450509,127.5177097);
        MarkerOptions markerOptions_goods56 = new MarkerOptions();
        markerOptions_goods56.position(goods56);
        markerOptions_goods56.title("애견풀하우스");
        markerOptions_goods56.snippet("전라남도 순천시 연향동 1377-10");
        markerOptions_goods56.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods56);

        //엔젤애견
        LatLng goods57 = new LatLng(34.809998,126.4212227);
        MarkerOptions markerOptions_goods57 = new MarkerOptions();
        markerOptions_goods57.position(goods57);
        markerOptions_goods57.title("엔젤애견");
        markerOptions_goods57.snippet("전라남도 목포시 상동 787-7");
        markerOptions_goods57.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods57);

        //나주애견랜드
        LatLng goods58 = new LatLng(35.0297152,126.7173386);
        MarkerOptions markerOptions_goods58 = new MarkerOptions();
        markerOptions_goods58.position(goods58);
        markerOptions_goods58.title("나주애견랜드");
        markerOptions_goods58.snippet("전라남도 나주시 금성동 30-1");
        markerOptions_goods58.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods58);

        //진월애견편의방
        LatLng goods59 = new LatLng(35.1179428,126.8968964);
        MarkerOptions markerOptions_goods59 = new MarkerOptions();
        markerOptions_goods59.position(goods59);
        markerOptions_goods59.title("진월애견편의방");
        markerOptions_goods59.snippet("광주광역시 남구 진월동 410-5");
        markerOptions_goods59.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods59);

        //소문난강아지
        LatLng goods60 = new LatLng(35.140055,126.8594742);
        MarkerOptions markerOptions_goods60 = new MarkerOptions();
        markerOptions_goods60.position(goods60);
        markerOptions_goods60.title("소문난강아지");
        markerOptions_goods60.snippet("광주광역시 서구 금호동 733-13");
        markerOptions_goods60.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods60);

        //에바다애견
        LatLng goods61 = new LatLng(35.146512,126.9114876);
        MarkerOptions markerOptions_goods61 = new MarkerOptions();
        markerOptions_goods61.position(goods61);
        markerOptions_goods61.title("에바다애견");
        markerOptions_goods61.snippet("광주광역시 남구 구동 37-4");
        markerOptions_goods61.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods61);

        //한빛애견
        LatLng goods62 = new LatLng(35.150793,126.9025612);
        MarkerOptions markerOptions_goods62 = new MarkerOptions();
        markerOptions_goods62.position(goods62);
        markerOptions_goods62.title("한빛애견");
        markerOptions_goods62.snippet("광주광역시 서구 양동 25-44");
        markerOptions_goods62.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods62);

        //빅펫하우스
        LatLng goods63 = new LatLng(35.1306493,126.8987846);
        MarkerOptions markerOptions_goods63 = new MarkerOptions();
        markerOptions_goods63.position(goods63);
        markerOptions_goods63.title("빅펫하우스");
        markerOptions_goods63.snippet("광주광역시 남구 주월동 서문대로 801");
        markerOptions_goods63.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods63);

        //서강애견샵
        LatLng goods64 = new LatLng(35.1773162,126.8713188);
        MarkerOptions markerOptions_goods64 = new MarkerOptions();
        markerOptions_goods64.position(goods64);
        markerOptions_goods64.title("서강애견샵");
        markerOptions_goods64.snippet("광주광역시 북구 운암동 925");
        markerOptions_goods64.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods64);

        //애견용품할인점
        LatLng goods65 = new LatLng(35.1872075,126.8917465);
        MarkerOptions markerOptions_goods65 = new MarkerOptions();
        markerOptions_goods65.position(goods65);
        markerOptions_goods65.title("애견용품할인점");
        markerOptions_goods65.snippet("광주광역시 북구 매곡동 32-2");
        markerOptions_goods65.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods65);

        //애견하우스 광주
        LatLng goods66 = new LatLng(35.1797716,126.9144917);
        MarkerOptions markerOptions_goods66 = new MarkerOptions();
        markerOptions_goods66.position(goods66);
        markerOptions_goods66.title("애견하우스 광주");
        markerOptions_goods66.snippet("광주광역시 북구 용봉동 1274-1");
        markerOptions_goods66.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods66);

        //멍멍아
        LatLng goods67 = new LatLng(35.1848225,126.8332958);
        MarkerOptions markerOptions_goods67 = new MarkerOptions();
        markerOptions_goods67.position(goods67);
        markerOptions_goods67.title("멍멍아");
        markerOptions_goods67.snippet("광주광역시 광산구 신가동 1001-1");
        markerOptions_goods67.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods67);

        //펫플러스
        LatLng goods68 = new LatLng(35.2147006,126.8523502);
        MarkerOptions markerOptions_goods68 = new MarkerOptions();
        markerOptions_goods68.position(goods68);
        markerOptions_goods68.title("펫플러스");
        markerOptions_goods68.snippet("광주광역시 광산구 쌍암동 694-93");
        markerOptions_goods68.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods68);

        //패밀리애견
        LatLng goods69 = new LatLng(35.2204505,126.8434238);
        MarkerOptions markerOptions_goods69 = new MarkerOptions();
        markerOptions_goods69.position(goods69);
        markerOptions_goods69.title("패밀리애견");
        markerOptions_goods69.snippet("광주광역시 광산구 쌍암동 662-1");
        markerOptions_goods69.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods69);

        //펫스토리
        LatLng goods70 = new LatLng(35.1936608,126.807375);
        MarkerOptions markerOptions_goods70 = new MarkerOptions();
        markerOptions_goods70.position(goods70);
        markerOptions_goods70.title("펫스토리");
        markerOptions_goods70.snippet("광주광역시 광산구 장덕동");
        markerOptions_goods70.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods70);

        //강아지천국
        LatLng goods71 = new LatLng(35.5679805,126.8543243);
        MarkerOptions markerOptions_goods71 = new MarkerOptions();
        markerOptions_goods71.position(goods71);
        markerOptions_goods71.title("강아지천국");
        markerOptions_goods71.snippet("전라북도 정읍시 수성동 706-1");
        markerOptions_goods71.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods71);

        //강아지나라 김제시
        LatLng goods72 = new LatLng(35.7988801,126.8844938);
        MarkerOptions markerOptions_goods72 = new MarkerOptions();
        markerOptions_goods72.position(goods72);
        markerOptions_goods72.title("강아지나라 김제시");
        markerOptions_goods72.snippet("전라북도 김제시 요촌동 514-16 KR");
        markerOptions_goods72.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods72);

        //애견백화점
        LatLng goods73 = new LatLng(35.8019083,126.891489);
        MarkerOptions markerOptions_goods73 = new MarkerOptions();
        markerOptions_goods73.position(goods73);
        markerOptions_goods73.title("애견백화점");
        markerOptions_goods73.snippet("전라북도 김제시 요촌동 83-1");
        markerOptions_goods73.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods73);

        //펫마트송천에코점
        LatLng goods74 = new LatLng(35.8694733,127.1219444);
        MarkerOptions markerOptions_goods74 = new MarkerOptions();
        markerOptions_goods74.position(goods74);
        markerOptions_goods74.title("펫마트송천에코점");
        markerOptions_goods74.snippet("전라북도 전주시 덕진구 송천동2가 453-2");
        markerOptions_goods74.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods74);

        //올리브애견
        LatLng goods75 = new LatLng(35.8560136,127.1200562);
        MarkerOptions markerOptions_goods75 = new MarkerOptions();
        markerOptions_goods75.position(goods75);
        markerOptions_goods75.title("올리브애견");
        markerOptions_goods75.snippet("전라북도 전주시 덕진구 송천동1가 386-3");
        markerOptions_goods75.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods75);

        //펫마트 전주효자점
        LatLng goods76 = new LatLng(35.8129759,127.1133184);
        MarkerOptions markerOptions_goods76 = new MarkerOptions();
        markerOptions_goods76.position(goods76);
        markerOptions_goods76.title("펫마트 전주효자점");
        markerOptions_goods76.snippet("전라북도 전주시 완산구 효자동2가 거마평로 235");
        markerOptions_goods76.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods76);

        //펫마트 전주평화점
        LatLng goods77 = new LatLng(35.7992282,127.1434021);
        MarkerOptions markerOptions_goods77 = new MarkerOptions();
        markerOptions_goods77.position(goods77);
        markerOptions_goods77.title("펫마트 전주평화점");
        markerOptions_goods77.snippet("전라북도 전주시 완산구 평화동1가 434-56");
        markerOptions_goods77.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods77);

        //나나애견
        LatLng goods78 = new LatLng(35.8090433,127.115078);
        MarkerOptions markerOptions_goods78 = new MarkerOptions();
        markerOptions_goods78.position(goods78);
        markerOptions_goods78.title("나나애견");
        markerOptions_goods78.snippet("전라북도 전주시 완산구 효자동1가 403-3");
        markerOptions_goods78.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods78);

        //애견코리아 익산
        LatLng goods79 = new LatLng(35.9439645,126.9488668);
        MarkerOptions markerOptions_goods79 = new MarkerOptions();
        markerOptions_goods79.position(goods79);
        markerOptions_goods79.title("애견코리아 익산");
        markerOptions_goods79.snippet("전라북도 익산시 창인동1가 49-23");
        markerOptions_goods79.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods79);

        //동양애견백화점
        LatLng goods80 = new LatLng(36.2006507,127.084651);
        MarkerOptions markerOptions_goods80 = new MarkerOptions();
        markerOptions_goods80.position(goods80);
        markerOptions_goods80.title("동양애견백화점");
        markerOptions_goods80.snippet("충청남도 논산시 부창동 29-4");
        markerOptions_goods80.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods80);

        //야옹아멍멍해봐
        LatLng goods81 = new LatLng(36.2017242,127.0870972);
        MarkerOptions markerOptions_goods81 = new MarkerOptions();
        markerOptions_goods81.position(goods81);
        markerOptions_goods81.title("야옹아멍멍해봐");
        markerOptions_goods81.snippet("충청남도 논산시 취암동 중앙로 427");
        markerOptions_goods81.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods81);

        //공주나라강쥐
        LatLng goods82 = new LatLng(36.2945128,127.3329163);
        MarkerOptions markerOptions_goods82 = new MarkerOptions();
        markerOptions_goods82.position(goods82);
        markerOptions_goods82.title("공주나라강쥐");
        markerOptions_goods82.snippet("대전광역시 서구 관저동");
        markerOptions_goods82.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods82);

        //양옹아 멍멍해봐
        LatLng goods83 = new LatLng(36.3007385,127.3362637);
        MarkerOptions markerOptions_goods83 = new MarkerOptions();
        markerOptions_goods83.position(goods83);
        markerOptions_goods83.title("양옹아 멍멍해봐");
        markerOptions_goods83.snippet("대전광역시 서구 관저동 관저중로96번길 21");
        markerOptions_goods83.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods83);

        //대청애견
        LatLng goods84 = new LatLng(36.3194475,127.3737717);
        MarkerOptions markerOptions_goods84 = new MarkerOptions();
        markerOptions_goods84.position(goods84);
        markerOptions_goods84.title("대청애견");
        markerOptions_goods84.snippet("대전광역시 서구 도마1동 61-6");
        markerOptions_goods84.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods84);

        //예쁜강아지
        LatLng goods85 = new LatLng(36.3153326,127.3788357);
        MarkerOptions markerOptions_goods85 = new MarkerOptions();
        markerOptions_goods85.position(goods85);
        markerOptions_goods85.title("예쁜강아지");
        markerOptions_goods85.snippet("대전광역시 서구 도마2동 128-5");
        markerOptions_goods85.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods85);

        //우아펫
        LatLng goods86 = new LatLng(36.3348678,127.3804665);
        MarkerOptions markerOptions_goods86 = new MarkerOptions();
        markerOptions_goods86.position(goods86);
        markerOptions_goods86.title("우아펫");
        markerOptions_goods86.snippet("대전광역시 서구 괴정동");
        markerOptions_goods86.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods86);

        //진주애견마트
        LatLng goods87 = new LatLng(36.3387397,127.4135971);
        MarkerOptions markerOptions_goods87 = new MarkerOptions();
        markerOptions_goods87.position(goods87);
        markerOptions_goods87.title("진주애견마트");
        markerOptions_goods87.snippet("대전광역시 중구 중촌동 117-14");
        markerOptions_goods87.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods87);

        //소문난강아지 대전
        LatLng goods88 = new LatLng(36.3256368,127.4037266);
        MarkerOptions markerOptions_goods88 = new MarkerOptions();
        markerOptions_goods88.position(goods88);
        markerOptions_goods88.title("소문난강아지 대전");
        markerOptions_goods88.snippet("대전광역시 중구 오류동 179-30");
        markerOptions_goods88.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods88);

        //삼성애견
        LatLng goods89 = new LatLng(36.3227324,127.4092197);
        MarkerOptions markerOptions_goods89 = new MarkerOptions();
        markerOptions_goods89.position(goods89);
        markerOptions_goods89.title("삼성애견");
        markerOptions_goods89.snippet("대전광역시 중구 오류동 155-1");
        markerOptions_goods89.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods89);

        //부사애견
        LatLng goods90 = new LatLng(36.3134999,127.4352264);
        MarkerOptions markerOptions_goods90 = new MarkerOptions();
        markerOptions_goods90.position(goods90);
        markerOptions_goods90.title("부사애견");
        markerOptions_goods90.snippet("대전광역시 중구 부사동 150-6");
        markerOptions_goods90.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods90);

        //누리애견샵
        LatLng goods91 = new LatLng(36.3047159,127.4540663);
        MarkerOptions markerOptions_goods91 = new MarkerOptions();
        markerOptions_goods91.position(goods91);
        markerOptions_goods91.title("누리애견샵");
        markerOptions_goods91.snippet("대전광역시 동구 가오동 667");
        markerOptions_goods91.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods91);

        //명품애견 대전
        LatLng goods92 = new LatLng(36.4232163,127.3900795);
        MarkerOptions markerOptions_goods92 = new MarkerOptions();
        markerOptions_goods92.position(goods92);
        markerOptions_goods92.title("명품애견 대전");
        markerOptions_goods92.snippet("대전광역시 유성구 관평동 1004");
        markerOptions_goods92.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods92);

        //나라애견 청주
        LatLng goods93 = new LatLng(36.6266874,127.4338531);
        MarkerOptions markerOptions_goods93 = new MarkerOptions();
        markerOptions_goods93.position(goods93);
        markerOptions_goods93.title("나라애견 청주");
        markerOptions_goods93.snippet("충청북도 청주시 흥덕구 가경동 1353");
        markerOptions_goods93.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods93);

        //심플펫
        LatLng goods94 = new LatLng(36.7804173,127.136364);
        MarkerOptions markerOptions_goods94 = new MarkerOptions();
        markerOptions_goods94.position(goods94);
        markerOptions_goods94.title("심플펫");
        markerOptions_goods94.snippet("충청남도 천안시 동남구 신방동 풍세로 769-39");
        markerOptions_goods94.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods94);

        //웃는강아지
        LatLng goods95 = new LatLng(36.789766,127.1253777);
        MarkerOptions markerOptions_goods95 = new MarkerOptions();
        markerOptions_goods95.position(goods95);
        markerOptions_goods95.title("웃는강아지");
        markerOptions_goods95.snippet("충청남도 천안시 동남구 신방동 72-6");
        markerOptions_goods95.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods95);

        //마이펫애견용품할인점
        LatLng goods96 = new LatLng(36.8189048,127.135334);
        MarkerOptions markerOptions_goods96 = new MarkerOptions();
        markerOptions_goods96.position(goods96);
        markerOptions_goods96.title("마이펫애견용품할인점");
        markerOptions_goods96.snippet("충청남도 천안시 서북구 성정2동");
        markerOptions_goods96.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods96);

        //아싸애견용품할인매장
        LatLng goods97 = new LatLng(36.8222028,127.1250343);
        MarkerOptions markerOptions_goods97 = new MarkerOptions();
        markerOptions_goods97.position(goods97);
        markerOptions_goods97.title("아싸애견용품할인매장");
        markerOptions_goods97.snippet("서북구 백석동 872번지 109호 천안시 충청남도");
        markerOptions_goods97.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods97);

        //애견하우스 당진
        LatLng goods98 = new LatLng(36.878934,126.7533875);
        MarkerOptions markerOptions_goods98 = new MarkerOptions();
        markerOptions_goods98.position(goods98);
        markerOptions_goods98.title("애견하우스 당진");
        markerOptions_goods98.snippet("충청남도 당진시 신평면 거산리 163-9");
        markerOptions_goods98.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods98);

        //조이펫(애견용품할인매장)
        LatLng goods99 = new LatLng(37.1326084,126.9105005);
        MarkerOptions markerOptions_goods99 = new MarkerOptions();
        markerOptions_goods99.position(goods99);
        markerOptions_goods99.title("조이펫(애견용품할인매장)");
        markerOptions_goods99.snippet("경기도 화성시 향남읍 평리 82-3");
        markerOptions_goods99.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods99);

        //뽀뽀애견샵
        LatLng goods100 = new LatLng(37.2187782,126.9465494);
        MarkerOptions markerOptions_goods100 = new MarkerOptions();
        markerOptions_goods100.position(goods100);
        markerOptions_goods100.title("뽀뽀애견샵");
        markerOptions_goods100.snippet("경기도 화성시 봉담읍 상리 33-1");
        markerOptions_goods100.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods100);

        //애견샵 어린왕자
        LatLng goods101 = new LatLng(37.2056881,127.012167);
        MarkerOptions markerOptions_goods101 = new MarkerOptions();
        markerOptions_goods101.position(goods101);
        markerOptions_goods101.title("애견샵 어린왕자");
        markerOptions_goods101.snippet("경기도 화성시 안녕동 37-22");
        markerOptions_goods101.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods101);

        //갤럭시펫
        LatLng goods102 = new LatLng(37.2326861,127.0642233);
        MarkerOptions markerOptions_goods102 = new MarkerOptions();
        markerOptions_goods102.position(goods102);
        markerOptions_goods102.title("갤럭시펫");
        markerOptions_goods102.snippet("경기도 화성시 반월동 135-10");
        markerOptions_goods102.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods102);

        //노을애견
        LatLng goods103 = new LatLng(37.266437,127.046113);
        MarkerOptions markerOptions_goods103 = new MarkerOptions();
        markerOptions_goods103.position(goods103);
        markerOptions_goods103.title("노을애견");
        markerOptions_goods103.snippet("경기도 수원시 영통구 매탄4동 208-19");
        markerOptions_goods103.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods103);

        //요술강아지
        LatLng goods104 = new LatLng(37.2614505,127.0471859);
        MarkerOptions markerOptions_goods104 = new MarkerOptions();
        markerOptions_goods104.position(goods104);
        markerOptions_goods104.title("요술강아지");
        markerOptions_goods104.snippet("경기도 수원시 영통구 매탄동 1253-5");
        markerOptions_goods104.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods104);

        //체리애견
        LatLng goods105 = new LatLng(37.2656173,127.0214367);
        MarkerOptions markerOptions_goods105 = new MarkerOptions();
        markerOptions_goods105.position(goods105);
        markerOptions_goods105.title("체리애견");
        markerOptions_goods105.snippet("경기도 수원시 팔달구 인계동 1003-25");
        markerOptions_goods105.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods105);

        //행운애견
        LatLng goods106 = new LatLng(37.2735747,127.0166302);
        MarkerOptions markerOptions_goods106 = new MarkerOptions();
        markerOptions_goods106.position(goods106);
        markerOptions_goods106.title("행운애견");
        markerOptions_goods106.snippet("경기도 수원시 팔달구 중동 74-1");
        markerOptions_goods106.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods106);

        //강아지세상
        LatLng goods107 = new LatLng(37.2700572,126.9587374);
        MarkerOptions markerOptions_goods107 = new MarkerOptions();
        markerOptions_goods107.position(goods107);
        markerOptions_goods107.title("강아지세상");
        markerOptions_goods107.snippet("경기도 수원시 권선구 호매실동 80-1");
        markerOptions_goods107.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods107);

        //스타애견
        LatLng goods108 = new LatLng(37.2879505,127.0292044);
        MarkerOptions markerOptions_goods108 = new MarkerOptions();
        markerOptions_goods108.position(goods108);
        markerOptions_goods108.title("스타애견");
        markerOptions_goods108.snippet("경기도 수원시 팔달구 우만동 485");
        markerOptions_goods108.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods108);

        //강아지가정부
        LatLng goods109 = new LatLng(37.3176153,126.955862);
        MarkerOptions markerOptions_goods109 = new MarkerOptions();
        markerOptions_goods109.position(goods109);
        markerOptions_goods109.title("강아지가정부");
        markerOptions_goods109.snippet("경기도 의왕시 삼동 125");
        markerOptions_goods109.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods109);

        //똥강아지
        LatLng goods110 = new LatLng(37.3063857,126.8617916);
        MarkerOptions markerOptions_goods110 = new MarkerOptions();
        markerOptions_goods110.position(goods110);
        markerOptions_goods110.title("똥강아지");
        markerOptions_goods110.snippet("경기도 안산시 상록구 이동 605-3");
        markerOptions_goods110.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods110);

        //해피애견 안산
        LatLng goods111 = new LatLng(37.2959738,126.8632936);
        MarkerOptions markerOptions_goods111 = new MarkerOptions();
        markerOptions_goods111.position(goods111);
        markerOptions_goods111.title("해피애견 안산");
        markerOptions_goods111.snippet("경기도 안산시 상록구 본오2동 871-4");
        markerOptions_goods111.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods111);

        //프로펫애견삽
        LatLng goods112 = new LatLng(37.3438909,126.7499971);
        MarkerOptions markerOptions_goods112 = new MarkerOptions();
        markerOptions_goods112.position(goods112);
        markerOptions_goods112.title("프로펫애견삽");
        markerOptions_goods112.snippet("경기도 시흥시 정왕동 1214-8");
        markerOptions_goods112.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods112);

        //벤지애견백화점
        LatLng goods113 = new LatLng(37.4535004,126.6388464);
        MarkerOptions markerOptions_goods113 = new MarkerOptions();
        markerOptions_goods113.position(goods113);
        markerOptions_goods113.title("벤지애견백화점");
        markerOptions_goods113.snippet("인천광역시 남구 용현동 622-112");
        markerOptions_goods113.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods113);

        //애견용품힐인매장 인천
        LatLng goods114 = new LatLng(37.4618807,126.6812897);
        MarkerOptions markerOptions_goods114 = new MarkerOptions();
        markerOptions_goods114.position(goods114);
        markerOptions_goods114.title("애견용품힐인매장 인천");
        markerOptions_goods114.snippet("주안동 153-3번지 지하1층 남구 인천광역시");
        markerOptions_goods114.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods114);

        //도그피아 시흥
        LatLng goods115 = new LatLng(37.4540454,126.8200779);
        MarkerOptions markerOptions_goods115 = new MarkerOptions();
        markerOptions_goods115.position(goods115);
        markerOptions_goods115.title("도그피아 시흥");
        markerOptions_goods115.snippet("경기도 시흥시 대야동 계수로197번길 16");
        markerOptions_goods115.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods115);

        //애견백화점 강릉
        LatLng goods116 = new LatLng(37.8847632,128.8252553);
        MarkerOptions markerOptions_goods116 = new MarkerOptions();
        markerOptions_goods116.position(goods116);
        markerOptions_goods116.title("애견백화점 강릉");
        markerOptions_goods116.snippet("강원도 강릉시 주문진읍 교항리 119");
        markerOptions_goods116.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods116);

        //펫클럽 강릉점
        LatLng goods117 = new LatLng(37.7582808,128.8923204);
        MarkerOptions markerOptions_goods117 = new MarkerOptions();
        markerOptions_goods117.position(goods117);
        markerOptions_goods117.title("펫클럽 강릉점");
        markerOptions_goods117.snippet("강원도 강릉시 교동 162-183");
        markerOptions_goods117.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods117);

        //애견용품 대형백화점
        LatLng goods118 = new LatLng(37.7595786,128.8958502);
        MarkerOptions markerOptions_goods118 = new MarkerOptions();
        markerOptions_goods118.position(goods118);
        markerOptions_goods118.title("애견용품 대형백화점");
        markerOptions_goods118.snippet("강원도 강릉시 교동 156-68");
        markerOptions_goods118.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods118);

        //러브펫 강릉
        LatLng goods119 = new LatLng(37.7576616,128.9001364);
        MarkerOptions markerOptions_goods119 = new MarkerOptions();
        markerOptions_goods119.position(goods119);
        markerOptions_goods119.title("러브펫 강릉");
        markerOptions_goods119.snippet("강원도 강릉시 옥천동 158-3");
        markerOptions_goods119.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods119);

        //나라애견 강릉
        LatLng goods120 = new LatLng(37.757123,128.9000076);
        MarkerOptions markerOptions_goods120 = new MarkerOptions();
        markerOptions_goods120.position(goods120);
        markerOptions_goods120.title("나라애견 강릉");
        markerOptions_goods120.snippet("강원도 강릉시 옥천동 경강로 2138-1");
        markerOptions_goods120.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods120);

        //주주클럽 동해
        LatLng goods121 = new LatLng(37.5436714,129.1018867);
        MarkerOptions markerOptions_goods121 = new MarkerOptions();
        markerOptions_goods121.position(goods121);
        markerOptions_goods121.title("주주클럽 동해");
        markerOptions_goods121.snippet("강원도 동해시 발한동 573-1");
        markerOptions_goods121.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods121);

        //서울애견 동해
        LatLng goods122 = new LatLng(37.5203506,129.1147667);
        MarkerOptions markerOptions_goods122 = new MarkerOptions();
        markerOptions_goods122.position(goods122);
        markerOptions_goods122.title("서울애견 동해");
        markerOptions_goods122.snippet("강원도 동해시 천곡동 1021");
        markerOptions_goods122.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods122);

        //강아지와고양이 동해
        LatLng goods123 = new LatLng(37.5217334,129.1180015);
        MarkerOptions markerOptions_goods123 = new MarkerOptions();
        markerOptions_goods123.position(goods123);
        markerOptions_goods123.title("강아지와고양이 동해");
        markerOptions_goods123.snippet("강원도 동해시 천곡동 1022");
        markerOptions_goods123.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods123);

        //행복한강아지 삼척
        LatLng goods124 = new LatLng(37.4397568,129.1664153);
        MarkerOptions markerOptions_goods124 = new MarkerOptions();
        markerOptions_goods124.position(goods124);
        markerOptions_goods124.title("행복한강아지 삼척");
        markerOptions_goods124.snippet("강원도 삼척시 남양동 129-3");
        markerOptions_goods124.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods124);

        //위드펫애견용품할인점
        LatLng goods125 = new LatLng(37.4472273,129.166528);
        MarkerOptions markerOptions_goods125 = new MarkerOptions();
        markerOptions_goods125.position(goods125);
        markerOptions_goods125.title("위드펫애견용품할인점");
        markerOptions_goods125.snippet("강원도 삼척시 당저동 54-2번지 1층");
        markerOptions_goods125.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods125);

        //강아지반상회
        LatLng goods126 = new LatLng(35.9573919,129.402616);
        MarkerOptions markerOptions_goods126 = new MarkerOptions();
        markerOptions_goods126.position(goods126);
        markerOptions_goods126.title("강아지반상회");
        markerOptions_goods126.snippet("경상북도 포항시 남구 오천읍 문덕리 368-1");
        markerOptions_goods126.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods126);

        //펫마트 포항상도점
        LatLng goods127 = new LatLng(36.0082493,129.3466759);
        MarkerOptions markerOptions_goods127 = new MarkerOptions();
        markerOptions_goods127.position(goods127);
        markerOptions_goods127.title("펫마트 포항상도점");
        markerOptions_goods127.snippet("경상북도 포항시 남구 상도동 중흥로 25");
        markerOptions_goods127.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods127);

        //펫마트 포항상도
        LatLng goods128 = new LatLng(36.0102801,129.3524265);
        MarkerOptions markerOptions_goods128 = new MarkerOptions();
        markerOptions_goods128.position(goods128);
        markerOptions_goods128.title("펫마트 포항상도");
        markerOptions_goods128.snippet("경상북도 포항시 남구 상도동 684");
        markerOptions_goods128.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods128);

        //해피애견타운 포항 남구점
        LatLng goods129 = new LatLng(36.0157128,129.3525982);
        MarkerOptions markerOptions_goods129 = new MarkerOptions();
        markerOptions_goods129.position(goods129);
        markerOptions_goods129.title("해피애견타운 포항 남구점");
        markerOptions_goods129.snippet("상도동 601-14 2층 남구 포항시 경상북도 KR");
        markerOptions_goods129.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods129);

        //스누피애견 포항
        LatLng goods130 = new LatLng(36.0140986,129.3466759);
        MarkerOptions markerOptions_goods130 = new MarkerOptions();
        markerOptions_goods130.position(goods130);
        markerOptions_goods130.title("스누피애견 포항");
        markerOptions_goods130.snippet("경상북도 포항시 남구 대잠동 938");
        markerOptions_goods130.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods130);

        //펫마트 포항점
        LatLng goods131 = new LatLng(36.0278785,129.3669319);
        MarkerOptions markerOptions_goods131 = new MarkerOptions();
        markerOptions_goods131.position(goods131);
        markerOptions_goods131.title("펫마트 포항점");
        markerOptions_goods131.snippet("경상북도 포항시 남구 해도동 34-9");
        markerOptions_goods131.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods131);

        //강아지나라 포항
        LatLng goods132 = new LatLng(36.0337785,129.3601942);
        MarkerOptions markerOptions_goods132 = new MarkerOptions();
        markerOptions_goods132.position(goods132);
        markerOptions_goods132.title("강아지나라 포항");
        markerOptions_goods132.snippet("경상북도 포항시 북구 대흥동 620-9");
        markerOptions_goods132.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods132);

        //포항애견
        LatLng goods133 = new LatLng(36.0346461,129.3608594);
        MarkerOptions markerOptions_goods133 = new MarkerOptions();
        markerOptions_goods133.position(goods133);
        markerOptions_goods133.title("포항애견");
        markerOptions_goods133.snippet("경상북도 포항시 북구 대흥동 619-24");
        markerOptions_goods133.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods133);

        //펫마트 포항
        LatLng goods134 = new LatLng(36.0418468,129.3656445);
        MarkerOptions markerOptions_goods134 = new MarkerOptions();
        markerOptions_goods134.position(goods134);
        markerOptions_goods134.title("펫마트 포항");
        markerOptions_goods134.snippet("경북 포항시 북구 용흥2동 625-2");
        markerOptions_goods134.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods134);

        //황제애견
        LatLng goods135 = new LatLng(36.0399036,129.3637347);
        MarkerOptions markerOptions_goods135 = new MarkerOptions();
        markerOptions_goods135.position(goods135);
        markerOptions_goods135.title("황제애견");
        markerOptions_goods135.snippet("경상북도 포항시 북구 신흥동 693-11");
        markerOptions_goods135.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods135);

        //펫마트 경주화랑점
        LatLng goods136 = new LatLng(35.8445693,129.2016864);
        MarkerOptions markerOptions_goods136 = new MarkerOptions();
        markerOptions_goods136.position(goods136);
        markerOptions_goods136.title("펫마트 경주화랑점");
        markerOptions_goods136.snippet("경상북도 경주시 성건동 411-14");
        markerOptions_goods136.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods136);

        //황제애견 경주
        LatLng goods137 = new LatLng(35.8454738,129.2068148);
        MarkerOptions markerOptions_goods137 = new MarkerOptions();
        markerOptions_goods137.position(goods137);
        markerOptions_goods137.title("황제애견 경주");
        markerOptions_goods137.snippet("경상북도 경주시 성건동 211-9");
        markerOptions_goods137.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods137);

        //우리애견 경주
        LatLng goods138 = new LatLng(35.8492307,129.2172647);
        MarkerOptions markerOptions_goods138 = new MarkerOptions();
        markerOptions_goods138.position(goods138);
        markerOptions_goods138.title("우리애견 경주");
        markerOptions_goods138.snippet("경상북도 경주시 성동동 201-3");
        markerOptions_goods138.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods138);

        //펫마트경주점
        LatLng goods139 = new LatLng(35.8627089,129.2212343);
        MarkerOptions markerOptions_goods139 = new MarkerOptions();
        markerOptions_goods139.position(goods139);
        markerOptions_goods139.title("펫마트경주점");
        markerOptions_goods139.snippet("경상북도 경주시 용강동 1174-11");
        markerOptions_goods139.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods139);

        //꿈꾸는강아지
        LatLng goods140 = new LatLng(35.8679257,129.2203116);
        MarkerOptions markerOptions_goods140 = new MarkerOptions();
        markerOptions_goods140.position(goods140);
        markerOptions_goods140.title("꿈꾸는강아지");
        markerOptions_goods140.snippet("경상북도 경주시 황성동 255-3");
        markerOptions_goods140.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods140);

        //멍멍아놀자
        LatLng goods141 = new LatLng(35.5664096,129.2492795);
        MarkerOptions markerOptions_goods141 = new MarkerOptions();
        markerOptions_goods141.position(goods141);
        markerOptions_goods141.title("멍멍아놀자");
        markerOptions_goods141.snippet("울산광역시 울주군 범서읍 구영리 411-1");
        markerOptions_goods141.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods141);

        //도그앤피플
        LatLng goods142 = new LatLng(35.5462121,129.2610812);
        MarkerOptions markerOptions_goods142 = new MarkerOptions();
        markerOptions_goods142.position(goods142);
        markerOptions_goods142.title("도그앤피플");
        markerOptions_goods142.snippet("울산광역시 남구 무거동 대학로 127");
        markerOptions_goods142.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods142);

        //신운미애견미용
        LatLng goods143 = new LatLng(35.5467708,129.3093181);
        MarkerOptions markerOptions_goods143 = new MarkerOptions();
        markerOptions_goods143.position(goods143);
        markerOptions_goods143.title("신운미애견미용");
        markerOptions_goods143.snippet("울산광역시 남구 신정1동 519-16");
        markerOptions_goods143.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods143);

        //스타멍스
        LatLng goods144 = new LatLng(35.5319818,129.3102193);
        MarkerOptions markerOptions_goods144 = new MarkerOptions();
        markerOptions_goods144.position(goods144);
        markerOptions_goods144.title("스타멍스");
        markerOptions_goods144.snippet("울산광역시 남구 신정동");
        markerOptions_goods144.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods144);

        //애견스케치
        LatLng goods145 = new LatLng(35.5297291,129.3177295);
        MarkerOptions markerOptions_goods145 = new MarkerOptions();
        markerOptions_goods145.position(goods145);
        markerOptions_goods145.title("애견스케치");
        markerOptions_goods145.snippet("울산광역시 남구 신정4동 1836-12");
        markerOptions_goods145.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods145);

        //아라애견 영주
        LatLng goods146 = new LatLng(36.8258614,128.6195612);
        MarkerOptions markerOptions_goods146 = new MarkerOptions();
        markerOptions_goods146.position(goods146);
        markerOptions_goods146.title("아라애견 영주");
        markerOptions_goods146.snippet("경상북도 영주시 영주2동 505-7");
        markerOptions_goods146.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods146);

        //애견용품전문매장 영주
        LatLng goods147 = new LatLng(36.8150397,128.6190033);
        MarkerOptions markerOptions_goods147 = new MarkerOptions();
        markerOptions_goods147.position(goods147);
        markerOptions_goods147.title("애견용품전문매장 영주");
        markerOptions_goods147.snippet("경상북도 영주시 가흥1동 1454-19");
        markerOptions_goods147.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods147);

        //작은동물원
        LatLng goods148 = new LatLng(35.9679342,128.9328647);
        MarkerOptions markerOptions_goods148 = new MarkerOptions();
        markerOptions_goods148.position(goods148);
        markerOptions_goods148.title("작은동물원");
        markerOptions_goods148.snippet("경상북도 영천시 문내동 152-25");
        markerOptions_goods148.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods148);

        //행복한강아지나라
        LatLng goods149 = new LatLng(35.9166511,128.8198471);
        MarkerOptions markerOptions_goods149 = new MarkerOptions();
        markerOptions_goods149.position(goods149);
        markerOptions_goods149.title("행복한강아지나라");
        markerOptions_goods149.snippet("경상북도 경산시 하양읍 동서리 592-44");
        markerOptions_goods149.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods149);

        //더펫샵 경산점
        LatLng goods150 = new LatLng(35.8283392,128.7241673);
        MarkerOptions markerOptions_goods150 = new MarkerOptions();
        markerOptions_goods150.position(goods150);
        markerOptions_goods150.title("더펫샵 경산점");
        markerOptions_goods150.snippet("경상북도 경산시 옥산동 경산로 227");
        markerOptions_goods150.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods150);

        //내사랑애견
        LatLng goods151 = new LatLng(35.8286524,128.7260985);
        MarkerOptions markerOptions_goods151 = new MarkerOptions();
        markerOptions_goods151.position(goods151);
        markerOptions_goods151.title("내사랑애견");
        markerOptions_goods151.snippet("경상북도 경산시 옥산동 726-3");
        markerOptions_goods151.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods151);

        //펫마트딜라잇
        LatLng goods152 = new LatLng(35.8389683,128.7650442);
        MarkerOptions markerOptions_goods152 = new MarkerOptions();
        markerOptions_goods152.position(goods152);
        markerOptions_goods152.title("펫마트딜라잇");
        markerOptions_goods152.snippet("경상북도 경산시 압량면 압독2로8길 25-1");
        markerOptions_goods152.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods152);

        //엄지애견
        LatLng goods153 = new LatLng(35.8714729,128.7089109);
        MarkerOptions markerOptions_goods153 = new MarkerOptions();
        markerOptions_goods153.position(goods153);
        markerOptions_goods153.title("엄지애견");
        markerOptions_goods153.snippet("대구광역시 동구 신기동 29-1");
        markerOptions_goods153.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods153);

        //똥강아지 대구
        LatLng goods154 = new LatLng(35.8699601,128.7020659);
        MarkerOptions markerOptions_goods154 = new MarkerOptions();
        markerOptions_goods154.position(goods154);
        markerOptions_goods154.title("똥강아지 대구");
        markerOptions_goods154.snippet("대구광역시 동구 신기동 547-9");
        markerOptions_goods154.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods154);

        //펫마트 동구점
        LatLng goods155 = new LatLng(35.8761327,128.6832476);
        MarkerOptions markerOptions_goods155 = new MarkerOptions();
        markerOptions_goods155.position(goods155);
        markerOptions_goods155.title("펫마트 동구점");
        markerOptions_goods155.snippet("대구광역시 동구 안심2동 동촌로 403-1");
        markerOptions_goods155.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods155);

        //애견의 품격
        LatLng goods156 = new LatLng(35.8662911,128.6214709);
        MarkerOptions markerOptions_goods156 = new MarkerOptions();
        markerOptions_goods156.position(goods156);
        markerOptions_goods156.title("애견의 품격");
        markerOptions_goods156.snippet("대구광역시 수성구 범어3동 국채보상로164길 25");
        markerOptions_goods156.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods156);

        //구피애견
        LatLng goods157 = new LatLng(35.8892065,128.6286592);
        MarkerOptions markerOptions_goods157 = new MarkerOptions();
        markerOptions_goods157.position(goods157);
        markerOptions_goods157.title("구피애견");
        markerOptions_goods157.snippet("대구광역시 동구 신암5동 134-328");
        markerOptions_goods157.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods157);

        //미래애견
        LatLng goods158 = new LatLng(35.8823743,128.6120081);
        MarkerOptions markerOptions_goods158 = new MarkerOptions();
        markerOptions_goods158.position(goods158);
        markerOptions_goods158.title("미래애견");
        markerOptions_goods158.snippet("대구광역시 북구 대현1동 218-19");
        markerOptions_goods158.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods158);

        //강아지마을 대구
        LatLng goods159 = new LatLng(35.8823396,128.5977817);
        MarkerOptions markerOptions_goods159 = new MarkerOptions();
        markerOptions_goods159.position(goods159);
        markerOptions_goods159.title("강아지마을 대구");
        markerOptions_goods159.snippet("대구광역시 북구 침산2동 157-8");
        markerOptions_goods159.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods159);

        //펫마트중구점
        LatLng goods160 = new LatLng(35.8646739,128.5979748);
        MarkerOptions markerOptions_goods160 = new MarkerOptions();
        markerOptions_goods160.position(goods160);
        markerOptions_goods160.title("펫마트중구점");
        markerOptions_goods160.snippet("대구광역시 중구 성내2동 달구벌대로 2100 메트로센터 지하 E105~108호");
        markerOptions_goods160.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods160);

        //디앤피애견샵
        LatLng goods161 = new LatLng(35.8475088,128.5954857);
        MarkerOptions markerOptions_goods161 = new MarkerOptions();
        markerOptions_goods161.position(goods161);
        markerOptions_goods161.title("디앤피애견샵");
        markerOptions_goods161.snippet("대구광역시 남구 봉덕1동 572-1");
        markerOptions_goods161.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods161);

        //애견마트 대구
        LatLng goods162 = new LatLng(35.8389683,128.574264);
        MarkerOptions markerOptions_goods162 = new MarkerOptions();
        markerOptions_goods162.position(goods162);
        markerOptions_goods162.title("애견마트 대구");
        markerOptions_goods162.snippet("대구광역시 남구 대명9동 대명로 152");
        markerOptions_goods162.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods162);

        //펫마트서대구점
        LatLng goods163 = new LatLng(35.8380985,128.5607672);
        MarkerOptions markerOptions_goods163 = new MarkerOptions();
        markerOptions_goods163.position(goods163);
        markerOptions_goods163.title("펫마트서대구점");
        markerOptions_goods163.snippet("대구광역시 남구 대명11동 1116-2");
        markerOptions_goods163.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods163);

        //펫샵 허니몬
        LatLng goods164 = new LatLng(35.8595439,128.5634923);
        MarkerOptions markerOptions_goods164 = new MarkerOptions();
        markerOptions_goods164.position(goods164);
        markerOptions_goods164.title("펫샵 허니몬");
        markerOptions_goods164.snippet("1816 1 층 허니 몬, 달구벌대로 달서구 대구광역시");
        markerOptions_goods164.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods164);

        //리틀애견 대구
        LatLng goods165 = new LatLng(35.8723423,128.5730624);
        MarkerOptions markerOptions_goods165 = new MarkerOptions();
        markerOptions_goods165.position(goods165);
        markerOptions_goods165.title("리틀애견 대구");
        markerOptions_goods165.snippet("대구광역시 서구 비산2.3동 396-8");
        markerOptions_goods165.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods165);

        //펫마트 칠곡점
        LatLng goods166 = new LatLng(35.9218643,128.5462832);
        MarkerOptions markerOptions_goods166 = new MarkerOptions();
        markerOptions_goods166.position(goods166);
        markerOptions_goods166.title("펫마트 칠곡점");
        markerOptions_goods166.snippet("대구광역시 북구 태전동 149-3");
        markerOptions_goods166.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods166);

        //펫마트화원점
        LatLng goods167 = new LatLng(35.7970701,128.4875751);
        MarkerOptions markerOptions_goods167 = new MarkerOptions();
        markerOptions_goods167.position(goods167);
        markerOptions_goods167.title("펫마트화원점");
        markerOptions_goods167.snippet("대구광역시 달성군 화원읍 설화리 553-10");
        markerOptions_goods167.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods167);

        //더기스토리
        LatLng goods168 = new LatLng(35.8093913,128.5269284);
        MarkerOptions markerOptions_goods168 = new MarkerOptions();
        markerOptions_goods168.position(goods168);
        markerOptions_goods168.title("더기스토리");
        markerOptions_goods168.snippet("대구광역시 달서구 진천동 293-6");
        markerOptions_goods168.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods168);

        //펫마트 테크노폴리스점
        LatLng goods169 = new LatLng(35.6889689,128.4495735);
        MarkerOptions markerOptions_goods169 = new MarkerOptions();
        markerOptions_goods169.position(goods169);
        markerOptions_goods169.title("펫마트 테크노폴리스점");
        markerOptions_goods169.snippet("대구광역시 달성군 현풍면 테크노대로 24");
        markerOptions_goods169.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods169);

        //펫마트밀양점
        LatLng goods170 = new LatLng(35.496491,128.7470198);
        MarkerOptions markerOptions_goods170 = new MarkerOptions();
        markerOptions_goods170.position(goods170);
        markerOptions_goods170.title("펫마트밀양점");
        markerOptions_goods170.snippet("경상남도 밀양시 내이동 백민로 43");
        markerOptions_goods170.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods170);

        //이쁜멍멍이
        LatLng goods171 = new LatLng(35.4951163,128.7516385);
        MarkerOptions markerOptions_goods171 = new MarkerOptions();
        markerOptions_goods171.position(goods171);
        markerOptions_goods171.title("이쁜멍멍이");
        markerOptions_goods171.snippet("경상남도 밀양시 내일동 486-1");
        markerOptions_goods171.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods171);

        //도그마미
        LatLng goods172 = new LatLng(35.2652088,129.0926814);
        MarkerOptions markerOptions_goods172 = new MarkerOptions();
        markerOptions_goods172.position(goods172);
        markerOptions_goods172.title("도그마미");
        markerOptions_goods172.snippet("부산광역시 금정구 남산동 245-2");
        markerOptions_goods172.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods172);

        //펫마트진해점
        LatLng goods173 = new LatLng(35.1553983,128.6976564);
        MarkerOptions markerOptions_goods173 = new MarkerOptions();
        markerOptions_goods173.position(goods173);
        markerOptions_goods173.title("펫마트진해점");
        markerOptions_goods173.snippet("경상남도 창원시 진해구 이동 455-9");
        markerOptions_goods173.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods173);

        //똥개애견용품할인점
        LatLng goods174 = new LatLng(35.1538019,128.6954248);
        MarkerOptions markerOptions_goods174 = new MarkerOptions();
        markerOptions_goods174.position(goods174);
        markerOptions_goods174.title("똥개애견용품할인점");
        markerOptions_goods174.snippet("경상남도 창원시 진해구 이동");
        markerOptions_goods174.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods174);

        //장보는강양이마산합포점
        LatLng goods175 = new LatLng(35.1807713,128.5653698);
        MarkerOptions markerOptions_goods175 = new MarkerOptions();
        markerOptions_goods175.position(goods175);
        markerOptions_goods175.title("장보는강양이마산합포점");
        markerOptions_goods175.snippet("경상남도 창원시 마산합포구 해운동 드림베이대로");
        markerOptions_goods175.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods175);

        //언양애견
        LatLng goods176 = new LatLng(35.5667063,129.124825);
        MarkerOptions markerOptions_goods176 = new MarkerOptions();
        markerOptions_goods176.position(goods176);
        markerOptions_goods176.title("언양애견");
        markerOptions_goods176.snippet("울산광역시 울주군 언양읍 동부리 241-3");
        markerOptions_goods176.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods176);

        //개발바닥
        LatLng goods177 = new LatLng(36.7874977,126.9974899);
        MarkerOptions markerOptions_goods177 = new MarkerOptions();
        markerOptions_goods177.position(goods177);
        markerOptions_goods177.title("개발바닥");
        markerOptions_goods177.snippet("충청남도 아산시 온양1동 1320");
        markerOptions_goods177.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods177);

        //애견마을 아산
        LatLng goods178 = new LatLng(36.7805891,126.9992924);
        MarkerOptions markerOptions_goods178 = new MarkerOptions();
        markerOptions_goods178.position(goods178);
        markerOptions_goods178.title("애견마을 아산");
        markerOptions_goods178.snippet("충청남도 아산시 온양2동 201-13");
        markerOptions_goods178.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods178);

        //하얀강아지
        LatLng goods179 = new LatLng(36.780761,127.0079184);
        MarkerOptions markerOptions_goods179 = new MarkerOptions();
        markerOptions_goods179.position(goods179);
        markerOptions_goods179.title("하얀강아지");
        markerOptions_goods179.snippet("충청남도 아산시 온양1동 80-2");
        markerOptions_goods179.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods179);

        //해피애견백화점
        LatLng goods180 = new LatLng(36.6783664,126.8446684);
        MarkerOptions markerOptions_goods180 = new MarkerOptions();
        markerOptions_goods180.position(goods180);
        markerOptions_goods180.title("해피애견백화점");
        markerOptions_goods180.snippet("충청남도 예산군 예산읍 예산리 425-3");
        markerOptions_goods180.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods180);

        //야옹아멍멍해봐 홍성
        LatLng goods181 = new LatLng(36.6007143,126.6691446);
        MarkerOptions markerOptions_goods181 = new MarkerOptions();
        markerOptions_goods181.position(goods181);
        markerOptions_goods181.title("야옹아멍멍해봐 홍성");
        markerOptions_goods181.snippet("충청남도 홍성군 홍성읍 오관리 322-1");
        markerOptions_goods181.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods181);

        //야옹아멍멍해봐 서산
        LatLng goods182 = new LatLng(36.7784752,126.4652538);
        MarkerOptions markerOptions_goods182 = new MarkerOptions();
        markerOptions_goods182.position(goods182);
        markerOptions_goods182.title("야옹아멍멍해봐 서산");
        markerOptions_goods182.snippet("충청남도 서산시 동문1동 201-1");
        markerOptions_goods182.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods182);

        //애견용품할인매장 서산
        LatLng goods183 = new LatLng(36.7777706,126.4569712);
        MarkerOptions markerOptions_goods183 = new MarkerOptions();
        markerOptions_goods183.position(goods183);
        markerOptions_goods183.title("애견용품할인매장 서산");
        markerOptions_goods183.snippet("충청남도 서산시 동문동 1025-32");
        markerOptions_goods183.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods183);

        //서산애견
        LatLng goods184 = new LatLng(36.7770143,126.4526367);
        MarkerOptions markerOptions_goods184 = new MarkerOptions();
        markerOptions_goods184.position(goods184);
        markerOptions_goods184.title("서산애견");
        markerOptions_goods184.snippet("충청남도 서산시 읍내동 23-13");
        markerOptions_goods184.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods184);

        //애견사랑 서산
        LatLng goods185 = new LatLng(36.7799533,126.4484525);
        MarkerOptions markerOptions_goods185 = new MarkerOptions();
        markerOptions_goods185.position(goods185);
        markerOptions_goods185.title("애견사랑 서산");
        markerOptions_goods185.snippet("충청남도 서산시 읍내동 124-19");
        markerOptions_goods185.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods185);

        //금비동물애견
        LatLng goods186 = new LatLng(36.4738236,127.1354413);
        MarkerOptions markerOptions_goods186 = new MarkerOptions();
        markerOptions_goods186.position(goods186);
        markerOptions_goods186.title("금비동물애견");
        markerOptions_goods186.snippet("충청남도 공주시 신관동 256-6");
        markerOptions_goods186.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods186);

        //애견클럽 공주
        LatLng goods187 = new LatLng(36.4739099,127.1358275);
        MarkerOptions markerOptions_goods187 = new MarkerOptions();
        markerOptions_goods187.position(goods187);
        markerOptions_goods187.title("애견클럽 공주");
        markerOptions_goods187.snippet("충청남도 공주시 신관동 257-6");
        markerOptions_goods187.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods187);

        //빅토리애견
        LatLng goods188 = new LatLng(36.5918939,128.1974459);
        MarkerOptions markerOptions_goods188 = new MarkerOptions();
        markerOptions_goods188.position(goods188);
        markerOptions_goods188.title("빅토리애견");
        markerOptions_goods188.snippet("경상북도 문경시 점촌동 178-3");
        markerOptions_goods188.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods188);

        //하니애견
        LatLng goods189 = new LatLng(36.4143065,128.1510115);
        MarkerOptions markerOptions_goods189 = new MarkerOptions();
        markerOptions_goods189.position(goods189);
        markerOptions_goods189.title("하니애견");
        markerOptions_goods189.snippet("경상북도 상주시 낙양동 19-9");
        markerOptions_goods189.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods189);

        //퍼피엔젤애견미용전문샵
        LatLng goods190 = new LatLng(36.4169312,128.17204);
        MarkerOptions markerOptions_goods190 = new MarkerOptions();
        markerOptions_goods190.position(goods190);
        markerOptions_goods190.title("퍼피엔젤애견미용전문샵");
        markerOptions_goods190.snippet("경상북도 상주시 복룡동 230-11");
        markerOptions_goods190.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods190);

        //김천애견클럽
        LatLng goods191 = new LatLng(36.1238021,128.1028175);
        MarkerOptions markerOptions_goods191 = new MarkerOptions();
        markerOptions_goods191.position(goods191);
        markerOptions_goods191.title("김천애견클럽");
        markerOptions_goods191.snippet("경상북도 김천시 부곡동 428-21");
        markerOptions_goods191.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods191);

        //펫마트김천점
        LatLng goods192 = new LatLng(36.1219649,128.1201982);
        MarkerOptions markerOptions_goods192 = new MarkerOptions();
        markerOptions_goods192.position(goods192);
        markerOptions_goods192.title("펫마트김천점");
        markerOptions_goods192.snippet("경상북도 김천시 성내동 74-1");
        markerOptions_goods192.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods192);

        //애견천국 구미
        LatLng goods193 = new LatLng(36.112414,128.3320713);
        MarkerOptions markerOptions_goods193 = new MarkerOptions();
        markerOptions_goods193.position(goods193);
        markerOptions_goods193.title("애견천국 구미");
        markerOptions_goods193.snippet("경상북도 구미시 형곡동 152-3");
        markerOptions_goods193.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods193);

        //애견용품백화점
        LatLng goods194 = new LatLng(36.1271472,128.3368564);
        MarkerOptions markerOptions_goods194 = new MarkerOptions();
        markerOptions_goods194.position(goods194);
        markerOptions_goods194.title("애견용품백화점");
        markerOptions_goods194.snippet("경상북도 구미시 원평1동 964-209");
        markerOptions_goods194.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods194);

        //펫마트 구미점
        LatLng goods195 = new LatLng(36.1260553,128.3453965);
        MarkerOptions markerOptions_goods195 = new MarkerOptions();
        markerOptions_goods195.position(goods195);
        markerOptions_goods195.title("펫마트 구미점");
        markerOptions_goods195.snippet("경상북도 구미시 원평동 1050-3");
        markerOptions_goods195.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods195);

        //바람난똥강아지
        LatLng goods196 = new LatLng(36.138204,128.4278584);
        MarkerOptions markerOptions_goods196 = new MarkerOptions();
        markerOptions_goods196.position(goods196);
        markerOptions_goods196.title("바람난똥강아지");
        markerOptions_goods196.snippet("경상북도 구미시 옥계동 790-2");
        markerOptions_goods196.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods196);

        //애견나라 칠곡
        LatLng goods197 = new LatLng(35.9921569,128.3982253);
        MarkerOptions markerOptions_goods197 = new MarkerOptions();
        markerOptions_goods197.position(goods197);
        markerOptions_goods197.title("애견나라 칠곡");
        markerOptions_goods197.snippet("경상북도 칠곡군 왜관읍 왜관리 251-2");
        markerOptions_goods197.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods197);

        //펫마트 왜관점
        LatLng goods198 = new LatLng(35.9808539,128.398397);
        MarkerOptions markerOptions_goods198 = new MarkerOptions();
        markerOptions_goods198.position(goods198);
        markerOptions_goods198.title("펫마트 왜관점");
        markerOptions_goods198.snippet("경상북도 칠곡군 왜관읍 중앙로 67");
        markerOptions_goods198.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods198);

        //아림애견의집
        LatLng goods199 = new LatLng(35.6893182,127.9100424);
        MarkerOptions markerOptions_goods199 = new MarkerOptions();
        markerOptions_goods199.position(goods199);
        markerOptions_goods199.title("아림애견의집");
        markerOptions_goods199.snippet("경상남도 거창군 거창읍 중앙리 334-2");
        markerOptions_goods199.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods199);

        //강아지풀
        LatLng goods200 = new LatLng(35.6880801,127.9065442);
        MarkerOptions markerOptions_goods200 = new MarkerOptions();
        markerOptions_goods200.position(goods200);
        markerOptions_goods200.title("강아지풀");
        markerOptions_goods200.snippet("경상남도 거창군 거창읍 상림리 814-2");
        markerOptions_goods200.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods200);

        //강아지멋내기
        LatLng goods201 = new LatLng(37.3218815,127.8338242);
        MarkerOptions markerOptions_goods201 = new MarkerOptions();
        markerOptions_goods201.position(goods201);
        markerOptions_goods201.title("강아지멋내기");
        markerOptions_goods201.snippet("강원도 원주시 문막읍 동화리 1264");
        markerOptions_goods201.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods201);

        //둘리애견
        LatLng goods202 = new LatLng(37.3184856,127.9619265);
        MarkerOptions markerOptions_goods202 = new MarkerOptions();
        markerOptions_goods202.position(goods202);
        markerOptions_goods202.title("둘리애견");
        markerOptions_goods202.snippet("강원도 원주시 관설동 1747-4");
        markerOptions_goods202.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods202);

        //애니펫살롱
        LatLng goods203 = new LatLng(37.3253369,127.9423356);
        MarkerOptions markerOptions_goods203 = new MarkerOptions();
        markerOptions_goods203.position(goods203);
        markerOptions_goods203.title("애니펫살롱");
        markerOptions_goods203.snippet("강원도 원주시 단구동 1532-16");
        markerOptions_goods203.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods203);

        //강이랑아지랑(단계점)
        LatLng goods204 = new LatLng(37.3405899,127.934171);
        MarkerOptions markerOptions_goods204 = new MarkerOptions();
        markerOptions_goods204.position(goods204);
        markerOptions_goods204.title("강이랑아지랑(단계점)");
        markerOptions_goods204.snippet("강원도 원주시 단계동 915");
        markerOptions_goods204.icon(BitmapDescriptorFactory.fromBitmap(good_smallMarker2));
        mMap.addMarker(markerOptions_goods204);

    }

    public void view_hospital(){
        // -------------------- 애견병원 ---------------------


        // 윤신근애견종합병원
        LatLng hospital1 = new LatLng(37.561697,126.9968491);
        MarkerOptions markerOptions_hospital1 = new MarkerOptions();
        markerOptions_hospital1.position(hospital1);
        markerOptions_hospital1.title("윤신근애견종합병원");
        markerOptions_hospital1.snippet("서울특별시 중구 필동2가 53");
        BitmapDrawable hos_bitmapdraw2=(BitmapDrawable)getResources().getDrawable(R.drawable.hospital);
        Bitmap hos_b2=hos_bitmapdraw2.getBitmap();
        Bitmap hos_smallMarker2 = Bitmap.createScaledBitmap(hos_b2, 60, 60, false);
        markerOptions_hospital1.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hospital1);

        // 동진애견병원
        LatLng hotel2 = new LatLng(37.6521969,127.3075183);
        MarkerOptions markerOptions_hotel2 = new MarkerOptions();
        markerOptions_hotel2.position(hotel2);
        markerOptions_hotel2.title("동진애견병원");
        markerOptions_hotel2.snippet("경기도 남양주시 화도읍 마석우리 271-3");
        markerOptions_hotel2.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel2);

        // 고려애견종합병원
        LatLng hotel3 = new LatLng(37.4412007,127.1440029);
        MarkerOptions markerOptions_hotel3 = new MarkerOptions();
        markerOptions_hotel3.position(hotel3);
        markerOptions_hotel3.title("고려애견종합병원");
        markerOptions_hotel3.snippet("경기도 성남시 수정구 신흥3동 3777");
        markerOptions_hotel3.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel3);

        // 24시 넬 동물의료센터
        LatLng hotel4 = new LatLng(37.395351,126.964889);
        MarkerOptions markerOptions_hotel4 = new MarkerOptions();
        markerOptions_hotel4.position(hotel4);
        markerOptions_hotel4.title("24시 넬 동물의료센터");
        markerOptions_hotel4.snippet("경기도 안양시 동안구 평촌동 시민대로 312\n");
        markerOptions_hotel4.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel4);

        // 대학동물병원 수지
        LatLng hotel5 = new LatLng(37.3329544,127.0984088);
        MarkerOptions markerOptions_hotel5 = new MarkerOptions();
        markerOptions_hotel5.position(hotel5);
        markerOptions_hotel5.title("대학동물병원 수지");
        markerOptions_hotel5.snippet("경기도 용인시 수지구 풍덕천1동 666-1");
        markerOptions_hotel5.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel5);

        // 아프리카동물병원
        LatLng hotel6 = new LatLng(37.5055152,126.7313915);
        MarkerOptions markerOptions_hotel6 = new MarkerOptions();
        markerOptions_hotel6.position(hotel6);
        markerOptions_hotel6.title("아프리카동물병원");
        markerOptions_hotel6.snippet("인천광역시 부평구 부개동 장제로 224-1 1층");
        markerOptions_hotel6.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel6);

        //수원펫동물병원
        LatLng hotel7 = new LatLng(37.2550315,127.0216334);
        MarkerOptions markerOptions_hotel7 = new MarkerOptions();
        markerOptions_hotel7.position(hotel7);
        markerOptions_hotel7.title("수원펫동물병원");
        markerOptions_hotel7.snippet("경기도 수원시 권선구 권선동 1058-4");
        markerOptions_hotel7.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel7);

        //위니펫 동물병원
        LatLng hotel8 = new LatLng(37.2094216,127.0340396);
        MarkerOptions markerOptions_hotel8 = new MarkerOptions();
        markerOptions_hotel8.position(hotel8);
        markerOptions_hotel8.title("위니펫 동물병원");
        markerOptions_hotel8.snippet("경기도 화성시 진안동 540-4");
        markerOptions_hotel8.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel8);

        //강남종합동물병원 기흥
        LatLng hotel9 = new LatLng(37.2742935,127.1055457);
        MarkerOptions markerOptions_hotel9 = new MarkerOptions();
        markerOptions_hotel9.position(hotel9);
        markerOptions_hotel9.title("강남종합동물병원 기흥");
        markerOptions_hotel9.snippet("경기도 용인시 기흥구 신갈동 38-5");
        markerOptions_hotel9.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel9);

        //강남동물병원 강남구
        LatLng hotel10 = new LatLng(37.507793,127.0344379);
        MarkerOptions markerOptions_hotel10 = new MarkerOptions();
        markerOptions_hotel10.position(hotel10);
        markerOptions_hotel10.title("강남동물병원 강남구");
        markerOptions_hotel10.snippet("서울특별시 강남구 논현2동 봉은사로 205");
        markerOptions_hotel10.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel10);

        //쿨펫동물병원 강서구
        LatLng hotel11 = new LatLng(37.5578966,126.8622978);
        MarkerOptions markerOptions_hotel11 = new MarkerOptions();
        markerOptions_hotel11.position(hotel11);
        markerOptions_hotel11.title("쿨펫동물병원 강서구");
        markerOptions_hotel11.snippet("서울특별시 강서구 가양3동");
        markerOptions_hotel11.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel11);

        //청담24시동물병원 수지구
        LatLng hotel12 = new LatLng(37.3287607,127.1151851);
        MarkerOptions markerOptions_hotel12 = new MarkerOptions();
        markerOptions_hotel12.position(hotel12);
        markerOptions_hotel12.title("청담24시동물병원 수지구");
        markerOptions_hotel12.snippet("경기도 용인시 수지구 죽전동 1254-1");
        markerOptions_hotel12.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel12);

        //서울동물메디컬센터
        LatLng hotel13 = new LatLng(37.5317642,127.1381216);
        MarkerOptions markerOptions_hotel13 = new MarkerOptions();
        markerOptions_hotel13.position(hotel13);
        markerOptions_hotel13.title("서울동물메디컬센터");
        markerOptions_hotel13.snippet("서울특별시 강동구 둔촌동 486-7");
        markerOptions_hotel13.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel13);

        //나라동물병원 분당구
        LatLng hotel14 = new LatLng(37.3375066,127.116529);
        MarkerOptions markerOptions_hotel14 = new MarkerOptions();
        markerOptions_hotel14.position(hotel14);
        markerOptions_hotel14.title("나라동물병원 분당구");
        markerOptions_hotel14.snippet("경기도 성남시 분당구 구미동 205-1");
        markerOptions_hotel14.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel14);

        //애플펫동물병원
        LatLng hotel15 = new LatLng(37.360109,127.1517023);
        MarkerOptions markerOptions_hotel15 = new MarkerOptions();
        markerOptions_hotel15.position(hotel15);
        markerOptions_hotel15.title("애플펫동물병원");
        markerOptions_hotel15.snippet("경기도 광주시 오포읍 신현리 709-27");
        markerOptions_hotel15.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel15);

        //동백동물병원
        LatLng hotel16 = new LatLng(37.2856016,127.165647);
        MarkerOptions markerOptions_hotel16 = new MarkerOptions();
        markerOptions_hotel16.position(hotel16);
        markerOptions_hotel16.title("동백동물병원");
        markerOptions_hotel16.snippet("경기도 용인시 기흥구 동백동");
        markerOptions_hotel16.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel16);

        //윤종합동물병원
        LatLng hotel17 = new LatLng(37.2367074,127.2089374);
        MarkerOptions markerOptions_hotel17 = new MarkerOptions();
        markerOptions_hotel17.position(hotel17);
        markerOptions_hotel17.title("윤종합동물병원");
        markerOptions_hotel17.snippet("경기도 용인시 처인구 김량장동 142-11");
        markerOptions_hotel17.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel17);

        //애견종합병원 처인구
        LatLng hotel18 = new LatLng(37.2346348,127.1989296);
        MarkerOptions markerOptions_hotel18 = new MarkerOptions();
        markerOptions_hotel18.position(hotel18);
        markerOptions_hotel18.title("애견종합병원");
        markerOptions_hotel18.snippet("경기도 용인시 처인구 김량장동 333-2");
        markerOptions_hotel18.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel18);

        //페티앙동물병원
        LatLng hotel19 = new LatLng(37.2260383,127.1966072);
        MarkerOptions markerOptions_hotel19 = new MarkerOptions();
        markerOptions_hotel19.position(hotel19);
        markerOptions_hotel19.title("페티앙동물병원");
        markerOptions_hotel19.snippet("경기도 용인시 처인구 남동 71-1");
        markerOptions_hotel19.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel19);

        //용인센트럴동물병원
        LatLng hotel20 = new LatLng(37.2307165,127.2102329);
        MarkerOptions markerOptions_hotel20 = new MarkerOptions();
        markerOptions_hotel20.position(hotel20);
        markerOptions_hotel20.title("용인센트럴동물병원");
        markerOptions_hotel20.snippet("경기도 용인시 처인구 김량장동 12-8");
        markerOptions_hotel20.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel20);

        //보라동물병원 기흥구
        LatLng hotel21 = new LatLng(37.254902,127.1052195);
        MarkerOptions markerOptions_hotel21 = new MarkerOptions();
        markerOptions_hotel21.position(hotel21);
        markerOptions_hotel21.title("보라동물병원 기흥구");
        markerOptions_hotel21.snippet("경기도 용인시 기흥구 보라동 417-1");
        markerOptions_hotel21.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel21);

        //현대동물병원 기흥구
        LatLng hotel22 = new LatLng(37.2683513,127.1056322);
        MarkerOptions markerOptions_hotel22 = new MarkerOptions();
        markerOptions_hotel22.position(hotel22);
        markerOptions_hotel22.title("현대동물병원 기흥구");
        markerOptions_hotel22.snippet("경기도 용인시 기흥구 상갈동 102-12");
        markerOptions_hotel22.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel22);

        //우리동네 동물병원
        LatLng hotel23 = new LatLng(37.2888255,127.1112484);
        MarkerOptions markerOptions_hotel23 = new MarkerOptions();
        markerOptions_hotel23.position(hotel23);
        markerOptions_hotel23.title("우리동네 동물병원");
        markerOptions_hotel23.snippet("경기도 용인시 기흥구 신갈동 722");
        markerOptions_hotel23.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel23);

        //탑동물병원 기흥구
        LatLng hotel24 = new LatLng(37.294911,127.1182691);
        MarkerOptions markerOptions_hotel24 = new MarkerOptions();
        markerOptions_hotel24.position(hotel24);
        markerOptions_hotel24.title("탑동물병원 기흥구");
        markerOptions_hotel24.snippet("경기도 용인시 기흥구 언남동 361");
        markerOptions_hotel24.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel24);

        //구성종합동물병원
        LatLng hotel25 = new LatLng(37.2955982,127.1170467);
        MarkerOptions markerOptions_hotel25 = new MarkerOptions();
        markerOptions_hotel25.position(hotel25);
        markerOptions_hotel25.title("구성종합동물병원");
        markerOptions_hotel25.snippet("경기도 용인시 기흥구 마북동 317-21");
        markerOptions_hotel25.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel25);

        //어정동물병원
        LatLng hotel26 = new LatLng(37.2749088,127.1453243);
        MarkerOptions markerOptions_hotel26 = new MarkerOptions();
        markerOptions_hotel26.position(hotel26);
        markerOptions_hotel26.title("어정동물병원");
        markerOptions_hotel26.snippet("경기도 용인시 기흥구 중동 760-1");
        markerOptions_hotel26.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel26);

        //리치펫 동물병원
        LatLng hotel27 = new LatLng(37.2702279,127.1537876);
        MarkerOptions markerOptions_hotel27 = new MarkerOptions();
        markerOptions_hotel27.position(hotel27);
        markerOptions_hotel27.title("리치펫 동물병원");
        markerOptions_hotel27.snippet("경기도 용인시 기흥구 중동 852-2");
        markerOptions_hotel27.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel27);

        //강남종합동물병원 송파구
        LatLng hotel28 = new LatLng(37.509572,127.112958);
        MarkerOptions markerOptions_hotel28 = new MarkerOptions();
        markerOptions_hotel28.position(hotel28);
        markerOptions_hotel28.title("강남종합동물병원");
        markerOptions_hotel28.snippet("서울특별시 송파구 송파1동 135-8");
        markerOptions_hotel28.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel28);

        //영통동물병원
        LatLng hotel29 = new LatLng(37.2501029,127.0736913);
        MarkerOptions markerOptions_hotel29 = new MarkerOptions();
        markerOptions_hotel29.position(hotel29);
        markerOptions_hotel29.title("영통동물병원");
        markerOptions_hotel29.snippet("경기도 수원시 영통구 영통동 1000-9");
        markerOptions_hotel29.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel29);

        //도그플러스동물병원 강서구
        LatLng hotel30 = new LatLng(37.5584359,126.8547981);
        MarkerOptions markerOptions_hotel30 = new MarkerOptions();
        markerOptions_hotel30.position(hotel30);
        markerOptions_hotel30.title("도그플러스동물병원 강서구");
        markerOptions_hotel30.snippet("서울특별시 강서구 등촌1동 639-11");
        markerOptions_hotel30.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel30);

        //행복나눔동물병원
        LatLng hotel31 = new LatLng(37.3486146,126.9442995);
        MarkerOptions markerOptions_hotel31 = new MarkerOptions();
        markerOptions_hotel31.position(hotel31);
        markerOptions_hotel31.title("행복나눔동물병원");
        markerOptions_hotel31.snippet("경기도 군포시 당동 904");
        markerOptions_hotel31.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel31);

        //다나동물병원 동작구
        LatLng hotel32 = new LatLng(37.4808509,126.9814716);
        MarkerOptions markerOptions_hotel32 = new MarkerOptions();
        markerOptions_hotel32.position(hotel32);
        markerOptions_hotel32.title("다나동물병원 동작구");
        markerOptions_hotel32.snippet("서울특별시 동작구 사당동 1019-54");
        markerOptions_hotel32.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel32);

        //참사랑동물병원 양천구
        LatLng hotel33 = new LatLng(37.5081788,126.8650655);
        MarkerOptions markerOptions_hotel33 = new MarkerOptions();
        markerOptions_hotel33.position(hotel33);
        markerOptions_hotel33.title("참사랑동물병원 양천구");
        markerOptions_hotel33.snippet("서울특별시 양천구 신정7동 337-7");
        markerOptions_hotel33.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel33);

        //우리동물병원 금천구
        LatLng hotel34 = new LatLng(37.4778499,126.9038681);
        MarkerOptions markerOptions_hotel34 = new MarkerOptions();
        markerOptions_hotel34.position(hotel34);
        markerOptions_hotel34.title("우리동물병원");
        markerOptions_hotel34.snippet("서울특별시 금천구 독산동 961");
        markerOptions_hotel34.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel34);

        //서울대학교 수의과 동물병원
        LatLng hotel35 = new LatLng(37.4680972,126.9531584);
        MarkerOptions markerOptions_hotel35 = new MarkerOptions();
        markerOptions_hotel35.position(hotel35);
        markerOptions_hotel35.title("서울대학교 수의과 동물병원");
        markerOptions_hotel35.snippet("서울특별시 관악구 신림동 관악로 1");
        markerOptions_hotel35.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel35);

        //내방동물병원
        LatLng hotel36 = new LatLng(37.487437,126.9941229);
        MarkerOptions markerOptions_hotel36 = new MarkerOptions();
        markerOptions_hotel36.position(hotel36);
        markerOptions_hotel36.title("내방동물병원");
        markerOptions_hotel36.snippet("서울특별시 서초구 방배동 898-3");
        markerOptions_hotel36.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel36);

        //도곡종합동물병원
        LatLng hotel37 = new LatLng(37.495879,127.046228);
        MarkerOptions markerOptions_hotel37 = new MarkerOptions();
        markerOptions_hotel37.position(hotel37);
        markerOptions_hotel37.title("도곡종합동물병원");
        markerOptions_hotel37.snippet("서울특별시 강남구 역삼2동");
        markerOptions_hotel37.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel37);

        //성심동물의료센터
        LatLng hotel38 = new LatLng(36.7774645,126.4489852);
        MarkerOptions markerOptions_hotel38 = new MarkerOptions();
        markerOptions_hotel38.position(hotel38);
        markerOptions_hotel38.title("성심동물의료센터");
        markerOptions_hotel38.snippet("충청남도 서산시 읍내동 71-13");
        markerOptions_hotel38.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel38);

        //고려동물병원 화성시
        LatLng hotel39 = new LatLng(37.2219803,126.9743752);
        MarkerOptions markerOptions_hotel39 = new MarkerOptions();
        markerOptions_hotel39.position(hotel39);
        markerOptions_hotel39.title("고려동물병원 화성시");
        markerOptions_hotel39.snippet("경기도 화성시 봉담읍 와우리 148-2");
        markerOptions_hotel39.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel39);

        //로얄동물메디컬센터 중랑구
        LatLng hotel40 = new LatLng(37.5952831,127.0799032);
        MarkerOptions markerOptions_hotel40 = new MarkerOptions();
        markerOptions_hotel40.position(hotel40);
        markerOptions_hotel40.title("로얄동물메디컬센터 중랑구");
        markerOptions_hotel40.snippet("서울특별시 중랑구 중화2동 207");
        markerOptions_hotel40.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel40);

        //신화동물병원
        LatLng hotel41 = new LatLng(36.7775937,126.4560699);
        MarkerOptions markerOptions_hotel41 = new MarkerOptions();
        markerOptions_hotel41.position(hotel41);
        markerOptions_hotel41.title("신화동물병원");
        markerOptions_hotel41.snippet("충청남도 서산시 동문동 1012-24");
        markerOptions_hotel41.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel41);

        //서산동물병원
        LatLng hotel42 = new LatLng(36.7779704,126.4574627);
        MarkerOptions markerOptions_hotel42 = new MarkerOptions();
        markerOptions_hotel42.position(hotel42);
        markerOptions_hotel42.title("서산동물병원");
        markerOptions_hotel42.snippet("충청남도 서산시 동문동 1024-6");
        markerOptions_hotel42.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel42);

        //우리동물병원 성남시
        LatLng hotel43 = new LatLng(37.3714222,127.1158227);
        MarkerOptions markerOptions_hotel43 = new MarkerOptions();
        markerOptions_hotel43.position(hotel43);
        markerOptions_hotel43.title("우리동물병원 성남시");
        markerOptions_hotel43.snippet("경기도 성남시 분당구 정자동 56-1");
        markerOptions_hotel43.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel43);

        //김신환동물병원
        LatLng hotel44 = new LatLng(36.7800382,126.4595415);
        MarkerOptions markerOptions_hotel44 = new MarkerOptions();
        markerOptions_hotel44.position(hotel44);
        markerOptions_hotel44.title("김신환동물병원");
        markerOptions_hotel44.snippet("충청남도 서산시 동문동 293-11");
        markerOptions_hotel44.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel44);

        //여한경동물병원
        LatLng hotel45 = new LatLng(36.7817316,126.4587818);
        MarkerOptions markerOptions_hotel45 = new MarkerOptions();
        markerOptions_hotel45.position(hotel45);
        markerOptions_hotel45.title("여한경동물병원");
        markerOptions_hotel45.snippet("충청남도 서산시 동문동 309-3");
        markerOptions_hotel45.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel45);

        //태안동물병원
        LatLng hotel46 = new LatLng(36.7465175,126.3034895);
        MarkerOptions markerOptions_hotel46 = new MarkerOptions();
        markerOptions_hotel46.position(hotel46);
        markerOptions_hotel46.title("태안동물병원");
        markerOptions_hotel46.snippet("충청남도 태안군 태안읍 남문리 713-1");
        markerOptions_hotel46.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel46);

        //라온반려동물병원
        LatLng hotel47 = new LatLng(36.7503343,126.3039799);
        MarkerOptions markerOptions_hotel47 = new MarkerOptions();
        markerOptions_hotel47.position(hotel47);
        markerOptions_hotel47.title("라온반려동물병원");
        markerOptions_hotel47.snippet("충청남도 태안군 태안읍 동문리 342-3");
        markerOptions_hotel47.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel47);

        //최가축병원
        LatLng hotel48 = new LatLng(36.7555057,126.293147);
        MarkerOptions markerOptions_hotel48 = new MarkerOptions();
        markerOptions_hotel48.position(hotel48);
        markerOptions_hotel48.title("최가축병원");
        markerOptions_hotel48.snippet("충청남도 태안군 태안읍 남문리 504-11");
        markerOptions_hotel48.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel48);

        //현대동물병원 권선구
        LatLng hotel49 = new LatLng(37.2499346,126.976923);
        MarkerOptions markerOptions_hotel49 = new MarkerOptions();
        markerOptions_hotel49.position(hotel49);
        markerOptions_hotel49.title("현대동물병원 권선구");
        markerOptions_hotel49.snippet("경기도 수원시 권선구 고색동 408-16");
        markerOptions_hotel49.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel49);

        //보령동물병원
        LatLng hotel50 = new LatLng(36.3475192,126.6064387);
        MarkerOptions markerOptions_hotel50 = new MarkerOptions();
        markerOptions_hotel50.position(hotel50);
        markerOptions_hotel50.title("보령동물병원");
        markerOptions_hotel50.snippet("충청남도 보령시 동대동 1701 KR");
        markerOptions_hotel50.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel50);

        //훈동물병원 부산
        LatLng hotel51 = new LatLng(35.2110503,129.0829162);
        MarkerOptions markerOptions_hotel51 = new MarkerOptions();
        markerOptions_hotel51.position(hotel51);
        markerOptions_hotel51.title("훈동물병원 부산");
        markerOptions_hotel51.snippet("부산광역시 동래구 명륜동 20번지 676");
        markerOptions_hotel51.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel51);

        //김동물병원
        LatLng hotel52 = new LatLng(36.3514865,126.5924713);
        MarkerOptions markerOptions_hotel52 = new MarkerOptions();
        markerOptions_hotel52.position(hotel52);
        markerOptions_hotel52.title("김동물병원");
        markerOptions_hotel52.snippet("충청남도 보령시 대천동 434-6");
        markerOptions_hotel52.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel52);

        //보령제일동물병원
        LatLng hotel53 = new LatLng(36.3514535,126.5910679);
        MarkerOptions markerOptions_hotel53 = new MarkerOptions();
        markerOptions_hotel53.position(hotel53);
        markerOptions_hotel53.title("보령제일동물병원");
        markerOptions_hotel53.snippet("충청남도 보령시 대천동 344-9");
        markerOptions_hotel53.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel53);

        //서해가축병원 서천군
        LatLng hotel54 = new LatLng(36.0762384,126.68901);
        MarkerOptions markerOptions_hotel54 = new MarkerOptions();
        markerOptions_hotel54.position(hotel54);
        markerOptions_hotel54.title("서해가축병원 서천군");
        markerOptions_hotel54.snippet("충청남도 서천군 서천읍 군사리 862-10");
        markerOptions_hotel54.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel54);

        //한라동물병원 제주시
        LatLng hotel55 = new LatLng(33.510874,126.5391132);
        MarkerOptions markerOptions_hotel55 = new MarkerOptions();
        markerOptions_hotel55.position(hotel55);
        markerOptions_hotel55.title("한라동물병원 제주시");
        markerOptions_hotel55.snippet("제주특별자치도 제주시 일도2동 321-10");
        markerOptions_hotel55.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel55);

        //백동물병원 영동군
        LatLng hotel56 = new LatLng(36.1740215,127.7802233);
        MarkerOptions markerOptions_hotel56 = new MarkerOptions();
        markerOptions_hotel56.position(hotel56);
        markerOptions_hotel56.title("백동물병원 영동군");
        markerOptions_hotel56.snippet("충청북도 영동군 영동읍 계산로 27");
        markerOptions_hotel56.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel56);

        //녹십자동물병원
        LatLng hotel57 = new LatLng(36.2824118,126.910039);
        MarkerOptions markerOptions_hotel57 = new MarkerOptions();
        markerOptions_hotel57.position(hotel57);
        markerOptions_hotel57.title("녹십자동물병원");
        markerOptions_hotel57.snippet("충청남도 부여군 부여읍 구아리 141-5");
        markerOptions_hotel57.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel57);

        //세종동물병원
        LatLng hotel58 = new LatLng(36.2817354,126.9092506);
        MarkerOptions markerOptions_hotel58 = new MarkerOptions();
        markerOptions_hotel58.position(hotel58);
        markerOptions_hotel58.title("세종동물병원");
        markerOptions_hotel58.snippet("");
        markerOptions_hotel58.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel58);

        //논산동물병원
        LatLng hotel59 = new LatLng(36.2075246,127.0826662);
        MarkerOptions markerOptions_hotel59 = new MarkerOptions();
        markerOptions_hotel59.position(hotel59);
        markerOptions_hotel59.title("논산동물병원");
        markerOptions_hotel59.snippet("");
        markerOptions_hotel59.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel59);

        //케비어동물병원
        LatLng hotel60 = new LatLng(36.2033691,127.0888245);
        MarkerOptions markerOptions_hotel60 = new MarkerOptions();
        markerOptions_hotel60.position(hotel60);
        markerOptions_hotel60.title("케비어동물병원");
        markerOptions_hotel60.snippet("");
        markerOptions_hotel60.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel60);

        //유명동물병원
        LatLng hotel61 = new LatLng(36.2044253,127.090745);
        MarkerOptions markerOptions_hotel61 = new MarkerOptions();
        markerOptions_hotel61.position(hotel61);
        markerOptions_hotel61.title("유명동물병원");
        markerOptions_hotel61.snippet("");
        markerOptions_hotel61.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel61);

        //해원이네동물병원
        LatLng hotel62 = new LatLng(36.0565933,127.0849943);
        MarkerOptions markerOptions_hotel62 = new MarkerOptions();
        markerOptions_hotel62.position(hotel62);
        markerOptions_hotel62.title("해원이네동물병원");
        markerOptions_hotel62.snippet("");
        markerOptions_hotel62.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel62);

        //이영준동물병원
        LatLng hotel63 = new LatLng(35.9352433,127.1578646);
        MarkerOptions markerOptions_hotel63 = new MarkerOptions();
        markerOptions_hotel63.position(hotel63);
        markerOptions_hotel63.title("이영준동물병원");
        markerOptions_hotel63.snippet("");
        markerOptions_hotel63.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel63);

        //미래종합동물병원
        LatLng hotel64 = new LatLng(35.9584861,126.9826412);
        MarkerOptions markerOptions_hotel64 = new MarkerOptions();
        markerOptions_hotel64.position(hotel64);
        markerOptions_hotel64.title("미래종합동물병원");
        markerOptions_hotel64.snippet("");
        markerOptions_hotel64.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel64);

        //쿨펫동물병원
        LatLng hotel65 = new LatLng(35.9590419,126.9740152);
        MarkerOptions markerOptions_hotel65 = new MarkerOptions();
        markerOptions_hotel65.position(hotel65);
        markerOptions_hotel65.title("미래종합동물병원");
        markerOptions_hotel65.snippet("");
        markerOptions_hotel65.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel65);

        //와우동물병원
        LatLng hotel66 = new LatLng(35.9591982,126.9819009);
        MarkerOptions markerOptions_hotel66 = new MarkerOptions();
        markerOptions_hotel66.position(hotel66);
        markerOptions_hotel66.title("와우동물병원");
        markerOptions_hotel66.snippet("");
        markerOptions_hotel66.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel66);

        //닥터펫동물병원
        LatLng hotel67 = new LatLng(35.8268953,127.1230602);
        MarkerOptions markerOptions_hotel67 = new MarkerOptions();
        markerOptions_hotel67.position(hotel67);
        markerOptions_hotel67.title("닥터펫동물병원");
        markerOptions_hotel67.snippet("");
        markerOptions_hotel67.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel67);

        //웰동물병원
        LatLng hotel68 = new LatLng(35.8133587,127.102375);
        MarkerOptions markerOptions_hotel68 = new MarkerOptions();
        markerOptions_hotel68.position(hotel68);
        markerOptions_hotel68.title("웰동물병원");
        markerOptions_hotel68.snippet("");
        markerOptions_hotel68.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel68);

        //더펫동물병원
        LatLng hotel69 = new LatLng(35.8133761,127.1128678);
        MarkerOptions markerOptions_hotel69 = new MarkerOptions();
        markerOptions_hotel69.position(hotel69);
        markerOptions_hotel69.title("더펫동물병원");
        markerOptions_hotel69.snippet("");
        markerOptions_hotel69.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel69);

        //박영재동물병원
        LatLng hotel70 = new LatLng(35.8102179,127.1491635);
        MarkerOptions markerOptions_hotel70 = new MarkerOptions();
        markerOptions_hotel70.position(hotel70);
        markerOptions_hotel70.title("박영재동물병원");
        markerOptions_hotel70.snippet("");
        markerOptions_hotel70.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel70);

        //행복한동물병원
        LatLng hotel71 = new LatLng(35.8102179,127.1491635);
        MarkerOptions markerOptions_hotel71 = new MarkerOptions();
        markerOptions_hotel71.position(hotel71);
        markerOptions_hotel71.title("행복한동물병원");
        markerOptions_hotel71.snippet("");
        markerOptions_hotel71.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel71);

        //엔젤동물병원
        LatLng hotel72 = new LatLng(35.8449172,127.1536052);
        MarkerOptions markerOptions_hotel72 = new MarkerOptions();
        markerOptions_hotel72.position(hotel72);
        markerOptions_hotel72.title("엔젤동물병원");
        markerOptions_hotel72.snippet("");
        markerOptions_hotel72.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel72);

        //야간24시동물병원
        LatLng hotel73 = new LatLng(35.8715424,127.1363103);
        MarkerOptions markerOptions_hotel73 = new MarkerOptions();
        markerOptions_hotel73.position(hotel73);
        markerOptions_hotel73.title("야간24시동물병원");
        markerOptions_hotel73.snippet("");
        markerOptions_hotel73.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel73);

        //올리몰스 동물메디컬센터
        LatLng hotel74 = new LatLng(35.8660303,127.1210968);
        MarkerOptions markerOptions_hotel74 = new MarkerOptions();
        markerOptions_hotel74.position(hotel74);
        markerOptions_hotel74.title("올리몰스 동물메디컬센터");
        markerOptions_hotel74.snippet("");
        markerOptions_hotel74.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel74);

        //하나동물병원
        LatLng hotel75 = new LatLng(35.8488307,127.1185541);
        MarkerOptions markerOptions_hotel75 = new MarkerOptions();
        markerOptions_hotel75.position(hotel75);
        markerOptions_hotel75.title("하나동물병원");
        markerOptions_hotel75.snippet("");
        markerOptions_hotel75.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel75);

        //서신동물병원
        LatLng hotel76 = new LatLng(35.8283305,127.1156144);
        MarkerOptions markerOptions_hotel76 = new MarkerOptions();
        markerOptions_hotel76.position(hotel76);
        markerOptions_hotel76.title("서신동물병원");
        markerOptions_hotel76.snippet("");
        markerOptions_hotel76.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel76);

        //메이동물메디컬센터
        LatLng hotel77 = new LatLng(35.8123234,127.1236825);
        MarkerOptions markerOptions_hotel77 = new MarkerOptions();
        markerOptions_hotel77.position(hotel77);
        markerOptions_hotel77.title("메이동물메디컬센터");
        markerOptions_hotel77.snippet("");
        markerOptions_hotel77.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel77);

        //효자동물병원
        LatLng hotel78 = new LatLng(35.8037791,127.1237791);
        MarkerOptions markerOptions_hotel78 = new MarkerOptions();
        markerOptions_hotel78.position(hotel78);
        markerOptions_hotel78.title("메이동물메디컬센터");
        markerOptions_hotel78.snippet("");
        markerOptions_hotel78.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel78);

        //코끼리동물병원
        LatLng hotel79 = new LatLng(35.7950947,127.1321261);
        MarkerOptions markerOptions_hotel79 = new MarkerOptions();
        markerOptions_hotel79.position(hotel79);
        markerOptions_hotel79.title("코끼리동물병원");
        markerOptions_hotel79.snippet("");
        markerOptions_hotel79.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel79);

        //아리랑동물병원
        LatLng hotel80 = new LatLng(35.8115577,127.126472);
        MarkerOptions markerOptions_hotel80 = new MarkerOptions();
        markerOptions_hotel80.position(hotel80);
        markerOptions_hotel80.title("아리랑동물병원");
        markerOptions_hotel80.snippet("");
        markerOptions_hotel80.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel80);

        //동부동물병원
        LatLng hotel81 = new LatLng(35.8209668,127.1521139);
        MarkerOptions markerOptions_hotel81 = new MarkerOptions();
        markerOptions_hotel81.position(hotel81);
        markerOptions_hotel81.title("동부동물병원");
        markerOptions_hotel81.snippet("");
        markerOptions_hotel81.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel81);

        //진안가축병원
        LatLng hotel82 = new LatLng(35.7914222,127.4270028);
        MarkerOptions markerOptions_hotel82 = new MarkerOptions();
        markerOptions_hotel82.position(hotel82);
        markerOptions_hotel82.title("진안가축병원");
        markerOptions_hotel82.snippet("");
        markerOptions_hotel82.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel82);

        //대동동물병원
        LatLng hotel83 = new LatLng(35.7309763,127.5830269);
        MarkerOptions markerOptions_hotel83 = new MarkerOptions();
        markerOptions_hotel83.position(hotel83);
        markerOptions_hotel83.title("대동동물병원");
        markerOptions_hotel83.snippet("");
        markerOptions_hotel83.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel83);

        //해맑은동물병원
        LatLng hotel84 = new LatLng(35.2123514,126.87428);
        MarkerOptions markerOptions_hotel84 = new MarkerOptions();
        markerOptions_hotel84.position(hotel84);
        markerOptions_hotel84.title("해맑은동물병원");
        markerOptions_hotel84.snippet("");
        markerOptions_hotel84.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel84);

        //첨단우리동물병원
        LatLng hotel85 = new LatLng(35.2127371,126.8424368);
        MarkerOptions markerOptions_hotel85 = new MarkerOptions();
        markerOptions_hotel85.position(hotel85);
        markerOptions_hotel85.title("첨단우리동물병원");
        markerOptions_hotel85.snippet("");
        markerOptions_hotel85.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel85);

        //연두동물병원
        LatLng hotel86 = new LatLng(35.1888209,126.8372869);
        MarkerOptions markerOptions_hotel86 = new MarkerOptions();
        markerOptions_hotel86.position(hotel86);
        markerOptions_hotel86.title("연두동물병원");
        markerOptions_hotel86.snippet("");
        markerOptions_hotel86.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel86);

        //한빛 동물병원
        LatLng hotel87 = new LatLng(35.1819814,126.8174171);
        MarkerOptions markerOptions_hotel87 = new MarkerOptions();
        markerOptions_hotel87.position(hotel87);
        markerOptions_hotel87.title("한빛 동물병원");
        markerOptions_hotel87.snippet("");
        markerOptions_hotel87.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel87);

        //밝은동물병원
        LatLng hotel88 = new LatLng(35.1662308,126.8095207);
        MarkerOptions markerOptions_hotel88 = new MarkerOptions();
        markerOptions_hotel88.position(hotel88);
        markerOptions_hotel88.title("밝은동물병원");
        markerOptions_hotel88.snippet("");
        markerOptions_hotel88.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel88);

        //하나로동물병원
        LatLng hotel89 = new LatLng(35.1521264,126.8478441);
        MarkerOptions markerOptions_hotel89 = new MarkerOptions();
        markerOptions_hotel89.position(hotel89);
        markerOptions_hotel89.title("하나로동물병원");
        markerOptions_hotel89.snippet("");
        markerOptions_hotel89.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel89);

        //마루동물병원
        LatLng hotel90 = new LatLng(35.132369,126.8565989);
        MarkerOptions markerOptions_hotel90 = new MarkerOptions();
        markerOptions_hotel90.position(hotel90);
        markerOptions_hotel90.title("마루동물병원");
        markerOptions_hotel90.snippet("");
        markerOptions_hotel90.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel90);

        //프렌즈동물병원
        LatLng hotel91 = new LatLng(35.1585474,126.8596458);
        MarkerOptions markerOptions_hotel91 = new MarkerOptions();
        markerOptions_hotel91.position(hotel91);
        markerOptions_hotel91.title("프렌즈동물병원");
        markerOptions_hotel91.snippet("");
        markerOptions_hotel91.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel91);

        //유림동물병원
        LatLng hotel92 = new LatLng(35.1716334,126.8644094);
        MarkerOptions markerOptions_hotel92 = new MarkerOptions();
        markerOptions_hotel92.position(hotel92);
        markerOptions_hotel92.title("유림동물병원");
        markerOptions_hotel92.snippet("");
        markerOptions_hotel92.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel92);

        //백제동물병원
        LatLng hotel93 = new LatLng(35.165459,126.8836784);
        MarkerOptions markerOptions_hotel93 = new MarkerOptions();
        markerOptions_hotel93.position(hotel93);
        markerOptions_hotel93.title("백제동물병원");
        markerOptions_hotel93.snippet("");
        markerOptions_hotel93.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel93);

        //화정힐동물병원
        LatLng hotel94 = new LatLng(35.1463365,126.8811893);
        MarkerOptions markerOptions_hotel94 = new MarkerOptions();
        markerOptions_hotel94.position(hotel94);
        markerOptions_hotel94.title("화정힐동물병원");
        markerOptions_hotel94.snippet("");
        markerOptions_hotel94.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel94);

        //닥터남동물병원
        LatLng hotel95 = new LatLng(35.1421255,126.8752241);
        MarkerOptions markerOptions_hotel95 = new MarkerOptions();
        markerOptions_hotel95.position(hotel95);
        markerOptions_hotel95.title("닥터남동물병원");
        markerOptions_hotel95.snippet("");
        markerOptions_hotel95.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel95);

        //한결동물병원
        LatLng hotel96 = new LatLng(35.1276659,126.8822622);
        MarkerOptions markerOptions_hotel96 = new MarkerOptions();
        markerOptions_hotel96.position(hotel96);
        markerOptions_hotel96.title("한결동물병원");
        markerOptions_hotel96.snippet("");
        markerOptions_hotel96.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel96);

        //고려동물병원
        LatLng hotel97 = new LatLng(35.1518808,126.8968534);
        MarkerOptions markerOptions_hotel97 = new MarkerOptions();
        markerOptions_hotel97.position(hotel97);
        markerOptions_hotel97.title("고려동물병원");
        markerOptions_hotel97.snippet("");
        markerOptions_hotel97.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel97);

        //엔젤동물병원
        LatLng hotel98 = new LatLng(35.1705108,126.9372368);
        MarkerOptions markerOptions_hotel98 = new MarkerOptions();
        markerOptions_hotel98.position(hotel98);
        markerOptions_hotel98.title("엔젤동물병원");
        markerOptions_hotel98.snippet("");
        markerOptions_hotel98.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel98);

        //영재애견종합병원
        LatLng hotel99 = new LatLng(35.1591087,126.9307566);
        MarkerOptions markerOptions_hotel99 = new MarkerOptions();
        markerOptions_hotel99.position(hotel99);
        markerOptions_hotel99.title("영재애견종합병원");
        markerOptions_hotel99.snippet("");
        markerOptions_hotel99.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel99);

        //현동물병원
        LatLng hotel100 = new LatLng(35.1354575,126.9268513);
        MarkerOptions markerOptions_hotel100 = new MarkerOptions();
        markerOptions_hotel100.position(hotel100);
        markerOptions_hotel100.title("현동물병원");
        markerOptions_hotel100.snippet("");
        markerOptions_hotel100.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel100);

        //함평동물병원
        LatLng hotel101 = new LatLng(35.0635494,126.5189409);
        MarkerOptions markerOptions_hotel101 = new MarkerOptions();
        markerOptions_hotel101.position(hotel101);
        markerOptions_hotel101.title("함평동물병원");
        markerOptions_hotel101.snippet("");
        markerOptions_hotel101.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel101);

        //무안종합동물병원
        LatLng hotel102 = new LatLng(34.989609,126.4772916);
        MarkerOptions markerOptions_hotel102 = new MarkerOptions();
        markerOptions_hotel102.position(hotel102);
        markerOptions_hotel102.title("무안종합동물병원");
        markerOptions_hotel102.snippet("");
        markerOptions_hotel102.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel102);

        //서울동물병원
        LatLng hotel103 = new LatLng(34.8070733,126.3743591);
        MarkerOptions markerOptions_hotel103 = new MarkerOptions();
        markerOptions_hotel103.position(hotel103);
        markerOptions_hotel103.title("서울동물병원");
        markerOptions_hotel103.snippet("");
        markerOptions_hotel103.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel103);

        //호남동물병원
        LatLng hotel104 = new LatLng(34.7933651,126.3813114);
        MarkerOptions markerOptions_hotel104 = new MarkerOptions();
        markerOptions_hotel104.position(hotel104);
        markerOptions_hotel104.title("호남동물병원");
        markerOptions_hotel104.snippet("");
        markerOptions_hotel104.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel104);

        //도야동물병원
        LatLng hotel105 = new LatLng(34.8044482,126.3934565);
        MarkerOptions markerOptions_hotel105 = new MarkerOptions();
        markerOptions_hotel105.position(hotel105);
        markerOptions_hotel105.title("도야동물병원");
        markerOptions_hotel105.snippet("");
        markerOptions_hotel105.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel105);

        //목포동물병원
        LatLng hotel106 = new LatLng(34.798246,126.3907957);
        MarkerOptions markerOptions_hotel106 = new MarkerOptions();
        markerOptions_hotel106.position(hotel106);
        markerOptions_hotel106.title("목포동물병원");
        markerOptions_hotel106.snippet("");
        markerOptions_hotel106.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel106);

        //119동물병원
        LatLng hotel107 = new LatLng(34.8098394,126.4245272);
        MarkerOptions markerOptions_hotel107 = new MarkerOptions();
        markerOptions_hotel107.position(hotel107);
        markerOptions_hotel107.title("119동물병원");
        markerOptions_hotel107.snippet("");
        markerOptions_hotel107.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel107);

        //우리동물병원
        LatLng hotel108 = new LatLng(34.8072848,126.4240122);
        MarkerOptions markerOptions_hotel108 = new MarkerOptions();
        markerOptions_hotel108.position(hotel108);
        markerOptions_hotel108.title("우리동물병원");
        markerOptions_hotel108.snippet("");
        markerOptions_hotel108.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel108);

        //한빛 동물병원
        LatLng hotel109 = new LatLng(34.8021929,126.426909);
        MarkerOptions markerOptions_hotel109 = new MarkerOptions();
        markerOptions_hotel109.position(hotel109);
        markerOptions_hotel109.title("한빛 동물병원");
        markerOptions_hotel109.snippet("");
        markerOptions_hotel109.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel109);

        //한사랑동물의료센터
        LatLng hotel110 = new LatLng(34.8009419,126.4330888);
        MarkerOptions markerOptions_hotel110 = new MarkerOptions();
        markerOptions_hotel110.position(hotel110);
        markerOptions_hotel110.title("한사랑동물의료센터");
        markerOptions_hotel110.snippet("");
        markerOptions_hotel110.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel110);

        //아빠손동물병원
        LatLng hotel111 = new LatLng(34.8120945,126.4607048);
        MarkerOptions markerOptions_hotel111 = new MarkerOptions();
        markerOptions_hotel111.position(hotel111);
        markerOptions_hotel111.title("아빠손동물병원");
        markerOptions_hotel111.snippet("");
        markerOptions_hotel111.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel111);

        //남악동물병원
        LatLng hotel112 = new LatLng(34.8105089,126.4662409);
        MarkerOptions markerOptions_hotel112 = new MarkerOptions();
        markerOptions_hotel112.position(hotel112);
        markerOptions_hotel112.title("남악악동물병원");
        markerOptions_hotel112.snippet("");
        markerOptions_hotel112.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel112);

        //서광동물병원
        LatLng hotel113 = new LatLng(34.8028272,126.7010093);
        MarkerOptions markerOptions_hotel113 = new MarkerOptions();
        markerOptions_hotel113.position(hotel113);
        markerOptions_hotel113.title("서광동물병원");
        markerOptions_hotel113.snippet("");
        markerOptions_hotel113.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel113);

        //시종동물병원
        LatLng hotel114 = new LatLng(34.7990566,126.7010736);
        MarkerOptions markerOptions_hotel114 = new MarkerOptions();
        markerOptions_hotel114.position(hotel114);
        markerOptions_hotel114.title("시종동물병원");
        markerOptions_hotel114.snippet("");
        markerOptions_hotel114.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel114);

        //신해남가축병원
        LatLng hotel115 = new LatLng(34.5673267,126.5930772);
        MarkerOptions markerOptions_hotel115 = new MarkerOptions();
        markerOptions_hotel115.position(hotel115);
        markerOptions_hotel115.title("신해남가축병원");
        markerOptions_hotel115.snippet("");
        markerOptions_hotel115.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel115);

        //청산동물병원
        LatLng hotel116 = new LatLng(34.5693586,126.6062737);
        MarkerOptions markerOptions_hotel116 = new MarkerOptions();
        markerOptions_hotel116.position(hotel116);
        markerOptions_hotel116.title("청산동물병원");
        markerOptions_hotel116.snippet("");
        markerOptions_hotel116.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel116);

        //다산동물병원
        LatLng hotel117 = new LatLng(34.6325193,126.7704463);
        MarkerOptions markerOptions_hotel117 = new MarkerOptions();
        markerOptions_hotel117.position(hotel117);
        markerOptions_hotel117.title("다산동물병원");
        markerOptions_hotel117.snippet("");
        markerOptions_hotel117.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel117);

        //김길남동물병원
        LatLng hotel118 = new LatLng(34.639599,126.7753601);
        MarkerOptions markerOptions_hotel118 = new MarkerOptions();
        markerOptions_hotel118.position(hotel118);
        markerOptions_hotel118.title("김길남동물병원");
        markerOptions_hotel118.snippet("");
        markerOptions_hotel118.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel118);

        //사거리동물병원
        LatLng hotel119 = new LatLng(34.6386809,126.7740512);
        MarkerOptions markerOptions_hotel119 = new MarkerOptions();
        markerOptions_hotel119.position(hotel119);
        markerOptions_hotel119.title("사거리동물병원");
        markerOptions_hotel119.snippet("");
        markerOptions_hotel119.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel119);

        //희망동물병원
        LatLng hotel120 = new LatLng(34.6773879,126.9111013);
        MarkerOptions markerOptions_hotel120 = new MarkerOptions();
        markerOptions_hotel120.position(hotel120);
        markerOptions_hotel120.title("희망동물병원");
        markerOptions_hotel120.snippet("");
        markerOptions_hotel120.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel120);

        //목도가축병원
        LatLng hotel121 = new LatLng(36.8723256,127.8546166);
        MarkerOptions markerOptions_hotel121 = new MarkerOptions();
        markerOptions_hotel121.position(hotel121);
        markerOptions_hotel121.title("목도가축병원");
        markerOptions_hotel121.snippet("");
        markerOptions_hotel121.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel121);

        //정종합동물병원
        LatLng hotel122 = new LatLng(36.9690439,127.9309845);
        MarkerOptions markerOptions_hotel122 = new MarkerOptions();
        markerOptions_hotel122.position(hotel122);
        markerOptions_hotel122.title("정종합동물병원");
        markerOptions_hotel122.snippet("");
        markerOptions_hotel122.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel122);

        //애플동물병원
        LatLng hotel123 = new LatLng(36.9827234,127.9162002);
        MarkerOptions markerOptions_hotel123 = new MarkerOptions();
        markerOptions_hotel123.position(hotel123);
        markerOptions_hotel123.title("애플동물병원");
        markerOptions_hotel123.snippet("");
        markerOptions_hotel123.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel123);

        //열린동물병원
        LatLng hotel124 = new LatLng(36.984506,127.9244399);
        MarkerOptions markerOptions_hotel124 = new MarkerOptions();
        markerOptions_hotel124.position(hotel124);
        markerOptions_hotel124.title("열린동물병원");
        markerOptions_hotel124.snippet("");
        markerOptions_hotel124.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel124);

        //보드미동물병원
        LatLng hotel125 = new LatLng(36.9811464,127.9285383);
        MarkerOptions markerOptions_hotel125 = new MarkerOptions();
        markerOptions_hotel125.position(hotel125);
        markerOptions_hotel125.title("보드미동물병원");
        markerOptions_hotel125.snippet("");
        markerOptions_hotel125.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel125);

        //가인동물병원
        LatLng hotel126 = new LatLng(36.9825006,127.9318643);
        MarkerOptions markerOptions_hotel126 = new MarkerOptions();
        markerOptions_hotel126.position(hotel126);
        markerOptions_hotel126.title("가인동물병원");
        markerOptions_hotel126.snippet("");
        markerOptions_hotel126.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel126);

        //현대동물병원
        LatLng hotel127 = new LatLng(36.9868542,127.9332376);
        MarkerOptions markerOptions_hotel127 = new MarkerOptions();
        markerOptions_hotel127.position(hotel127);
        markerOptions_hotel127.title("현대동물병원");
        markerOptions_hotel127.snippet("");
        markerOptions_hotel127.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel127);

        //박동물병원
        LatLng hotel128 = new LatLng(36.9909848,127.9292464);
        MarkerOptions markerOptions_hotel128 = new MarkerOptions();
        markerOptions_hotel128.position(hotel128);
        markerOptions_hotel128.title("박동물병원");
        markerOptions_hotel128.snippet("");
        markerOptions_hotel128.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel128);

        //명진동물병원
        LatLng hotel129 = new LatLng(37.1354139,128.1976604);
        MarkerOptions markerOptions_hotel129 = new MarkerOptions();
        markerOptions_hotel129.position(hotel129);
        markerOptions_hotel129.title("명진동물병원");
        markerOptions_hotel129.snippet("");
        markerOptions_hotel129.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel129);

        //한일동물병원
        LatLng hotel130 = new LatLng(37.1372956,128.2070374);
        MarkerOptions markerOptions_hotel130 = new MarkerOptions();
        markerOptions_hotel130.position(hotel130);
        markerOptions_hotel130.title("한일동물병원");
        markerOptions_hotel130.snippet("");
        markerOptions_hotel130.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel130);

        //진주동물병원
        LatLng hotel131 = new LatLng(37.1395877,128.2098913);
        MarkerOptions markerOptions_hotel131 = new MarkerOptions();
        markerOptions_hotel131.position(hotel131);
        markerOptions_hotel131.title("진주동물병원");
        markerOptions_hotel131.snippet("");
        markerOptions_hotel131.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel131);

        //현대동물병원
        LatLng hotel132 = new LatLng(37.1485675,128.2119083);
        MarkerOptions markerOptions_hotel132 = new MarkerOptions();
        markerOptions_hotel132.position(hotel132);
        markerOptions_hotel132.title("현대동물병원");
        markerOptions_hotel132.snippet("");
        markerOptions_hotel132.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel132);

        //김가축병원
        LatLng hotel133 = new LatLng(37.183416,128.4660745);
        MarkerOptions markerOptions_hotel133 = new MarkerOptions();
        markerOptions_hotel133.position(hotel133);
        markerOptions_hotel133.title("김가축병원");
        markerOptions_hotel133.snippet("");
        markerOptions_hotel133.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel133);

        //강원가축병원
        LatLng hotel134 = new LatLng(37.1835528,128.4721041);
        MarkerOptions markerOptions_hotel134 = new MarkerOptions();
        markerOptions_hotel134.position(hotel134);
        markerOptions_hotel134.title("강원가축병원");
        markerOptions_hotel134.snippet("");
        markerOptions_hotel134.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel134);

        //태백동물병원
        LatLng hotel135 = new LatLng(37.1623344,128.985157);
        MarkerOptions markerOptions_hotel135 = new MarkerOptions();
        markerOptions_hotel135.position(hotel135);
        markerOptions_hotel135.title("태백동물병원");
        markerOptions_hotel135.snippet("");
        markerOptions_hotel135.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel135);

        //호수동물병원
        LatLng hotel136 = new LatLng(37.2853769,127.1528488);
        MarkerOptions markerOptions_hotel136 = new MarkerOptions();
        markerOptions_hotel136.position(hotel136);
        markerOptions_hotel136.title("호수동물병원");
        markerOptions_hotel136.snippet("경기도 용인시 기흥구 동백동 607-11");
        markerOptions_hotel136.icon(BitmapDescriptorFactory.fromBitmap(hos_smallMarker2));
        mMap.addMarker(markerOptions_hotel136);

    }

    public void view_cafe(){

        // --------------------- 해피치 애견카페 -----------------------------
        LatLng cafe2 = new LatLng(37.4649123,126.8972397); // 해피치 애견카페
        MarkerOptions markerOptions3 = new MarkerOptions(); // 해피치 애견카페
        markerOptions3.position(cafe2);
        markerOptions3.title("해피치 애견카페");
        markerOptions3.snippet("서울특별시 금천구 독산1동 시흥대로 351");
        BitmapDrawable bitmapdraw3=(BitmapDrawable)getResources().getDrawable(R.drawable.cafe);
        Bitmap b3=bitmapdraw3.getBitmap();
        Bitmap smallMarker3 = Bitmap.createScaledBitmap(b3, 60, 60, false);
        markerOptions3.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(markerOptions3);

        // 카페멍 안양
        LatLng cafe3 = new LatLng(37.4000964,126.9215298);
        MarkerOptions cafe_marker3 = new MarkerOptions();
        cafe_marker3.position(cafe3);
        cafe_marker3.title("카페멍 안양");
        cafe_marker3.snippet("경기도 안양시 만안구 안양동 장내로139번길 56-5");
        cafe_marker3.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker3);

        // 애견카페 에코독
        LatLng cafe4 = new LatLng(37.2483236,127.0459445);
        MarkerOptions cafe_marker4 = new MarkerOptions();
        cafe_marker4.position(cafe4);
        cafe_marker4.title("애견카페 에코독");
        cafe_marker4.snippet("경기도 수원시 영통구 권선로 908 번길 3");
        cafe_marker4.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker4);

        // 아이러브퍼피
        LatLng cafe5 = new LatLng(37.3328358,127.1086407);
        MarkerOptions cafe_marker5 = new MarkerOptions();
        cafe_marker5.position(cafe5);
        cafe_marker5.title("아이러브퍼피");
        cafe_marker5.snippet("경기도 용인시 수지구 죽전동 876-6");
        cafe_marker5.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker5);

        // 멍스데이
        LatLng cafe6 = new LatLng(37.2945399,127.0560265);
        MarkerOptions cafe_marker6 = new MarkerOptions();
        cafe_marker6.position(cafe6);
        cafe_marker6.title("멍스데이");
        cafe_marker6.snippet("경기도 수원시 영통구 이의동 27-26");
        cafe_marker6.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker6);

        // 딩고 영통구
        LatLng cafe7 = new LatLng(37.2352487,127.0549965);
        MarkerOptions cafe_marker7 = new MarkerOptions();
        cafe_marker7.position(cafe7);
        cafe_marker7.title("딩고 영통구");
        cafe_marker7.snippet("경기도 수원시 영통구 망포동 599-1");
        cafe_marker7.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker7);

        // 플레이어스
        LatLng cafe8 = new LatLng(37.1964245,127.0789433);
        MarkerOptions cafe_marker8 = new MarkerOptions();
        cafe_marker8.position(cafe8);
        cafe_marker8.title("플레이어스");
        cafe_marker8.snippet("경기도 화성시 동탄2동 123-3 1층");
        cafe_marker8.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker8);

        // 제이독(J.DOG)애견카페
        LatLng cafe9 = new LatLng(36.9996052,127.1372223);
        MarkerOptions cafe_marker9 = new MarkerOptions();
        cafe_marker9.position(cafe9);
        cafe_marker9.title("제이독(J.DOG)애견카페");
        cafe_marker9.snippet("경기도 평택시 용이동 126-7");
        cafe_marker9.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker9);


        // 청주애견cafe D.D
        LatLng cafe10 = new LatLng(36.6471428,127.4856091);
        MarkerOptions cafe_marker10 = new MarkerOptions();
        cafe_marker10.position(cafe10);
        cafe_marker10.title("청주애견cafe D.D");
        cafe_marker10.snippet("충청북도 청주시 상당구 우암동 345");
        cafe_marker10.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker10);

        // 헤이독 청주
        LatLng cafe11 = new LatLng(36.6324733,127.4889565);
        MarkerOptions cafe_marker11 = new MarkerOptions();
        cafe_marker11.position(cafe11);
        cafe_marker11.title("헤이독 청주");
        cafe_marker11.snippet("충청북도 청주시 상당구 남문로2가 성안로 50");
        cafe_marker11.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker11);

        // 코이이누
        LatLng cafe12 = new LatLng(36.3509763,127.3763037);
        MarkerOptions cafe_marker12 = new MarkerOptions();
        cafe_marker12.position(cafe12);
        cafe_marker12.title("코이이누");
        cafe_marker12.snippet("대전광역시 서구 둔산동 1067번지 5층");
        cafe_marker12.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker12);

        // 원쿡 대구
        LatLng cafe13 = new LatLng(35.8318708,128.6852217);
        MarkerOptions cafe_marker13 = new MarkerOptions();
        cafe_marker13.position(cafe13);
        cafe_marker13.title("원쿡 대구");
        cafe_marker13.snippet("대흥동 138-4번지 수성구 대구광역시 KR");
        cafe_marker13.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker13);

        //도기온애견카페
        LatLng cafe14 = new LatLng(35.8807401,128.7667179);
        MarkerOptions cafe_marker14 = new MarkerOptions();
        cafe_marker14.position(cafe14);
        cafe_marker14.title("도기온애견카페");
        cafe_marker14.snippet("경상북도 경산시 하양읍 청천리 550");
        cafe_marker14.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker14);

        // 라떼애견카페 (개스트하우스)
        LatLng cafe15 = new LatLng(35.6394834,129.0595176);
        MarkerOptions cafe_marker15 = new MarkerOptions();
        cafe_marker15.position(cafe15);
        cafe_marker15.title("라떼애견카페 (개스트하우스)");
        cafe_marker15.snippet("울산광역시 울주군 상북면 삽재로 310-1");
        cafe_marker15.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker15);

        // 덕수네 애견카페
        LatLng cafe16 = new LatLng(35.1546527,129.0626192);
        MarkerOptions cafe_marker16 = new MarkerOptions();
        cafe_marker16.position(cafe16);
        cafe_marker16.title("덕수네 애견카페");
        cafe_marker16.snippet("부산광역시 부산진구 전포동 동천로 66");
        cafe_marker16.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker16);

        // 포레스트 3002
        LatLng cafe17 = new LatLng(35.1159068,128.8935328);
        MarkerOptions cafe_marker17 = new MarkerOptions();
        cafe_marker17.position(cafe17);
        cafe_marker17.title("포레스트 3002");
        cafe_marker17.snippet("부산광역시 강서구 녹산동 낙동남로682번길 262");
        cafe_marker17.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker17);

        //빈티지399
        LatLng cafe18 = new LatLng(35.1262268,126.7457271);
        MarkerOptions cafe_marker18 = new MarkerOptions();
        cafe_marker18.position(cafe18);
        cafe_marker18.title("빈티지399");
        cafe_marker18.snippet("광주광역시 광산구 평동 399-3");
        cafe_marker18.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker18);

        //나비야놀자
        LatLng cafe19 = new LatLng(36.0836508,128.397095);
        MarkerOptions cafe_marker19 = new MarkerOptions();
        cafe_marker19.position(cafe19);
        cafe_marker19.title("나비야놀자");
        cafe_marker19.snippet("경상북도 칠곡군 석적읍 남구미로 191");
        cafe_marker19.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker19);

        //디반식스
        LatLng cafe20 = new LatLng(35.2331059,128.6912727);
        MarkerOptions cafe_marker20 = new MarkerOptions();
        cafe_marker20.position(cafe20);
        cafe_marker20.title("디반식스");
        cafe_marker20.snippet("경상남도 창원시 의창구 신월동 상남로248번길 4");
        cafe_marker20.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker20);

        //멍뭉애견카페
        LatLng cafe21 = new LatLng(35.8537789,128.5485816);
        MarkerOptions cafe_marker21 = new MarkerOptions();
        cafe_marker21.position(cafe21);
        cafe_marker21.title("멍뭉애견카페");
        cafe_marker21.snippet("대구광역시 달서구 두류3동 495-17");
        cafe_marker21.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker21);

        //퍼니퍼피
        LatLng cafe22 = new LatLng(36.0787029,129.4014719);
        MarkerOptions cafe_marker22 = new MarkerOptions();
        cafe_marker22.position(cafe22);
        cafe_marker22.title("퍼니퍼피");
        cafe_marker22.snippet("경상북도 포항시 북구 장량동 양덕남로 85");
        cafe_marker22.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker22);

        //모모몽애견(카페)
        LatLng cafe23 = new LatLng(37.521905,129.1134928);
        MarkerOptions cafe_marker23 = new MarkerOptions();
        cafe_marker23.position(cafe23);
        cafe_marker23.title("모모몽애견(카페)");
        cafe_marker23.snippet("강원도 동해시 천곡동 1061");
        cafe_marker23.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker23);

        //카페히릿
        LatLng cafe24 = new LatLng(37.5469318,127.074404);
        MarkerOptions cafe_marker24 = new MarkerOptions();
        cafe_marker24.position(cafe24);
        cafe_marker24.title("카페히릿");
        cafe_marker24.snippet("화양동 111-72번지 3층 2호 광진구 서울특별시 KR");
        cafe_marker24.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker24);

        //애견 카페 개그멍
        LatLng cafe25 = new LatLng(37.6563222,127.0617557);
        MarkerOptions cafe_marker25 = new MarkerOptions();
        cafe_marker25.position(cafe25);
        cafe_marker25.title("애견 카페 개그멍");
        cafe_marker25.snippet("서울특별시 노원구 상계2동 상계로1길 8");
        cafe_marker25.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker25);

        //(주)펫톡
        LatLng cafe26 = new LatLng(37.4910716,126.7245978);
        MarkerOptions cafe_marker26 = new MarkerOptions();
        cafe_marker26.position(cafe26);
        cafe_marker26.title("(주)펫톡");
        cafe_marker26.snippet("인천광역시 부평구 부평동 185-24");
        cafe_marker26.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker26);

        //아루스(애견카페)
        LatLng cafe27 = new LatLng(37.2512031,126.9159196);
        MarkerOptions cafe_marker27 = new MarkerOptions();
        cafe_marker27.position(cafe27);
        cafe_marker27.title("아루스(애견카페)");
        cafe_marker27.snippet("경기도 화성시 매송면 화성로 2298-8");
        cafe_marker27.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker27);

        //개간지애견카페
        LatLng cafe28 = new LatLng(37.3354826,126.8072091);
        MarkerOptions cafe_marker28 = new MarkerOptions();
        cafe_marker28.position(cafe28);
        cafe_marker28.title("개간지애견카페");
        cafe_marker28.snippet("단원구 선부동 1070-11번지 304호 동양빌딩 안산시 경기도 KR");
        cafe_marker28.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker28);

        //개버랜드
        LatLng cafe29 = new LatLng(37.2341889,127.2318563);
        MarkerOptions cafe_marker29 = new MarkerOptions();
        cafe_marker29.position(cafe29);
        cafe_marker29.title("개버랜드");
        cafe_marker29.snippet("경기도 용인시 처인구 마평동 457-1");
        cafe_marker29.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker29);

        //하이디 펫
        LatLng cafe30 = new LatLng(37.203956,127.1230532);
        MarkerOptions cafe_marker30 = new MarkerOptions();
        cafe_marker30.position(cafe30);
        cafe_marker30.title("하이디 펫");
        cafe_marker30.snippet("경기도 화성시 동탄면 중리 435");
        cafe_marker30.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker30);

        //개스타그램 (Dog Cafe)
        LatLng cafe31 = new LatLng(37.0790922,127.0544787);
        MarkerOptions cafe_marker31 = new MarkerOptions();
        cafe_marker31.position(cafe31);
        cafe_marker31.title("개스타그램 (Dog Cafe)");
        cafe_marker31.snippet("경기도 평택시 신장동 223-8");
        cafe_marker31.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker31);

        //Pet Story 카페
        LatLng cafe32 = new LatLng(37.0669305,127.0169852);
        MarkerOptions cafe_marker32 = new MarkerOptions();
        cafe_marker32.position(cafe32);
        cafe_marker32.title("Pet Story 카페");
        cafe_marker32.snippet("경기도 평택시 고덕면 두릉리 296-6");
        cafe_marker32.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker32);

        //A dog house 개집
        LatLng cafe33 = new LatLng(37.08206,127.0660196);
        MarkerOptions cafe_marker33 = new MarkerOptions();
        cafe_marker33.position(cafe33);
        cafe_marker33.title("A dog house 개집");
        cafe_marker33.snippet("경기도 평택시 독곡동 308-2 2층");
        cafe_marker33.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker33);

        //BARACHEL (바라겔)
        LatLng cafe34 = new LatLng(36.7983734,126.518326);
        MarkerOptions cafe_marker34 = new MarkerOptions();
        cafe_marker34.position(cafe34);
        cafe_marker34.title("BARACHEL (바라겔)");
        cafe_marker34.snippet("충청남도 서산시 음암면 도당꽃밭재길 59-57");
        cafe_marker34.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker34);

        //원독
        LatLng cafe35 = new LatLng(36.5159118,127.2337674);
        MarkerOptions cafe_marker35 = new MarkerOptions();
        cafe_marker35.position(cafe35);
        cafe_marker35.title("원독");
        cafe_marker35.snippet("충청남도 연기군 남면 고정리 701-1 고정리");
        cafe_marker35.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker35);

        //INUINN
        LatLng cafe36 = new LatLng(36.2734813,127.2887971);
        MarkerOptions cafe_marker36 = new MarkerOptions();
        cafe_marker36.position(cafe36);
        cafe_marker36.title("INUINN");
        cafe_marker36.snippet("대전광역시 유성구 방동 455-2");
        cafe_marker36.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker36);

        //핫도그애견카페
        LatLng cafe37 = new LatLng(36.3660427,127.4372474);
        MarkerOptions cafe_marker37 = new MarkerOptions();
        cafe_marker37.position(cafe37);
        cafe_marker37.title("핫도그애견카페");
        cafe_marker37.snippet("대전광역시 대덕구 송촌동 449-4");
        cafe_marker37.icon(BitmapDescriptorFactory.fromBitmap(smallMarker3));
        mMap.addMarker(cafe_marker37);

    }

    public void view_camp(){

        // -------------------싱글벙글 캠핑장-------------------------------
        LatLng Camping = new LatLng(37.212083,127.158638);
        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(Camping);
        markerOptions4.title("싱글벙글캠핑장");
        markerOptions4.snippet("");
        BitmapDrawable bitmapdraw4=(BitmapDrawable)getResources().getDrawable(R.drawable.camp);
        Bitmap b4=bitmapdraw4.getBitmap();
        Bitmap smallMarker4 = Bitmap.createScaledBitmap(b4, 60, 60, false);
        markerOptions4.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions4);

        // 낙원농원 캠핑장
        LatLng Camping2 = new LatLng(35.3363085,129.1269064);
        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(Camping2);
        markerOptions5.title("낙원농원 캠핑장");
        markerOptions5.snippet("");
        markerOptions5.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions5);

        // 임랑카라반 파크펜션
        LatLng Camping3 = new LatLng(35.3091719,129.259429); // 개버랜드
        MarkerOptions markerOptions6 = new MarkerOptions(); // 개버랜드
        markerOptions6.position(Camping3);
        markerOptions6.title("낙원농원 캠핑장");
        markerOptions6.snippet("");
        markerOptions6.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions6);

        // 가야랜드 달빛야영장
        LatLng Camping4 = new LatLng(35.2592518,128.9053774); // 개버랜드
        MarkerOptions markerOptions7 = new MarkerOptions(); // 개버랜드
        markerOptions7.position(Camping4);
        markerOptions7.title("가야랜드 달빛야영장");
        markerOptions7.snippet("");
        markerOptions7.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions7);

        // 달천공원 오토캠핑장
        LatLng Camping5 = new LatLng(35.2823414,128.5998631); // 개버랜드
        MarkerOptions markerOptions8 = new MarkerOptions(); // 개버랜드
        markerOptions8.position(Camping5);
        markerOptions8.title("달천공원 오토캠핑장");
        markerOptions8.snippet("");
        markerOptions8.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions8);

        //한별도그파크
        LatLng Camping6 = new LatLng(35.3365535,128.3562326); // 개버랜드
        MarkerOptions markerOptions9 = new MarkerOptions(); // 개버랜드
        markerOptions9.position(Camping6);
        markerOptions9.title("한별도그파크");
        markerOptions9.snippet("");
        markerOptions9.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions9);

        //유곡천다목적캠핑장
        LatLng Camping7 = new LatLng(35.443645,128.3510828); // 개버랜드
        MarkerOptions markerOptions10 = new MarkerOptions(); // 개버랜드
        markerOptions10.position(Camping7);
        markerOptions10.title("유곡천다목적캠핑장");
        markerOptions10.snippet("");
        markerOptions10.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions10);

        //밀양스쿨오토캠핑장
        LatLng Camping8 = new LatLng(35.5106752,128.840661); // 개버랜드
        MarkerOptions markerOptions11 = new MarkerOptions(); // 개버랜드
        markerOptions11.position(Camping8);
        markerOptions11.title("밀양스쿨오토캠핑장");
        markerOptions11.snippet("");
        markerOptions11.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions11);

        //알프스캠핑장
        LatLng Camping9 = new LatLng(35.5421091,128.8563251); // 개버랜드
        MarkerOptions markerOptions12 = new MarkerOptions(); // 개버랜드
        markerOptions12.position(Camping9);
        markerOptions12.title("알프스캠핑장");
        markerOptions12.snippet("");
        markerOptions12.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions12);

        //배내골대추나무 오토캠핑장
        LatLng Camping10 = new LatLng(35.5591132,129.0209484); // 개버랜드
        MarkerOptions markerOptions13 = new MarkerOptions(); // 개버랜드
        markerOptions13.position(Camping10);
        markerOptions13.title("알프스캠핑장");
        markerOptions13.snippet("");
        markerOptions13.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions13);

        //태화연 오토캠핑장
        LatLng Camping11 = new LatLng(35.5617665,129.2866802); // 개버랜드
        MarkerOptions markerOptions14 = new MarkerOptions(); // 개버랜드
        markerOptions14.position(Camping11);
        markerOptions14.title("알프스캠핑장");
        markerOptions14.snippet("");
        markerOptions14.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions14);

        //입화산 참살이숲 야영장
        LatLng Camping12 = new LatLng(35.5768467,129.2774534); // 개버랜드
        MarkerOptions markerOptions15 = new MarkerOptions(); // 개버랜드
        markerOptions15.position(Camping12);
        markerOptions15.title("입화산 참살이숲 야영장");
        markerOptions15.snippet("");
        markerOptions15.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions15);

        //천리안캠프힐
        LatLng Camping13 = new LatLng(35.6409408,129.0589714); // 개버랜드
        MarkerOptions markerOptions16 = new MarkerOptions(); // 개버랜드
        markerOptions16.position(Camping13);
        markerOptions16.title("입화산 참살이숲 야영장");
        markerOptions16.snippet("");
        markerOptions16.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions16);

        //산내 별빛 오토캠핑장
        LatLng Camping14 = new LatLng(35.6640606,129.058156); // 개버랜드
        MarkerOptions markerOptions17 = new MarkerOptions(); // 개버랜드
        markerOptions17.position(Camping14);
        markerOptions17.title("산내 별빛 오토캠핑장");
        markerOptions17.snippet("");
        markerOptions17.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions17);

        //청도 그린 나래 캠핑장
        LatLng Camping15 = new LatLng(35.6160699,128.804183); // 개버랜드
        MarkerOptions markerOptions18 = new MarkerOptions(); // 개버랜드
        markerOptions18.position(Camping15);
        markerOptions18.title("청도 그린 나래 캠핑장");
        markerOptions18.snippet("");
        markerOptions18.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions18);

        //문복산 캠핑장
        LatLng Camping16 = new LatLng(35.6607134,129.0524483); // 개버랜드
        MarkerOptions markerOptions19 = new MarkerOptions(); // 개버랜드
        markerOptions19.position(Camping16);
        markerOptions19.title("청도 그린 나래 캠핑장");
        markerOptions19.snippet("");
        markerOptions19.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions19);

        //속리산 나래캠핑장
        LatLng Camping17 = new LatLng(36.5738019,127.7709532); // 개버랜드
        MarkerOptions markerOptions20 = new MarkerOptions(); // 개버랜드
        markerOptions20.position(Camping17);
        markerOptions20.title("속리산 나래캠핑장");
        markerOptions20.snippet("");
        markerOptions20.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions20);

        //문암생태공원
        LatLng Camping18 = new LatLng(36.6747524,127.4475861); // 개버랜드
        MarkerOptions markerOptions21 = new MarkerOptions(); // 개버랜드
        markerOptions21.position(Camping18);
        markerOptions21.title("문암생태공원");
        markerOptions21.snippet("");
        markerOptions21.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions21);

        //로하스 가족공원 캠핑장
        LatLng Camping19 = new LatLng(36.4561528,127.4747086); // 개버랜드
        MarkerOptions markerOptions22 = new MarkerOptions(); // 개버랜드
        markerOptions22.position(Camping19);
        markerOptions22.title("문암생태공원");
        markerOptions22.snippet("");
        markerOptions22.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions22);

        //합강공원 오토캠핑장
        LatLng Camping20 = new LatLng(36.5163276,127.337594); // 개버랜드
        MarkerOptions markerOptions23 = new MarkerOptions(); // 개버랜드
        markerOptions23.position(Camping20);
        markerOptions23.title("문암생태공원");
        markerOptions23.snippet("");
        markerOptions23.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions23);

        //동학사 야영장
        LatLng Camping21 = new LatLng(36.3581999,127.2406054); // 개버랜드
        MarkerOptions markerOptions24 = new MarkerOptions(); // 개버랜드
        markerOptions24.position(Camping21);
        markerOptions24.title("문암생태공원");
        markerOptions24.snippet("");
        markerOptions24.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions24);

        //밤별캠핑장
        LatLng Camping22 = new LatLng(37.1545705,127.7273941); // 개버랜드
        MarkerOptions markerOptions25 = new MarkerOptions(); // 개버랜드
        markerOptions25.position(Camping22);
        markerOptions25.title("문암생태공원");
        markerOptions25.snippet("");
        markerOptions25.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions25);

        //힐포인트
        LatLng Camping23 = new LatLng(37.3513282,127.0527649); // 개버랜드
        MarkerOptions markerOptions26 = new MarkerOptions(); // 개버랜드
        markerOptions26.position(Camping23);
        markerOptions26.title("문암생태공원");
        markerOptions26.snippet("");
        markerOptions26.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions26);

        //솔뜰펜션 캠핑장
        LatLng Camping24 = new LatLng(37.5421274,127.4680138); // 개버랜드
        MarkerOptions markerOptions27 = new MarkerOptions(); // 개버랜드
        markerOptions27.position(Camping24);
        markerOptions27.title("문암생태공원");
        markerOptions27.snippet("");
        markerOptions27.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions27);

        //개똥이네 애견캠핑장
        LatLng Camping25 = new LatLng(37.7203556,127.7603531); // 개버랜드
        MarkerOptions markerOptions28 = new MarkerOptions(); // 개버랜드
        markerOptions28.position(Camping25);
        markerOptions28.title("개똥이네 애견캠핑장");
        markerOptions28.snippet("");
        markerOptions28.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions28);

        //동해 추암오토캠핑장
        LatLng Camping26 = new LatLng(37.4769697,129.1592216); // 개버랜드
        MarkerOptions markerOptions29 = new MarkerOptions(); // 개버랜드
        markerOptions29.position(Camping26);
        markerOptions29.title("동해 추암오토캠핑장");
        markerOptions29.snippet("");
        markerOptions29.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions29);

        //무릉계곡 캠핑장
        LatLng Camping27 = new LatLng(37.463652,129.024682); // 개버랜드
        MarkerOptions markerOptions30 = new MarkerOptions(); // 개버랜드
        markerOptions30.position(Camping27);
        markerOptions30.title("무릉계곡 캠핑장");
        markerOptions30.snippet("");
        markerOptions30.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions30);

        //당골 야영장
        LatLng Camping28 = new LatLng(37.1230964,128.9546871); // 개버랜드
        MarkerOptions markerOptions31 = new MarkerOptions(); // 개버랜드
        markerOptions31.position(Camping28);
        markerOptions31.title("당골 야영장");
        markerOptions31.snippet("");
        markerOptions31.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions31);

        //칠량이계곡장산야영장
        LatLng Camping29 = new LatLng(37.1092025,128.8662815); // 개버랜드
        MarkerOptions markerOptions32 = new MarkerOptions(); // 개버랜드
        markerOptions32.position(Camping29);
        markerOptions32.title("당골 야영장");
        markerOptions32.snippet("");
        markerOptions32.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions32);

        //사랑나무캠핑장
        LatLng Camping30 = new LatLng(37.0725735,128.7448311);
        MarkerOptions markerOptions33 = new MarkerOptions();
        markerOptions33.position(Camping30);
        markerOptions33.title("사랑나무캠핑장");
        markerOptions33.snippet("");
        markerOptions33.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions33);

        //느티나무 캠핑장
        LatLng Camping31 = new LatLng(37.1038804,128.6786127);
        MarkerOptions markerOptions34 = new MarkerOptions();
        markerOptions34.position(Camping31);
        markerOptions34.title("느티나무캠핑장");
        markerOptions34.snippet("");
        markerOptions34.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions34);

        //산솔오토캠핑장
        LatLng Camping32 = new LatLng(37.1474558,128.7077308);
        MarkerOptions markerOptions35 = new MarkerOptions();
        markerOptions35.position(Camping32);
        markerOptions35.title("산솔오토캠핑장");
        markerOptions35.snippet("");
        markerOptions35.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions35);

        //남천야영장
        LatLng Camping33 = new LatLng(37.038753,128.5129166);
        MarkerOptions markerOptions36 = new MarkerOptions();
        markerOptions36.position(Camping33);
        markerOptions36.title("남천야영장");
        markerOptions36.snippet("");
        markerOptions36.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions36);

        //삼가야영장
        LatLng Camping34 = new LatLng(36.9264124,128.5018873);
        MarkerOptions markerOptions37 = new MarkerOptions();
        markerOptions37.position(Camping34);
        markerOptions37.title("삼가야영장");
        markerOptions37.snippet("");
        markerOptions37.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions37);

        //저류지 캠핑장
        LatLng Camping35 = new LatLng(37.1912966,128.4480286);
        MarkerOptions markerOptions38 = new MarkerOptions();
        markerOptions38.position(Camping35);
        markerOptions38.title("저류지 캠핑장");
        markerOptions38.snippet("");
        markerOptions38.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions38);

        //구룡자동차 야영장
        LatLng Camping36 = new LatLng(37.4084827,128.045783);
        MarkerOptions markerOptions39 = new MarkerOptions();
        markerOptions39.position(Camping36);
        markerOptions39.title("구룡자동차 야영장");
        markerOptions39.snippet("");
        markerOptions39.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions39);

        //흥터마을휴양지캠핑장
        LatLng Camping37 = new LatLng(37.4967885,128.7221718);
        MarkerOptions markerOptions40 = new MarkerOptions();
        markerOptions40.position(Camping37);
        markerOptions40.title("흥터마을휴양지캠핑장");
        markerOptions40.snippet("");
        markerOptions40.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions40);

        //졸드루야영장
        LatLng Camping38 = new LatLng(37.4514222,128.6366844);
        MarkerOptions markerOptions41 = new MarkerOptions();
        markerOptions41.position(Camping38);
        markerOptions41.title("졸드루야영장");
        markerOptions41.snippet("");
        markerOptions41.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions41);

        //동강전망 자연휴양림
        LatLng Camping39 = new LatLng(37.2608015,128.6079311);
        MarkerOptions markerOptions42 = new MarkerOptions();
        markerOptions42.position(Camping39);
        markerOptions42.title("동강전망 자연휴양림");
        markerOptions42.snippet("");
        markerOptions42.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions42);

        //진탄나루터 야영장
        LatLng Camping40 = new LatLng(37.2907843,128.543644);
        MarkerOptions markerOptions43 = new MarkerOptions();
        markerOptions43.position(Camping40);
        markerOptions43.title("진탄나루터 야영장");
        markerOptions43.snippet("");
        markerOptions43.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions43);

        //망상오토캠핑리조트
        LatLng Camping41 = new LatLng(37.5982521,129.0807724);
        MarkerOptions markerOptions44 = new MarkerOptions();
        markerOptions44.position(Camping41);
        markerOptions44.title("망상오토캠핑리조트");
        markerOptions44.snippet("");
        markerOptions44.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions44);

        //상의자동차야영장
        String address_camping42 = getCurrentAddress(36.3928222,129.142313);
        LatLng Camping42 = new LatLng(36.3928222,129.142313);
        MarkerOptions markerOptions45 = new MarkerOptions();
        markerOptions45.position(Camping42);
        markerOptions45.title("상의자동차야영장");
        markerOptions45.snippet(address_camping42.substring(5));
        markerOptions45.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions45);

        //썬빌리지펜션오토캠핑장
        LatLng Camping43 = new LatLng(36.061936,129.5741272);
        MarkerOptions markerOptions46 = new MarkerOptions();
        markerOptions46.position(Camping43);
        markerOptions46.title("썬빌리지펜션오토캠핑장");
        markerOptions46.snippet("");
        markerOptions46.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions46);

        // 팔공산도학캠핑장
        LatLng Camping44 = new LatLng(35.9819652,128.7031174);
        MarkerOptions markerOptions47 = new MarkerOptions();
        markerOptions47.position(Camping44);
        markerOptions47.title("팔공산도학캠핑장");
        markerOptions47.snippet("");
        markerOptions47.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions47);

        // 칠곡보 오토캠핑장
        LatLng Camping45 = new LatLng(36.0211971,128.3952427);
        MarkerOptions markerOptions48 = new MarkerOptions();
        markerOptions48.position(Camping45);
        markerOptions48.title("칠곡보 오토캠핑장");
        markerOptions48.snippet("");
        markerOptions48.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions48);

        // 구미 캠핑장
        LatLng Camping46 = new LatLng(36.1334383,128.3710384);
        MarkerOptions markerOptions49 = new MarkerOptions();
        markerOptions49.position(Camping46);
        markerOptions49.title("구미 캠핑장");
        markerOptions49.snippet("");
        markerOptions49.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions49);

        // 가산산성야영장
        LatLng Camping47 = new LatLng(36.0173095,128.6202049);
        MarkerOptions markerOptions50 = new MarkerOptions();
        markerOptions50.position(Camping47);
        markerOptions50.title("가산산성야영장");
        markerOptions50.snippet("");
        markerOptions50.icon(BitmapDescriptorFactory.fromBitmap(smallMarker4));
        mMap.addMarker(markerOptions50);

    }

    public void view_park(){
        // 굿 초이스 애견 운동장
        LatLng park1 = new LatLng(37.7769563,126.9442749);
        MarkerOptions markerOptions_park1 = new MarkerOptions();
        markerOptions_park1.position(park1);
        markerOptions_park1.title("굿 초이스 애견 운동장");
        markerOptions_park1.snippet("");
        BitmapDrawable park_bitmapdraw2=(BitmapDrawable)getResources().getDrawable(R.drawable.park);
        Bitmap park_b2=park_bitmapdraw2.getBitmap();
        Bitmap park_smallMarker2 = Bitmap.createScaledBitmap(park_b2, 60, 60, false);
        markerOptions_park1.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park1);


        // 강아지 공원
        LatLng park2 = new LatLng(37.5853982,127.016716);
        MarkerOptions markerOptions_park2 = new MarkerOptions();
        markerOptions_park2.position(park2);
        markerOptions_park2.title("강아지 공원");
        markerOptions_park2.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park2);

        // 강아지 놀이터
        LatLng park3 = new LatLng(37.5421274,127.0984268);
        MarkerOptions markerOptions_park3 = new MarkerOptions();
        markerOptions_park3.position(park3);
        markerOptions_park3.title("강아지 놀이터");
        markerOptions_park3.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park3);

        //삼막애견공원
        LatLng park4 = new LatLng(37.4290013,126.9151354);
        MarkerOptions markerOptions_park4 = new MarkerOptions();
        markerOptions_park4.position(park4);
        markerOptions_park4.title("삼막애견공원");
        markerOptions_park4.snippet("경기도 안양시 만안구 석수동 14-5");
        markerOptions_park4.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park4);

        //인천개공원
        LatLng park5 = new LatLng(37.4556466,126.7469072);
        MarkerOptions markerOptions_park5 = new MarkerOptions();
        markerOptions_park5.position(park5);
        markerOptions_park5.title("인천개공원");
        markerOptions_park5.snippet("인천광역시 남동구 장수서창동 무네미로 201-15");
        markerOptions_park5.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park5);

        //도그파크
        LatLng park6 = new LatLng(37.3159088,127.1502686);
        MarkerOptions markerOptions_park6 = new MarkerOptions();
        markerOptions_park6.position(park6);
        markerOptions_park6.title("도그파크");
        markerOptions_park6.snippet("경기도 용인시 처인구 모현면 오산리 372");
        markerOptions_park6.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park6);

        //컨츄리독힐링파크
        LatLng park7 = new LatLng(37.3316073,127.2371292);
        MarkerOptions markerOptions_park7 = new MarkerOptions();
        markerOptions_park7.position(park7);
        markerOptions_park7.title("컨츄리독힐링파크");
        markerOptions_park7.snippet("경기도 용인시 처인구 모현면 갈담리 614");
        markerOptions_park7.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park7);

        //기흥레스피아호수공원
        LatLng park8 = new LatLng(37.2546191,127.0961952);
        MarkerOptions markerOptions_park8 = new MarkerOptions();
        markerOptions_park8.position(park8);
        markerOptions_park8.title("기흥레스피아호수공원");
        markerOptions_park8.snippet("경기도 용인시 기흥구 하갈동 114-5");
        markerOptions_park8.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park8);

        //레이지독
        LatLng park9 = new LatLng(37.2879505,127.3267365);
        MarkerOptions markerOptions_park9 = new MarkerOptions();
        markerOptions_park9.position(park9);
        markerOptions_park9.title("레이지독");
        markerOptions_park9.snippet("경기도 광주시 도척면 유정리 119-1");
        markerOptions_park9.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park9);

        //퍼피앤커피
        LatLng park10 = new LatLng(37.4814992,126.9294691);
        MarkerOptions markerOptions_park10 = new MarkerOptions();
        markerOptions_park10.position(park10);
        markerOptions_park10.title("퍼피앤커피");
        markerOptions_park10.snippet("서울특별시 관악구 서원동 1637-18");
        markerOptions_park10.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park10);

        //Dog Park Anjeong-ri
        LatLng park11 = new LatLng(36.9612602,127.052443);
        MarkerOptions markerOptions_park11 = new MarkerOptions();
        markerOptions_park11.position(park11);
        markerOptions_park11.title("Dog Park Anjeong-ri");
        markerOptions_park11.snippet("경기도 평택시 팽성읍 송화리 66-3");
        markerOptions_park11.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park11);

        //Eden Dog Park
        LatLng park12 = new LatLng(36.7636245,126.9192767);
        MarkerOptions markerOptions_park12 = new MarkerOptions();
        markerOptions_park12.position(park12);
        markerOptions_park12.title("Eden Dog Park");
        markerOptions_park12.snippet("충청남도 아산시 도고면 온천대로 605-23");
        markerOptions_park12.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park12);

        //Dog park
        LatLng park13 = new LatLng(36.8261534,127.161963);
        MarkerOptions markerOptions_park13 = new MarkerOptions();
        markerOptions_park13.position(park13);
        markerOptions_park13.title("Dog park");
        markerOptions_park13.snippet("충청남도 천안시 동남구 신부동");
        markerOptions_park13.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park13);

        //오송호수공원
        LatLng park14 = new LatLng(36.6319223,127.3250198);
        MarkerOptions markerOptions_park14 = new MarkerOptions();
        markerOptions_park14.position(park14);
        markerOptions_park14.title("오송호수공원");
        markerOptions_park14.snippet("충청북도 청주시 청원구 오송읍 만수리");
        markerOptions_park14.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park14);

        //준스켄넬
        LatLng park15 = new LatLng(36.6381899,127.5066376);
        MarkerOptions markerOptions_park15 = new MarkerOptions();
        markerOptions_park15.position(park15);
        markerOptions_park15.title("준스켄넬");
        markerOptions_park15.snippet("충청북도 청주시 상당구 용담동 100-1");
        markerOptions_park15.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park15);

        //홍동애향공원
        LatLng park16 = new LatLng(36.560256,126.6889286);
        MarkerOptions markerOptions_park16 = new MarkerOptions();
        markerOptions_park16.position(park16);
        markerOptions_park16.title("홍동애향공원");
        markerOptions_park16.snippet("충청남도 홍성군 홍동면 구정리 519");
        markerOptions_park16.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park16);

        //보라매공원
        LatLng park17 = new LatLng(37.4932474,126.9197273);
        MarkerOptions markerOptions_park17 = new MarkerOptions();
        markerOptions_park17.position(park17);
        markerOptions_park17.title("보라매공원");
        markerOptions_park17.snippet("서울특별시 동작구 신대방2동 여의대방로20길 33");
        markerOptions_park17.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park17);

        //루디힐
        LatLng park18 = new LatLng(37.6381939,127.4143267);
        MarkerOptions markerOptions_park18 = new MarkerOptions();
        markerOptions_park18.position(park18);
        markerOptions_park18.title("루디힐");
        markerOptions_park18.snippet("경기도 양평군 서종면 수입리 175-10");
        markerOptions_park18.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park18);

        //신나물 두매향기
        LatLng park19 = new LatLng(37.5524031,127.3865604);
        MarkerOptions markerOptions_park19 = new MarkerOptions();
        markerOptions_park19.position(park19);
        markerOptions_park19.title("신나물 두매향기");
        markerOptions_park19.snippet("경기도 양평군 양서면 목왕로592번길 62-59");
        markerOptions_park19.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park19);

        //아우름
        LatLng park20 = new LatLng(37.6078741,127.2788429);
        MarkerOptions markerOptions_park20 = new MarkerOptions();
        markerOptions_park20.position(park20);
        markerOptions_park20.title("아우름");
        markerOptions_park20.snippet("경기도 남양주시 와부읍 월문리 53-1");
        markerOptions_park20.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park20);

        //애완견돌봄원
        LatLng park21 = new LatLng(37.6763481,127.1751595);
        MarkerOptions markerOptions_park21 = new MarkerOptions();
        markerOptions_park21.position(park21);
        markerOptions_park21.title("애완견돌봄원");
        markerOptions_park21.snippet("경기도 남양주시 진건읍 사릉로 720-135");
        markerOptions_park21.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park21);

        //광교호수공원 애견놀이터
        LatLng park22 = new LatLng(37.2857311,127.0785999);
        MarkerOptions markerOptions_park22 = new MarkerOptions();
        markerOptions_park22.position(park22);
        markerOptions_park22.snippet("경기도 수원시 영통구 하동 40-1");
        markerOptions_park22.title("광교호수공원 애견놀이터");
        markerOptions_park22.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park22);

        //이웅종강아지테마파크
        LatLng park23 = new LatLng(37.6641871,126.7541599);
        MarkerOptions markerOptions_park23 = new MarkerOptions();
        markerOptions_park23.position(park23);
        markerOptions_park23.title("이웅종강아지테마파크");
        markerOptions_park23.snippet("경기도 고양시 일산서구 송포동 한류월드로 300");
        markerOptions_park23.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park23);

        //초안산공공반려견놀이터
        LatLng park24 = new LatLng(37.647471,127.0413065);
        MarkerOptions markerOptions_park24 = new MarkerOptions();
        markerOptions_park24.position(park24);
        markerOptions_park24.title("초안산공공반려견놀이터");
        markerOptions_park24.snippet("서울특별시 도봉구 창동 산24");
        markerOptions_park24.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park24);

        //부산펫택시
        LatLng park25 = new LatLng(35.1368965,129.0983677);
        MarkerOptions markerOptions_park25 = new MarkerOptions();
        markerOptions_park25.position(park25);
        markerOptions_park25.title("부산펫택시");
        markerOptions_park25.snippet("부산광역시 남구 대연동 수영로 295");
        markerOptions_park25.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park25);

        //애견운동공원
        LatLng park26 = new LatLng(35.5318071,129.2716599);
        MarkerOptions markerOptions_park26 = new MarkerOptions();
        markerOptions_park26.position(park26);
        markerOptions_park26.title("애견운동공원");
        markerOptions_park26.snippet("울산광역시 남구 옥동");
        markerOptions_park26.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park26);

        //독봉산웰빙공원
        LatLng park27 = new LatLng(34.8781859,128.6339378);
        MarkerOptions markerOptions_park27 = new MarkerOptions();
        markerOptions_park27.position(park27);
        markerOptions_park27.title("독봉산웰빙공원");
        markerOptions_park27.snippet("경상남도 거제시 상동동 373-1");
        markerOptions_park27.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park27);

        //뿌리공원
        LatLng park28 = new LatLng(36.2854153,127.3880625);
        MarkerOptions markerOptions_park28 = new MarkerOptions();
        markerOptions_park28.position(park28);
        markerOptions_park28.title("뿌리공원");
        markerOptions_park28.snippet("대전광역시 중구 침산동 뿌리공원로 79");
        markerOptions_park28.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park28);

        //작은내수변공원
        LatLng park29 = new LatLng(36.3432682,127.3418427);
        MarkerOptions markerOptions_park29 = new MarkerOptions();
        markerOptions_park29.position(park29);
        markerOptions_park29.title("작은내수변공원");
        markerOptions_park29.snippet("대전광역시 유성구 봉명동");
        markerOptions_park29.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park29);

        //보라매공원
        LatLng park30 = new LatLng(36.3481766,127.3845863);
        MarkerOptions markerOptions_park30 = new MarkerOptions();
        markerOptions_park30.position(park30);
        markerOptions_park30.title("보라매공원 대전");
        markerOptions_park30.snippet("대전광역시 서구 둔산동");
        markerOptions_park30.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park30);

        //대동하늘공원
        LatLng park31 = new LatLng(36.3324824,127.4514055);
        MarkerOptions markerOptions_park31 = new MarkerOptions();
        markerOptions_park31.position(park31);
        markerOptions_park31.title("대동하늘공원");
        markerOptions_park31.snippet("대전광역시 동구 자양동 20-9");
        markerOptions_park31.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park31);

        //동락공원
        LatLng park32 = new LatLng(36.097938,128.4013367);
        MarkerOptions markerOptions_park32 = new MarkerOptions();
        markerOptions_park32.position(park32);
        markerOptions_park32.title("동락공원");
        markerOptions_park32.snippet("경상북도 구미시 진평동 766");
        markerOptions_park32.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park32);

        //침산공원
        LatLng park33 = new LatLng(35.8961424,128.586216);
        MarkerOptions markerOptions_park33 = new MarkerOptions();
        markerOptions_park33.position(park33);
        markerOptions_park33.title("침산공원");
        markerOptions_park33.snippet("대구광역시 북구 침산동 1168-3");
        markerOptions_park33.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park33);

        //하얀민들레
        LatLng park34 = new LatLng(35.7182249,128.7259483);
        MarkerOptions markerOptions_park34 = new MarkerOptions();
        markerOptions_park34.position(park34);
        markerOptions_park34.title("하얀민들레");
        markerOptions_park34.snippet("경상북도 청도군 화양읍 852 남성현로");
        markerOptions_park34.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park34);

        //복현공원
        LatLng park35 = new LatLng(35.8973244,128.6271572);
        MarkerOptions markerOptions_park35 = new MarkerOptions();
        markerOptions_park35.position(park35);
        markerOptions_park35.title("복현공원");
        markerOptions_park35.snippet("대구광역시 북구 복현동");
        markerOptions_park35.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park35);

        //진도개테마파크
        LatLng park36 = new LatLng(34.4827526,126.2831177);
        MarkerOptions markerOptions_park36 = new MarkerOptions();
        markerOptions_park36.position(park36);
        markerOptions_park36.title("진도개테마파크");
        markerOptions_park36.snippet("전라남도 진도군 진도읍 동외리 57");
        markerOptions_park36.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park36);

        //모현공원
        LatLng park37 = new LatLng(35.9493149,126.9375801);
        MarkerOptions markerOptions_park37 = new MarkerOptions();
        markerOptions_park37.position(park37);
        markerOptions_park37.title("모현공원");
        markerOptions_park37.snippet("전라북도 익산시 모현동1가");
        markerOptions_park37.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park37);

        //용인중앙공원
        LatLng park38 = new LatLng(37.2316097,127.2074103);
        MarkerOptions markerOptions_park38 = new MarkerOptions();
        markerOptions_park38.position(park38);
        markerOptions_park38.title("용인중앙공원");
        markerOptions_park38.snippet("경기도 용인시 처인구 김량장동");
        markerOptions_park38.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park38);

        //동백호수공원
        LatLng park39 = new LatLng(37.2757774,127.149539);
        MarkerOptions markerOptions_park39 = new MarkerOptions();
        markerOptions_park39.position(park39);
        markerOptions_park39.title("동백호수공원");
        markerOptions_park39.snippet("경기도 용인시 기흥구 중동 동백5로 12");
        markerOptions_park39.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park39);

        //한길찬공원
        LatLng park40 = new LatLng(37.2757774,127.149539);
        MarkerOptions markerOptions_park40 = new MarkerOptions();
        markerOptions_park40.position(park40);
        markerOptions_park40.title("한길찬공원");
        markerOptions_park40.snippet("경기도 용인시 기흥구 중동");
        markerOptions_park40.icon(BitmapDescriptorFactory.fromBitmap(park_smallMarker2));
        mMap.addMarker(markerOptions_park40);

    }

    public void view_hotel(){

        // -------------------- 애견 호텔 ---------------------
        //멍스라이프
        LatLng hotel1 = new LatLng(37.290827,127.141446);
        MarkerOptions markerOptions_hotel1 = new MarkerOptions();
        markerOptions_hotel1.position(hotel1);
        markerOptions_hotel1.title("멍스라이프");
        markerOptions_hotel1.snippet("경기도 용인시 기흥구 청덕동 534-2");
        BitmapDrawable hotel_hotel_bitmapdraw2=(BitmapDrawable)getResources().getDrawable(R.drawable.hotel);
        Bitmap hotel_b2=hotel_hotel_bitmapdraw2.getBitmap();
        Bitmap hotel_smallMarker2 = Bitmap.createScaledBitmap(hotel_b2, 60, 60, false);
        markerOptions_hotel1.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel1);

        //퍼피캐슬
        LatLng hotel2 = new LatLng(37.1317072,127.2022942);
        MarkerOptions markerOptions_hotel2 = new MarkerOptions();
        markerOptions_hotel2.position(hotel2);
        markerOptions_hotel2.title("퍼피캐슬");
        markerOptions_hotel2.snippet("경기도 용인시 처인구 이동면 송전리 265");
        markerOptions_hotel2.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel2);

        //발리애견호텔
        LatLng hotel3 = new LatLng(36.3350407,127.3926544);
        MarkerOptions markerOptions_hotel3 = new MarkerOptions();
        markerOptions_hotel3.position(hotel3);
        markerOptions_hotel3.title("발리애견호텔");
        markerOptions_hotel3.snippet("");
        markerOptions_hotel3.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel3);

        //애견호텔1번가
        LatLng hotel4 = new LatLng(36.6265496,127.4153137);
        MarkerOptions markerOptions_hotel4 = new MarkerOptions();
        markerOptions_hotel4.position(hotel4);
        markerOptions_hotel4.title("발리애견호텔");
        markerOptions_hotel4.snippet("");
        markerOptions_hotel4.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel4);

        //행강 애견호텔
        LatLng hotel5 = new LatLng(37.1430771,127.3881912);
        MarkerOptions markerOptions_hotel5 = new MarkerOptions();
        markerOptions_hotel5.position(hotel5);
        markerOptions_hotel5.title("행강애견호텔");
        markerOptions_hotel5.snippet("");
        markerOptions_hotel5.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel5);

        //평상캔넬
        LatLng hotel6 = new LatLng(37.0061169,127.0153427);
        MarkerOptions markerOptions_hotel6 = new MarkerOptions();
        markerOptions_hotel6.position(hotel6);
        markerOptions_hotel6.title("평상캔넬");
        markerOptions_hotel6.snippet("");
        markerOptions_hotel6.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel6);

        //도그파크
        LatLng hotel7 = new LatLng(37.3158405,127.1502686);
        MarkerOptions markerOptions_hotel7 = new MarkerOptions();
        markerOptions_hotel7.position(hotel7);
        markerOptions_hotel7.title("도그파크");
        markerOptions_hotel7.snippet("");
        markerOptions_hotel7.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel7);

        //리틀펫애견미용호텔
        LatLng hotel8 = new LatLng(37.5788683,127.2237396);
        MarkerOptions markerOptions_hotel8 = new MarkerOptions();
        markerOptions_hotel8.position(hotel8);
        markerOptions_hotel8.title("리틀펫애견미용호텔");
        markerOptions_hotel8.snippet("");
        markerOptions_hotel8.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel8);

        //펫38도씨
        LatLng hotel9 = new LatLng(37.4939284,126.5443039);
        MarkerOptions markerOptions_hotel9 = new MarkerOptions();
        markerOptions_hotel9.position(hotel9);
        markerOptions_hotel9.title("펫38도씨");
        markerOptions_hotel9.snippet("");
        markerOptions_hotel9.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel9);

        //펫앤펫
        LatLng hotel10 = new LatLng(37.4598708,126.6950226);
        MarkerOptions markerOptions_hotel10 = new MarkerOptions();
        markerOptions_hotel10.position(hotel10);
        markerOptions_hotel10.title("펫앤펫");
        markerOptions_hotel10.snippet("");
        markerOptions_hotel10.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel10);

        //바우벨리애견호텔
        LatLng hotel11 = new LatLng(37.3338595,126.8467712);
        MarkerOptions markerOptions_hotel11 = new MarkerOptions();
        markerOptions_hotel11.position(hotel11);
        markerOptions_hotel11.title("바우벨리애견호텔");
        markerOptions_hotel11.snippet("");
        markerOptions_hotel11.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel11);

        //애견호텔
        LatLng hotel12 = new LatLng(37.534777,127.0671844);
        MarkerOptions markerOptions_hotel12 = new MarkerOptions();
        markerOptions_hotel12.position(hotel12);
        markerOptions_hotel12.title("애견호텔");
        markerOptions_hotel12.snippet("");
        markerOptions_hotel12.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel12);

        //애견카페 애코독
        LatLng hotel13 = new LatLng(37.2483678,127.0458984);
        MarkerOptions markerOptions_hotel13 = new MarkerOptions();
        markerOptions_hotel13.position(hotel13);
        markerOptions_hotel13.title("애견카페 애코독");
        markerOptions_hotel13.snippet("");
        markerOptions_hotel13.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel13);

        //런투독 애견호텔
        LatLng hotel14 = new LatLng(37.3832528,126.9322586);
        MarkerOptions markerOptions_hotel14 = new MarkerOptions();
        markerOptions_hotel14.position(hotel14);
        markerOptions_hotel14.title("런투독 애견호텔");
        markerOptions_hotel14.snippet("");
        markerOptions_hotel14.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel14);

        //투퍼피
        LatLng hotel15 = new LatLng(37.4884802,126.9281387);
        MarkerOptions markerOptions_hotel15 = new MarkerOptions();
        markerOptions_hotel15.position(hotel15);
        markerOptions_hotel15.title("투퍼피");
        markerOptions_hotel15.snippet("");
        markerOptions_hotel15.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel15);

        //엘리애견호텔
        LatLng hotel16 = new LatLng(37.4999209,127.0428085);
        MarkerOptions markerOptions_hotel16 = new MarkerOptions();
        markerOptions_hotel16.position(hotel16);
        markerOptions_hotel16.title("엘리애견호텔");
        markerOptions_hotel16.snippet("");
        markerOptions_hotel16.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel16);

        //이든애견호텔미용
        LatLng hotel17 = new LatLng(37.5279704,126.8587875);
        MarkerOptions markerOptions_hotel17 = new MarkerOptions();
        markerOptions_hotel17.position(hotel17);
        markerOptions_hotel17.title("이든애견호텔미용");
        markerOptions_hotel17.snippet("");
        markerOptions_hotel17.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel17);

        //수애견호텔카페
        LatLng hotel18 = new LatLng(34.8835371,128.6256981);
        MarkerOptions markerOptions_hotel18 = new MarkerOptions();
        markerOptions_hotel18.position(hotel18);
        markerOptions_hotel18.title("수애견호텔카페");
        markerOptions_hotel18.snippet("");
        markerOptions_hotel18.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel18);

        //애견호텔전문점
        LatLng hotel19 = new LatLng(35.1213127,129.0981102);
        MarkerOptions markerOptions_hotel19 = new MarkerOptions();
        markerOptions_hotel19.position(hotel19);
        markerOptions_hotel19.title("애견호텔전문점");
        markerOptions_hotel19.snippet("");
        markerOptions_hotel19.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel19);

        //더펫텔
        LatLng hotel20 = new LatLng(35.159214,129.1532135);
        MarkerOptions markerOptions_hotel20 = new MarkerOptions();
        markerOptions_hotel20.position(hotel20);
        markerOptions_hotel20.title("더펫텔");
        markerOptions_hotel20.snippet("");
        markerOptions_hotel20.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel20);

        //깐돌이네애견미용호텔
        LatLng hotel21 = new LatLng(35.2294953,128.5747147);
        MarkerOptions markerOptions_hotel21 = new MarkerOptions();
        markerOptions_hotel21.position(hotel21);
        markerOptions_hotel21.title("깐돌이네애견미용호텔");
        markerOptions_hotel21.snippet("");
        markerOptions_hotel21.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel21);

        //땡구네애견분양카페호텔
        LatLng hotel22 = new LatLng(35.3365535,129.0275574);
        MarkerOptions markerOptions_hotel22 = new MarkerOptions();
        markerOptions_hotel22.position(hotel22);
        markerOptions_hotel22.title("땡구네애견분양카페호텔");
        markerOptions_hotel22.snippet("");
        markerOptions_hotel22.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel22);

        //스타독스 애견카페호텔
        LatLng hotel23 = new LatLng(35.386391,129.1480637);
        MarkerOptions markerOptions_hotel23 = new MarkerOptions();
        markerOptions_hotel23.position(hotel23);
        markerOptions_hotel23.title("스타독스 애견카페호텔");
        markerOptions_hotel23.snippet("");
        markerOptions_hotel23.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel23);

        //가정 애견호텔 유치원
        LatLng hotel24 = new LatLng(35.5394901,129.2944908);
        MarkerOptions markerOptions_hotel24 = new MarkerOptions();
        markerOptions_hotel24.position(hotel24);
        markerOptions_hotel24.title("가정 애견호텔 유치원");
        markerOptions_hotel24.snippet("");
        markerOptions_hotel24.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel24);

        //게스트하우스 애견호텔
        LatLng hotel25 = new LatLng(35.6394411,129.0594864);
        MarkerOptions markerOptions_hotel25 = new MarkerOptions();
        markerOptions_hotel25.position(hotel25);
        markerOptions_hotel25.title("게스트하우스 애견호텔");
        markerOptions_hotel25.snippet("");
        markerOptions_hotel25.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel25);

        //뚱이네 애견호텔 카페
        LatLng hotel26 = new LatLng(35.9584166,129.406414);
        MarkerOptions markerOptions_hotel26 = new MarkerOptions();
        markerOptions_hotel26.position(hotel26);
        markerOptions_hotel26.title("뚱이네 애견호텔 카페");
        markerOptions_hotel26.snippet("");
        markerOptions_hotel26.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel26);

        //복독방 애견카페&호텔
        LatLng hotel27 = new LatLng(35.827278,128.5584068);
        MarkerOptions markerOptions_hotel27 = new MarkerOptions();
        markerOptions_hotel27.position(hotel27);
        markerOptions_hotel27.title("복독방 애견카페&호텔");
        markerOptions_hotel27.snippet("");
        markerOptions_hotel27.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel27);

        //밀독애견호텔&카페
        LatLng hotel28 = new LatLng(35.9228374,128.6408043);
        MarkerOptions markerOptions_hotel28 = new MarkerOptions();
        markerOptions_hotel28.position(hotel28);
        markerOptions_hotel28.title("밀독애견호텔&카페");
        markerOptions_hotel28.snippet("");
        markerOptions_hotel28.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel28);

        //교감애견카페&호텔
        LatLng hotel29 = new LatLng(35.9803677,128.6342812);
        MarkerOptions markerOptions_hotel29 = new MarkerOptions();
        markerOptions_hotel29.position(hotel29);
        markerOptions_hotel29.title("교감애견카페&호텔");
        markerOptions_hotel29.snippet("");
        markerOptions_hotel29.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel29);

        //더펫하우스 협동조합
        LatLng hotel30 = new LatLng(35.1246824,126.8670273);
        MarkerOptions markerOptions_hotel30 = new MarkerOptions();
        markerOptions_hotel30.position(hotel30);
        markerOptions_hotel30.title("더펫하우스 협동조합");
        markerOptions_hotel30.snippet("");
        markerOptions_hotel30.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel30);

        //카누네 애견호텔
        LatLng hotel31 = new LatLng(36.0767134,126.9679642);
        MarkerOptions markerOptions_hotel31 = new MarkerOptions();
        markerOptions_hotel31.position(hotel31);
        markerOptions_hotel31.title("카누네 애견호텔");
        markerOptions_hotel31.snippet("");
        markerOptions_hotel31.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel31);

        //미미애견
        LatLng hotel32 = new LatLng(37.130624,128.2047844);
        MarkerOptions markerOptions_hotel32 = new MarkerOptions();
        markerOptions_hotel32.position(hotel32);
        markerOptions_hotel32.title("미미애견");
        markerOptions_hotel32.snippet("");
        markerOptions_hotel32.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel32);

        //강아지트
        LatLng hotel33 = new LatLng(36.9854145,127.9397392);
        MarkerOptions markerOptions_hotel33 = new MarkerOptions();
        markerOptions_hotel33.position(hotel33);
        markerOptions_hotel33.title("강아지트");
        markerOptions_hotel33.snippet("");
        markerOptions_hotel33.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel33);

        //도그하우스
        LatLng hotel34 = new LatLng(37.6934652,127.8996563);
        MarkerOptions markerOptions_hotel34 = new MarkerOptions();
        markerOptions_hotel34.position(hotel34);
        markerOptions_hotel34.title("도그하우스");
        markerOptions_hotel34.snippet("");
        markerOptions_hotel34.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel34);

        //애견호텔맥시
        LatLng hotel35 = new LatLng(37.8330393,127.7642155);
        MarkerOptions markerOptions_hotel35 = new MarkerOptions();
        markerOptions_hotel35.position(hotel35);
        markerOptions_hotel35.title("애견호텔맥시");
        markerOptions_hotel35.snippet("");
        markerOptions_hotel35.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel35);

        //몽몽
        LatLng hotel36 = new LatLng(37.7742427,128.9288521);
        MarkerOptions markerOptions_hotel36 = new MarkerOptions();
        markerOptions_hotel36.position(hotel36);
        markerOptions_hotel36.title("몽몽");
        markerOptions_hotel36.snippet("");
        markerOptions_hotel36.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel36);

        //도그빌리지
        LatLng hotel37 = new LatLng(36.7962957,127.1232319);
        MarkerOptions markerOptions_hotel37 = new MarkerOptions();
        markerOptions_hotel37.position(hotel37);
        markerOptions_hotel37.title("도그빌리지");
        markerOptions_hotel37.snippet("");
        markerOptions_hotel37.icon(BitmapDescriptorFactory.fromBitmap(hotel_smallMarker2));
        mMap.addMarker(markerOptions_hotel37);

    }


}
