package com.EgyPoint.Electronic.Tests.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.EgyPoint.Electronic.Tests.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Lesson_test extends Fragment {
    private ImageView imageView;
    String flag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher=new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"JannaLT-Regular.ttf",true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lesson_test, container, false);
        initView(view);
        getDataFromBundle();
        return view;
    }

    private void getDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            flag = bundle.getString("flag");
            setData(flag);

        }
    }

    private void setData(String flag) {
        switch (flag)
        {
            case "1":
                imageView.setImageResource(R.drawable.test1);
                break;
            case "2" :
                imageView.setImageResource(R.drawable.test2);
                break;
            case "3" :
                imageView.setImageResource(R.drawable.test3);
                break;
        }
    }

    private void initView(View view) {
        imageView = view.findViewById(R.id.image_test);
    }
}
