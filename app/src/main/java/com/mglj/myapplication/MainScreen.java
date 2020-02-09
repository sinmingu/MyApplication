package com.mglj.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.kakao.util.helper.Utility.getPackageInfo;
import static com.mglj.myapplication.SessionCallback.kakao_ID;
import static com.mglj.myapplication.SessionCallback.kakao_status;

public class MainScreen extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener{

    public static int Register_status = 0;
    ArrayList<User> userList;
    String email;
    String googleID;
   //카카오
   private SessionCallback callback;
   Button btn_custom_login, btn_custom_login2, btn_custom_login3;

   ImageView id_img, pw_img, img_logo;

   LoginButton btn_kakao_login;
   OAuthLoginButton buttonNaverLogin;
   SignInButton Google_Login;
    /**
     * 로그인 버튼을 클릭 했을시 access token을 요청하도록 설정한다.
     *
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */

    //네이버 변수
    private OAuthLoginButton naverLogInButton;
    private static OAuthLogin naverLoginInstance;
    Button btnGetApi, btnLogout;
    // 네이버 변수
    static final String CLIENT_ID = "5mzfNM7GRSbF2zDUBkO_";
    static final String CLIENT_SECRET = "8dmJCOsbLc";
    static final String CLIENT_NAME = "네이버 아이디로 로그인 테스트";

    // 구글 변수
    private static final int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    TextView tv_mail; // 네이버 로그인 아이디
    static Context context;

    double latitude;
    double longitude;
    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    private static final String TAG = "";

    TextView move_loginAc, move_RegiAc; // 로그인, 회원가입 버튼

    long backKeyPressedTime;    //앱종료 위한 백버튼 누른시간

    Button login_btn, pet_btn;
    EditText userId, userPw;

    CheckBox auto_login;

    TextView test_hash;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // 카카오 시작
//        KakaoSDK.init(new GlobalApplication.KakaoSDKAdapter());
//
//        callback = new SessionCallback();
//        Session.getCurrentSession().addCallback(callback);
//        Session.getCurrentSession().checkAndImplicitOpen();


        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {

            checkRunTimePermission();
        }


        id_img = (ImageView)findViewById(R.id.id_img);
        pw_img = (ImageView)findViewById(R.id.pw_img);

        Glide.with(this).load(R.drawable.id).fitCenter().into(id_img);
        Glide.with(this).load(R.drawable.pass).fitCenter().into(pw_img);

        btn_custom_login = (Button)findViewById(R.id.btn_custom_login);
        btn_custom_login2 = (Button)findViewById(R.id.btn_custom_login2);
        btn_custom_login3 = (Button)findViewById(R.id.btn_custom_login3) ;
        test_hash = (TextView)findViewById(R.id.test_hash);
        btn_custom_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session session = Session.getCurrentSession();
                session.addCallback(new SessionCallback());
                session.open(AuthType.KAKAO_LOGIN_ALL, MainScreen.this);
                if(kakao_status==0) {
                    Toast.makeText(getApplicationContext(), "카카오톡 로그인버튼을 다시 눌러주세요", Toast.LENGTH_SHORT).show();
                }
                btn_kakao_login.performClick();

                if(kakao_status == 1){
                    new BackgroundTask_user_screen_kakao().execute();


                }

            }
        });
        btn_kakao_login = (LoginButton)findViewById(R.id.btn_kakao_login);



        btn_custom_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonNaverLogin.performClick();
            }
        });
        buttonNaverLogin = (OAuthLoginButton)findViewById(R.id.buttonNaverLogin);

        btn_custom_login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Google_Login.performClick();
//                Toast.makeText(getApplicationContext(),"dd",Toast.LENGTH_SHORT).show();

            }
        });
        Google_Login = (SignInButton)findViewById(R.id.Google_Login);

        TextView textView = (TextView) Google_Login.getChildAt(0);
        textView.setText("");

        Button button = (Button)Google_Login.getChildAt(0);
        button.setTextColor(Color.BLACK);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.google));
        button.setAnimation(null);
        button.setTextSize(14);
