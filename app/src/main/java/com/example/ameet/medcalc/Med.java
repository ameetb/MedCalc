package com.example.ameet.medcalc;

/**
 * Created by Ameet on 1/13/2016.
 */
public class Med {

    private int mDose;
    private String mName;
    public Med(String name,int dose)
    {
        this.mDose=dose;
        this.mName=name;

    }
    public String getName()
    {
        return mName;
    }
    public int getDose()
    {
        return mDose;
    }
}
