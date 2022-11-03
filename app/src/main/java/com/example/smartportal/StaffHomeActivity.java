package com.example.smartportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StaffHomeActivity extends AppCompatActivity {

    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

//        Link to XML
        tv1 = findViewById(R.id.generateReport);
        tv2 = findViewById(R.id.viewStudents);
//        Redirection Part
        tv1.setOnClickListener(view -> startActivity(new Intent(this, StaffActivity.class)));

        tv2.setOnClickListener(v -> startActivity(new Intent(this, ViewStudentsActivity.class)));

    }
}