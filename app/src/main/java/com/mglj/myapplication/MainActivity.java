package com.mglj.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
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
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Executors;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    int pet_number = 0;
    LinearLayout linear_main_bg;
    ImageView img_local, img_local2;
    //애드몹
    AdView adBanner;
    InterstitialAd adfull;


    //주간 날씨 부분
    TextView view4_text_address, view4_now_weather_text, view4_now_dust_text, view4_now_temp_text, view4_now_temp_maxmin_text, view4_now_temp_center_text, view4_now_temp_max_text;
    ImageView view4_img_main, view4_now_temp_img, view4_now_dust_img,view4_now_weather_img ;
    ImageView view4_today_img, view4_tomo_img, view4_tomo2_img, view4_tomo3_img, view4_tomo4_img;
    TextView view4_today_text1, view4_today_text2, view4_today_max, view4_today_min, view4_today_temp;
    TextView view4_tomo_text1, view4_tomo_text2, view4_tomo_max, view4_tomo_min, view4_tomo_temp;
    TextView view4_tomo2_text1, view4_tomo2_text2, view4_tomo2_max, view4_tomo2_min, view4_tomo2_temp;
    TextView view4_tomo3_text1, view4_tomo3_text2, view4_tomo3_max, view4_tomo3_min, view4_tomo3_temp;
    TextView view4_tomo4_text1, view4_tomo4_text2, view4_tomo4_max, view4_tomo4_min, view4_tomo4_temp;
    Boolean more_boolean=true;
    LinearLayout more_layout;
    TextView view4_more_text;
    String[] weekDay = { "dasda","일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ,"일요일" , "월요일", "화요일","수요일","목요일","금요일","토요일","일요일" };
    // 주간날씨 부분 끝
    int bo = 2;
    int boo = 2;

    int pet_status=1;

    ImageView nav_image1, pet_add0, pet_add1, pet_add2;
    String userID, userName;
    private List<Pet> petList;

    TextView nav_user_petName;

    public static int exit_status=0;

    // 뷰 페이저(광고)
    ViewPager vp;

    Thread thread = null;
    Handler handler = null;
    int p=0;	//페이지번호

    // 그래프 변수들
    private LineChart lineChart; // 사람 그래프
    private LineChart pet_lineChart; // 반려견 그래프
    EditText graph_test;
    Button graph_test_btn;
    private List<Pet_Graph> pet_graphList;
    ArrayList<Pet_Graph> pet_graphList_speed;
    ArrayList<Pet_Graph> pet_graphList_time;
    private List<Pet_Graph> user_pet_graphList;
    static String testmsg="0";

    int TODAY;

    TextView text_today_info0, text_today_info1, text_today_info2, text_today_info3, text_today_info4, text_today_info5, text_today_info6;
    TextView text_today_info_date0,text_today_info_date1,text_today_info_date2,text_today_info_date3,text_today_info_date4,text_today_info_date5,text_today_info_date6;
    TextView text_today_info_time0, text_today_info_time1,text_today_info_time2,text_today_info_time3,text_today_info_time4,text_today_info_time5,text_today_info_time6;
    ImageView img_today_info_date0,img_today_info_date1,img_today_info_date2,img_today_info_date3,img_today_info_date4,img_today_info_date5,img_today_info_date6;

    TextView graph_km, graph_time, graph_speed, graph_count;

    // 그래프 변수들 끝
    long backKeyPressedTime;    //앱종료 위한 백버튼 누른시간

    Button btn_pet_walking; // 산책하기 버튼

    CircleImageView pet_imgbtn, pet_img_talk;
    TextView pet_talk, text_count_walk, text_count_walk2; // 상단의 반려견 말과, 몇일째 산책중인지 나타내는 텍스트

    TabHost tabHost; // 탭호스트

    ImageView now_temp_img, now_dust_img, now_weather_img;
    TextView now_temp_text, now_dust_text, now_weather_text, now_temp_maxmin_text, now_temp_center_text, now_temp_min_text;

    // openweather 을 위한 url과 키
    private String url = "http://api.openweathermap.org";
    private String key = "1966fce57124a1e39ecef2d1eaeb7c0b";

    //미세먼지를 위한 url과 키
    private String dust_url = "http://openapi.airkorea.or.kr";
    private String dust_key = "FhIbqBYEHWDmIgHDufkWFiRccTmu8Lavedlo%2FnccvmChSLG1QY%2Bd0lQoyYX%2B55JyA34U75DrTvcUt5feJRZ9Ng%3D%3D";

    TextView user_login_id;

    //------------- 캘린더 변수 시작 --------------
    MaterialCalendarView materialCalendarView;
    TextView today_walk, text_click_date;
    EditText edit_today_walk;
    ImageView btn_today_add_walk;
    private List<User_Date> user_dateList;
    private List<User_Date> user_dateList2;
    ImageView custom_sche;
    ArrayList<User_Date_walk> user_walkList;
    TextView btn_delete_date1, btn_delete_date2, btn_delete_date3;
    LinearLayout date3_hide, date2_hide;

    static final int REQUEST_IMAGE_CAPTURE = 100;

    // 일정 변수
    String result[];
    HashMap<String,String> date_hash = new HashMap<String,String>(); // 일정을 담을 HashMap
    TextView today_walk_list, today_walk_list2, today_walk_list3;  // 오늘의 할일
    String click_date;
    //---------- 캘린더 변수 끝 ---------------

    //---------- 주간날씨 변수 ---------------
    TextView weather_content;
    String temp;
    String weather;
    String address;
    //---------- 주간 날씨 변수 끝 -----------

    // 펫톡
    TextView text_pet_talk;
    EditText edit_pet_talk;
    Button btn_pet_talk;
    String user_talk;
    int random;
    ArrayList<Talk_List> talk_lists;
    ArrayList<Talk_List> talk_lists_copy;
    ListView pet_talk_list;
    private talkListAdapter talk_adapter;
    private List<pet_TalkList> pet_talkLists;
    // 펫톡 끝
    private final int GET_GALLERY_IMAGE = 200;

    //GPS 변수들

    double latitude;
    double longitude;
    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    Button testads;

    //로그 확인을 위해
    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //애드몹
//        MobileAds.initialize(this,"ca-app-pub-5784293657699097/7951196301");

//        adBanner = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adBanner.loadAd(adRequest);

        adfull = new InterstitialAd(this);
        adfull.setAdUnitId("ca-app-pub-5784293657699097/1022927504");
        adfull.loadAd(new AdRequest.Builder().build());

//        adfull.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                // Load the next interstitial.
//                adfull.loadAd(new AdRequest.Builder().build());
//            }
//
//        });


        //변수들
        pet_status = 1;
        text_count_walk = (TextView)findViewById(R.id.text_count_walk);
        text_count_walk2 = (TextView)findViewById(R.id.text_count_walk2);
        pet_talk = (TextView)findViewById(R.id.pet_talk);
        new BackgroundTask_user_pet().execute();
        new BackgroundTask_graphList_speed().execute();
        new BackgroundTask_graphList_time().execute();
        more_layout = (LinearLayout)findViewById(R.id.more_layout);
        custom_sche = (ImageView)findViewById(R.id.custom_sche);
        edit_pet_talk = (EditText)findViewById(R.id.edit_pet_talk);
        btn_pet_talk = (Button)findViewById(R.id.btn_pet_talk);
        linear_main_bg = (LinearLayout)findViewById(R.id.linear_main_bg);
        img_local = (ImageView)findViewById(R.id.img_local);
        img_local2 = (ImageView)findViewById(R.id.img_local2);
        btn_delete_date1 = (TextView) findViewById(R.id.btn_delete_date1);
        btn_delete_date2 = (TextView) findViewById(R.id.btn_delete_date2);
        btn_delete_date3 = (TextView) findViewById(R.id.btn_delete_date3);
        date3_hide = (LinearLayout)findViewById(R.id.date3_hide);
        date2_hide = (LinearLayout)findViewById(R.id.date2_hide);

        //---------- 주간날씨 변수 ---------------

        view4_text_address = (TextView)findViewById(R.id.view4_text_address);
        weather_content = (TextView)findViewById(R.id.weather_content);
        view4_img_main = (ImageView)findViewById(R.id.view4_img_main);


        view4_now_temp_maxmin_text = (TextView)findViewById(R.id.view4_now_temp_maxmin_text);
        view4_now_temp_center_text = (TextView)findViewById(R.id.view4_now_temp_center_text);
        view4_now_temp_max_text = (TextView)findViewById(R.id.view4_now_temp_max_text);
        view4_now_temp_img = (ImageView)findViewById(R.id.view4_now_temp_img);
        view4_now_dust_img = (ImageView)findViewById(R.id.view4_now_dust_img);
        view4_now_weather_img = (ImageView)findViewById(R.id.view4_now_weather_img);
        view4_now_weather_text = (TextView)findViewById(R.id.view4_now_weather_text);
        view4_now_dust_text = (TextView)findViewById(R.id.view4_now_dust_text);
        view4_now_temp_text = (TextView)findViewById(R.id.view4_now_temp_text);
        view4_today_img = (ImageView)findViewById(R.id.view4_today_img);
        view4_today_text1 = (TextView)findViewById(R.id.view4_today_text1);
        view4_today_text2 = (TextView)findViewById(R.id.view4_today_text2);
        view4_today_max = (TextView)findViewById(R.id.view4_today_max);
        view4_today_min = (TextView)findViewById(R.id.view4_today_min);
        view4_today_temp = (TextView)findViewById(R.id.view4_today_temp);
        view4_tomo_img = (ImageView)findViewById(R.id.view4_tomo_img);
        view4_tomo_text1 = (TextView)findViewById(R.id.view4_tomo_text1);
        view4_tomo_text2 = (TextView)findViewById(R.id.view4_tomo_text2);
        view4_tomo_max = (TextView)findViewById(R.id.view4_tomo_max);
        view4_tomo_min = (TextView)findViewById(R.id.view4_tomo_min);
        view4_tomo_temp = (TextView)findViewById(R.id.view4_tomo_temp);
        view4_more_text = (TextView)findViewById(R.id.view4_more_text);
        view4_tomo2_img = (ImageView)findViewById(R.id.view4_tomo2_img);
        view4_tomo2_text1 = (TextView)findViewById(R.id.view4_tomo2_text1);
        view4_tomo2_text2 = (TextView)findViewById(R.id.view4_tomo2_text2);
        view4_tomo2_max = (TextView)findViewById(R.id.view4_tomo2_max);
        view4_tomo2_min = (TextView)findViewById(R.id.view4_tomo2_min);
        view4_tomo2_temp = (TextView)findViewById(R.id.view4_tomo2_temp);

        view4_tomo3_img = (ImageView)findViewById(R.id.view4_tomo3_img);
        view4_tomo3_text1 = (TextView)findViewById(R.id.view4_tomo3_text1);
        view4_tomo3_text2 = (TextView)findViewById(R.id.view4_tomo3_text2);
        view4_tomo3_max = (TextView)findViewById(R.id.view4_tomo3_max);
        view4_tomo3_min = (TextView)findViewById(R.id.view4_tomo3_min);
        view4_tomo3_temp = (TextView)findViewById(R.id.view4_tomo3_temp);

        view4_tomo4_img = (ImageView)findViewById(R.id.view4_tomo4_img);
        view4_tomo4_text1 = (TextView)findViewById(R.id.view4_tomo4_text1);
        view4_tomo4_text2 = (TextView)findViewById(R.id.view4_tomo4_text2);
        view4_tomo4_max = (TextView)findViewById(R.id.view4_tomo4_max);
        view4_tomo4_min = (TextView)findViewById(R.id.view4_tomo4_min);
        view4_tomo4_temp = (TextView)findViewById(R.id.view4_tomo4_temp);

        //---------- 주간 날씨 변수 끝 -----------

        today_walk_list = (TextView)findViewById(R.id.today_walk_list);
        today_walk_list2 = (TextView)findViewById(R.id.today_walk_list2);
        today_walk_list3 = (TextView)findViewById(R.id.today_walk_list3);
        final Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userName = intent.getStringExtra("userName");

        // 산책하기 버튼 클릭 이벤트

        btn_pet_walking = (Button)findViewById(R.id.btn_pet_walking);
        btn_pet_walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_pet = new Intent(getApplicationContext(),PetWalking.class);
                intent_pet.putExtra("userID", userID);

                intent_pet.putExtra("pet",pet_status);
                intent_pet.putExtra("latitude",latitude);
                intent_pet.putExtra("longitude" ,longitude);
                MainActivity.this.startActivity(intent_pet);
            }
        });

        //------------------------ gps 시작

        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {

            checkRunTimePermission();
        }
        final TextView location_here = (TextView)findViewById(R.id.location_here);

        gpsTracker = new GpsTracker(MainActivity.this);

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        double week_latitude = gpsTracker.getLatitude();
        double week_longitude = gpsTracker.getLongitude();

        address = getCurrentAddress(latitude, longitude);


