package com.example.smartportal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

@SuppressLint("MissingInflatedId")
public class MidtermFragment extends Fragment {
    StringBuilder mid = new StringBuilder();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String username;

    public MidtermFragment() {
        // Required empty public constructor
    }

    public MidtermFragment(String uName) {
        this.username = uName;
    }

    // TODO: Rename and change types and number of parameters
    public static MidtermFragment newInstance(String param1, String param2) {
        MidtermFragment fragment = new MidtermFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mid.setLength(0);
        View rootView = inflater.inflate(R.layout.fragment_midterm, container, false);
        TextView t1 = rootView.findViewById(R.id.marksmid);
        FirebaseFirestore.getInstance()
                .collection("Student_marks_midterm")
                .document(username)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                        if (doc.exists()) {
                            Map<String, Object> map = doc.getData();
                            mid.append("ENGLISH : " + map.get("english").toString() + "\n");
                            mid.append("LANGUAGE: " + map.get("language").toString() + "\n");
                            mid.append("MATHS   : " + map.get("math").toString() + "\n");
                            mid.append("SCIENCE : " + map.get("science").toString() + "\n");
                            mid.append("SOCIAL  : " + map.get("socialScience").toString() + "\n");
                            t1.setText(mid.toString());
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return rootView;
    }
}