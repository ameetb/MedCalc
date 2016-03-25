package com.example.ameet.medcalc;

/**
 * Created by Ameet on 1/13/2016.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ameet on 11/10/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<Med> {

    ArrayList<Med> medList;
    Context context;
    int layoutResourceId;

    public CustomArrayAdapter(Context context,int layoutResourceId,ArrayList<Med>classList)
    {
        super(context,layoutResourceId);
        this.context=context;
        this.medList =classList;
        this.layoutResourceId=layoutResourceId;

    }
    //    @Override
//    public Class getItem(int position)
//    {
//        return super.getItem();
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Med md = getItem(position);
        if (convertView == null) {
            Log.e("b0ss","pls");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView listClassText = (TextView)convertView.findViewById(R.id.itemClassText);
        TextView gradeText=(TextView)convertView.findViewById(R.id.itemGradeText);
        listClassText.setText(md.getName());
        gradeText.setText(String.valueOf(md.getDose()));

        return convertView;

    }

}
