package com.example.ameet.medcalc;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Ameet on 1/11/2016.
 */
public class DataRetriever {

    Context mContext;

    public DataRetriever(Context context)
    {

        this.mContext=context;

    }

    public int getValue(String medName)
    {
        int i=0;

        String s;
        try {
             s = searchDataForValue(medName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (s.length()>0)
        {
            try
            {
                // the String to int conversion happens here
                 i = Integer.parseInt(s.trim());
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
        }



        return i;
    }
    public boolean checkNameExists(String name)
    {
        String s;
        try {
            s = searchDataForValue(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(s.length()==0)
        {
            return false;
        }
            return true;
    }



    private String searchDataForValue(String medName) throws IOException {
        final Resources resources = mContext.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.definitions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String value="";
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = TextUtils.split(line, "-");
                if (strings.length < 2) continue;
                String string1=strings[0].trim();
                String string2=strings[1].trim();
                if(string1.equals(medName))
                {
                    value=string2;
                }
            }

        } finally {
            reader.close();
        }

        return value;
    }

    public String[]getStringList()
    {
        try {

            int arrLength=searchDataForNameList().size();
            String[] mStringArr = new String[arrLength];
            for (int i=0;i<arrLength;i++)
            {
                mStringArr[i]=searchDataForNameList().get(i);
            }
            return mStringArr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<String> searchDataForNameList() throws IOException {
        final Resources resources = mContext.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.definitions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String>arrayList=new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = TextUtils.split(line, "-");
                if (strings.length < 2) continue;
                String string1=strings[0].trim();
                arrayList.add(string1);
            }

        } finally {
            reader.close();
        }

        return arrayList;
    }



}
