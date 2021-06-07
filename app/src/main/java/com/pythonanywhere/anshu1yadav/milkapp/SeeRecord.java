package com.pythonanywhere.anshu1yadav.milkapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SeeRecord extends AppCompatActivity {
    private EditText milkPrice;
    private Button calculate;
    private Button deleteDatabase;
    private TextView calculatedPrice;
    private RecyclerView recyclerView;
    private Double totalMilk;
    private Double calculatedPriceFinal;

    private ArrayList<String> arrayList = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_record);

        milkPrice = findViewById(R.id.milkPrice);
        String temp = milkPrice.getText().toString();
        double milkpriceR = 0;
        Log.d("main",temp+" temp--");
        if(!"".equals(temp)){
            Log.d("main", "gone into if");
            milkpriceR = Double.parseDouble(temp);
        }

        Log.d("mytag1", "aa gya activity me see record");
        calculate = findViewById(R.id.calculate);
        deleteDatabase = findViewById(R.id.deleteDatabase);
        calculatedPrice = findViewById(R.id.calculatedPrice);

        recyclerView = findViewById(R.id.recyclerView);

        final DbHandler handler = new DbHandler(this,"milkrecd",null,1);
        Log.d("mytag1", "hander ho gya");
        Log.d("mytag1", "handler set");
        arrayList = handler.getMilkArraylist();
        String[] stockArr = new String[arrayList.size()];
        stockArr = arrayList.toArray(stockArr);




        CustomAdapter adapter = new CustomAdapter(stockArr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        totalMilk = handler.totalMilkReturn();
        final double finalMilkpriceR1 = milkpriceR;
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = milkPrice.getText().toString();
                double milkpriceR = 0;
                Log.d("main",temp+" temp--");
                if(!"".equals(temp)){
                    Log.d("main", "gone into if");
                    milkpriceR = Double.parseDouble(temp);
                }
                Log.d("main",String.valueOf(totalMilk));
                Log.d("main",String.valueOf(milkpriceR));
                calculatedPriceFinal = totalMilk * milkpriceR;
                calculatedPrice.setText("Your total milk is "+totalMilk+" litres."+" Your total Price is Rs "+ calculatedPriceFinal);
            }
        });
        final Intent intentAd = new Intent(this, DeleteAllConfirm.class);
        deleteDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAd);
                finish();
            }
        });



    }
}