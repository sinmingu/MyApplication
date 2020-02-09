package com.mglj.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class PhotoListAdapter extends BaseAdapter {

    private Context context;
    private List<DB_photo> db_photoList;

    Handler handler = new Handler();  // 외부쓰레드 에서 메인 UI화면을


    public PhotoListAdapter(Context context, List<DB_photo> db_photoList) {

        this.context = context;
        this.db_photoList = db_photoList;

    }

    @Override
    public int getCount() {
        return db_photoList.size();
    }

    @Override
    public Object getItem(int i) {
        return db_photoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final View v = View.inflate(context, R.layout.photo, null);
        final TextView photoNum = (TextView) v.findViewById(R.id.photoNUM);
        TextView photoID = (TextView)v.findViewById(R.id.photoID);
        TextView photoTITLE = (TextView) v.findViewById(R.id.photoTITLE);
        TextView photoCONTENT = (TextView) v.findViewById(R.id.photoCONTENT);
        TextView photoDATE = (TextView)v.findViewById(R.id.photoDATE);
        TextView photoNAME = (TextView)v.findViewById(R.id.photoNAME);
        ImageView photoIMG = (ImageView)v.findViewById(R.id.photoIMG);

        photoNum.setText(db_photoList.get(i).getPhotoNUM());
        photoID.setText(db_photoList.get(i).getPhotoID());
        photoTITLE.setText(db_photoList.get(i).getPhotoTITLE());
        photoCONTENT.setText(db_photoList.get(i).getPhotoCONTENT());
        photoDATE.setText(db_photoList.get(i).getPhotoDATE().substring(0,10));

//        photoNAME.setText(db_photoList.get(i).getPhotoNAME().substring(1,10));
//        photoNAME.setText(db_photoList.get(i).getPhotoNAME());
//
//        byte[] decodedByteArray = Base64.decode(db_photoList.get(i).getPhotoNAME(), Base64.NO_WRAP);
//        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
//        Glide.with(context).load(decodedBitmap).fitCenter().into(photoIMG);

        final String url2 = db_photoList.get(i).getPhotoNAME();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한다
                // TODO Auto-generated method stub
                try{
                    final ImageView iv = (ImageView)v.findViewById(R.id.photoIMG);
                    URL url = new URL(url2);
                    InputStream is = url.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    handler.post(new Runnable() {

                        @Override
                        public void run() {  // 화면에 그려줄 작업
                            Glide.with(context).load(bm).fitCenter().into(iv);
//                            iv.setImageBitmap(bm);
                        }
                    });
//                    iv.setImageBitmap(bm); //비트맵 객체로 보여주기
                } catch(Exception e){

                }

            }
        });

        t.start();



        v.setTag(db_photoList.get(i).getPhotoNUM());

        return v;

    }

    // 비트맵을 문자열로
    public String getBase64String(Bitmap bitmap)
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);

    }
}