package com.mglj.myapplication;

import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import java.util.ArrayList;

public class SessionCallback implements ISessionCallback {

    ArrayList<User> userList;
    String kakao_id;
    public static String kakao_ID;
    public static int kakao_status;
    // 로그인에 성공한 상태
    @Override

    public void onSessionOpened() {

        requestMe();


    }




    // 로그인에 실패한 상태

    @Override

    public void onSessionOpenFailed(KakaoException exception) {

        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());

    }




    // 사용자 정보 요청

    public void requestMe() {

        // 사용자정보 요청 결과에 대한 Callback

        UserManagement.requestMe(new MeResponseCallback() {

            // 세션 오픈 실패. 세션이 삭제된 경우,

            @Override

            public void onSessionClosed(ErrorResult errorResult) {

                Log.e("SessionCallback :: ", "onSessionClosed : " + errorResult.getErrorMessage());

            }




            // 회원이 아닌 경우,

            @Override

            public void onNotSignedUp() {

                Log.e("SessionCallback :: ", "onNotSignedUp");

            }



            // 사용자정보 요청에 성공한 경우,

            @Override

            public void onSuccess(UserProfile userProfile) {

                kakao_id = String.valueOf(userProfile.getId());
                kakao_ID = String.valueOf(userProfile.getId());
                kakao_status = 1;
                Log.e("SessionCallback :: ", "onSuccess");

                String nickname = userProfile.getNickname();

                String email = userProfile.getEmail();

                String profileImagePath = userProfile.getProfileImagePath();

                String thumnailPath = userProfile.getThumbnailImagePath();

                String UUID = userProfile.getUUID();

                long id = userProfile.getId();

                Log.e("Profile : ", nickname + "닉네임");

                Log.e("Profile : ", email + "이메일");

                Log.e("Profile : ", profileImagePath  + "이미지");

                Log.e("Profile : ", thumnailPath + "");

                Log.e("Profile : ", UUID + "uuid");

                Log.e("Profile : ", id + "id");








            }




            // 사용자 정보 요청 실패

            @Override

            public void onFailure(ErrorResult errorResult) {

                Log.e("SessionCallback :: ", "onFailure : " + errorResult.getErrorMessage());

            }

        });

    }




}
