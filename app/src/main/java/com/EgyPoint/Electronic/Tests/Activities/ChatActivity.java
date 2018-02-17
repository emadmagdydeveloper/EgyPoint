package com.EgyPoint.Electronic.Tests.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.EgyPoint.Electronic.Tests.Adapters.Messages_Adapter;
import com.EgyPoint.Electronic.Tests.Models.MessageModel;
import com.EgyPoint.Electronic.Tests.Models.UserModel;
import com.EgyPoint.Electronic.Tests.R;
import com.EgyPoint.Electronic.Tests.Services.MyApp;
import com.EgyPoint.Electronic.Tests.Services.NetworkConnection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backBtn;
    private Toolbar chat_toolBar;
    private DatabaseReference dref;
    public String curr_user_id;
    private UserModel chat_user_data;
    private RecyclerView msg_recycler;
    private LinearLayoutManager manager;
    private ImageButton send;
    private TextView user_chat_name,user_chat_status,userImage;
    private List<MessageModel> messageModelList;
    private EditText msg_container;
    Messages_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        dref = FirebaseDatabase.getInstance().getReference();
        initView();
        getDataFromIntent();
        DisplayMessages();
        adapter  = new Messages_Adapter(this,curr_user_id,messageModelList);
        msg_recycler.setAdapter(adapter);
        send = findViewById(R.id.sendBtn);
        send.setOnClickListener(this);
        msg_container = findViewById(R.id.msg_et);
        user_chat_name.setText(chat_user_data.getId());
        userImage.setText(chat_user_data.getName());

        checkuser_status();

    }


    private void checkuser_status() {

        NetworkConnection connection = new NetworkConnection(this);
        if (connection.getConnection()==true)
        {
            dref.child("Users").child(chat_user_data.getId()).child("status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue().toString().equals("online"))
                    {
                        user_chat_status.setText("Active now");
                    }else
                    {
                        long TimeAgo = (long) dataSnapshot.getValue();
                        String timeAgo = MyApp.getTimeAgo2(TimeAgo, ChatActivity.this);
                        user_chat_status.setText("Last seen "+timeAgo);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            if (intent.hasExtra("curr_user_id")&&intent.hasExtra("chat_user"))
            {
                curr_user_id = intent.getStringExtra("curr_user_id");
                chat_user_data = (UserModel) intent.getSerializableExtra("chat_user");
            }
        }

    }

    private void initView() {
        chat_toolBar = findViewById(R.id.chat_toolBar);
        View view = LayoutInflater.from(this).inflate(R.layout.chat_custom_toolbar,null);
        backBtn = view.findViewById(R.id.back_arrow);
        backBtn.setOnClickListener(this);
        userImage = view.findViewById(R.id.user_image);
        user_chat_name = view.findViewById(R.id.user_chat_name);
        user_chat_status = view.findViewById(R.id.user_chat_status);
        setSupportActionBar(chat_toolBar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        getSupportActionBar().setCustomView(view);

        msg_recycler = findViewById(R.id.chatRecyclerView);
        manager = new LinearLayoutManager(this);
        manager.setReverseLayout(false);
        manager.setStackFromEnd(true);
        msg_recycler.setLayoutManager(manager);
        msg_recycler.setHasFixedSize(true);
        messageModelList = new ArrayList<>();
        adapter  = new Messages_Adapter(this,curr_user_id,messageModelList);
        msg_recycler.setAdapter(adapter);
        send = findViewById(R.id.sendBtn);
        send.setOnClickListener(this);
        msg_container = findViewById(R.id.msg_et);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sendBtn:
                sendMessage();
                break;
            case R.id.back_arrow:
                finish();
                break;
        }

    }
    private void DisplayMessages() {
        String path = curr_user_id+"_"+chat_user_data.getId();
        DatabaseReference ref =  dref.child("Messages").child(path);
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null)
                {
                    if (dataSnapshot.getValue()!=null)
                    {
                        messageModelList.clear();

                        for (DataSnapshot ds :dataSnapshot.getChildren())
                        {
                            Log.e("data",ds.getValue().toString());
                            MessageModel messageModel = ds.getValue(MessageModel.class);
                            messageModelList.add(messageModel);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void sendMessage() {
        if (!TextUtils.isEmpty(msg_container.getText().toString()))
        {
            final Map map = new HashMap();
            map.put("from",curr_user_id);
            map.put("text",msg_container.getText().toString());
            map.put("date", ServerValue.TIMESTAMP);

            final String push = dref.push().getKey();
           dref.child("Messages").child(curr_user_id+"_"+chat_user_data.getId()).child(push).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {

                   if (task.isSuccessful())
                   {
                       dref.child("Messages").child(chat_user_data.getId()+"_"+curr_user_id).child(push).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {

                               if (task.isSuccessful())
                               {
                                   Log.e("snd"," messages send ");
                               }
                           }
                       });
                   }
               }
           });
           msg_container.setText("");
        }
    }
}