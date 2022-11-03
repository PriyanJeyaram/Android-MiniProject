package com.example.smartportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Awards_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awards);
        TextView t=findViewById(R.id.award);
        Bundle extras=getIntent().getExtras();
        String username= extras.getString("usrname");
        FirebaseFirestore.getInstance()
                .collection("Student_awards")
                .document(username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                        if (doc.exists()) {
                            Log.d("Document", doc.getData().toString());
                            Map<String, Object> map = doc.getData();
                            String data=map.get("description").toString();
                            String arr[]=data.split("\\,");
                            StringBuilder str=new StringBuilder();
                            for(int i=0;i<arr.length;i++)
                            {
                                str.append(arr[i]+"\n");
                            }
                            t.setText(str);

                        } else {
                            Log.d("Document", "No data");
                        }
    }
});
    }
}