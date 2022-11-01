package com.example.smartportal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SportsActivity extends AppCompatActivity {

    String userName;
    Map<String, Object> sportMap = new HashMap<>();
    TextView tv1, tv2, tv3, tv4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
//        Get Values from Bundle
        Bundle extras = getIntent().getExtras();
        userName = extras.getString("uName");
//        Link with XML
        tv1 = findViewById(R.id.textView23);
        tv2 = findViewById(R.id.textView24);
        tv3 = findViewById(R.id.textView25);
        tv4 = findViewById(R.id.textView26);

        FirebaseFirestore.getInstance().collection("Student_sports").document(userName).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                if (doc.exists()) {
                    sportMap = doc.getData();
                    tv1.setText(sportMap.get("sports").toString());
                    tv2.setText(sportMap.get("awards").toString());
                    tv3.setText(sportMap.get("medals").toString());
                    tv4.setText(sportMap.get("description").toString());
                } else {
                    Toast.makeText(SportsActivity.this, "No data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}