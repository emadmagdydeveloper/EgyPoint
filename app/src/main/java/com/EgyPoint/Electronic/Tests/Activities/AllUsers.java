package com.EgyPoint.Electronic.Tests.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.EgyPoint.Electronic.Tests.Adapters.AllUsersAdapter;
import com.EgyPoint.Electronic.Tests.Models.UserModel;
import com.EgyPoint.Electronic.Tests.R;
import com.EgyPoint.Electronic.Tests.Services.MyApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllUsers extends AppCompatActivity {

    public String userId;
    public TextView user_image;
    private RecyclerView mRec;

    private Toolbar toolbar;
    private DatabaseReference dRef;
    private LinearLayoutManager manager;
    private List<UserModel> userModelList;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        initView();
        getDataFromIntent();
        dRef = FirebaseDatabase.getInstance().getReference();
        Display_Users();
    }
    private void initView()
    {
        toolbar = findViewById(R.id.chatRoom_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        user_image = findViewById(R.id.user_image);
        userModelList = new ArrayList<>();
        mRec = findViewById(R.id.roomRecycler);
        manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        manager.setReverseLayout(true);
        mRec.setLayoutManager(manager);
        mRec.setHasFixedSize(true);
        adapter = new AllUsersAdapter(this,userModelList);
        mRec.setAdapter(adapter);


    }
    private void getDataFromIntent()
    {
        Intent intent = getIntent();
        if (intent!=null)
        {
            if (intent.hasExtra("userName"))
            {
                userId = intent.getStringExtra("userName");
                user_image.setText(userId.substring(0,2));
            }
        }
    }
    private void Display_Users()
    {
        DatabaseReference ref =  dRef.child("Users");
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null)
                {
                    userModelList.clear();
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        if (ds.child("status").getValue().toString().equals("online"))
                        {
                            UserModel  userModel = ds.getValue(UserModel.class);
                            if (!userModel.getId().equals(userId))
                            {
                                userModelList.add(userModel);
                                adapter.notifyDataSetChanged();
                            }
                        }else
                            {
                                long time_ago = (long) ds.child("status").getValue();
                                String timeAgo = MyApp.getTimeAgo(time_ago, AllUsers.this);
                                String id = ds.child("id").getValue().toString();
                                String name=ds.child("name").getValue().toString();
                                UserModel userModel = new UserModel(id,name,timeAgo);
                                if (!userModel.getId().equals(userId))
                                {
                                    userModelList.add(userModel);
                                    adapter.notifyDataSetChanged();
                                }
                            }



                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void setPosition(int pos)
    {

        UserModel userModel = userModelList.get(pos);
        Intent intent = new Intent(AllUsers.this,ChatActivity.class);
        intent.putExtra("curr_user_id",userId);
        intent.putExtra("chat_user",userModel);
        startActivity(intent);

    }

}