//        button.setText("구글계정으로 로그인");
//        Typeface typeface = getResources().getFont(R.font.test3);
//        button.setTypeface(typeface);

        // 카카오 끝


        move_RegiAc = (TextView)findViewById(R.id.move_RegiAc);


        btnLogout = (Button)findViewById(R.id.btnlogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                signOut(); // 구글 로그아웃
                naverLoginInstance.logout(context);
                UserManagement.requestLogout(new LogoutResponseCallback() {

                    @Override
                    public void onCompleteLogout() {
                        kakao_ID = "";
                        kakao_status = 0 ;
                    }

                });

            }
        });

//        String test = getKeyHash(getApplicationContext());
//        test_hash.setText(test+"");

        // 회원가입 창으로
        move_RegiAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask_regi().execute();
            }
        });

        // 네이버 로그인
        init();
        init_View();

        // 네이버 로그인 끝

        // 구글 로그인
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        mAuth = FirebaseAuth.getInstance();
        Google_Login = findViewById(R.id.Google_Login);
        Google_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent,RC_SIGN_IN);

            }
        });

        // 구글 로그인 끝

        // 자동로그인
        SharedPreferences sf = getSharedPreferences("LoginUser",MODE_PRIVATE);

        if(!sf.getString("userID","").equals("")){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("userID", sf.getString("userID",""));
            intent.putExtra("userPassword", sf.getString("userPassword",""));
            intent.putExtra("userName", sf.getString("userName",""));
            startActivity(intent);
        }


        //  ----------------- 로그인 화면 --------------------

        userId = (EditText)findViewById(R.id.userId);
        userPw = (EditText)findViewById(R.id.userPw);

        login_btn = (Button)findViewById(R.id.login_btn);
        pet_btn = (Button)findViewById(R.id.pet_btn);

        auto_login = (CheckBox)findViewById(R.id.auto_login);

        // 로그인 버튼
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                final ProgressDialog progressDialog = new ProgressDialog(MainScreen.this);
                                progressDialog.setIndeterminate(true);
                                progressDialog.setMessage("로그인 요청중입니다.");
                                progressDialog.show();


                                final String userID = userId.getText().toString();
                                String userPassword = userPw.getText().toString();


                                Response.Listener<String>  responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try{
                                            JSONObject jsonReponse = new JSONObject(response);
                                            boolean success = jsonReponse.getBoolean("success");
                                            if(success){

                                                // 관리자 아이디면 관리자 페이지로 이동
                                                if(userID.equals("admin")){

                                                    new BackgroundTask().execute();
//                                    Intent intent_admin = new Intent(getApplicationContext(), Management.class);
//                                    startActivity(intent_admin);

                                                }
                                                // 로그인 페이지로 이동
                                                else {


                                                    if(auto_login.isChecked()==true){

                                                        String userID = jsonReponse.getString("userID");
                                                        String userPassword = jsonReponse.getString("userPassword");
                                                        String userName = jsonReponse.getString("userName");
                                                        Intent intent = new Intent(MainScreen.this, MainActivity.class);
                                                        intent.putExtra("userID", userID);
                                                        intent.putExtra("userPassword", userPassword);
                                                        intent.putExtra("userName", userName);

                                                        SharedPreferences sharedPreferences = getSharedPreferences("LoginUser",MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                                        editor.putString("userID",userID); // key, value를 이용하여 저장하는 형태
                                                        editor.putString("userPassword",userPassword);
                                                        editor.putString("userName",userName);
                                                        editor.commit();
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                                        MainScreen.this.startActivity(intent);

                                                    }
                                                    else {
                                                        String userID = jsonReponse.getString("userID");
                                                        String userPassword = jsonReponse.getString("userPassword");
                                                        String userName = jsonReponse.getString("userName");
                                                        Intent intent = new Intent(MainScreen.this, MainActivity.class);
                                                        intent.putExtra("userID", userID);
                                                        intent.putExtra("userPassword", userPassword);
                                                        intent.putExtra("userName", userName);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        MainScreen.this.startActivity(intent);
                                                    }


                                                }

                                            }
                                            else{
                                                AlertDialog.Builder builder = new AlertDialog.Builder(MainScreen.this);
                                                builder.setMessage("로그인에 실패했습니다.")
                                                        .setNegativeButton("다시시도",null)
                                                        .create()
                                                        .show();

                                                progressDialog.dismiss();

                                            }

                                        }
                                        catch(Exception e){
                                            e.getStackTrace();
                                        }
                                    }
                                };

                                LoginRequest loginRequest = new LoginRequest(userID,userPassword,responseListener);
                                RequestQueue queue  = Volley.newRequestQueue(MainScreen.this);
                                queue.add(loginRequest);




                            }
                        }, 100);



