package com.github.anurag145.slick;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by anura on 3/23/2018.
 */

@IgnoreExtraProperties
public class Data {

    public String userName;
    public String conditionA;
    public String conditionB;
    public long tA;
    public  long tB;
    public int count=0;
    public Data(){}
    public Data(String userName,String conditionA,String conditionB,long tA,long tB,int count)
    {
        this.userName=userName;
        this.conditionA=conditionA;
        this.conditionB=conditionB;
        this.tA=tA;
        this.tB=tB;
        this.count=count;
    }
}