//        location_here.setText(address.substring(5,address.indexOf(" ", 13)));
        if(address.equals("주소 미발견")){
            location_here.setText(address);
        }
        else{
            location_here.setText(address.substring(5,address.indexOf(" ", 13)));
        }

        //----------------- gps 끝 --------------------

        // 그래프 시작
        new BackgroundTask_graph().execute();
        new user_BackgroundTask_graph().execute();

        graph_test = (EditText)findViewById(R.id.graph_test);
        graph_test_btn = (Button)findViewById(R.id.graph_test_btn);

        lineChart = (LineChart)findViewById(R.id.walk_chart);

        // --------------------------펫 그래프 시작------------------------------

        pet_lineChart = (LineChart)findViewById(R.id.pet_walk_chart);
        text_today_info0 = (TextView)findViewById(R.id.text_today_info0);
        text_today_info1 = (TextView)findViewById(R.id.text_today_info1);
        text_today_info2 = (TextView)findViewById(R.id.text_today_info2);
        text_today_info3 = (TextView)findViewById(R.id.text_today_info3);
        text_today_info4 = (TextView)findViewById(R.id.text_today_info4);
        text_today_info5 = (TextView)findViewById(R.id.text_today_info5);
        text_today_info6 = (TextView)findViewById(R.id.text_today_info6);

        text_today_info_date0 = (TextView)findViewById(R.id.text_today_info_date0);
        text_today_info_date1 = (TextView)findViewById(R.id.text_today_info_date1);
        text_today_info_date2 = (TextView)findViewById(R.id.text_today_info_date2);
        text_today_info_date3 = (TextView)findViewById(R.id.text_today_info_date3);
        text_today_info_date4 = (TextView)findViewById(R.id.text_today_info_date4);
        text_today_info_date5 = (TextView)findViewById(R.id.text_today_info_date5);
        text_today_info_date6 = (TextView)findViewById(R.id.text_today_info_date6);

        text_today_info_time0 = (TextView)findViewById(R.id.text_today_info_time0);
        text_today_info_time1 = (TextView)findViewById(R.id.text_today_info_time1);
        text_today_info_time2 = (TextView)findViewById(R.id.text_today_info_time2);
        text_today_info_time3 = (TextView)findViewById(R.id.text_today_info_time3);
        text_today_info_time4 = (TextView)findViewById(R.id.text_today_info_time4);
        text_today_info_time5 = (TextView)findViewById(R.id.text_today_info_time5);
        text_today_info_time6 = (TextView)findViewById(R.id.text_today_info_time6);

        img_today_info_date0 = (ImageView)findViewById(R.id.img_today_info_date0);
        img_today_info_date1 = (ImageView)findViewById(R.id.img_today_info_date1);
        img_today_info_date2 = (ImageView)findViewById(R.id.img_today_info_date2);
        img_today_info_date3 = (ImageView)findViewById(R.id.img_today_info_date3);
        img_today_info_date4 = (ImageView)findViewById(R.id.img_today_info_date4);
        img_today_info_date5 = (ImageView)findViewById(R.id.img_today_info_date5);
        img_today_info_date6 = (ImageView)findViewById(R.id.img_today_info_date6);

        Glide.with(this).load(R.drawable.foot).fitCenter().into(img_today_info_date0);
        Glide.with(this).load(R.drawable.foot).fitCenter().into(img_today_info_date1);
        Glide.with(this).load(R.drawable.foot).fitCenter().into(img_today_info_date2);
        Glide.with(this).load(R.drawable.foot).fitCenter().into(img_today_info_date3);
        Glide.with(this).load(R.drawable.foot).fitCenter().into(img_today_info_date4);
        Glide.with(this).load(R.drawable.foot).fitCenter().into(img_today_info_date5);
        Glide.with(this).load(R.drawable.foot).fitCenter().into(img_today_info_date6);


        graph_km = (TextView)findViewById(R.id.graph_km);
        graph_time = (TextView)findViewById(R.id.graph_time);
        graph_speed = (TextView)findViewById(R.id.graph_speed);
        graph_count = (TextView)findViewById(R.id.graph_count);

        // ---------------------------펫 그래프 끝------------------------------

        //----------------- 일일 날씨 시작 -----------------

        now_temp_text = (TextView)findViewById(R.id.now_temp_text);
        now_dust_text = (TextView)findViewById(R.id.now_dust_text);
        now_weather_text = (TextView)findViewById(R.id.now_weather_text);
        now_temp_maxmin_text = (TextView)findViewById(R.id.now_temp_maxmin_text);
        now_temp_center_text = (TextView)findViewById(R.id.now_temp_center_text);
        now_temp_min_text = (TextView)findViewById(R.id.now_temp_min_text);
        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit client = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        Weather_Interface interFace = client.create(Weather_Interface.class);

        //Call
        Call<Repo> call = interFace.get_weather(key,Double.valueOf(latitude), Double.valueOf(longitude));
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                if(response.isSuccessful()){
                    Repo repo = response.body();

                    temp=  Math.round(((repo.getMain().getTemp()-273.15)*10)/10.0)+"º";
                    weather = repo.getWeather().get(0).getMain();
                    String tempMax = Math.round(((repo.getMain().getTemp_max()-273.15)*10)/10.0)+"";
                    String tempMin = Math.round(((repo.getMain().getTemp_min()-273.15)*10)/10.0)+"";
                    now_temp_text.setText(temp);

                    now_temp_maxmin_text.setText("최저 "+tempMin+"º");
                    now_temp_center_text.setText(" / ");
                    now_temp_min_text.setText("최고 "+tempMax+"º");

                    view4_now_temp_maxmin_text.setText("최저 "+tempMin+"º");
                    view4_now_temp_center_text.setText(" / ");
                    view4_now_temp_max_text.setText("최고 "+tempMax+"º");


                    if(repo.getWeather().get(0).getMain().equals("Clouds")){
                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(now_temp_img);
                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(view4_now_temp_img);

                    }
                    else if(repo.getWeather().get(0).getMain().equals("Clear")){
                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(now_temp_img);
                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(view4_now_temp_img);
                    }
                    else if(repo.getWeather().get(0).getMain().equals("Rain")){
                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(now_temp_img);
                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(view4_now_temp_img);
                    }
                    else if(repo.getWeather().get(0).getMain().equals("Snow")){
                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(now_temp_img);
                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(view4_now_temp_img);
                    }
                    else if(repo.getWeather().get(0).getMain().equals("Mist")){
                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(now_temp_img);
                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(view4_now_temp_img);
                    }
                    else if(repo.getWeather().get(0).getMain().equals("Haze")){
                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(now_temp_img);
                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(view4_now_temp_img);
                    }
                    else if(repo.getWeather().get(0).getMain().equals("Fog")){
                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(now_temp_img);
                        Glide.with(MainActivity.this).load(R.drawable.fog).fitCenter().into(view4_now_temp_img);
                    }

                    view4_now_temp_text.setText(temp);

                    // nofication 알람
                    nofication_alram();


                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"일일 날씨 에러",Toast.LENGTH_SHORT).show();
            }
        });

        //------------------ 날씨 끝 ---------------------

        //--------------- 주간날씨 시작 ----------------

        if(address.equals("주소 미발견")){
            view4_text_address.setText(address);
        }
        else{
            view4_text_address.setText(address.substring(5,address.indexOf(" ", 13)));
        }

        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit Week_client = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        Week_Weather_Interface Week_interFace = Week_client.create(Week_Weather_Interface.class);
        //Call
        Call<WeekRepo> Week_call = Week_interFace.get_weather(key,Double.valueOf(latitude), Double.valueOf(longitude));
        //Call
        Week_call.enqueue(new Callback<WeekRepo>() {
            @Override
            public void onResponse(Call<WeekRepo> Week_call, Response<WeekRepo> response) {
                if(response.isSuccessful()){
                    WeekRepo weekrepo = response.body();
                    // 주간날씨
                    weather_content.setText("일 자 : " +weekrepo.getList().get(0).getDt_txt().substring(0,10)
                            +"\n날 씨 : " +weekrepo.getList().get(0).getWeather().get(0).getDescription()
                            +"\n온 도 : " +(Math.round(weekrepo.getList().get(0).getMain().getTemp()- 273.15)*10)/10.0

                            +"\n일 자 : " +weekrepo.getList().get(6).getDt_txt().substring(0,10)
                            +"\n날 씨 : " +weekrepo.getList().get(6).getWeather().get(0).getDescription()
                            +"\n온 도 : " +(Math.round(weekrepo.getList().get(6).getMain().getTemp()- 273.15)*10)/10.0

                            +"\n일 자 : " +weekrepo.getList().get(12).getDt_txt().substring(0,10)
                            +"\n날 씨 : " +weekrepo.getList().get(12).getWeather().get(0).getDescription()
                            +"\n온 도 : " +(Math.round(weekrepo.getList().get(12).getMain().getTemp()- 273.15)*10)/10.0

                            +"\n일 자 : " +weekrepo.getList().get(20).getDt_txt().substring(0,10)
                            +"\n날 씨 : " +weekrepo.getList().get(20).getWeather().get(0).getDescription()
                            +"\n온 도 : " +(Math.round(weekrepo.getList().get(20).getMain().getTemp()- 273.15)*10)/10.0

                            +"\n일 자 : " +weekrepo.getList().get(28).getDt_txt().substring(0,10)
                            +"\n날 씨 : " +weekrepo.getList().get(28).getWeather().get(0).getDescription()
                            +"\n온 도 : " +(Math.round(weekrepo.getList().get(28).getMain().getTemp()- 273.15)*10)/10.0

                            +"\n일 자 : " +weekrepo.getList().get(36).getDt_txt().substring(0,10)
                            +"\n날 씨 : " +weekrepo.getList().get(36).getWeather().get(0).getDescription()
                            +"\n온 도 : " +(Math.round(weekrepo.getList().get(36).getMain().getTemp()- 273.15)*10)/10.0);

                    Calendar cal = Calendar.getInstance();
                    int num = cal.get(Calendar.DAY_OF_WEEK);
                    String today = weekDay[num];
                    int num2 = cal.get(Calendar.DAY_OF_WEEK)+1;
                    String today2 = weekDay[num2];
                    int num3 = cal.get(Calendar.DAY_OF_WEEK)+2;
                    String today3 = weekDay[num3];
                    int num4 = cal.get(Calendar.DAY_OF_WEEK)+3;
                    String today4 = weekDay[num4];
                    int num5 = cal.get(Calendar.DAY_OF_WEEK)+4;
                    String today5 = weekDay[num5];
                    int num6 = cal.get(Calendar.DAY_OF_WEEK)+5;
                    String today6 = weekDay[num6];

//                    Math.round(((repo.getMain().getTemp()-273.15)*10)/10.0)+"º";

                    view4_today_text1.setText(today2);
                    view4_today_text2.setText(weekrepo.getList().get(4).getDt_txt().substring(5,10));
                    view4_today_temp.setText(String.valueOf((Math.round(weekrepo.getList().get(4).getMain().getTemp()- 273.15)*10)/10)+"º");
                    view4_today_max.setText(String.valueOf((weekrepo.getList().get(4).getMain().getHumidity()))+"%");
                    view4_today_min.setText(String.valueOf((weekrepo.getList().get(4).getWind().getWind_speed()))+"m/s");

                    view4_tomo_text1.setText(today3);
                    view4_tomo_text2.setText(weekrepo.getList().get(12).getDt_txt().substring(5,10));
                    view4_tomo_temp.setText(String.valueOf((Math.round(weekrepo.getList().get(12).getMain().getTemp()- 273.15)*10)/10)+"º");
                    view4_tomo_max.setText(String.valueOf((weekrepo.getList().get(12).getMain().getHumidity()))+"%");
                    view4_tomo_min.setText(String.valueOf((weekrepo.getList().get(12).getWind().getWind_speed()))+"m/s");

                    view4_tomo2_text1.setText(today4);
                    view4_tomo2_text2.setText(weekrepo.getList().get(20).getDt_txt().substring(5,10));
                    view4_tomo2_temp.setText(String.valueOf((Math.round(weekrepo.getList().get(20).getMain().getTemp()- 273.15)*10)/10)+"º");
                    view4_tomo2_max.setText(String.valueOf((weekrepo.getList().get(20).getMain().getHumidity()))+"%");
                    view4_tomo2_min.setText(String.valueOf((weekrepo.getList().get(20).getWind().getWind_speed()))+"m/s");

                    view4_tomo3_text1.setText(today5);
                    view4_tomo3_text2.setText(weekrepo.getList().get(28).getDt_txt().substring(5,10));
                    view4_tomo3_temp.setText(String.valueOf((Math.round(weekrepo.getList().get(28).getMain().getTemp()- 273.15)*10)/10)+"º");
                    view4_tomo3_max.setText(String.valueOf((weekrepo.getList().get(28).getMain().getHumidity()))+"%");
                    view4_tomo3_min.setText(String.valueOf((weekrepo.getList().get(28).getWind().getWind_speed()))+"m/s");

                    view4_tomo4_text1.setText(today6);
                    view4_tomo4_text2.setText(weekrepo.getList().get(36).getDt_txt().substring(5,10));
                    view4_tomo4_temp.setText(String.valueOf((Math.round(weekrepo.getList().get(36).getMain().getTemp()- 273.15)*10)/10)+"º");
                    view4_tomo4_max.setText(String.valueOf((weekrepo.getList().get(36).getMain().getHumidity()))+"%");
                    view4_tomo4_min.setText(String.valueOf((weekrepo.getList().get(36).getWind().getWind_speed()))+"m/s");

                    if(weekrepo.getList().get(6).getWeather().get(0).getMain().equals("Clouds")){
                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(view4_today_img);
                    }
                    else if(weekrepo.getList().get(6).getWeather().get(0).getMain().equals("Clear")){
                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(view4_today_img);
                    }
                    else if(weekrepo.getList().get(6).getWeather().get(0).getMain().equals("Rain")){
                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(view4_today_img);
                    }
                    else if(weekrepo.getList().get(6).getWeather().get(0).getMain().equals("Snow")){
                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(view4_today_img);
                    }

                    if(weekrepo.getList().get(12).getWeather().get(0).getMain().equals("Clouds")){
                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(view4_tomo_img);
                    }
                    else if(weekrepo.getList().get(12).getWeather().get(0).getMain().equals("Clear")){
                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(view4_tomo_img);
                    }
                    else if(weekrepo.getList().get(12).getWeather().get(0).getMain().equals("Rain")){
                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(view4_tomo_img);
                    }
                    else if(weekrepo.getList().get(12).getWeather().get(0).getMain().equals("Snow")){
                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(view4_tomo_img);
                    }

                    if(weekrepo.getList().get(20).getWeather().get(0).getMain().equals("Clouds")){
                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(view4_tomo2_img);
                    }
                    else if(weekrepo.getList().get(20).getWeather().get(0).getMain().equals("Clear")){
                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(view4_tomo2_img);
                    }
                    else if(weekrepo.getList().get(20).getWeather().get(0).getMain().equals("Rain")){
                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(view4_tomo2_img);
                    }
                    else if(weekrepo.getList().get(20).getWeather().get(0).getMain().equals("Snow")){
                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(view4_tomo2_img);
                    }

                    if(weekrepo.getList().get(28).getWeather().get(0).getMain().equals("Clouds")){
                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(view4_tomo3_img);
                    }
                    else if(weekrepo.getList().get(28).getWeather().get(0).getMain().equals("Clear")){
                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(view4_tomo3_img);
                    }
                    else if(weekrepo.getList().get(28).getWeather().get(0).getMain().equals("Rain")){
                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(view4_tomo3_img);
                    }
                    else if(weekrepo.getList().get(28).getWeather().get(0).getMain().equals("Snow")){
                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(view4_tomo3_img);
                    }

                    if(weekrepo.getList().get(36).getWeather().get(0).getMain().equals("Clouds")){
                        Glide.with(MainActivity.this).load(R.drawable.cloud).fitCenter().into(view4_tomo4_img);
                    }
                    else if(weekrepo.getList().get(36).getWeather().get(0).getMain().equals("Clear")){
                        Glide.with(MainActivity.this).load(R.drawable.sun).fitCenter().into(view4_tomo4_img);
                    }
                    else if(weekrepo.getList().get(36).getWeather().get(0).getMain().equals("Rain")){
                        Glide.with(MainActivity.this).load(R.drawable.rain).fitCenter().into(view4_tomo4_img);
                    }
                    else if(weekrepo.getList().get(36).getWeather().get(0).getMain().equals("Snow")){
                        Glide.with(MainActivity.this).load(R.drawable.snowing).fitCenter().into(view4_tomo4_img);
                    }

                }
            }

            @Override
            public void onFailure(Call<WeekRepo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"주간날씨 에러",Toast.LENGTH_SHORT).show();
            }
        });
        view4_more_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(more_boolean==true) {
                    more_layout.setVisibility(View.VISIBLE);
                    more_boolean=false;
                }
                else {
                    more_layout.setVisibility(View.GONE);
                    more_boolean=true;
                }
            }
        });

        //------------------ 주간날씨 끝 ---------------------


        //------------------ 미세먼지 시작 ---------------------

        int numOfRows = 5;
        int pageNo = 1;
        String itemCode = "PM10";
        String dataGubun = "HOUR";
        String searchCondition = "MONTH";
        String _returnType ="JSON";

        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit dust_client = new Retrofit.Builder().baseUrl(dust_url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        find_dust_Interface dust_interFace = dust_client.create(find_dust_Interface.class);

        //Call
        Call<Find_dust_Repo> dust_call = dust_interFace.get_weather(dust_key,numOfRows, pageNo, itemCode,dataGubun, searchCondition, _returnType);

        dust_call.enqueue(new Callback<Find_dust_Repo>() {
            @Override
            public void onResponse(Call<Find_dust_Repo> call, Response<Find_dust_Repo> response) {
                if(response.isSuccessful()){
                    Find_dust_Repo dust_repo = response.body();
                    // 미세면지 표시
                    if(address.contains("경기")){
                        if(dust_repo.getList().get(0).getGyeonggi()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getGyeonggi()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }

                    }
                    else if(address.contains("서울")){
                        if(dust_repo.getList().get(0).getSeoul()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getSeoul()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("부산")){
                        if(dust_repo.getList().get(0).getBusan()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getBusan()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("대구")){
                        if(dust_repo.getList().get(0).getDaegu()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getDaegu()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("인천")){
                        if(dust_repo.getList().get(0).getIncheon()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getIncheon()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }

                    else if(address.contains("광주")){
                        if(dust_repo.getList().get(0).getGwangju()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getGwangju()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("대전")){
                        if(dust_repo.getList().get(0).getDaejeon()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getDaejeon()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("울산")){
                        if(dust_repo.getList().get(0).getUlsan()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getUlsan()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("강원")){
                        if(dust_repo.getList().get(0).getGangwon()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getGangwon()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("충청북도")){
                        if(dust_repo.getList().get(0).getChungbuk()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getChungbuk()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("충청남도")){
                        if(dust_repo.getList().get(0).getChungnam()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getChungnam()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("전라북도")){
                        if(dust_repo.getList().get(0).getJeonbuk()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getJeonbuk()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("전라남도")){
                        if(dust_repo.getList().get(0).getJeonnam()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getJeonnam()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("경상북도")){
                        if(dust_repo.getList().get(0).getGyeongbuk()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getGyeongbuk()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("경상남도")){
                        if(dust_repo.getList().get(0).getGyeongnam()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getGyeongnam()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("제주")){
                        if(dust_repo.getList().get(0).getJeju()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getJeju()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else if(address.contains("세종")){
                        if(dust_repo.getList().get(0).getSejong()<=30){
                            now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("좋음\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_dust_img);
                        }
                        else if(dust_repo.getList().get(0).getSejong()<=80){
                            now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("보통\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_dust_img);
                        }
                        else{
                            now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                            view4_now_dust_text.setText("나쁨\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                        }
                    }
                    else{
                        now_dust_text.setText("");
                        view4_now_dust_text.setText("");
                    }


                }
            }

            @Override
            public void onFailure(Call<Find_dust_Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
                Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_dust_img);
                Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_dust_img);
                now_dust_text.setText("실패");
                view4_now_dust_text.setText("실패");
            }
        });

        //------------------ 미세먼지 끝 ---------------------

        //------------------ 초미세먼지 시작 ---------------------

        int C_numOfRows = 5;
        int C_pageNo = 1;
        String C_itemCode = "PM25";
        String C_dataGubun = "HOUR";
        String C_searchCondition = "MONTH";
        String C_returnType ="JSON";

        //서버의 json 응답을 간단하게 변환하도록 해주는 작업
        Retrofit C_dust_client = new Retrofit.Builder().baseUrl(dust_url).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build();
        //인터페이스
        find_dust_Interface C_dust_interFace = C_dust_client.create(find_dust_Interface.class);

        //Call
        Call<Find_dust_Repo> C_dust_call = C_dust_interFace.get_weather(dust_key,C_numOfRows, C_pageNo, C_itemCode,C_dataGubun, C_searchCondition, C_returnType);

        C_dust_call.enqueue(new Callback<Find_dust_Repo>() {
            @Override
            public void onResponse(Call<Find_dust_Repo> call, Response<Find_dust_Repo> response) {
                if(response.isSuccessful()){
                    Find_dust_Repo dust_repo = response.body();
                    // 미세면지 표시
                    if(address.contains("경기")){


                        if(dust_repo.getList().get(0).getGyeonggi()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getGyeonggi()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeonggi()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }


                    }
                    else if(address.contains("서울")){

                        if(dust_repo.getList().get(0).getSeoul()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getSeoul()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getSeoul()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }

                    }
                    else if(address.contains("부산")){

                        if(dust_repo.getList().get(0).getBusan()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getBusan()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getBusan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }


                    }
                    else if(address.contains("대구")){

                        if(dust_repo.getList().get(0).getDaegu()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getDaegu()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaegu()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }

                    }
                    else if(address.contains("인천")){

                        if(dust_repo.getList().get(0).getIncheon()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getIncheon()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getIncheon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }

                    }
                    else if(address.contains("광주")){

                        if(dust_repo.getList().get(0).getGwangju()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getGwangju()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGwangju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }

                    }
                    else if(address.contains("대전")){
                        if(dust_repo.getList().get(0).getDaejeon()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getDaejeon()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getDaejeon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }

                    }
                    else if(address.contains("울산")){
                        if(dust_repo.getList().get(0).getUlsan()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getUlsan()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getUlsan()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }

                    }
                    else if(address.contains("강원")){
                        if(dust_repo.getList().get(0).getGangwon()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getGangwon()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGangwon()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("충청북도")){
                        if(dust_repo.getList().get(0).getChungbuk()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getChungbuk()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("충청남도")){
                        if(dust_repo.getList().get(0).getChungnam()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getChungbuk()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getChungnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("전라북도")){
                        if(dust_repo.getList().get(0).getJeonbuk()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getJeonbuk()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("전라남도")){
                        if(dust_repo.getList().get(0).getJeonnam()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getJeonnam()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeonnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("경상북도")){
                        if(dust_repo.getList().get(0).getGyeongbuk()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getGyeongbuk()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);

                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongbuk()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("경상남도")){
                        if(dust_repo.getList().get(0).getGyeongnam()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getGyeongnam()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getGyeongnam()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("제주")){
                        if(dust_repo.getList().get(0).getJeju()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getJeju()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);
                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getJeju()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else if(address.contains("세종")){
                        if(dust_repo.getList().get(0).getSejong()<=15){
                            now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("좋음\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.good).fitCenter().into(view4_now_weather_img);
                        }
                        else if(dust_repo.getList().get(0).getSejong()<=35){
                            now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("보통\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.nomal3).fitCenter().into(view4_now_weather_img);

                        }
                        else{
                            now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                            view4_now_weather_text.setText("나쁨\n("+dust_repo.getList().get(0).getSejong()+")");
                            Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
                        }
                    }
                    else{
                        now_weather_text.setText("");
                    }


                }
            }

            @Override
            public void onFailure(Call<Find_dust_Repo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
                now_weather_text.setText("실패");
                view4_now_weather_text.setText("실패");
                Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(now_weather_img);
                Glide.with(MainActivity.this).load(R.drawable.bad).fitCenter().into(view4_now_weather_img);
            }
        });

        //------------------ 초미세먼지 끝 ---------------------

        //--------------------- 달력 시작 --------------------

        today_walk = (TextView)findViewById(R.id.today_walk);
        btn_today_add_walk = (ImageView) findViewById(R.id.btn_today_add_walk);
        edit_today_walk = (EditText)findViewById(R.id.edit_today_walk);
        text_click_date = (TextView)findViewById(R.id.text_click_date);

        Glide.with(this).load(R.drawable.check_date).fitCenter().into(btn_today_add_walk);
        Glide.with(this).load(R.drawable.add_date).fitCenter().into(custom_sche);

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar_main);

        custom_sche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int banner = (int)(Math.random()*4)+1;

                if(text_click_date.getText().toString().equals("날짜를 선택해주세요")){

                    Toast.makeText(getApplicationContext(),"날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(banner==2||banner==3){

                        if(adfull.isLoaded()){
                            adfull.show();
                        };

                    }
                    else {
                        // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                        CustomDialog customDialog = new CustomDialog(MainActivity.this);

                        // 커스텀 다이얼로그를 호출한다.
                        // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                        customDialog.callFunction(today_walk);
                    }
                }

            }
        });

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2018, 0, 1))
                .setMaximumDate(CalendarDay.from(2022, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());

        // 달력 초기화
        new BackgroundTask_date().execute();
        new BackgroundTask_date_walk().execute();

        // 달력 클릭이벤트
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                custom_sche.setVisibility(View.VISIBLE);
                int Year = date.getYear();
                int Month = date.getMonth()+1;
                int Day = date.getDay();

                // Toast화면에 띄울 메시지
                final String shot_Day = Year + "년" + Month + "월" + Day + "일";
                text_click_date.setText(shot_Day);

                final String today = Year +","+Month+","+Day;
                click_date = today;

                new BackgroundTask_date_btn().execute();

//                materialCalendarView.clearSelection();

                // 추가 버튼 클릭시 이벤트
                btn_today_add_walk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(today_walk.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "일정을 입력하세요", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(user_dateList2.size()>=3){
                            Toast.makeText(getApplicationContext(), "일정은 3개까지 등록가능합니다", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        date_db_add(today);

                    }
                });
            }
        });

        //--------------------- 달력 끝 ----------------------


        //--------------------- 펫톡 시작 -------------------

        pet_talk_list = (ListView)findViewById(R.id.pet_talk_list);
        pet_talkLists = new ArrayList<pet_TalkList>();
        talk_adapter = new talkListAdapter(getApplicationContext(), pet_talkLists );
        pet_talk_list.setAdapter(talk_adapter);

        new BackgroundTask_pet_talk().execute();

        random_talk();




        btn_pet_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사용자의 말

                SimpleDateFormat format1 = new SimpleDateFormat ( "HH:mm");

                Calendar time = Calendar.getInstance();

                String format_time1 = format1.format(time.getTime());

                user_talk = edit_pet_talk.getText().toString();

                pet_talkLists.add(new pet_TalkList(user_talk,"user",format_time1));
                talk_adapter.notifyDataSetChanged();

                if(user_talk.equals("")){

                    pet_talkLists.add(new pet_TalkList("무슨말인지 모르겠어요","pet",format_time1));
                    talk_adapter.notifyDataSetChanged();
                    return;

                }

                if(user_talk.length()==1){

                    pet_talkLists.add(new pet_TalkList("좀 더 입력해야 알아들을 수 있을 것 같아요","pet",format_time1));
                    talk_adapter.notifyDataSetChanged();
                    return;

                }

                if (user_talk.contains("날씨")) {

                    pet_talkLists.add(new pet_TalkList("오늘 날씨는 "+now_temp_text.getText().toString()+"도 입니다","pet",format_time1));
                    talk_adapter.notifyDataSetChanged();
                    return;

                }



                talk_lists_copy = new ArrayList<Talk_List>();

                String talk_split[] = user_talk.split(" ");

                int split_size = talk_split.length;

                if(split_size == 1 ){

                    for (int i = 0; i < talk_lists.size(); i++) {

                        for (int j = 0; j < talk_split.length; j++) {

                            if (talk_lists.get(i).getTalkTITLE().contains(talk_split[j])) {

                                Talk_List talk_list = new Talk_List(talk_lists.get(i).getTalkNUM(), talk_lists.get(i).getTalkTITLE()
                                        , talk_lists.get(i).getTalkTITLE2(), talk_lists.get(i).getTalkTITLE3(), talk_lists.get(i).getTalkCONTENT());
                                talk_lists_copy.add(talk_list);

                            }
                        }
                    }
                }
                else if (split_size == 2 ) {
                    for (int i = 0; i < talk_lists.size(); i++) {

                        for (int j = 0; j < talk_split.length; j++) {

                            if (talk_lists.get(i).getTalkTITLE().contains(talk_split[j])) {

                                for (int k = 0; k < talk_split.length; k++) {

                                    if (talk_lists.get(i).getTalkTITLE2().contains(talk_split[k])) {

                                        Talk_List talk_list = new Talk_List(talk_lists.get(i).getTalkNUM(), talk_lists.get(i).getTalkTITLE()
                                                , talk_lists.get(i).getTalkTITLE2(), talk_lists.get(i).getTalkTITLE3(), talk_lists.get(i).getTalkCONTENT());
                                        talk_lists_copy.add(talk_list);

                                    }
                                }
                            }
                        }
                    }
                }

                else if(split_size == 3){

                    for (int i = 0; i < talk_lists.size(); i++) {

                        for (int j = 0; j < talk_split.length; j++) {

                            if (talk_lists.get(i).getTalkTITLE().contains(talk_split[j])) {

                                for (int k = 0; k < talk_split.length; k++) {

                                    if (talk_lists.get(i).getTalkTITLE2().contains(talk_split[k])) {

                                        for(int w = 0; w<talk_split.length; w++) {

                                            if(talk_lists.get(i).getTalkTITLE3().contains(talk_split[w])&&talk_lists.get(i).getTalkTITLE2().contains(talk_split[w-1])) {
                                                Talk_List talk_list = new Talk_List(talk_lists.get(i).getTalkNUM(), talk_lists.get(i).getTalkTITLE()
                                                        , talk_lists.get(i).getTalkTITLE2(), talk_lists.get(i).getTalkTITLE3(), talk_lists.get(i).getTalkCONTENT());
                                                talk_lists_copy.add(talk_list);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else{

                    for (int i = 0; i < talk_lists.size(); i++) {

                        for (int j = 0; j < talk_split.length; j++) {

                            if (talk_lists.get(i).getTalkCONTENT().contains(talk_split[j])) {

                                Talk_List talk_list = new Talk_List(talk_lists.get(i).getTalkNUM(), talk_lists.get(i).getTalkTITLE()
                                        , talk_lists.get(i).getTalkTITLE2(), talk_lists.get(i).getTalkTITLE3(), talk_lists.get(i).getTalkCONTENT());
                                talk_lists_copy.add(talk_list);

                            }
                        }
                    }

                }

                random = (int) (Math.random() * talk_lists_copy.size());
                if(talk_lists_copy.size()==0) {

                    pet_talkLists.add(new pet_TalkList("무슨말인지 모르겠어요","pet",format_time1));
                }
                else {
                    pet_talkLists.add(new pet_TalkList(talk_lists_copy.get(random).getTalkCONTENT(),"pet",format_time1));
                }


                talk_adapter.notifyDataSetChanged();


            }
        });

        // ---------------------- 펫톡 끝 ------------------------

        //----------------------- 탭 호스트 시작 --------------------------------

        tabHost = (TabHost)findViewById(R.id.tab_host);

        tabHost.setup();

        // 홈 탭
        final TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab1");
        tabSpec1.setIndicator(""); // Tab Subject
//        tabSpec1.setIndicator("",getResources().getDrawable(R.drawable.home_on)); // Tab Subject
        tabSpec1.setContent(new Intent(this,MainActivity.class));
        tabSpec1.setContent(R.id.tab_view1); // Tab Content
        tabHost.addTab(tabSpec1);


        // 일정 탭
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab2");
        tabSpec2.setIndicator(""); // Tab Subject
        tabSpec2.setContent(new Intent(this,MainActivity.class));
        tabSpec2.setContent(R.id.tab_view2); // Tab Content
        tabHost.addTab(tabSpec2);

        // 그래프 탭
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tab3");
        tabSpec3.setIndicator(""); // Tab Subject
        tabSpec3.setContent(new Intent(this,MainActivity.class));
        tabSpec3.setContent(R.id.tab_view3); // Tab Content
        tabHost.addTab(tabSpec3);

        // 주간날씨 탭

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("Tab4");
        tabSpec4.setIndicator(""); // Tab Subject
        tabSpec4.setContent(new Intent(this,MainActivity.class));
        tabSpec4.setContent(R.id.tab_view4); // Tab Content
        tabHost.addTab(tabSpec4);


        // 펫톡

        TabHost.TabSpec tabSpec5 = tabHost.newTabSpec("Tab5");
        tabSpec5.setIndicator(""); // Tab Subject
        tabSpec5.setContent(new Intent(this,MainActivity.class));
        tabSpec5.setContent(R.id.tab_view5); // Tab Content
        tabHost.addTab(tabSpec5);



//        tabHost.getTabWidget().getChildAt(0)
//
//                .setBackgroundColor(Color.parseColor("#ffffff"));
//
//        tabHost.getTabWidget().getChildAt(1)
//
//                .setBackgroundColor(Color.parseColor("#ffffff"));
//
//        tabHost.getTabWidget().getChildAt(2)
//
//                .setBackgroundColor(Color.parseColor("#ffffff"));
//
//        tabHost.getTabWidget().getChildAt(3)
//
//                .setBackgroundColor(Color.parseColor("#ffffff"));

        tabHost.getTabWidget().getChildAt(4)

                .setBackgroundColor(Color.parseColor("#ffffff"));


        tabHost.getTabWidget().getChildAt(0)

                .setBackgroundResource(R.drawable.on);

        tabHost.getTabWidget().getChildAt(1)

                .setBackgroundResource(R.drawable.off1);

        tabHost.getTabWidget().getChildAt(2)

                .setBackgroundResource(R.drawable.off2);

        tabHost.getTabWidget().getChildAt(3)

                .setBackgroundResource(R.drawable.off3);


        tabHost.getTabWidget().getChildAt(4)

                .setBackgroundResource(R.drawable.off4);

        tabHost.setCurrentTab(0); // 처음 열리는 탭

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            TabWidget tw = tabHost.getTabWidget();
            int menu_on[] = {R.drawable.on, R.drawable.on1, R.drawable.on2, R.drawable.on3, R.drawable.on4};
            int menu_off[] = {R.drawable.off, R.drawable.off1, R.drawable.off2, R.drawable.off3, R.drawable.off4};
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); // 탭에 있는 TextView 지정후
                    if (i == tabHost.getCurrentTab()) {
                        tw.getChildAt(i).setBackgroundResource(menu_on[i]);


                    }
                    else
                        tw.getChildAt(i).setBackgroundResource(menu_off[i]);
                }


            }
        });

        //----------------------- 탭 호스트 끝 ----------------------------------

        // 우측하단 버튼 클릭시 발생할 이벤트

        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        // 이미지 및 변수 초기화
        pet_imgbtn = (CircleImageView)findViewById(R.id.pet_imgbtn);
        now_temp_img = (ImageView)findViewById(R.id.now_temp_img);
        now_dust_img = (ImageView)findViewById(R.id.now_dust_img);
        now_weather_img = (ImageView)findViewById(R.id.now_weather_img);

        // 주간 날씨
        random_puppy();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setElevation(0);
        View nav_header_view = navigationView.getHeaderView(0);
        user_login_id = (TextView)nav_header_view.findViewById(R.id.user_login_id);
        nav_image1 = (ImageView)nav_header_view.findViewById(R.id.nav_image1);
        pet_add0 = (ImageView)nav_header_view.findViewById(R.id.pet_add0);
        pet_add1 = (ImageView)nav_header_view.findViewById(R.id.pet_add1);
        pet_add2 = (ImageView)nav_header_view.findViewById(R.id.pet_add2);
        nav_user_petName = (TextView)nav_header_view.findViewById(R.id.nav_user_petName);




        ImageView nav_image2 = (ImageView)nav_header_view.findViewById(R.id.nav_image2);
        Glide.with(this).load(R.drawable.setting).fitCenter().into(nav_image2);
        ImageView nav_image3 = (ImageView)nav_header_view.findViewById(R.id.nav_image3);
        Glide.with(this).load(R.drawable.cancel).fitCenter().into(nav_image3);

        Glide.with(this).load(R.drawable.local).fitCenter().into(img_local);
        Glide.with(this).load(R.drawable.local).fitCenter().into(img_local2);

        user_login_id.setText(userID+"님을 환영합니다.");

        // 뷰페이저(광고)

        vp = (ViewPager)nav_header_view.findViewById(R.id.vp);
        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        // 설정 버튼
        nav_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(getApplicationContext(), SettingActivity.class);
                intent3.putExtra("userID",userID);
                intent3.putExtra("userName",userName);
                intent3.putExtra("petName",petList.get(0).getPetName());
                intent3.putExtra("petBir",petList.get(0).getPetBir());
                intent3.putExtra("pet",pet_status);
                startActivity(intent3);

            }
        });

        // 닫기 버튼
        nav_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        // 뷰페이저 쓰레드

        handler = new Handler(){




            public void handleMessage(android.os.Message msg) {

                if(p==0){

                    vp.setCurrentItem(0);
                    p++;
                }
                else if(p==1){
                    vp.setCurrentItem(1);
                    p++;
                }
                else if(p==2){
                    vp.setCurrentItem(2);
                    p++;
                }
                else if(p==3){
                    vp.setCurrentItem(3);
                    p=0;
                }


            }

        };

        thread = new Thread(){

            public void run() {

                super.run();

                while(true){

                    try {
                        Thread.sleep(3000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }

                }

            }

        };
        thread.start();

        // 원래 펫 클릭
        pet_add0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pet_status = 1;
                text_count_walk.setText(petList.get(0).getPetName()+"랑");
                pet_talk.setText("제 이름은 "+petList.get(0).getPetName()+"예요");
                nav_user_petName.setText(petList.get(0).getPetName());


                new BackgroundTask_date_delete().execute();
                new BackgroundTask_date_walk_delete().execute();
                new BackgroundTask_graph().execute();
                new BackgroundTask_date_walk().execute();
                new BackgroundTask_date().execute();
//                new BackgroundTask_date_btn().execute();
                new BackgroundTask_user_pet().execute();
                SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

                if(sf.getString(userID+"text","").equals("")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy1).fitCenter().into(nav_image1);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
                }
                else if(sf.getString(userID+"text","").equals("puppy1")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy1).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy2")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy2).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy3")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy3).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy4")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy4).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy5")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy5).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy6")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy6).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy7")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy7).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy8")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy8).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy9")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy9).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text","").equals("puppy10")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(R.drawable.puppy10).fitCenter().into(nav_image1);
                }
                else {

                    byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text", ""), Base64.NO_WRAP);
                    Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                    Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_imgbtn);
                    Glide.with(getApplicationContext()).load(decodedBitmap).fitCenter().into(nav_image1);
                    //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

                }

            }
        });

        // 첫번째 펫 클릭
        pet_add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pet_status = 2;
                text_count_walk.setText(petList.get(1).getPetName()+"랑");
                pet_talk.setText("제 이름은 "+petList.get(1).getPetName()+"예요");
                nav_user_petName.setText(petList.get(1).getPetName());
                Glide.with(getApplicationContext()).load(R.drawable.puppy2).fitCenter().into(nav_image1);
                new BackgroundTask_date_delete().execute();
                new BackgroundTask_date_walk_delete().execute();
                new BackgroundTask_graph().execute();
                new BackgroundTask_date_walk().execute();
                new BackgroundTask_date().execute();
//                new BackgroundTask_date_btn().execute();

                SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

                if(sf.getString(userID+"text2","").equals("")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
                }
                else if(sf.getString(userID+"text2","").equals("puppy1")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy2")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy3")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy4")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy5")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy6")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy7")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy8")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy9")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text2","").equals("puppy10")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(nav_image1);
                }
                else {

                    byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text2", ""), Base64.NO_WRAP);
                    Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                    Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(nav_image1);
                    //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

                }
                new BackgroundTask_user_pet().execute();
            }
        });

        // 두번째 펫 클릭
        pet_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pet_status = 3;
                text_count_walk.setText(petList.get(2).getPetName()+"랑");
                pet_talk.setText("제 이름은 "+petList.get(2).getPetName()+"에요");
                nav_user_petName.setText(petList.get(2).getPetName());

                new BackgroundTask_date_delete().execute();
                new BackgroundTask_date_walk_delete().execute();
                new BackgroundTask_graph().execute();

                new BackgroundTask_date_walk().execute();
                new BackgroundTask_date().execute();

                SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

                if(sf.getString(userID+"text3","").equals("")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
                }
                else if(sf.getString(userID+"text3","").equals("puppy1")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy2")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy3")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy4")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy5")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy6")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy7")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy8")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy9")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(nav_image1);
                }
                else if(sf.getString(userID+"text3","").equals("puppy10")){
                    Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(nav_image1);
                }
                else {

                    byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text3", ""), Base64.NO_WRAP);
                    Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                    Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_imgbtn);
                    Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(nav_image1);
                    //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

                }

