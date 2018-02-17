package com.EgyPoint.Electronic.Tests.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.EgyPoint.Electronic.Tests.Fragments.Fragment_Goals;
import com.EgyPoint.Electronic.Tests.Fragments.Fragment_Title;
import com.EgyPoint.Electronic.Tests.R;

public class Title_GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_goals);
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            if (intent.hasExtra("title"))
            {
                Bundle bundle = new Bundle();
                bundle.putString("title","title");
                Fragment_Title fragment_title = new Fragment_Title();
                fragment_title.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragments_container,fragment_title).commit();

            }else if (intent.hasExtra("goal"))
            {
                Bundle bundle = new Bundle();
                bundle.putString("goal","goal");
                Fragment_Goals fragment_goals = new Fragment_Goals();
                fragment_goals.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragments_container,fragment_goals).commit();

            }
        }
    }
}
