package com.EgyPoint.Electronic.Tests.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.EgyPoint.Electronic.Tests.R;
import com.EgyPoint.Electronic.Tests.Services.NetworkConnection;
import com.EgyPoint.Electronic.Tests.Services.Preferense;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{
    LinearLayout l;
    private NavigationView nav_view;
    public String userName;
    private TextView search_title,search_goal,rooms,user_name_tv,user_image_tv;
    DatabaseReference dRef;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dRef = FirebaseDatabase.getInstance().getReference();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer =  findViewById(R.id.drawer_layout);

        nav_view = findViewById(R.id.nav_view);
        View view = nav_view.getHeaderView(0);
        user_name_tv = view.findViewById(R.id.user_name_tv);
        user_image_tv =view.findViewById(R.id.user_image_tv);
        search_title = findViewById(R.id.search_title);
        search_goal  = findViewById(R.id.search_goal);
        rooms = findViewById(R.id.rooms);
        rooms.setOnClickListener(this);
        setSupportActionBar(toolbar);
        l=findViewById(R.id.lessons);
        l.setVisibility(View.GONE);
        TextView mhtwa=findViewById(R.id.mhtwa);
        TextView l1=findViewById(R.id.first);
        TextView l2=findViewById(R.id.second);
        TextView l3=findViewById(R.id.third);
        TextView vedio=findViewById(R.id.txtvedio);
        vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Vedios.class));
            }
        });

        search_title.setOnClickListener(this);
        search_goal.setOnClickListener(this);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"JannaLT-Regular.ttf",true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        mhtwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (l.getVisibility()==View.VISIBLE){
                    l.setVisibility(View.GONE);

                }else {
                l.setVisibility(View.VISIBLE);}

            }
        });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Lesson.class);
                i.putExtra("lesson1","1");
                startActivity(i);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Lesson.class);
                i.putExtra("lesson2","2");
                startActivity(i);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Lesson.class);
                i.putExtra("lesson3","3");
                startActivity(i);
            }
        });

        getDataFromIntent();


    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            if (intent.hasExtra("userName"))
            {
                userName = intent.getStringExtra("userName");
                UpdateUI(userName);
            }
            else
                {
                    checkOfflineData();
                    Log.e("intent field","no field username");


                }
        }else {
            Log.e("intent","no intent");
            checkOfflineData();
        }
    }

    private void UpdateUI(String name) {
        user_name_tv.setText(name);
        user_image_tv.setText(name.substring(0,2));
    }

    private void checkOfflineData() {
        SharedPreferences pref = getSharedPreferences("id",MODE_PRIVATE);
        String id = pref.getString("user_id","");
        if (pref==null)
        {
            SignOut();
        }else
            {
                if (TextUtils.isEmpty(id)||id==null)
                {
                    SignOut();
                }else
                {
                    userName = id;
                    UpdateUI(id);
                }
            }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            Intent i=new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.search_title:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1 = new Intent(MainActivity.this,Title_GoalsActivity.class);
                        intent1.putExtra("title","title");
                        startActivity(intent1);
                    }
                },1000);

                break;
            case R.id.search_goal:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent2 = new Intent(MainActivity.this,Title_GoalsActivity.class);
                        intent2.putExtra("goal","goal");
                        startActivity(intent2);
                    }
                },1000);

                break;
            case R.id.first_lesson:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i1=new Intent(MainActivity.this,Lesson.class);
                        i1.putExtra("lesson1","1");
                        startActivity(i1);
                    }
                },1000);

                break;
            case R.id.second_lesson:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i2=new Intent(MainActivity.this,Lesson.class);
                        i2.putExtra("lesson2","2");
                        startActivity(i2);
                    }
                },1000);

                break;
            case R.id.third_lesson:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i3=new Intent(MainActivity.this,Lesson.class);
                        i3.putExtra("lesson3","3");
                        startActivity(i3);
                    }
                },1000);

                break;
            case R.id.logout:
                SignOut();

                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.search_title:
                Intent intent1 = new Intent(this,Title_GoalsActivity.class);
                intent1.putExtra("title","title");
                startActivity(intent1);
                break;
            case R.id.search_goal:
                Intent intent2 = new Intent(this,Title_GoalsActivity.class);
                intent2.putExtra("goal","goal");
                startActivity(intent2);
                break;
            case R.id.rooms:
                Intent intent3 = new Intent(this,AllUsers.class);
                intent3.putExtra("userName",userName);
                startActivity(intent3);
                break;
        }

    }
    private void SignOut()
    {
        NetworkConnection connection = new NetworkConnection(this);
        if (connection.getConnection()==true)
        {
            Map map = new HashMap();
            map.put("status", ServerValue.TIMESTAMP);

            if (userName!=null)
            {
                dRef.child("Users").child(userName).updateChildren(map);

            }

            Preferense pref = new Preferense(this);
            pref.clear();
            SharedPreferences preferences = getSharedPreferences("id",MODE_PRIVATE);
            startActivity(new Intent(this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            Log.e("pref",preferences.getString("user_id","loggedout"));
        }else
            {
                Toast.makeText(this, "check network connection.", Toast.LENGTH_SHORT).show();
            }

    }
}