//                new BackgroundTask_date_btn().execute();
                new BackgroundTask_user_pet().execute();
            }
        });

        nav_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pet_status==1){
                    SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

                    if(sf.getString(userID+"text","").equals("")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy1).fitCenter().into(nav_image1);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy1")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy1).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy2")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy2).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy3")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy3).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy4")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy4).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy5")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy5).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy6")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy6).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy7")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy7).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy8")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy8).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy9")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy9).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text","").equals("puppy10")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(R.drawable.puppy10).fitCenter().into(nav_image1);
                    }
                    else {

                        byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text", ""), Base64.NO_WRAP);
                        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                        Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_imgbtn);
                        Glide.with(getApplicationContext()).load(decodedBitmap).fitCenter().into(nav_image1);
                        //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

                    }
                }
                else if(pet_status==2){
                    SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

                    if(sf.getString(userID+"text2","").equals("")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy1")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy2")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy3")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy4")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy5")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy6")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy7")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy8")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy9")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text2","").equals("puppy10")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(nav_image1);
                    }

                    else {

                        byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text2", ""), Base64.NO_WRAP);
                        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                        Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(nav_image1);
                        //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

                    }
                }
                else if(pet_status==3){
                    SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

                    if(sf.getString(userID+"text3","").equals("")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy1")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy2")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy3")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy4")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy5")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy6")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy7")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy8")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy9")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(nav_image1);
                    }
                    else if(sf.getString(userID+"text3","").equals("puppy10")){
                        Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(nav_image1);
                    }
                    else {

                        byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text3", ""), Base64.NO_WRAP);
                        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
                        Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_imgbtn);
                        Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(nav_image1);
                        //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

                    }


                }
                new BackgroundTask_user_pet().execute();

            }
        });

