package com.mglj.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<DB_Notice> db_noticeList;

    public NoticeListAdapter(Context context, List<DB_Notice> db_noticeList) {

        this.context = context;
        this.db_noticeList = db_noticeList;

    }

    @Override
    public int getCount() {
        return db_noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return db_noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.notice, null);
        final TextView noNum = (TextView) v.findViewById(R.id.noNUM);
        TextView noTITLE = (TextView) v.findViewById(R.id.noTITLE);
        TextView noCONTENT = (TextView) v.findViewById(R.id.noCONTENT);
        TextView noDATE = (TextView)v.findViewById(R.id.noDATE);
        TextView noSTATUS = (TextView)v.findViewById(R.id.noSTATUS);

        noNum.setText(db_noticeList.get(i).getNoNUM());
        noTITLE.setText(db_noticeList.get(i).getNoTITLE());
        noCONTENT.setText(db_noticeList.get(i).getNoCONTENT());
        noDATE.setText(db_noticeList.get(i).getNoDATE().substring(0,10));
        if(!db_noticeList.get(i).getNoSTATUS().equals("null")) {
            noSTATUS.setText(db_noticeList.get(i).getNoSTATUS());
        }

        v.setTag(db_noticeList.get(i).getNoNUM());

        return v;
    }
}
