package com.mglj.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class talkListAdapter extends BaseAdapter {

    private Context context;
    private List<pet_TalkList> talk_lists;

    public talkListAdapter(Context context, List<pet_TalkList> talk_lists) {

        this.context = context;
        this.talk_lists = talk_lists;

    }

    @Override
    public int getCount() {
        return talk_lists.size();
    }

    @Override
    public Object getItem(int i) {
        return talk_lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.pet_talk, null);
        LinearLayout user_talk_list, pet_talk_list;

        user_talk_list = (LinearLayout)v.findViewById(R.id.user_talk_list);
        pet_talk_list = (LinearLayout)v.findViewById(R.id.pet_talk_list);

        TextView talk_date = (TextView) v.findViewById(R.id.talk_date);
        ImageView talk_img = (ImageView) v.findViewById(R.id.talk_img);
        TextView talk_content = (TextView) v.findViewById(R.id.talk_content);

        TextView user_talk_date = (TextView) v.findViewById(R.id.user_talk_date);

        TextView user_talk_content = (TextView) v.findViewById(R.id.user_talk_content);

        talk_content.setText(talk_lists.get(i).getTalkContent());
        talk_date.setText(talk_lists.get(i).getTalk_date());
        user_talk_content.setText(talk_lists.get(i).getTalkContent());
        user_talk_date.setText(talk_lists.get(i).getTalk_date());

        if(talk_lists.get(i).getTalk_user().equals("user")){

            pet_talk_list.setVisibility(View.GONE);

        }
        else if(talk_lists.get(i).getTalk_user().equals("pet")){

            user_talk_list.setVisibility(View.GONE);
        }


        if(!talk_lists.get(i).getTalkContent().equals("null")) {
            talk_content.setText(talk_lists.get(i).getTalkContent());
        }

        v.setTag(talk_lists.get(i).getTalkContent());

        return v;
    }
}

