package com.pythonanywhere.anshu1yadav.milkapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteAllConfirm extends AppCompatActivity {
    private Button delete;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_all_confirm);

        final DbHandler handler = new DbHandler(this,"milkrecd",null,1);
        delete = findViewById(R.id.delete);
        final Intent intent = new Intent(this, SeeRecord.class);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.deleteDatabase();
                startActivity(intent);
                finish();
            }
        });
    }
}