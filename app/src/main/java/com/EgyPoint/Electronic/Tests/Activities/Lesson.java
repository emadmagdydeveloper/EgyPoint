package com.EgyPoint.Electronic.Tests.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.EgyPoint.Electronic.Tests.Fragments.Lesson_content;
import com.EgyPoint.Electronic.Tests.Fragments.Lesson_gools;
import com.EgyPoint.Electronic.Tests.Fragments.Lesson_test;
import com.EgyPoint.Electronic.Tests.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Lesson extends AppCompatActivity {

    private  String flag="";
    private AHBottomNavigation navBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"JannaLT-Regular.ttf",true);
        navBar = findViewById(R.id.navBar);
        AHBottomNavigationAdapter adapter = new AHBottomNavigationAdapter(this,R.menu.navigation);
        adapter.setupWithBottomNavigation(navBar);
        navBar.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        navBar.setDefaultBackgroundColor(Color.WHITE);
        navBar.setAccentColor(ActivityCompat.getColor(this,R.color.colorPrimary));
        navBar.setBehaviorTranslationEnabled(false);
        Intent intent = getIntent();
        if (intent.hasExtra("lesson1"))
        {
            flag="3";
            switch (navBar.getCurrentItem()) {
                case 0:
                    navBar.setCurrentItem(0, false);
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", flag);
                    Lesson_gools gools = new Lesson_gools();
                    gools.setArguments(bundle);
                    setFragment(gools);
                    break;
            }

        }else if (intent.hasExtra("lesson2"))
        {
            flag="2";
            switch (navBar.getCurrentItem()) {
                case 0:
                    navBar.setCurrentItem(0, false);
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", flag);
                    Lesson_gools gools = new Lesson_gools();
                    gools.setArguments(bundle);
                    setFragment(gools);
                    break;
            }

        }
        else if (intent.hasExtra("lesson3"))
        {
            flag="1";
            switch (navBar.getCurrentItem()) {
                case 0:
                    navBar.setCurrentItem(0, false);
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", flag);
                    Lesson_gools gools = new Lesson_gools();
                    gools.setArguments(bundle);
                    setFragment(gools);
                    break;
            }

        }
        navBar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                    switch (position) {
                        case 0:
                            navBar.setCurrentItem(0,false);
                            Bundle bundle = new Bundle();
                            bundle.putString("flag",flag );
                            Lesson_gools gools = new Lesson_gools();
                            gools.setArguments(bundle);
                            setFragment(gools);
                            break;
                        case 1:
                            navBar.setCurrentItem(position,false);
                             Bundle bundle1 = new Bundle();
                               bundle1.putString("flag",flag);
                               Lesson_content lesson_content = new Lesson_content();
                               lesson_content.setArguments(bundle1);
                               setFragment(lesson_content);

                            break;
                        case 2:
                            navBar.setCurrentItem(position,false);

                            Bundle bundle3 = new Bundle();
                            bundle3.putString("flag",flag);
                            Lesson_test lesson_test = new Lesson_test();
                            lesson_test.setArguments(bundle3);
                            setFragment(lesson_test);



                            break;

                    }
                return true;

            }}
            );





    }
    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }
}
