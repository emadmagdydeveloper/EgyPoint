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

public class Lesson_gools extends Fragment {

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

        View view = inflater.inflate(R.layout.fragment_lesson_gools, container, false);
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
                String data =
                        "1-\tيستنتج مفهوم الاختبارات الالكترونية.\n" +
                                "2-\tيحدد أهداف الاختبار الإلكترونى. \n" +
                                "3-\tيعدد مميزات الاختبارات الإلكترونية. \n" +
                                "4-\tيشرح خصائص الاختبارات الالكترونية. \n" +
                                "5-\tيصنف أنواع الإختبارات الإلكترونية.\n" +
                                "6-\tيحلل العناصر المكونة للاختبار الإلكتروني.\n";
                content.setText(data);
                break;
            case "2" :
                String data2 =
                        "1-\tيستخدم برنامج Articulate Quiz maker فى إنتاج الإختبارات الألكترونية.\n" +
                                "2-\tيحمل برنامج Articulate Quiz maker .\n" +
                                "3-\tيثبت برنامج الاختبارات الالكترونية.\n" +
                                "4-\tيرتب خطوات إنشاء اختبار الكترونى  جديد.\n" +
                                "5-\tيتعامل مع أدوات برنامج الاختبارات الالكترونية.\n" +
                                "6-\tيعدد طرق إغلاق برنامج الاختبارات الالكترونية.\n";
                content.setText(data2);
                break;
            case "3" :
                String data3 =
                        "1-\tيضيف اختبار من نوع صح وخطأ True- False.\n" +
                                "2-\tيضيف اختبار من نوع  اختيار من متعدد .\n" +
                                "3-\tيضيف اختبار من نوع  املئ الفراع التالي  .\n" +
                                "4-\tيضيف اختبار من نوع  الترتيب.\n" +
                                "5-\tيضبط خصائص الإختبارات الالكترونية.\n";
                content.setText(data3);
                break;
        }
    }

    private void initView(View view) {
        content = view.findViewById(R.id.content);
    }


}
