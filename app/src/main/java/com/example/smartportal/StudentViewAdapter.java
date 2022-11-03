package com.example.smartportal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class StudentViewAdapter extends BaseAdapter {

    ViewStudentsActivity obj1;
    List<String> stdIds;
    Animation animation;

    public StudentViewAdapter(ViewStudentsActivity viewStudentsActivity, List<String> studentIds) {
        this.obj1 = viewStudentsActivity;
        this.stdIds = studentIds;
    }

    public static int getRandom(int max) {
        return (int) (Math.random()*max);
    }

    @Override
    public int getCount() {
        return stdIds.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(obj1).inflate(R.layout.new_student_layout, viewGroup, false);
        animation = AnimationUtils.loadAnimation(obj1,R.anim.animation1);

        TextView textView;
        LinearLayout ll_bg;

//        Link to XML
        ll_bg = view.findViewById(R.id.ll_bg);
        textView = view.findViewById(R.id.stdtext);

        int num = getRandom(8);
        switch(num) {
            case 1:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_1));
                break;
            case 2:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_2));
                break;
            case 3:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_3));
                break;
            case 4:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_4));
                break;
            case 5:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_5));
                break;
            case 6:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_6));
                break;
            case 7:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_7));
                break;
            case 8:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1, R.drawable.gradient_8));
                break;
            default:
                ll_bg.setBackground(ContextCompat.getDrawable(obj1,R.drawable.snow_bg));
        }

        textView.setText(stdIds.get(i));
        textView.setAnimation(animation);

        return view;
    }
}
