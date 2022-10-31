package com.example.smartportal;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentView extends AppCompatActivity {
    Map<String, Object> mapbio=new HashMap<>();
    Map<String, Object> mapsports=new HashMap<>();
    Map<String, Object> mapawards=new HashMap<>();
    Map<String, Object> mapmarks1=new HashMap<>();
    Map<String, Object> mapmarks2=new HashMap<>();
    Map<String, Object> mapmarks3=new HashMap<>();
    TextView t1,t2,t3,t4,t5,t6;
    int total=0;
    int pageHeight = 1120;
    int pagewidth = 792;
    Bitmap bmp, scaledbmp,bgmp;
    Uri uri;
    String admn,roll,standard,section,sport,awards,sportawards,sportmedals,sportdesc,username;
    double percentile;
    private static final int PERMISSION_REQUEST_CODE = 200;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);
        Bundle Extras= getIntent().getExtras();
        String usrname=Extras.getString("usrname");
        t1=findViewById(R.id.name1);
        t2=findViewById(R.id.admn1);
        t3=findViewById(R.id.rollno1);
        t4=findViewById(R.id.std1);
        t5=findViewById(R.id.ph1);
        t6=findViewById(R.id.percent);
        Button b=findViewById(R.id.button3);
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.student);
        scaledbmp=Bitmap.createScaledBitmap(bmp,140,140,false);
        bgmp=BitmapFactory.decodeResource(getResources(),R.drawable.bg);
        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        FirebaseFirestore.getInstance().collection("StudentBio").document(usrname).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                    if(doc.exists()){
                        Log.d("Document",doc.getData().toString());
                        mapbio=doc.getData();
                        admn=mapbio.get("admnNo").toString();
                        roll=mapbio.get("rollno").toString();
                        standard=mapbio.get("standard").toString();
                        section=mapbio.get("section").toString();
                        username=mapbio.get("username").toString();
                        t1.setText(mapbio.get("name").toString());
                        t2.setText(mapbio.get("admnNo").toString());
                        t3.setText(mapbio.get("rollno").toString());
                        t4.setText(mapbio.get("standard").toString());
                        t5.setText(mapbio.get("phone").toString());
                    }
                    else
                    {
                        Toast.makeText(StudentView.this, "No data", Toast.LENGTH_SHORT).show();
                        Log.d("Document","No data");
                    }
                }
            }
        });
        FirebaseFirestore.getInstance().collection("Student_awards").document(usrname).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                    if(doc.exists()){
                        Log.d("Document",doc.getData().toString());

                        mapawards=doc.getData();
                        awards= mapawards.get("description").toString();

                    }
                    else
                    {
                        Toast.makeText(StudentView.this, "No data", Toast.LENGTH_SHORT).show();

                        Log.d("Document","No data");
                    }
                }
            }
        });
        FirebaseFirestore.getInstance().collection("Student_sports").document(usrname).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                    if(doc.exists()){
                        Log.d("Document",doc.getData().toString());
                        mapsports=doc.getData();
                        sport=mapsports.get("sports").toString();
                        sportmedals=mapsports.get("medals").toString();
                        sportawards=mapsports.get("awards").toString();
                        sportdesc=mapsports.get("description").toString();

                    }
                    else
                    {
                        Toast.makeText(StudentView.this, "No data", Toast.LENGTH_SHORT).show();

                        Log.d("Document","No data");
                    }
                }
            }
        });
        FirebaseFirestore.getInstance().collection("Student_marks_midterm").document(usrname).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                    if(doc.exists()){
                        Log.d("Document",doc.getData().toString());
                        mapmarks1=doc.getData();
                        total+=Integer.parseInt(mapmarks1.get("english").toString());
                        total+=Integer.parseInt(mapmarks1.get("language").toString());
                        total+=Integer.parseInt(mapmarks1.get("math").toString());
                        total+=Integer.parseInt(mapmarks1.get("science").toString());
                        total+=Integer.parseInt(mapmarks1.get("socialScience").toString());



                    }
                    else
                    {
                        Toast.makeText(StudentView.this, "No data", Toast.LENGTH_SHORT).show();

                        Log.d("Document","No data");
                    }
                }
            }
        });
        FirebaseFirestore.getInstance().collection("Student_marks_revision").document(usrname).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                    if(doc.exists()){
                        Log.d("Document",doc.getData().toString());
                        mapmarks2=doc.getData();
                        total+=Integer.parseInt(mapmarks2.get("english").toString());
                        total+=Integer.parseInt(mapmarks2.get("language").toString());
                        total+=Integer.parseInt(mapmarks2.get("math").toString());
                        total+=Integer.parseInt(mapmarks2.get("science").toString());
                        total+=Integer.parseInt(mapmarks2.get("socialScience").toString());


                    }
                    else
                    {
                        Toast.makeText(StudentView.this, "No data", Toast.LENGTH_SHORT).show();
                        Log.d("Document","No data");
                    }
                }
            }
        });
        FirebaseFirestore.getInstance().collection("Student_marks_terminal").document(usrname).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                    if(doc.exists()){
                        Log.d("Document",doc.getData().toString());
                        mapmarks3=doc.getData();
                        total+=Integer.parseInt(mapmarks3.get("english").toString());
                        total+=Integer.parseInt(mapmarks3.get("language").toString());
                        total+=Integer.parseInt(mapmarks3.get("math").toString());
                        total+=Integer.parseInt(mapmarks3.get("science").toString());
                        total+=Integer.parseInt(mapmarks3.get("socialScience").toString());
                        percentile= (double) total/15.0;
                        t6.setText(String.valueOf(percentile));
                    }
                    else
                    {
                        Toast.makeText(StudentView.this, "No data", Toast.LENGTH_SHORT).show();
                        Log.d("Document","No data");
                    }
                }
            }
        });
        b.setOnClickListener(v->{
            generatePDF();
        });



    }
    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();
        Paint bgpaint=new Paint();
        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        bgpaint.setStyle(Paint.Style.FILL);
        bgpaint.setShader(new BitmapShader(bgmp, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT));
        canvas.drawPaint(bgpaint);
        canvas.drawBitmap(scaledbmp, 56, 40, paint);

        // below line is used for addin
        //g typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(50);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(this, R.color.purple_700));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("STUDENT REPORT", 209, 100, title);
        canvas.drawText(t1.getText().toString(), 209, 150, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(35);

        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("ADMISSION NO : "+admn, 50, 250, title);
        canvas.drawText("ROLL N0 : "+roll, 50, 300, title);
        canvas.drawText("STANDARD : "+standard, 50, 350, title);
        canvas.drawText("SECTION : "+section, 50, 400, title);
        canvas.drawText("STUDY PERCENTILE : "+percentile, 50, 450, title);
        canvas.drawText("AWARDS : "+awards , 50, 500, title);
        canvas.drawText("SPORTS : "+sport, 50, 550, title);
        canvas.drawText("SPORT MEDALS : "+sportmedals, 50, 600, title);
        canvas.drawText("SPORT AWARDS : "+sportawards, 50, 650, title);
        canvas.drawText("SPORT FEATS : "+sportdesc, 50, 700, title);
        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "SAMPLE18.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));
            uri = Uri.fromFile(file);
            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(StudentView.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        pdfDocument.close();
        Intent mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("application/pdf");
        mShareIntent.putExtra(Intent.EXTRA_EMAIL,username);
        mShareIntent.putExtra(Intent.EXTRA_SUBJECT, "STUDENT REPORT FOR "+t1.getText().toString().toUpperCase());
        mShareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(mShareIntent);

    }
    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

}