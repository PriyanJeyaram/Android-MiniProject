package com.example.smartportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Map;
import java.util.TreeMap;

public class StudentActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    TextView name,admn,roll,std,sec,nat,bg,mother,phno,mail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        name=findViewById(R.id.name);
        admn=findViewById(R.id.admn);
        roll=findViewById(R.id.roll);
        std=findViewById(R.id.std);
        sec=findViewById(R.id.sec);
        nat=findViewById(R.id.nat);
        bg=findViewById(R.id.blood);
        mother=findViewById(R.id.mother);
        phno=findViewById(R.id.ph);
        mail=findViewById(R.id.mail);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle Extras=getIntent().getExtras();
        String username=Extras.getString("usr");
        FirebaseFirestore.getInstance().collection("StudentBio").document(username).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                     DocumentSnapshot doc=task.getResult();
                     if(doc.exists()){
                         Log.d("Document",doc.getData().toString());
                         Map<String, Object> map=doc.getData();
                         name.setText(map.get("name").toString());
                         roll.setText(map.get("rollno").toString());
                         admn.setText(map.get("admnNo").toString());
                         std.setText(map.get("standard").toString());
                         sec.setText(map.get("section").toString());
                         nat.setText(map.get("native").toString());
                         mother.setText(map.get("motherTongue").toString());
                         bg.setText(map.get("bloodGroup").toString());
                         phno.setText(map.get("phone").toString());
                         mail.setText(map.get("username").toString());
                     }
                     else
                     {
                         Log.d("Document","No data");
                     }
                }
            }
        });

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