//        pet_imgbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, GET_GALLERY_IMAGE);
//
//            }
//        });

        //초기 이미지
        SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

        if(sf.getString(userID+"text","").equals("")){
            Glide.with(this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy1).fitCenter().into(pet_add0);

//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
        }
        else if(sf.getString(userID+"text","").equals("puppy1")){
            Glide.with(this).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy1).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy1).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy2")){
            Glide.with(this).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy2).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy2).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy3")){
            Glide.with(this).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy3).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy3).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy4")){
            Glide.with(this).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy4).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy4).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy5")){
            Glide.with(this).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy5).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy5).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy6")){
            Glide.with(this).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy6).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy6).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy7")){
            Glide.with(this).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy7).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy7).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy8")){
            Glide.with(this).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy8).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy8).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy9")){
            Glide.with(this).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy9).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy9).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy10")){
            Glide.with(this).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(R.drawable.puppy10).fitCenter().into(nav_image1);
            Glide.with(this).load(R.drawable.puppy10).fitCenter().into(pet_add0);
        }
        else {

            byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text", ""), Base64.NO_WRAP);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
            Glide.with(this).load(decodedBitmap).fitCenter().into(pet_imgbtn);
            Glide.with(this).load(decodedBitmap).fitCenter().into(nav_image1);
            Glide.with(this).load(decodedBitmap).fitCenter().into(pet_add0);
            //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

        }

        // 알람
//        alram();

        btn_delete_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("해당 일정을 삭제하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String num = user_dateList2.get(0).getDateNUM();
                        date_Delete(Integer.parseInt(num));


                    }
                })
                        .create()
                        .show();



            }
        });
        btn_delete_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("해당 일정을 삭제하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String num = user_dateList2.get(1).getDateNUM();
                        date_Delete(Integer.parseInt(num));


                    }
                })
                        .create()
                        .show();

            }
        });
        btn_delete_date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("해당 일정을 삭제하시겠습니까?")
                        .setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String num = user_dateList2.get(2).getDateNUM();
                        date_Delete(Integer.parseInt(num));


                    }
                })
                        .create()
                        .show();

            }
        });

//        registerForContextMenu(pet_imgbtn);


    }

    //앱종료
    public void AppFinish(){

        Intent intent = new Intent(getApplicationContext(),MainScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        moveTaskToBack(true);

        finish();
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    // 뒤로가기
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            //1번째 백버튼 클릭
            if(System.currentTimeMillis()>backKeyPressedTime+2000){
                backKeyPressedTime = System.currentTimeMillis();

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup)findViewById(R.id.toast_custom));
                TextView tv_title=(TextView)layout.findViewById(R.id.textView1);
                ImageView imageView1 = (ImageView)layout.findViewById(R.id.imageView1);
                Glide.with(this).load(R.drawable.sub_img).fitCenter().into(imageView1);
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

//                Toast.makeText(getApplicationContext(),"한번더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
            }
            //2번째 백버튼 클릭 (종료)
            else{
                AppFinish();
            }
        }

    }

    // 메뉴 함수
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main, menu);

        return true;

