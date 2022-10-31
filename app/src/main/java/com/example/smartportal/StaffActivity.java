package com.example.smartportal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class StaffActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        EditText e=findViewById(R.id.editTextTextPersonName2);
        Button b=findViewById(R.id.button3);
        b.setOnClickListener(v -> {
            String username=e.getText().toString();
            Intent i=new Intent(getApplicationContext(),StudentView.class);
            i.putExtra("usrname",username);
            startActivity(i);
        });

    }
}