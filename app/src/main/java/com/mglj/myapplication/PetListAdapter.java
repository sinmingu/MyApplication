package com.mglj.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PetListAdapter extends BaseAdapter {

    private Context context;
    private List<Pet> petList;



    public PetListAdapter(Context context, List<Pet> petList){
        this.context = context;
        this.petList = petList;

    }


    @Override
    public int getCount() {
        return petList.size();
    }

    @Override
    public Object getItem(int i) {
        return petList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.pet, null);
        final TextView petNum = (TextView)v.findViewById(R.id.petNum);
        TextView petID = (TextView)v.findViewById(R.id.petID);
        TextView petName = (TextView)v.findViewById(R.id.petName);
        TextView petType = (TextView)v.findViewById(R.id.petType);
        TextView petAge = (TextView)v.findViewById(R.id.petAge);
        TextView petSex = (TextView)v.findViewById(R.id.petSex);
        TextView petBir = (TextView)v.findViewById(R.id.petBir);
        TextView petWeight = (TextView)v.findViewById(R.id.petWeight);


        petNum.setText(petList.get(i).getPetNum());
        petID.setText(petList.get(i).getPetID());
        petName.setText(petList.get(i).getPetName());
        petType.setText(petList.get(i).getPetType());
        petAge.setText(petList.get(i).getPetAge());
        petSex.setText(petList.get(i).getPetSex());
        petBir.setText(petList.get(i).getPetBir());
        petWeight.setText(petList.get(i).getPetWeight());


        v.setTag(petList.get(i).getPetID());

        return v;
    }
}
