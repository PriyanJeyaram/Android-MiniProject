package com.example.smartportal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;

public class Queries extends AppCompatActivity {
    EditText e;
    Button b;
    String ph="7010129936";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);
        e=findViewById(R.id.QUERIES);
        b = findViewById(R.id.button);
        b.setOnClickListener(view -> {
            String query=e.getText().toString();
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(ph,null,query,null,null);
        });
    }
}