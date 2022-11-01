package com.example.smartportal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TerminalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("MissingInflatedId")

public class TerminalFragment extends Fragment {
    StringBuilder term = new StringBuilder();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String username;

    public TerminalFragment() {
        // Required empty public constructor
    }

    public TerminalFragment(String uName) {
        this.username = uName;
    }

    // TODO: Rename and change types and number of parameters
    public static TerminalFragment newInstance(String param1, String param2) {
        TerminalFragment fragment = new TerminalFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        term.setLength(0);
        View rootView = inflater.inflate(R.layout.fragment_terminal, container, false);
        TextView t1 = rootView.findViewById(R.id.marksterm);
        FirebaseFirestore.getInstance()
                .collection("Student_marks_terminal")
                .document(username)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();
                        if (doc.exists()) {
                            Map<String, Object> map = doc.getData();
                            term.append("ENGLISH : " + map.get("english").toString() + "\n");
                            term.append("LANGUAGE: " + map.get("language").toString() + "\n");
                            term.append("MATHS   : " + map.get("math").toString() + "\n");
                            term.append("SCIENCE : " + map.get("science").toString() + "\n");
                            term.append("SOCIAL  : " + map.get("socialScience").toString() + "\n");
                            t1.setText(term.toString());
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return rootView;
    }
}