//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;

    }

    // 메뉴 함수 리스너
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id== R.id.img_change1){

            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent2,REQUEST_IMAGE_CAPTURE);
            return true;
        }
        if(id== R.id.img_change2){

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_GALLERY_IMAGE);
            return true;
        }
        if(id== R.id.img_change3){

            // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
            CustomImage customDialog = new CustomImage(MainActivity.this);

            // 커스텀 다이얼로그를 호출한다.
            // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
            customDialog.callFunction(pet_imgbtn, userID, pet_status);
            return true;
        }


        return super.onOptionsItemSelected(item);

    }

    // 왼쪽 버튼 리스너
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_introduction){
            Intent intent = new Intent(getApplicationContext(),introduction.class);
            startActivity(intent);

        }
        else if(id == R.id.nav_care){
            new BackgroundTask_pre().execute();
        }

        else if (id == R.id.nav_weather) {

            tabHost.setCurrentTab(3);

        } else if (id == R.id.nav_date) {

            tabHost.setCurrentTab(1);

        } else if (id == R.id.nav_graph){

            tabHost.setCurrentTab(2);

        }
        else if(id==R.id.nav_talk) {
            tabHost.setCurrentTab(4);
        }
        else if(id==R.id.nav_picture) {

            new BackgroundTask_photo().execute();

        }
        else if(id==R.id.nav_map){
            Intent intent = new Intent(getApplicationContext(),GoogleMap.class);

            intent.putExtra("latitude",latitude);
            intent.putExtra("longitude" ,longitude);
            startActivity(intent);
        }
        else if (id == R.id.nav_info) {
            Intent intent = new Intent(getApplicationContext(), pet_info.class);
            intent.putExtra("userID",userID);
            intent.putExtra("pet",pet_status);
            startActivity(intent);
        }
        else if (id == R.id.nav_notice) {
            new BackgroundTask_notice().execute();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

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

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // 비트맵 이미지로 가져온다
            try{

                if(pet_status==1) {


                    Bundle bundle = data.getExtras();
                    Bitmap img = (Bitmap) bundle.get("data");

                    if (img.getHeight() >= 2000) {

                        img = imgRotate(img);

                    }


                    Glide.with(this).load(img).fitCenter().into(pet_imgbtn);

                    SharedPreferences sharedPreferences = getSharedPreferences("imgFile", MODE_PRIVATE);

                    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String text = getBase64String(img);

                    editor.putString(userID + "text", text); // key, value를 이용하여 저장하는 형태

                    editor.commit();

                }
                else if(pet_status==2){

                    Bundle bundle = data.getExtras();
                    Bitmap img = (Bitmap) bundle.get("data");

                    if (img.getHeight() >= 2000) {

                        img = imgRotate(img);

                    }


                    Glide.with(this).load(img).fitCenter().into(pet_imgbtn);

                    SharedPreferences sharedPreferences = getSharedPreferences("imgFile", MODE_PRIVATE);

                    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String text = getBase64String(img);

                    editor.putString(userID + "text2", text); // key, value를 이용하여 저장하는 형태

                    editor.commit();

                }
                else if(pet_status ==3){
                    Bundle bundle = data.getExtras();
                    Bitmap img = (Bitmap) bundle.get("data");

                    if (img.getHeight() >= 2000) {

                        img = imgRotate(img);

                    }

                    Glide.with(this).load(img).fitCenter().into(pet_imgbtn);

                    SharedPreferences sharedPreferences = getSharedPreferences("imgFile", MODE_PRIVATE);

                    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String text = getBase64String(img);

                    editor.putString(userID + "text3", text); // key, value를 이용하여 저장하는 형태

                    editor.commit();
                }


            }catch(Exception e)
            {

            }
        }



        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try{

                if(pet_status==1) {


                    // 비트맵 이미지로 가져온다
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    if (img.getHeight() >= 2000) {

                        img = imgRotate(img);

                    }
                    in.close();

                    Glide.with(this).load(img).fitCenter().into(pet_imgbtn);
                    SharedPreferences sharedPreferences = getSharedPreferences("imgFile", MODE_PRIVATE);
                    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String text = getBase64String(img);
                    editor.putString(userID + "text", text); // key, value를 이용하여 저장하는 형태
                    editor.commit();

                }
                else if(pet_status==2){
                    // 비트맵 이미지로 가져온다
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    if (img.getHeight() >= 2000) {

                        img = imgRotate(img);

                    }
                    in.close();

                    Glide.with(this).load(img).fitCenter().into(pet_imgbtn);
                    SharedPreferences sharedPreferences = getSharedPreferences("imgFile", MODE_PRIVATE);
                    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String text = getBase64String(img);
                    editor.putString(userID + "text2", text); // key, value를 이용하여 저장하는 형태
                    editor.commit();
                }
                else if(pet_status==3){
                    // 비트맵 이미지로 가져온다
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    if (img.getHeight() >= 2000) {

                        img = imgRotate(img);

                    }
                    in.close();

                    Glide.with(this).load(img).fitCenter().into(pet_imgbtn);
                    SharedPreferences sharedPreferences = getSharedPreferences("imgFile", MODE_PRIVATE);
                    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String text = getBase64String(img);
                    editor.putString(userID + "text3", text); // key, value를 이용하여 저장하는 형태
                    editor.commit();
                }


//                pet_imgbtn.setImageBitmap(img);
            }catch(Exception e)
            {

            }


        }else if(resultCode == RESULT_CANCELED)
        {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }


        // ------ gps
        if(requestCode == 1){
            if(resultCode==RESULT_OK){
                Uri imgUri = data.getData();

                sendMmsIntent("010-1234-1234", imgUri);
            }
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

//----------------------------------------------------------------

//--------------- 캘린더 -------------------------------------------

    // 일요일 날짜
    public class SundayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SundayDecorator() {

        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }

    }
    // 토요일 날짜
    public class SaturdayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SaturdayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }

    }
    // 오늘 날짜
    public class OneDayDecorator implements DayViewDecorator {

        private CalendarDay date;

        public OneDayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
            view.addSpan(new ForegroundColorSpan(Color.BLACK));
        }

        /**
         * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
         */
        public void setDate(Date date) {
            this.date = CalendarDay.from(date);
        }

    }
    // 달력 이벤트
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0; i< Time_Result.length; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }

            return dates;

        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            materialCalendarView.addDecorator(new MainActivity.EventDecorator(Color.GREEN, calendarDays,MainActivity.this));
        }

    }

    public class EventDecorator implements DayViewDecorator {

        private final Drawable drawable;
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates, Activity context) {

            drawable = context.getResources().getDrawable(R.drawable.foot);

            this.color = color;
            this.dates = new HashSet<>(dates);

        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
//            view.setBackgroundDrawable(drawable);
//            view.addSpan(new DotSpan(5, color)); // 날자밑에 점


        }

    }

    private class ApiSimulator2 extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator2(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0; i< Time_Result.length; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }

            return dates;

        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }
//            materialCalendarView.addDecorator(new MainActivity.EventDecorator2(Color.WHITE, calendarDays,MainActivity.this));


            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH)+1;
            String today = cal.get(Calendar.YEAR)+","+month+","+cal.get(Calendar.DATE);

            int boo = 0 ;

            for(int i = 0 ; i<user_walkList.size(); i++){

                if(today.equals(user_walkList.get(i).getWalkDAY())){
                    boo = 1;
                }

            }
           if(boo == 0)
                materialCalendarView.addDecorator(new MainActivity.EventDecorator2(Color.WHITE, calendarDays,MainActivity.this));
           else
               materialCalendarView.addDecorator(new MainActivity.EventDecorator2(Color.rgb(57,149, 211), calendarDays,MainActivity.this));
        }

    }

    public class EventDecorator2 implements DayViewDecorator {

        private final Drawable drawable;
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator2(int color, Collection<CalendarDay> dates, Activity context) {
            drawable = context.getResources().getDrawable(R.drawable.foot);
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
//            view.setSelectionDrawable(drawable);
            view.addSpan(new DotSpan(5, color)); // 날자밑에 점

        }

    }

    private class ApiSimulator3 extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator3(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0; i< Time_Result.length; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }

            return dates;

        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            materialCalendarView.addDecorator(new MainActivity.EventDecorator3(Color.RED, calendarDays,MainActivity.this));
        }

    }

    public class EventDecorator3 implements DayViewDecorator {

        private final Drawable drawable;
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator3(int color, Collection<CalendarDay> dates, Activity context) {
            drawable = context.getResources().getDrawable(R.drawable.schedule);
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
//            view.setSelectionDrawable(drawable);
            view.addSpan(new DotSpan(5, color)); // 날자밑에 점

        }

    }

    private class ApiSimulator4 extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator4(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0; i< Time_Result.length; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }

            return dates;

        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            materialCalendarView.addDecorator(new MainActivity.EventDecorator4(Color.WHITE, calendarDays,MainActivity.this));
        }

    }

    public class EventDecorator4 implements DayViewDecorator {

        private final Drawable drawable;
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator4(int color, Collection<CalendarDay> dates, Activity context) {
            drawable = context.getResources().getDrawable(R.drawable.back_white);
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
//            view.addSpan(new DotSpan(5, color)); // 날자밑에 점

        }

    }

    //------------------------------- 캘린더 끝-----------------------------------

    //-------------------------- gps 함수 --------------------------------------


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                //위치 값을 가져올 수 있음
                ;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

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


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 정상적으로 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 하실건가요?");
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


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//
//            case GPS_ENABLE_REQUEST_CODE:
//
//                //사용자가 GPS 활성 시켰는지 검사
//                if (checkLocationServicesStatus()) {
//                    if (checkLocationServicesStatus()) {
//
//                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
//                        checkRunTimePermission();
//                        return;
//                    }
//                }
//
//                break;
//        }
//    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //----------------------------- gps 함수 끝 -------------------------

    //-------------------------그래프 함수 시작----------------------------

    public class GraphAxisValueFormatter implements IAxisValueFormatter {

        private String[] mValues;
        // 생성자 초기화
        GraphAxisValueFormatter(String[] values){
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis){
            return mValues[(int) value];
        }

    }

    public class GraphYAxisValueFormatter implements IAxisValueFormatter {

        private String[] mEmojis;

        // 생성자 초기화
        GraphYAxisValueFormatter(String[] values) {
            this.mEmojis = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mEmojis[(int) value];
        }

    }

    // 뷰페이저
    private class pagerAdapter extends FragmentStatePagerAdapter
    {

        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new advertise_screen_first();
                case 1:
                    return new advertise_screen_two();
                case 2:
                    return new advertise_screen_three();
                case 3:
                    return new advertise_screen_four();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 4;
        }

    }
    // 뷰페이저 끝

    // 처음 어플 켜질때 산책일수 가져오기
    public class BackgroundTask_date_walk extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/date_walk_List.php";
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
            user_walkList = new ArrayList<User_Date_walk>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String walkID, walkDAY, walkNUM;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);

                    walkID = object.getString("walkID");
                    walkDAY = object.getString("walkDAY");
                    walkNUM = object.getString("walkNUM");

                    if(walkID.equals(userID)){

                        if(walkNUM.equals(String.valueOf(pet_status))) {
                            User_Date_walk date = new User_Date_walk(walkID, walkDAY, walkNUM);
                            user_walkList.add(date);
                        }
                    }
                    count++;
                }
            }catch(Exception e){
                e.getStackTrace();
            }
            String result_date[] = new String[user_walkList.size()+1];

            for(int i = 0; i<result_date.length-1 ;i++){
                result_date[i] = user_walkList.get(i).getWalkDAY();
            }
            result_date[user_walkList.size()] = "2019,03,18";


            //

            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH)+1;
            String today = cal.get(Calendar.YEAR)+","+month+","+cal.get(Calendar.DATE);

            boo = 0 ;

            for(int i = 0 ; i<user_walkList.size(); i++){

                if(today.equals(user_walkList.get(i).getWalkDAY())){
                    boo = 1;
                }

            }

            if( boo == 0){
                String[] result_to = {"2019,06,18"};
                new MainActivity.ApiSimulator(result_date).executeOnExecutor(Executors.newSingleThreadExecutor());
                new MainActivity.ApiSimulator4(result_to).executeOnExecutor(Executors.newSingleThreadExecutor());
                new MainActivity.ApiSimulator4(result_to).executeOnExecutor(Executors.newSingleThreadExecutor());
            }
            else{
                new MainActivity.ApiSimulator(result_date).executeOnExecutor(Executors.newSingleThreadExecutor());
            }

            //




