package com.EgyPoint.Electronic.Tests.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.EgyPoint.Electronic.Tests.R;

import me.anwarshahriar.calligrapher.Calligrapher;


public class Lesson_content extends Fragment {
    private TextView content;
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

        View view = inflater.inflate(R.layout.fragment_lesson_content, container, false);
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
                String txt = getActivity().getResources().getString(R.string.content_lesson1);
                content.setText(txt);
                break;
            case "2" :
                String txt2 = getActivity().getResources().getString(R.string.content_lesson2);
                content.setText(txt2);
                break;
            case "3" :
                String txt3 = getActivity().getResources().getString(R.string.content_lesson3);
                content.setText(txt3);
                break;
        }
    }

    private void initView(View view) {
        content = view.findViewById(R.id.content);
    }


}
