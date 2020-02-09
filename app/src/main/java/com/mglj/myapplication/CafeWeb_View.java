package com.mglj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CafeWeb_View extends AppCompatActivity {

    WebView web_view;
    ImageView map_cancle, map_can;

    private String myUrl = "http://";// 접속 URL (내장HTML의 경우 왼쪽과 같이 쓰고 아니면 걍 URL)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_web__view);

        Intent intent = getIntent();

        map_cancle = (ImageView)findViewById(R.id.map_cancle);
        map_can = (ImageView)findViewById(R.id.map_can);

        Glide.with(this).load(R.drawable.arrow_white).fitCenter().into(map_cancle);
        Glide.with(this).load(R.drawable.arrow_left).fitCenter().into(map_can);

        web_view=(WebView)findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용
        web_view.loadUrl(intent.getStringExtra("url"));//웹뷰 실행
        web_view.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 alert가 뜨지 않음
        web_view.setWebViewClient(new WebViewClientClass());//새창열기 없이 웹뷰 내에서 다시 열기//페이지 이동 원활히

        map_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web_view.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            web_view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }

}