//            new MainActivity.ApiSimulator(result_date).executeOnExecutor(Executors.newSingleThreadExecutor());

            text_count_walk2.setText("  +"+user_walkList.size()+"일  ");

        }

    }

    // 처음 어플 켜질때 날짜데이터 가져오기
    public class BackgroundTask_date extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/date_List.php";
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

            user_dateList = new ArrayList<User_Date>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String dateNUM, dateID, dateDAY, dateContent, petNUM;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    dateNUM = object.getString("dateNUM");
                    dateID = object.getString("dateID");
                    dateDAY = object.getString("dateDAY");
                    dateContent = object.getString("dateContent");
                    petNUM = object.getString("petNUM");

                    if(dateID.equals(userID)){
                        if(petNUM.equals(String.valueOf(pet_status))) {
                            User_Date date = new User_Date(dateNUM, dateID, dateDAY, dateContent, petNUM);
                            user_dateList.add(date);
                        }
                    }
                    count++;
                }
            }catch(Exception e){
                e.getStackTrace();
            }
            String result_date[] = new String[user_dateList.size()+1];

           for(int i = 0; i<result_date.length-1 ;i++){
               result_date[i] = user_dateList.get(i).getDateDAY();
           }
           result_date[user_dateList.size()] = "2019,03,18";

           Calendar cal = Calendar.getInstance();
           int month = cal.get(Calendar.MONTH)+1;
           String today = cal.get(Calendar.YEAR)+","+month+","+cal.get(Calendar.DATE);

           bo = 0 ;

           for(int i = 0 ; i<user_dateList.size(); i++){

               if(today.equals(user_dateList.get(i).getDateDAY())){
                   bo = 1;
               }

           }

           if( bo == 0){
               String[] result_to = {"2019,06,18"};
               new MainActivity.ApiSimulator3(result_date).executeOnExecutor(Executors.newSingleThreadExecutor());
               new MainActivity.ApiSimulator2(result_to).executeOnExecutor(Executors.newSingleThreadExecutor());
               new MainActivity.ApiSimulator2(result_to).executeOnExecutor(Executors.newSingleThreadExecutor());
           }
           else{

               new MainActivity.ApiSimulator3(result_date).executeOnExecutor(Executors.newSingleThreadExecutor());

           }

        }

    }

    // 일정 보기 이벤트 시작
    public class BackgroundTask_date_btn extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/date_List.php";
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

            user_dateList = new ArrayList<User_Date>();
            user_dateList2 = new ArrayList<User_Date>();

            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String dateNUM, dateID, dateDAY, dateContent, petNUM;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    dateNUM = object.getString("dateNUM");
                    dateID = object.getString("dateID");
                    dateDAY = object.getString("dateDAY");
                    dateContent = object.getString("dateContent");
                    petNUM = object.getString("petNUM");
                    if(dateID.equals(userID)){

                        if(petNUM.equals(String.valueOf(pet_status))) {
                            User_Date date = new User_Date(dateNUM, dateID, dateDAY, dateContent, petNUM);
                            user_dateList.add(date);
                        }
                    }
                    count++;
                }

            }catch(Exception e){
                e.getStackTrace();
            }

            today_walk_list.setText("일정 없음");


            for(int i=0; i<user_dateList.size();i++ ){
                if(user_dateList.get(i).getDateDAY().equals(click_date)){
                    User_Date date = new User_Date(user_dateList.get(i).getDateNUM(), user_dateList.get(i).getDateID(), user_dateList.get(i).getDateDAY()
                            ,user_dateList.get(i).getDateContent(), user_dateList.get(i).getPetNUM());
                    user_dateList2.add(date);

                }
            }

            if(user_dateList2.size()==0){
                today_walk_list.setText("일정 없음");
                today_walk_list2.setText("일정 없음");
                today_walk_list3.setText("일정 없음");
                btn_delete_date1.setVisibility(View.GONE);
                date2_hide.setVisibility(View.GONE);
                date3_hide.setVisibility(View.GONE);
            }
            else if(user_dateList2.size()==1){
                today_walk_list.setText(user_dateList2.get(0).getDateContent());
                today_walk_list2.setText("일정 없음");
                today_walk_list3.setText("일정 없음");
                btn_delete_date1.setVisibility(View.VISIBLE);
                date2_hide.setVisibility(View.GONE);
                date3_hide.setVisibility(View.GONE);
            }
            else if(user_dateList2.size()==2){
                today_walk_list.setText(user_dateList2.get(0).getDateContent());
                today_walk_list2.setText(user_dateList2.get(1).getDateContent());
                today_walk_list3.setText("일정 없음");
                btn_delete_date1.setVisibility(View.VISIBLE);
                date2_hide.setVisibility(View.VISIBLE);
                date3_hide.setVisibility(View.GONE);
            }
            else if(user_dateList2.size()==3){
                today_walk_list.setText(user_dateList2.get(0).getDateContent());
                today_walk_list2.setText(user_dateList2.get(1).getDateContent());
                today_walk_list3.setText(user_dateList2.get(2).getDateContent());
                btn_delete_date1.setVisibility(View.VISIBLE);
                date2_hide.setVisibility(View.VISIBLE);
                date3_hide.setVisibility(View.VISIBLE);
            }


        }

    }
    // 일정 보기 이벤트 끝

    // 그래프 보기 시작(펫)
    class BackgroundTask_graph extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/graph_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            pet_graphList = new ArrayList<Pet_Graph>();
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

            float max = 0;

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

            if(pet_graphList.size()!=0){

                if (Float.parseFloat(pet_graphList.get(0).getTODAY()) > max) {
                    max = Float.parseFloat(pet_graphList.get(0).getTODAY());
                }

                if (Float.parseFloat(pet_graphList.get(0).getTODAY1()) > max) {
                    max = Float.parseFloat(pet_graphList.get(0).getTODAY1());
                }

                if (Float.parseFloat(pet_graphList.get(0).getTODAY2()) > max) {
                    max = Float.parseFloat(pet_graphList.get(0).getTODAY2());
                }

                if (Float.parseFloat(pet_graphList.get(0).getTODAY3()) > max) {
                    max = Float.parseFloat(pet_graphList.get(0).getTODAY3());
                }

                if (Float.parseFloat(pet_graphList.get(0).getTODAY4()) > max) {
                    max = Float.parseFloat(pet_graphList.get(0).getTODAY4());
                }

                if (Float.parseFloat(pet_graphList.get(0).getTODAY5()) > max) {
                    max = Float.parseFloat(pet_graphList.get(0).getTODAY5());
                }

                if (Float.parseFloat(pet_graphList.get(0).getTODAY6()) > max) {
                    max = Float.parseFloat(pet_graphList.get(0).getTODAY6());
                }

            }

                List<Entry> entries = new ArrayList<>();

            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, 0); //
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷
            String today = sdf.format(calendar.getTime()); // String으로 저장
            calendar.add(Calendar.DATE, -1); //
            String today1 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today2 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today3 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today4 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today5 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today6 = sdf.format(calendar.getTime());

            String mDays[] = {today6, today5, today4, today3, today2, today1, today};

                if(pet_graphList.size()!=0) {

                    if(pet_status == 1) {
                        entries.add(new Entry(0, Float.parseFloat(pet_graphList.get(0).getTODAY6())));
                        entries.add(new Entry(1, Float.parseFloat(pet_graphList.get(0).getTODAY5())));
                        entries.add(new Entry(2, Float.parseFloat(pet_graphList.get(0).getTODAY4())));
                        entries.add(new Entry(3, Float.parseFloat(pet_graphList.get(0).getTODAY3())));
                        entries.add(new Entry(4, Float.parseFloat(pet_graphList.get(0).getTODAY2())));
                        entries.add(new Entry(5, Float.parseFloat(pet_graphList.get(0).getTODAY1())));
                        entries.add(new Entry(6, Float.parseFloat(pet_graphList.get(0).getTODAY())));

                        text_today_info_date0.setText(mDays[6]);
                        text_today_info0.setText(pet_graphList.get(0).getTODAY()+"m");
                        text_today_info_time0.setText(pet_graphList_time.get(0).getTODAY()+"분");

                        text_today_info_date1.setText(mDays[5]);
                        text_today_info1.setText(pet_graphList.get(0).getTODAY1()+"m");
                        text_today_info_time1.setText(pet_graphList_time.get(0).getTODAY1()+"분");

                        text_today_info_date2.setText(mDays[4]);
                        text_today_info2.setText(pet_graphList.get(0).getTODAY2()+"m");
                        text_today_info_time2.setText(pet_graphList_time.get(0).getTODAY2()+"분");

                        text_today_info_date3.setText(mDays[3]);
                        text_today_info3.setText(pet_graphList.get(0).getTODAY3()+"m");
                        text_today_info_time3.setText(pet_graphList_time.get(0).getTODAY3()+"분");

                        text_today_info_date4.setText(mDays[2]);
                        text_today_info4.setText(pet_graphList.get(0).getTODAY4()+"m");
                        text_today_info_time4.setText(pet_graphList_time.get(0).getTODAY4()+"분");

                        text_today_info_date5.setText(mDays[1]);
                        text_today_info5.setText(pet_graphList.get(0).getTODAY5()+"m");
                        text_today_info_time5.setText(pet_graphList_time.get(0).getTODAY5()+"분");

                        text_today_info_date6.setText(mDays[0]);
                        text_today_info6.setText(pet_graphList.get(0).getTODAY6()+"m");
                        text_today_info_time6.setText(pet_graphList_time.get(0).getTODAY6()+"분");

//                        graph_km.setText("DA");


                        graph_km.setText(String.valueOf(Integer.parseInt(pet_graphList.get(0).TODAY)+Integer.parseInt(pet_graphList.get(0).TODAY1)+
                                Integer.parseInt(pet_graphList.get(0).TODAY2)+Integer.parseInt(pet_graphList.get(0).TODAY3)+
                                Integer.parseInt(pet_graphList.get(0).TODAY4)+Integer.parseInt(pet_graphList.get(0).TODAY5)+
                                Integer.parseInt(pet_graphList.get(0).TODAY6)));

                        int count = 0;

                        if(Integer.parseInt(pet_graphList.get(0).TODAY)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(0).TODAY1)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(0).TODAY2)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(0).TODAY3)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(0).TODAY4)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(0).TODAY5)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(0).TODAY6)!=0){
                            count++;

                        }

                        graph_count.setText(String.valueOf(count));

                        graph_speed.setText(String.valueOf((Integer.parseInt(pet_graphList.get(0).TODAY)+Integer.parseInt(pet_graphList.get(0).TODAY1)+
                                Integer.parseInt(pet_graphList.get(0).TODAY2)+Integer.parseInt(pet_graphList.get(0).TODAY3)+
                                Integer.parseInt(pet_graphList.get(0).TODAY4)+Integer.parseInt(pet_graphList.get(0).TODAY5)+
                                Integer.parseInt(pet_graphList.get(0).TODAY6))/7));

                        graph_time.setText(String.valueOf((Integer.parseInt(pet_graphList_time.get(0).TODAY)+Integer.parseInt(pet_graphList_time.get(0).TODAY1)+
                                Integer.parseInt(pet_graphList_time.get(0).TODAY2)+Integer.parseInt(pet_graphList_time.get(0).TODAY3)+
                                Integer.parseInt(pet_graphList_time.get(0).TODAY4)+Integer.parseInt(pet_graphList_time.get(0).TODAY5)+
                                Integer.parseInt(pet_graphList_time.get(0).TODAY6))/7));

                    }
                    else if(pet_status == 2){
                        entries.add(new Entry(0, Float.parseFloat(pet_graphList.get(1).getTODAY6())));
                        entries.add(new Entry(1, Float.parseFloat(pet_graphList.get(1).getTODAY5())));
                        entries.add(new Entry(2, Float.parseFloat(pet_graphList.get(1).getTODAY4())));
                        entries.add(new Entry(3, Float.parseFloat(pet_graphList.get(1).getTODAY3())));
                        entries.add(new Entry(4, Float.parseFloat(pet_graphList.get(1).getTODAY2())));
                        entries.add(new Entry(5, Float.parseFloat(pet_graphList.get(1).getTODAY1())));
                        entries.add(new Entry(6, Float.parseFloat(pet_graphList.get(1).getTODAY())));

                        text_today_info_date0.setText(mDays[6]);
                        text_today_info0.setText(pet_graphList.get(1).getTODAY()+"m");
                        text_today_info_time0.setText(pet_graphList_time.get(1).getTODAY()+"분");

                        text_today_info_date1.setText(mDays[5]);
                        text_today_info1.setText(pet_graphList.get(1).getTODAY1()+"m");
                        text_today_info_time1.setText(pet_graphList_time.get(1).getTODAY1()+"분");

                        text_today_info_date2.setText(mDays[4]);
                        text_today_info2.setText(pet_graphList.get(1).getTODAY2()+"m");
                        text_today_info_time2.setText(pet_graphList_time.get(1).getTODAY2()+"분");

                        text_today_info_date3.setText(mDays[3]);
                        text_today_info3.setText(pet_graphList.get(1).getTODAY3()+"m");
                        text_today_info_time3.setText(pet_graphList_time.get(1).getTODAY3()+"분");

                        text_today_info_date4.setText(mDays[2]);
                        text_today_info4.setText(pet_graphList.get(1).getTODAY4()+"m");
                        text_today_info_time4.setText(pet_graphList_time.get(1).getTODAY4()+"분");

                        text_today_info_date5.setText(mDays[1]);
                        text_today_info5.setText(pet_graphList.get(1).getTODAY5()+"m");
                        text_today_info_time5.setText(pet_graphList_time.get(1).getTODAY5()+"분");

                        text_today_info_date6.setText(mDays[0]);
                        text_today_info6.setText(pet_graphList.get(1).getTODAY6()+"m");
                        text_today_info_time6.setText(pet_graphList_time.get(1).getTODAY6()+"분");

                        graph_km.setText(String.valueOf(Integer.parseInt(pet_graphList.get(1).TODAY)+Integer.parseInt(pet_graphList.get(1).TODAY1)+
                                Integer.parseInt(pet_graphList.get(1).TODAY2)+Integer.parseInt(pet_graphList.get(1).TODAY3)+
                                Integer.parseInt(pet_graphList.get(1).TODAY4)+Integer.parseInt(pet_graphList.get(1).TODAY5)+
                                Integer.parseInt(pet_graphList.get(1).TODAY6)));

                        int count = 0;

                        if(Integer.parseInt(pet_graphList.get(1).TODAY)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(1).TODAY1)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(1).TODAY2)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(1).TODAY3)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(1).TODAY4)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(1).TODAY5)!=0){
                            count++;

                        }
                        if(Integer.parseInt(pet_graphList.get(1).TODAY6)!=0){
                            count++;

                        }

                        graph_count.setText(String.valueOf(count));

                        graph_speed.setText(String.valueOf((Integer.parseInt(pet_graphList_speed.get(1).TODAY)+Integer.parseInt(pet_graphList_speed.get(1).TODAY1)+
                                Integer.parseInt(pet_graphList_speed.get(1).TODAY2)+Integer.parseInt(pet_graphList_speed.get(1).TODAY3)+
                                Integer.parseInt(pet_graphList_speed.get(1).TODAY4)+Integer.parseInt(pet_graphList_speed.get(1).TODAY5)+
                                Integer.parseInt(pet_graphList_speed.get(1).TODAY6))/7));

                        graph_time.setText(String.valueOf((Integer.parseInt(pet_graphList_time.get(1).TODAY)+Integer.parseInt(pet_graphList_time.get(1).TODAY1)+
                                Integer.parseInt(pet_graphList_time.get(1).TODAY2)+Integer.parseInt(pet_graphList_time.get(1).TODAY3)+
                                Integer.parseInt(pet_graphList_time.get(1).TODAY4)+Integer.parseInt(pet_graphList_time.get(1).TODAY5)+
                                Integer.parseInt(pet_graphList_time.get(1).TODAY6))/7));

                    }
                    else if(pet_status==3){
                        entries.add(new Entry(0, Float.parseFloat(pet_graphList.get(2).getTODAY6())));
                        entries.add(new Entry(1, Float.parseFloat(pet_graphList.get(2).getTODAY5())));
                        entries.add(new Entry(2, Float.parseFloat(pet_graphList.get(2).getTODAY4())));
                        entries.add(new Entry(3, Float.parseFloat(pet_graphList.get(2).getTODAY3())));
                        entries.add(new Entry(4, Float.parseFloat(pet_graphList.get(2).getTODAY2())));
                        entries.add(new Entry(5, Float.parseFloat(pet_graphList.get(2).getTODAY1())));
                        entries.add(new Entry(6, Float.parseFloat(pet_graphList.get(2).getTODAY())));

                        text_today_info_date0.setText(mDays[6]);
                        text_today_info0.setText(pet_graphList.get(2).getTODAY()+"m");
                        text_today_info_time0.setText(pet_graphList_time.get(2).getTODAY()+"분");

                        text_today_info_date1.setText(mDays[5]);
                        text_today_info1.setText(pet_graphList.get(2).getTODAY1()+"m");
                        text_today_info_time1.setText(pet_graphList_time.get(2).getTODAY1()+"분");

                        text_today_info_date2.setText(mDays[4]);
                        text_today_info2.setText(pet_graphList.get(2).getTODAY2()+"m");
                        text_today_info_time2.setText(pet_graphList_time.get(2).getTODAY2()+"분");

                        text_today_info_date3.setText(mDays[3]);
                        text_today_info3.setText(pet_graphList.get(2).getTODAY3()+"m");
                        text_today_info_time3.setText(pet_graphList_time.get(2).getTODAY3()+"분");

                        text_today_info_date4.setText(mDays[2]);
                        text_today_info4.setText(pet_graphList.get(2).getTODAY4()+"m");
                        text_today_info_time4.setText(pet_graphList_time.get(2).getTODAY4()+"분");

                        text_today_info_date5.setText(mDays[1]);
                        text_today_info5.setText(pet_graphList.get(2).getTODAY5()+"m");
                        text_today_info_time5.setText(pet_graphList_time.get(2).getTODAY5()+"분");

                        text_today_info_date6.setText(mDays[0]);
                        text_today_info6.setText(pet_graphList.get(2).getTODAY6()+"m");
                        text_today_info_time6.setText(pet_graphList_time.get(2).getTODAY6()+"분");

                        graph_km.setText(String.valueOf(Integer.parseInt(pet_graphList.get(2).TODAY)+Integer.parseInt(pet_graphList.get(2).TODAY1)+
                                Integer.parseInt(pet_graphList.get(2).TODAY2)+Integer.parseInt(pet_graphList.get(2).TODAY3)+
                                Integer.parseInt(pet_graphList.get(2).TODAY4)+Integer.parseInt(pet_graphList.get(2).TODAY5)+
                                Integer.parseInt(pet_graphList.get(2).TODAY6)));

                        int count = 0;

                        if(Integer.parseInt(pet_graphList.get(2).TODAY)!=0){
                            count++;
                        }
                        if(Integer.parseInt(pet_graphList.get(2).TODAY1)!=0){
                            count++;
                        }
                        if(Integer.parseInt(pet_graphList.get(2).TODAY2)!=0){
                            count++;
                        }
                        if(Integer.parseInt(pet_graphList.get(2).TODAY3)!=0){
                            count++;
                        }
                        if(Integer.parseInt(pet_graphList.get(2).TODAY4)!=0){
                            count++;
                        }
                        if(Integer.parseInt(pet_graphList.get(2).TODAY5)!=0){
                            count++;
                        }
                        if(Integer.parseInt(pet_graphList.get(2).TODAY6)!=0){
                            count++;
                        }

                        graph_count.setText(String.valueOf(count));

                        graph_speed.setText(String.valueOf((Integer.parseInt(pet_graphList_speed.get(2).TODAY)+Integer.parseInt(pet_graphList_speed.get(2).TODAY1)+
                                Integer.parseInt(pet_graphList_speed.get(2).TODAY2)+Integer.parseInt(pet_graphList_speed.get(2).TODAY3)+
                                Integer.parseInt(pet_graphList_speed.get(2).TODAY4)+Integer.parseInt(pet_graphList_speed.get(2).TODAY5)+
                                Integer.parseInt(pet_graphList_speed.get(2).TODAY6))/7));

                        graph_time.setText(String.valueOf((Integer.parseInt(pet_graphList_time.get(2).TODAY)+Integer.parseInt(pet_graphList_time.get(2).TODAY1)+
                                Integer.parseInt(pet_graphList_time.get(2).TODAY2)+Integer.parseInt(pet_graphList_time.get(2).TODAY3)+
                                Integer.parseInt(pet_graphList_time.get(2).TODAY4)+Integer.parseInt(pet_graphList_time.get(2).TODAY5)+
                                Integer.parseInt(pet_graphList_time.get(2).TODAY6))/7));

                    }
                }
                else{
                    entries.add(new Entry(0, 0f));
                    entries.add(new Entry(1, 0f));
                    entries.add(new Entry(2, 0f));
                    entries.add(new Entry(3, 0f));
                    entries.add(new Entry(4, 0f));
                    entries.add(new Entry(5, 0f));
                    entries.add(new Entry(6, 0f));
                    add_graph();
                    add_graph_speed();
                    add_graph_time();
                }
                LineDataSet lineDataSet = new LineDataSet(entries, "반려견 운동량");
                lineDataSet.setLineWidth(4); // 선 굵기
                lineDataSet.setCircleRadius(4); // 곡률
                lineDataSet.setCircleHoleRadius(2);

                lineDataSet.setCircleColor(Color.parseColor("#FF00B700")); // 원 칼러
                lineDataSet.setCircleColorHole(Color.WHITE); // 원안에 가운데 점
                lineDataSet.setColor(Color.parseColor("#FF00B700")); // 선 칼라


                lineDataSet.setDrawCircleHole(true);
                lineDataSet.setDrawCircles(true);
                lineDataSet.setDrawHorizontalHighlightIndicator(false);
                lineDataSet.setDrawHighlightIndicators(false);
                lineDataSet.setDrawValues(false);

                LineData lineData = new LineData(lineDataSet);  // 라인 데이터의 텍스트 컬러 / 사이즈를 설정할 수 있다.
                lineChart.setData(lineData);

                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextColor(Color.BLACK);
                xAxis.enableGridDashedLine(8, 24, 0);

                YAxis yLAxis = lineChart.getAxisLeft();  // Y축 위치
                yLAxis.setTextColor(Color.BLACK); // Y축 텍스트 칼라

                YAxis yRAxis = lineChart.getAxisRight();
                yRAxis.setDrawLabels(false);
                yRAxis.setDrawAxisLine(false);
                yRAxis.setDrawGridLines(false);

                max +=  30;
                yLAxis.setAxisMaximum((float) max); // Y축 최대값 지정

                Description description = new Description();
                description.setText("거리");

                lineChart.setDoubleTapToZoomEnabled(false);
                lineChart.setDrawGridBackground(false);
                lineChart.setDescription(description);
                lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
                lineChart.invalidate();

                MyMarkerView marker = new MyMarkerView(getApplicationContext(),R.layout.marker_view2);
                marker.setChartView(lineChart);
                lineChart.setMarker(marker);

            xAxis.setValueFormatter(new GraphAxisValueFormatter(mDays));

        }

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
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("수정 완료")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(graph_all_update);
        // 수정 끝

    }

    // graph 테이블 등록(펫)
    private void add_graph(){

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        graph_add graph_add = new graph_add(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(graph_add);


    }

    // graph 테이블 등록(펫 달린 속도)
    private void add_graph_speed(){

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        graph_add_speed graph_add = new graph_add_speed(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(graph_add);


    }

    // graph 테이블 등록(펫 달린 시간)
    private void add_graph_time(){

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        graph_add_time graph_add = new graph_add_time(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(graph_add);


    }

    // 그래프 보기 시작(사람)
    class user_BackgroundTask_graph extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/user_graph_List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            user_pet_graphList = new ArrayList<Pet_Graph>();
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
            user_pet_graphList = new ArrayList<Pet_Graph>();
            float max = 0;
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
                        user_pet_graphList.add(pet_graph);
                    }
                    count++;
                }
            }catch(Exception e){
                e.getStackTrace();
            }
            if(user_pet_graphList.size()!=0) {

                if (Float.parseFloat(user_pet_graphList.get(0).getTODAY()) > max) {
                    max = Float.parseFloat(user_pet_graphList.get(0).getTODAY());
                }

                if (Float.parseFloat(user_pet_graphList.get(0).getTODAY1()) > max) {
                    max = Float.parseFloat(user_pet_graphList.get(0).getTODAY1());
                }

                if (Float.parseFloat(user_pet_graphList.get(0).getTODAY2()) > max) {
                    max = Float.parseFloat(user_pet_graphList.get(0).getTODAY2());
                }

                if (Float.parseFloat(user_pet_graphList.get(0).getTODAY3()) > max) {
                    max = Float.parseFloat(user_pet_graphList.get(0).getTODAY3());
                }

                if (Float.parseFloat(user_pet_graphList.get(0).getTODAY4()) > max) {
                    max = Float.parseFloat(user_pet_graphList.get(0).getTODAY4());
                }

                if (Float.parseFloat(user_pet_graphList.get(0).getTODAY5()) > max) {
                    max = Float.parseFloat(user_pet_graphList.get(0).getTODAY5());
                }

                if (Float.parseFloat(user_pet_graphList.get(0).getTODAY6()) > max) {
                    max = Float.parseFloat(user_pet_graphList.get(0).getTODAY6());
                }
            }

            List<Entry> entries = new ArrayList<>();

            if(user_pet_graphList.size()!=0) {
                entries.add(new Entry(0, Float.parseFloat(user_pet_graphList.get(0).getTODAY6())));
                entries.add(new Entry(1, Float.parseFloat(user_pet_graphList.get(0).getTODAY5())));
                entries.add(new Entry(2, Float.parseFloat(user_pet_graphList.get(0).getTODAY4())));
                entries.add(new Entry(3, Float.parseFloat(user_pet_graphList.get(0).getTODAY3())));
                entries.add(new Entry(4, Float.parseFloat(user_pet_graphList.get(0).getTODAY2())));
                entries.add(new Entry(5, Float.parseFloat(user_pet_graphList.get(0).getTODAY1())));
                entries.add(new Entry(6, Float.parseFloat(user_pet_graphList.get(0).getTODAY())));
            }
            else{
                entries.add(new Entry(0, 0f));
                entries.add(new Entry(1, 0f));
                entries.add(new Entry(2, 0f));
                entries.add(new Entry(3, 0f));
                entries.add(new Entry(4, 0f));
                entries.add(new Entry(5, 0f));
                entries.add(new Entry(6, 0f));
                user_add_graph();
            }
            LineDataSet pet_lineDataSet = new LineDataSet(entries, "소모 칼로리");
            pet_lineDataSet.setLineWidth(2); // 선 굵기
            pet_lineDataSet.setCircleRadius(5); // 곡률
            pet_lineDataSet.setCircleHoleRadius(3);


            pet_lineDataSet.setCircleColor(Color.parseColor("#FF3995d3")); // 원 칼러
            pet_lineDataSet.setCircleColorHole(Color.WHITE); // 원안에 가운데 점
            pet_lineDataSet.setColor(Color.parseColor("#FF3995d3")); // 선 칼라

            pet_lineDataSet.setDrawCircleHole(true);
            pet_lineDataSet.setDrawCircles(true);
            pet_lineDataSet.setDrawHorizontalHighlightIndicator(false);
            pet_lineDataSet.setDrawHighlightIndicators(false);
            pet_lineDataSet.setDrawValues(false);

            LineData pet_lineData = new LineData(pet_lineDataSet);  // 라인 데이터의 텍스트 컬러 / 사이즈를 설정할 수 있다.
            pet_lineChart.setData(pet_lineData);

            XAxis xAxis = pet_lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextColor(Color.BLACK);
            xAxis.enableGridDashedLine(8, 24, 0);

            YAxis yLAxis = pet_lineChart.getAxisLeft();  // Y축 위치
            yLAxis.setTextColor(Color.BLACK); // Y축 텍스트 칼라

            YAxis yRAxis = pet_lineChart.getAxisRight();
            yRAxis.setDrawLabels(false);
            yRAxis.setDrawAxisLine(false);
            yRAxis.setDrawGridLines(false);

            max+=30;
            yLAxis.setAxisMaximum((float) max); // Y축 최대값 지정

            Description description = new Description();
            description.setText("");


            pet_lineChart.setDoubleTapToZoomEnabled(false);
            pet_lineChart.setDrawGridBackground(false);
            pet_lineChart.setDescription(description);
            pet_lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
            pet_lineChart.invalidate();


            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, 0); //
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd"); // 날짜 포맷
            String today = sdf.format(calendar.getTime()); // String으로 저장
            calendar.add(Calendar.DATE, -1); //
            String today1 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today2 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today3 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today4 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today5 = sdf.format(calendar.getTime());
            calendar.add(Calendar.DATE, -1); //
            String today6 = sdf.format(calendar.getTime());


            String mDays[] = {today6, today5, today4, today3, today2, today1, today};

            MyMarkerView marker = new MyMarkerView(getApplicationContext(),R.layout.marker_view);
            marker.setChartView(pet_lineChart);
            pet_lineChart.setMarker(marker);

            xAxis.setValueFormatter(new GraphAxisValueFormatter(mDays));

        }

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
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("수정 완료")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(user_graph_all_update);
        // 수정 끝

    }

    // graph 테이블 등록 (사람)
    private void user_add_graph(){

        String graphID = userID;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("회원 등록에 실패했습니다.")
                                .setNegativeButton("다시 시도", null)
                                .create()
                                .show();

                    }
                } catch (JSONException e) {
                    e.getStackTrace();
                }
            }
        };

        user_graph_add user_graph_add = new user_graph_add(graphID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(user_graph_add);

    }

    // 오늘 그래프 업데이트 (사람)
    private void user_update_today_graph(){

        String graphID = userID;
        TODAY = (int) Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(graph_test.getText().toString())))) / 2;