//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);

            }
        });



    }

    //뒤로가기 2번하면 앱종료
    @Override
    public void onBackPressed() {

        // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
        CustomDialogExit customDialog = new CustomDialogExit(MainScreen.this);

        // 커스텀 다이얼로그를 호출한다.
        // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.


        customDialog.callFunction();


//        //1번째 백버튼 클릭
//        if(System.currentTimeMillis()>backKeyPressedTime+2000){
//            backKeyPressedTime = System.currentTimeMillis();
//            Toast.makeText(this, "한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
//        }
//        //2번째 백버튼 클릭 (종료)
//        else{
//            AppFinish();
//        }

    }

    //앱종료
    public void AppFinish(){
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    //초기화(네이버 로그인)
    private void init(){

        context = this;
        naverLoginInstance = OAuthLogin.getInstance();
        naverLoginInstance.init(this,CLIENT_ID,CLIENT_SECRET,CLIENT_NAME);

    }

    //변수 붙이기(네이버 로그인)
    private void init_View(){
        naverLogInButton = (OAuthLoginButton)findViewById(R.id.buttonNaverLogin);

        //로그인 핸들러
        OAuthLoginHandler naverLoginHandler  = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {//로그인 성공
                    Toast.makeText(context,"로그인 성공",Toast.LENGTH_SHORT).show();

                    //------ 수정
                    new RequestApiTask().execute();

                } else {//로그인 실패
                    String errorCode = naverLoginInstance.getLastErrorCode(context).getCode();
                    String errorDesc = naverLoginInstance.getLastErrorDesc(context);
                    //Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                }
            }

        };;

        naverLogInButton.setOAuthLoginHandler(naverLoginHandler);

    }

    private class RequestApiTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {//작업이 실행되기 전에 먼저 실행.

        }

        @Override
        protected String doInBackground(Void... params) {//네트워크에 연결하는 과정이 있으므로 다른 스레드에서 실행되어야 한다.
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = naverLoginInstance.getAccessToken(context);
            return naverLoginInstance.requestApi(context, at, url);//url, 토큰을 넘겨서 값을 받아온다.json 타입으로 받아진다.
        }

        protected void onPostExecute(String content) {//doInBackground 에서 리턴된 값이 여기로 들어온다.
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONObject response = jsonObject.getJSONObject("response");
                email = response.getString("email");



                // 수정 ---------------------------------
                new BackgroundTask_user_screen().execute();
                // 수정 끝 -------------------------------


            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // 구글 로그인
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                googleID = account.getEmail();
                new BackgroundTask_user_screen_google().execute();

            }
            else{
            }
        }
        //카카오
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainScreen.this, "인증 실패", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainScreen.this, "구글 로그인 인증 성공", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // 해쉬값 가져오는 메소드 -------------------------------------------
    private void getHashKey(){

        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }

    }
    // 해쉬값 가져오는 메소드 끝 ------------------------------------

    // 구글 로그아웃
    public void signOut() {

        mGoogleApiClient.connect();

        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {

            @Override
            public void onConnected(@Nullable Bundle bundle) {

                mAuth.signOut();
                if (mGoogleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {

                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.v("알림", "구글 로그아웃 성공");
//                                Toast.makeText(getApplicationContext(), "로그아웃 성공", Toast.LENGTH_SHORT).show();
                                setResult(1);
                            } else {
                                setResult(0);
                            }
                           //finish();
                        }
                    });
                }
            }
            @Override
            public void onConnectionSuspended(int i) {
                Log.v("알림", "Google API Client Connection Suspended");
                setResult(-1);
                finish();
            }
        });
    }


