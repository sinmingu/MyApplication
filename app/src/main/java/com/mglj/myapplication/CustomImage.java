package com.mglj.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static android.content.Context.MODE_PRIVATE;

public class CustomImage {

    private Context context;

    public CustomImage(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(final de.hdodenhof.circleimageview.CircleImageView pet_imgbtn, final String userID, final int pet_status) {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog_image);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();
        final int petnum = pet_status;

        final String ID = userID;

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final CheckBox pet_sex1 = (CheckBox) dlg.findViewById(R.id.pet_sex1);
        final CheckBox pet_sex2 = (CheckBox) dlg.findViewById(R.id.pet_sex2);
        final CheckBox pet_sex3 = (CheckBox) dlg.findViewById(R.id.pet_sex3);
        final CheckBox pet_sex4 = (CheckBox) dlg.findViewById(R.id.pet_sex4);
        final CheckBox pet_sex5 = (CheckBox) dlg.findViewById(R.id.pet_sex5);
        final CheckBox pet_sex6 = (CheckBox) dlg.findViewById(R.id.pet_sex6);
        final CheckBox pet_sex7 = (CheckBox) dlg.findViewById(R.id.pet_sex7);
        final CheckBox pet_sex8 = (CheckBox) dlg.findViewById(R.id.pet_sex8);
        final CheckBox pet_sex9 = (CheckBox) dlg.findViewById(R.id.pet_sex9);
        final CheckBox pet_sex10 = (CheckBox) dlg.findViewById(R.id.pet_sex10);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);
        final ImageView img_01 = (ImageView)dlg.findViewById(R.id.img_01);
        final ImageView img_02 = (ImageView)dlg.findViewById(R.id.img_02);
        final ImageView img_03 = (ImageView)dlg.findViewById(R.id.img_03);
        final ImageView img_04 = (ImageView)dlg.findViewById(R.id.img_04);
        final ImageView img_05 = (ImageView)dlg.findViewById(R.id.img_05);
        final ImageView img_06 = (ImageView)dlg.findViewById(R.id.img_06);
        final ImageView img_07 = (ImageView)dlg.findViewById(R.id.img_07);
        final ImageView img_08 = (ImageView)dlg.findViewById(R.id.img_08);
        final ImageView img_09 = (ImageView)dlg.findViewById(R.id.img_09);
        final ImageView img_10 = (ImageView)dlg.findViewById(R.id.img_10);


        Glide.with(context).load(R.drawable.puppy1).fitCenter().into(img_01);
        Glide.with(context).load(R.drawable.puppy2).fitCenter().into(img_02);
        Glide.with(context).load(R.drawable.puppy3).fitCenter().into(img_03);
        Glide.with(context).load(R.drawable.puppy4).fitCenter().into(img_04);
        Glide.with(context).load(R.drawable.puppy5).fitCenter().into(img_05);
        Glide.with(context).load(R.drawable.puppy6).fitCenter().into(img_06);
        Glide.with(context).load(R.drawable.puppy7).fitCenter().into(img_07);
        Glide.with(context).load(R.drawable.puppy8).fitCenter().into(img_08);
        Glide.with(context).load(R.drawable.puppy9).fitCenter().into(img_09);
        Glide.with(context).load(R.drawable.puppy10).fitCenter().into(img_10);

        pet_sex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });

        pet_sex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex1.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });

        pet_sex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });

        pet_sex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });

        pet_sex5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });

        pet_sex6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });

        pet_sex7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });

        pet_sex8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });
        pet_sex9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex8.setChecked(false);
                pet_sex10.setChecked(false);
            }
        });
        pet_sex10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_sex2.setChecked(false);
                pet_sex3.setChecked(false);
                pet_sex4.setChecked(false);
                pet_sex5.setChecked(false);
                pet_sex6.setChecked(false);
                pet_sex7.setChecked(false);
                pet_sex1.setChecked(false);
                pet_sex9.setChecked(false);
                pet_sex8.setChecked(false);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.

                if(petnum==1){
                    if(pet_sex1.isChecked()){
                        Glide.with(context).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy1"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex2.isChecked()){
                        Glide.with(context).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy2"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex3.isChecked()){
                        Glide.with(context).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy3"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex4.isChecked()){
                        Glide.with(context).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy4"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex5.isChecked()){
                        Glide.with(context).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy5"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex6.isChecked()){
                        Glide.with(context).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy6"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex7.isChecked()){
                        Glide.with(context).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy7"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex8.isChecked()){
                        Glide.with(context).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy8"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex9.isChecked()){
                        Glide.with(context).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy9"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex10.isChecked()){
                        Glide.with(context).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text","puppy10"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }

                }
                else if(petnum==2){
                    if(pet_sex1.isChecked()){
                        Glide.with(context).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy1"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex2.isChecked()){
                        Glide.with(context).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy2"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex3.isChecked()){
                        Glide.with(context).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy3"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex4.isChecked()){
                        Glide.with(context).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy4"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex5.isChecked()){
                        Glide.with(context).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy5"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex6.isChecked()){
                        Glide.with(context).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy6"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex7.isChecked()){
                        Glide.with(context).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy7"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex8.isChecked()){
                        Glide.with(context).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy8"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex9.isChecked()){
                        Glide.with(context).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy9"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex10.isChecked()){
                        Glide.with(context).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text2","puppy10"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                }
                else if(petnum==3){
                    if(pet_sex1.isChecked()){
                        Glide.with(context).load(R.drawable.puppy1).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy1"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex2.isChecked()){
                        Glide.with(context).load(R.drawable.puppy2).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy2"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex3.isChecked()){
                        Glide.with(context).load(R.drawable.puppy3).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy3"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex4.isChecked()){
                        Glide.with(context).load(R.drawable.puppy4).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy4"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex5.isChecked()){
                        Glide.with(context).load(R.drawable.puppy5).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy5"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex6.isChecked()){
                        Glide.with(context).load(R.drawable.puppy6).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy6"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex7.isChecked()){
                        Glide.with(context).load(R.drawable.puppy7).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy7"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex8.isChecked()){
                        Glide.with(context).load(R.drawable.puppy8).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy8"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex9.isChecked()){
                        Glide.with(context).load(R.drawable.puppy9).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy9"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                    else if(pet_sex10.isChecked()){
                        Glide.with(context).load(R.drawable.puppy10).fitCenter().into(pet_imgbtn);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("imgFile",MODE_PRIVATE);
                        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(ID+"text3","puppy10"); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                    }
                }






                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }
}
