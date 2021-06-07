package com.pythonanywhere.anshu1yadav.milkapp;


import android.util.Log;

public class MilkRecords {
    public MilkRecords(double milk) {
        this.milk = milk;
        Log.d("mytag2", String.valueOf(milk)+"in click constructor confirm");
    }
    private String sno;
    private String date;
    private double milk;




//    public MilkRecords() {
//
//    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMilk() {
        Log.d("mytag2", String.valueOf(milk)+"in click getMilk confirm");
        return milk;

    }

    public void setMilk(double milk) {
        this.milk = milk;
    }
}
