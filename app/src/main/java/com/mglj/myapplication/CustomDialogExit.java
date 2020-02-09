package com.mglj.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class CustomDialogExit extends AppCompatActivity {

    private Context context;

    public CustomDialogExit(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog_exit);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        ImageView exit_img = (ImageView)dlg.findViewById(R.id.exit_img);
        Glide.with(context).load(R.drawable.sub_img).fitCenter().into(exit_img);

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.

        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);



        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.

                AppFinish();

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

    //앱종료
    public void AppFinish(){


        moveTaskToBack(true);
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());

    }



}
