package com.EgyPoint.Electronic.Tests.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.EgyPoint.Electronic.Tests.Models.MessageModel;
import com.EgyPoint.Electronic.Tests.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Delta on 16/02/2018.
 */

public class Messages_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int item1 = 1;
    public static final int item2 = 2;

    Context context;
    List<MessageModel> messageModelList;
    String curr_user_id;

    public Messages_Adapter(Context context, String curr_user_id, List<MessageModel> messageModelList) {
        this.context = context;
        this.messageModelList = messageModelList;
        this.curr_user_id = curr_user_id;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == item1) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.msg_right, parent, false);
            ViewHolder1 holder1 = new ViewHolder1(view1);
            return holder1;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.msg_left, parent, false);
            ViewHolder2 holder2 = new ViewHolder2(view);
            return holder2;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int item = holder.getItemViewType();
        MessageModel messageModel = messageModelList.get(position);

        if (item == item1) {
            Log.e("item ", "" + item);
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.BindData(messageModel.getText(), messageModel.getDate());

        } else {
            Log.e("item ", "" + item);

            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.BindData(messageModel.getText(), messageModel.getFrom().substring(0, 2));
        }
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView msg, time;

        public ViewHolder1(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.msg_txt);
            time = itemView.findViewById(R.id.date);


        }

        public void BindData(String text, long date) {
            msg.setText(text);
            String t = new SimpleDateFormat("hh:mm aa").format(new Date(date));
            time.setText(t);
        }
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView msg, user_image;

        public ViewHolder2(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.msg);
            user_image = itemView.findViewById(R.id.user_image);

        }

        public void BindData(String text, String image) {
            msg.setText(text);
            user_image.setText(image);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messageModelList.get(position).getFrom().toString().equals(curr_user_id)) {
            Log.e("1", "1"+messageModelList.get(position).getFrom().toString()+curr_user_id);
            return item1;

        } else {
            Log.e("2", "2"+messageModelList.get(position).getFrom().toString()+curr_user_id);

            return item2;
        }

    }


}
