package com.pythonanywhere.anshu1yadav.milkapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    private Button o5;
    private Button o1;
    private Button o15;
    private Button custom_milk_submit;
    private Button seeRecord;
    private EditText custom_milk;
    private double  milk = 0.5;
    public final static String MILK_CONFIRM_STRING = "com.pythonanywhere.anshu1yadav.milkapp.extra.milkConfirmString";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        o5 = findViewById(R.id.o5);
        o1 = findViewById(R.id.o1);
        o15 = findViewById(R.id.o15);
        custom_milk_submit = findViewById(R.id.custom_milk_submit);
        custom_milk = findViewById(R.id.custom_milk);
        seeRecord = findViewById(R.id.seeRecord);
        final DbHandler handler = new DbHandler(this, "milkrecd", null,1);



        seeRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                handler.getMilk("02/06/2021");
                Intent intent = new Intent(getBaseContext(), SeeRecord.class);
                startActivity(intent);

            }
        });
        final Intent intent = new Intent(this, ConfirmMilk.class);
//        Intent intent1 = getIntent();
//        intent1.getEx
        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milk = 0.5;
                Log.d("mytag","milk tak thik hai");
                intent.putExtra(MILK_CONFIRM_STRING, milk);
                Log.d("mytag","start activity se pehle thik hai");
                startActivity(intent);
            }
        });
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milk = 1.0;
                intent.putExtra(MILK_CONFIRM_STRING,milk);
                startActivity(intent);
            }
        });
        o15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milk = 1.5;
                intent.putExtra(MILK_CONFIRM_STRING,milk);
                startActivity(intent);
            }
        });
        custom_milk_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(custom_milk!=null) {
//                    milk = Double.parseDouble(custom_milk.getText().toString());
//                    intent.putExtra(MILK_CONFIRM_STRING, milk);
//                    startActivity(intent);
//                    custom_milk.setText("");
//                } else{
//                    custom_milk_submit.setHint("Please Enter a Value");
//                }
                try {
                    milk = Double.parseDouble(custom_milk.getText().toString());
                    intent.putExtra(MILK_CONFIRM_STRING, milk);
                    startActivity(intent);
                    custom_milk.setText("");
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}