//        int TODAY = (int) Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(testmsg))));

        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("회원 등록에 성공했습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(user_graph_updateRequest);

    }

    // 재시작
    @Override
    protected void onRestart() {

        super.onRestart();

        pet_status=1;
        random_puppy();
        new BackgroundTask_graphList_speed().execute();
        new BackgroundTask_graphList_time().execute();
        new BackgroundTask_graph().execute();
        new user_BackgroundTask_graph().execute();
        new BackgroundTask_user_pet().execute();
        random_talk();

        adfull.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                adfull.loadAd(new AdRequest.Builder().build());
            }

        });

    }

    // 공지사항 리스트 불러오기
    class BackgroundTask_notice extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/notice_List.php";
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

            Intent intent = new Intent(MainActivity.this,notice.class);
            intent.putExtra("noticeList",result);
            MainActivity.this.startActivity(intent);

        }

    }

    // 사용자 펫 아이디 불러오기
    class BackgroundTask_user_pet extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/pet_List.php";
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
            petList = new ArrayList<Pet>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String petNum, petID, petName, petType, petAge, petSex, petBir, petWeight, petStatus;

                while(count < jsonArray.length()) {

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

                    if (petID.equals(userID)) {
                        Pet pet = new Pet(petNum, petID, petName, petType, petAge, petSex, petBir, petWeight,petStatus);
                        petList.add(pet);
                    }

                    count++;


                }

                text_count_walk.setText(petList.get(0).getPetName()+"랑");
                pet_talk.setText("제 이름은 "+petList.get(0).getPetName()+"에요");
                nav_user_petName.setText(petList.get(0).getPetName());

                pet_add0.setVisibility(View.GONE);
                pet_add1.setVisibility(View.GONE);

                if(petList.size()>=2){
                    pet_add0.setVisibility(View.VISIBLE);
                    pet_add1.setVisibility(View.VISIBLE);
                    pet_add2.setVisibility(View.GONE);
//                    Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_add0);
//                    Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_add1);

                    pet1_image();
                    pet2_image();

                }
                if(petList.size()>=3){
                    pet_add2.setVisibility(View.VISIBLE);
//                    Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_add2);

                    pet3_image();
                }

                if(pet_status==1){
                    text_count_walk.setText(petList.get(0).getPetName()+"랑");
                    pet_talk.setText("제 이름은 "+petList.get(0).getPetName()+"예요");
                    nav_user_petName.setText(petList.get(0).getPetName());
                }
                else if(pet_status==2){
                    text_count_walk.setText(petList.get(1).getPetName()+"랑");
                    pet_talk.setText("제 이름은 "+petList.get(1).getPetName()+"예요");
                    nav_user_petName.setText(petList.get(1).getPetName());
                }
                else if(pet_status==3){
                    text_count_walk.setText(petList.get(2).getPetName()+"랑");
                    pet_talk.setText("제 이름은 "+petList.get(2).getPetName()+"예요");
                    nav_user_petName.setText(petList.get(2).getPetName());
                }


            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }
    public void pet1_image(){
        SharedPreferences sf = getSharedPreferences("imgFile",MODE_PRIVATE);

        if(sf.getString(userID+"text","").equals("")){
            Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_add0);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
        }
        else if(sf.getString(userID+"text","").equals("puppy1")){
            Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy2")){
            Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy3")){
            Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy4")){
            Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy5")){
            Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy6")){
            Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy7")){
            Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy8")){
            Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy9")){
            Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_add0);
        }
        else if(sf.getString(userID+"text","").equals("puppy10")){
            Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_add0);
        }
        else {

            byte[] decodedByteArray = Base64.decode(sf.getString(userID+"text", ""), Base64.NO_WRAP);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
            Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_add0);
            //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

        }

    }

    public void pet2_image(){
        SharedPreferences sf2 = getSharedPreferences("imgFile",MODE_PRIVATE);

        if(sf2.getString(userID+"text2","").equals("")){
            Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_add1);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy1")){
            Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy2")){
            Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy3")){
            Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy4")){
            Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy5")){
            Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy6")){
            Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy7")){
            Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy8")){
            Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy9")){
            Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_add1);
        }
        else if(sf2.getString(userID+"text2","").equals("puppy10")){
            Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_add1);
        }
        else {

            byte[] decodedByteArray = Base64.decode(sf2.getString(userID+"text2", ""), Base64.NO_WRAP);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
            Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_add1);
            //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

        }

    }

    public void pet3_image(){
        SharedPreferences sf2 = getSharedPreferences("imgFile",MODE_PRIVATE);

        if(sf2.getString(userID+"text3","").equals("")){
            Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_add2);
//            Glide.with(this).load(R.drawable.mainimg).fitCenter().into(pet_img_talk);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy1")){
            Glide.with(MainActivity.this).load(R.drawable.puppy1).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy2")){
            Glide.with(MainActivity.this).load(R.drawable.puppy2).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy3")){
            Glide.with(MainActivity.this).load(R.drawable.puppy3).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy4")){
            Glide.with(MainActivity.this).load(R.drawable.puppy4).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy5")){
            Glide.with(MainActivity.this).load(R.drawable.puppy5).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy6")){
            Glide.with(MainActivity.this).load(R.drawable.puppy6).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy7")){
            Glide.with(MainActivity.this).load(R.drawable.puppy7).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy8")){
            Glide.with(MainActivity.this).load(R.drawable.puppy8).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy9")){
            Glide.with(MainActivity.this).load(R.drawable.puppy9).fitCenter().into(pet_add2);
        }
        else if(sf2.getString(userID+"text3","").equals("puppy10")){
            Glide.with(MainActivity.this).load(R.drawable.puppy10).fitCenter().into(pet_add2);
        }
        else {

            byte[] decodedByteArray = Base64.decode(sf2.getString(userID+"text3", ""), Base64.NO_WRAP);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
            Glide.with(MainActivity.this).load(decodedBitmap).fitCenter().into(pet_add2);
            //Glide.with(this).load(decodedBitmap).fitCenter().into(pet_img_talk);

        }

    }

    // 주의사항
    class BackgroundTask_pre extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/pre_List.php";
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

            Intent intent = new Intent(MainActivity.this,preList.class);
            intent.putExtra("preList",result);
            MainActivity.this.startActivity(intent);

        }

    }

    //날짜 DB등록
    public void date_db_add(String today){

        // -------------------- 날짜 DB등록 시작 ----------------
        String dateID = userID;
        String dateDAY = today;
        String dateContent = today_walk.getText().toString();

        if(dateContent.equals("")) {
            Toast.makeText(MainActivity.this, "일정을 압력하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> date_responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("날짜 등록에 성공했습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                                        MainActivity.this.startActivity(intent);

                                        new BackgroundTask_date_btn().execute();
                                        new BackgroundTask_date().execute();
                                        today_walk.setText("");

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("날짜 등록에 실패했습니다.")
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
        Date_AddRequest date_addRequest = new Date_AddRequest(dateID, dateDAY, dateContent, pet_status,date_responseListener);
        RequestQueue date_queue = Volley.newRequestQueue(MainActivity.this);
        date_queue.add(date_addRequest);

        // -------------------- 날짜 DB등록 끝 ------------------

    }

    // 일정 초기화
    public class BackgroundTask_date_delete extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/date_List.php";
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
            user_dateList = new ArrayList<User_Date>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String dateNUM, dateID, dateDAY, dateContent, petNUM;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);
                    dateNUM = object.getString("dateNUM");
                    dateID = object.getString("dateID");
                    dateDAY = object.getString("dateDAY");
                    dateContent = object.getString("dateContent");
                    petNUM = object.getString("petNUM");

                    if(dateID.equals(userID)){

                        User_Date date = new User_Date(dateNUM, dateID, dateDAY, dateContent, petNUM);
                        user_dateList.add(date);

                    }
                    count++;
                }
            }catch(Exception e){
                e.getStackTrace();
            }
            String result_date[] = new String[user_dateList.size()+1];

            for(int i = 0; i<result_date.length-1 ;i++){
                result_date[i] = user_dateList.get(i).getDateDAY();
            }
            result_date[user_dateList.size()] = "2019,03,18";

            new MainActivity.ApiSimulator2(result_date).executeOnExecutor(Executors.newSingleThreadExecutor());



        }

    }

    // 산책일수 초기화
    public class BackgroundTask_date_walk_delete extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/date_walk_List.php";
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
            user_walkList = new ArrayList<User_Date_walk>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String walkID, walkDAY, walkNUM;

                while(count < jsonArray.length()){

                    JSONObject object = jsonArray.getJSONObject(count);

                    walkID = object.getString("walkID");
                    walkDAY = object.getString("walkDAY");
                    walkNUM = object.getString("walkNUM");

                    if(walkID.equals(userID)){


                        User_Date_walk date = new User_Date_walk(walkID, walkDAY, walkNUM);
                        user_walkList.add(date);

                    }
                    count++;
                }
            }catch(Exception e){
                e.getStackTrace();
            }
            String result_date[] = new String[user_walkList.size()+1];

            for(int i = 0; i<result_date.length-1 ;i++){
                result_date[i] = user_walkList.get(i).getWalkDAY();
            }
            result_date[user_walkList.size()] = "2019,03,18";

            new MainActivity.ApiSimulator4(result_date).executeOnExecutor(Executors.newSingleThreadExecutor());



        }

    }

    // 심심이 리스트 불러오기
    class BackgroundTask_pet_talk extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "https://tlsalsrn1.cafe24.com/talk_List.php";
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
            talk_lists = new ArrayList<Talk_List>();
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count= 0;
                String talkNUM, talkTITLE, talkTITLE2, talkTITLE3, talkCONTENT;

                while(count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);
                    talkNUM = object.getString("talkNUM");
                    talkTITLE = object.getString("talkTITLE");
                    talkTITLE2 = object.getString("talkTITLE2");
                    talkTITLE3 = object.getString("talkTITLE3");
                    talkCONTENT = object.getString("talkCONTENT");

                    Talk_List talk_list = new Talk_List(talkNUM, talkTITLE, talkTITLE2, talkTITLE3, talkCONTENT);
                    talk_lists.add(talk_list);


                    count++;


                }


            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

    // 비트맵을 문자열로
    public String getBase64String(Bitmap bitmap)
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

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

            Intent intent = new Intent(MainActivity.this,pet_photo.class);
            intent.putExtra("photoList",result);
            intent.putExtra("userID",userID);
            MainActivity.this.startActivity(intent);

        }

    }

    //알람 매니저
    public void alram(){

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("userID",userID);

        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);


        // 알람을 받을 시간을 5초 뒤로 설정

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

