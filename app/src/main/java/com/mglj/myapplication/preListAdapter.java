package com.mglj.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class preListAdapter extends BaseAdapter {

    private Context context;
    private List<DB_preList> db_preLists;

    public preListAdapter(Context context, List<DB_preList> db_preLists) {
        this.context = context;
        this.db_preLists = db_preLists;

    }

    @Override
    public int getCount() {
        return db_preLists.size();
    }

    @Override
    public Object getItem(int i) {
        return db_preLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.precautions, null);
        final TextView preNum = (TextView) v.findViewById(R.id.preNUM);
        TextView preTITLE = (TextView) v.findViewById(R.id.preTITLE);
        TextView preCONTENT = (TextView) v.findViewById(R.id.preCONTENT);
        TextView preDATE = (TextView)v.findViewById(R.id.preDATE);

        preNum.setText(db_preLists.get(i).getPreNum());
        preTITLE.setText(db_preLists.get(i).getPreTITLE());
        preCONTENT.setText(db_preLists.get(i).getPreCONTENT());
        preDATE.setText(db_preLists.get(i).getPreDATE().substring(0,10));

        v.setTag(db_preLists.get(i).getPreNum());

        return v;
    }
}

