package com.EgyPoint.Electronic.Tests.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.EgyPoint.Electronic.Tests.Activities.AllUsers;
import com.EgyPoint.Electronic.Tests.Models.UserModel;
import com.EgyPoint.Electronic.Tests.R;
import com.EgyPoint.Electronic.Tests.Services.NetworkConnection;

import java.util.List;

/**
 * Created by Delta on 16/02/2018.
 */

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.MyViewHolder> {

    Context context;
    List<UserModel> userModelList;
    AllUsers allUsers;

    public AllUsersAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
        this.allUsers = (AllUsers) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.users_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        UserModel userModel = userModelList.get(position);
        holder.BindData(userModel.getId(),userModel.getName(),userModel.getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                allUsers.setPosition(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView user_Name,userImage,Status,TimeAgo;

        public MyViewHolder(View itemView) {
            super(itemView);
            user_Name = itemView.findViewById(R.id.userName);
            userImage= itemView.findViewById(R.id.user_image_name);
            Status = itemView.findViewById(R.id.status);
            TimeAgo = itemView.findViewById(R.id.time_ago);


        }
        public void BindData(String userId,String user_image,String status)
        {
            Log.e("dddd",userId+"\n"+user_image);
            user_Name.setText(userId);
            userImage.setText(user_image);

            NetworkConnection connection = new NetworkConnection(context);
            if (connection.getConnection()==true)
            {
                if (status.equals("online"))
                {
                    Status.setVisibility(View.VISIBLE);
                    TimeAgo.setVisibility(View.GONE);
                }else
                {
                    TimeAgo.setText(status);
                    TimeAgo.setVisibility(View.VISIBLE);
                    Status.setVisibility(View.GONE);
                }
            }


        }
    }
}