//----------------------- 카카오 시작
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Session.getCurrentSession().removeCallback(callback);
//    }
//    private class SessionCallback implements ISessionCallback {
//        @Override
//        public void onSessionOpened() {
//            redirectSignupActivity();
//        }
//        @Override
//        public void onSessionOpenFailed(KakaoException exception) {
//            if (exception != null) {
//                Logger.e(exception);
//            }
//        }
//    }
//    protected void redirectSignupActivity() {
//        final Intent intent = new Intent(this, MainScreen.class);
//        startActivity(intent);
//        finish();
//    }

//----------------------- 카카오 끝끝

    // 유저 정보 불러오기 // 네이버 로그인
    class BackgroundTask_user_screen extends AsyncTask<Void, Void, String> {

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

                    if (userID.equals(email)) {
                        User user = new User(userID, userPassword, userName, userAge, userHeight, userWeight);
                        userList.add(user);
                    }

                    count++;

                }

                if(userList.size()==0){
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    intent.putExtra("email",email);
                    Register_status=1;
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("userID",userList.get(0).getUserID());
                    intent.putExtra("userName",userList.get(0).getUserName());
                    intent.putExtra("userPassword", userList.get(0).getUserPassword());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }




            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

    // 유저 정보 불러오기 // 구글 로그인
    class BackgroundTask_user_screen_google extends AsyncTask<Void, Void, String> {

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

                    if (userID.equals(googleID)) {
                        User user = new User(userID, userPassword, userName, userAge, userHeight, userWeight);
                        userList.add(user);
                    }

                    count++;

                }

                if(userList.size()==0){
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    intent.putExtra("email",googleID);
                    Register_status = 1;
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("userID",userList.get(0).getUserID());
                    intent.putExtra("userPassword", userList.get(0).getUserPassword());
                    intent.putExtra("userName", userList.get(0).getUserName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }




            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }

    // 유저 정보 불러오기 // 구글 로그인
    class BackgroundTask_user_screen_kakao extends AsyncTask<Void, Void, String> {

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

                    if (userID.equals(kakao_ID)) {
                        User user = new User(userID, userPassword, userName, userAge, userHeight, userWeight);
                        userList.add(user);
                    }

                    count++;

                }

                if(userList.size()==0){
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    intent.putExtra("email",kakao_ID);
                    Register_status = 1;
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("userID",userList.get(0).getUserID());
                    intent.putExtra("userPassword", userList.get(0).getUserPassword());
                    intent.putExtra("userName", userList.get(0).getUserName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }




            }catch(Exception e){
                e.getStackTrace();
            }

        }

    }


    @Override
    protected void onRestart() {

        signOut(); // 구글 로그아웃
        naverLoginInstance.logout(context);

        UserManagement.requestLogout(new LogoutResponseCallback() {

            @Override
            public void onCompleteLogout() {
                kakao_ID = "";
                kakao_status = 0 ;
            }

        });

        super.onRestart();
    }


    // 회원 목록 불러오기
    class BackgroundTask extends AsyncTask<Void, Void, String> {

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
            Intent intent = new Intent(MainScreen.this,Management.class);
            intent.putExtra("userList",result);
            MainScreen.this.startActivity(intent);
        }

    }
    // 회원 목록 불러오기 끝

    // 회원 목록 불러오기
    class BackgroundTask_regi extends AsyncTask<Void, Void, String> {

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
            Intent intent = new Intent(MainScreen.this,RegisterActivity.class);
            intent.putExtra("userList",result);
            MainScreen.this.startActivity(intent);
        }
    }
    // 회원 목록 불러오기 끝

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

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

                    Toast.makeText(MainScreen.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(MainScreen.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainScreen.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainScreen.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainScreen.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainScreen.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainScreen.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainScreen.this, REQUIRED_PERMISSIONS,
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

        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreen.this);
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
}
