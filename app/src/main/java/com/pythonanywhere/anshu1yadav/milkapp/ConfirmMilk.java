package com.pythonanywhere.anshu1yadav.milkapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

//import static com.pythonanywhere.anshu1yadav.milkapp.MainActivity.*;
import static com.pythonanywhere.anshu1yadav.milkapp.MainActivity.MILK_CONFIRM_STRING;

public class ConfirmMilk extends AppCompatActivity {
    private double milkAmount;
    private TextView selectAmount3;
    private Button confirm;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_milk);
        final Intent intent = getIntent();

        milkAmount = intent.getDoubleExtra(MILK_CONFIRM_STRING, 0);


//        assert s != null;
//        milkAmount = Integer.parseInt(String.valueOf(s));


        final DbHandler handler = new DbHandler(this, "milkrecd", null,1);


        selectAmount3 = findViewById(R.id.selectAmount3);
        selectAmount3.setText("Today you bought - " + milkAmount +"- litre milk.");

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mytag2", String.valueOf(milkAmount)+"in click listner confirm");
                handler.addMilk(new MilkRecords(milkAmount));
                Log.d("mytag2", String.valueOf(milkAmount)+"in click listner confirm");

                Toast.makeText(ConfirmMilk.this, "Confirmed - " + milkAmount +" - litre milk.", Toast.LENGTH_SHORT).show();



            }
        });



    }
}