//      calendar.add(Calendar.SECOND, 5);

        calendar.set(Calendar.HOUR_OF_DAY, 23); // calendar.set(Calendar.HOUR_OF_DAY, 시간(int)); 저녁10시->22시

        calendar.set(Calendar.MINUTE, 59); //calendar.set(Calendar.MINUTE, 분(int));

        calendar.set(Calendar.SECOND, 59);

//      calendar.set(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.DATE), 18, 57, 0);


        // 알람 매니저에 알람을 등록

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 86400000,sender);


    }

    //알람 매니저
    public void nofication_alram(){

        Intent intent = new Intent(MainActivity.this, AlarmReceiver_nofi.class);
        intent.putExtra("address",address);
        intent.putExtra("temp",temp);
        intent.putExtra("weather",weather);
        intent.putExtra("temp2","dada");

        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);


        // 알람을 받을 시간을 5초 뒤로 설정

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

//        calendar.add(Calendar.SECOND, 5);

        calendar.set(Calendar.HOUR_OF_DAY, 12); // calendar.set(Calendar.HOUR_OF_DAY, 시간(int)); 저녁10시->22시

        calendar.set(Calendar.MINUTE, 59); //calendar.set(Calendar.MINUTE, 분(int));

        calendar.set(Calendar.SECOND, 59);

//      calendar.set(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.DATE), 18, 57, 0);


        // 알람 매니저에 알람을 등록

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 3600000,sender);


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

    //메인 펫 사진 랜덤으로 변경하기
    public void random_puppy(){

        int view4_random = (int)(Math.random()*8)+1;
        if(view4_random == 1) {
            Glide.with(this).load(R.drawable.puppy1).fitCenter().into(view4_img_main);
        }
        else if(view4_random == 2){
            Glide.with(this).load(R.drawable.puppy2).fitCenter().into(view4_img_main);
        }
        else if(view4_random == 3){
            Glide.with(this).load(R.drawable.puppy3).fitCenter().into(view4_img_main);
        }
        else if(view4_random == 4){
            Glide.with(this).load(R.drawable.puppy4).fitCenter().into(view4_img_main);
        }
        else if(view4_random == 5){
            Glide.with(this).load(R.drawable.puppy5).fitCenter().into(view4_img_main);
        }
        else if(view4_random == 6){
            Glide.with(this).load(R.drawable.puppy6).fitCenter().into(view4_img_main);
        }
        else if(view4_random == 7){
            Glide.with(this).load(R.drawable.puppy7).fitCenter().into(view4_img_main);
        }
        else if(view4_random == 8){
            Glide.with(this).load(R.drawable.puppy8).fitCenter().into(view4_img_main);
        }

    }

    // 산책 일수 삭제
    public void date_Delete(int date){
        int dateNUM = date;
        // 특정요청후 리스너에서 원하는 결과값을 다룰 수 있게 함
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);  // 결과를 담을 수 있게 함
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("삭제 성공")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                                        MainActivity.this.startActivity(intent);
                                        new BackgroundTask_date_btn().execute();
                                        new BackgroundTask_date_delete().execute();
                                        new BackgroundTask_date().execute();

                                    }
                                })
                                .create()
                                .show();

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

        date_DeleteRequest pet_deleteWalkRequest = new date_DeleteRequest(dateNUM, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(pet_deleteWalkRequest);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // 컨텍스트 메뉴가 최초로 한번만 호출되는 콜백 메서드

//        menu.setHeaderTitle("프로필사진 변경");
        menu.add(0,1,100,"카메라로 프로필 변경");
        menu.add(0,2,100,"앨범에서 프로필 변경");
        menu.add(0,3,100,"기본사진 프로필 변경");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 롱클릭했을 때 나오는 context Menu 의 항목을 선택(클릭) 했을 때 호출
        switch(item.getItemId()) {
            case 1 :// 빨강 메뉴 선택시
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent2,REQUEST_IMAGE_CAPTURE);
                return true;
            case 2 :// 녹색 메뉴 선택시
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
                return true;
            case 3 :// 파랑 메뉴 선택시
//                tv.setTextColor(Color.BLUE);
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                CustomImage customDialog = new CustomImage(MainActivity.this);

                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction(pet_imgbtn, userID, pet_status);

                return true;
        }

        return super.onContextItemSelected(item);
    }

    public void random_talk(){
        Random ran = new Random();

        int random2 = (ran.nextInt(11)+1);

        if(random2 == 2){
            SimpleDateFormat format1 = new SimpleDateFormat ( "HH:mm");

            Calendar time = Calendar.getInstance();

            String format_time1 = format1.format(time.getTime());

            pet_talkLists.add(new pet_TalkList("반가워","pet",format_time1));
            talk_adapter.notifyDataSetChanged();
        }
        else if(random2 == 5 ){
            SimpleDateFormat format1 = new SimpleDateFormat ( "HH:mm");

            Calendar time = Calendar.getInstance();

            String format_time1 = format1.format(time.getTime());

            pet_talkLists.add(new pet_TalkList("심심해 ㅠㅠ","pet",format_time1));
            talk_adapter.notifyDataSetChanged();
        }
        else if(random2 == 7){
            SimpleDateFormat format1 = new SimpleDateFormat ( "HH:mm");

            Calendar time = Calendar.getInstance();

            String format_time1 = format1.format(time.getTime());

            pet_talkLists.add(new pet_TalkList("뭐해??","pet",format_time1));
            talk_adapter.notifyDataSetChanged();
        }

    }




}