package com.example.apicall.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apicall.R;
import com.example.apicall.model.UserPost;

import java.util.ArrayList;

/**
 * This is Adapter class for RecycleView of User Post
 */

public class UserPostAdapter extends RecyclerView.Adapter {

    private ArrayList<UserPost> postArrayList;

    public UserPostAdapter(ArrayList<UserPost> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_post_adapter,viewGroup, false);

        return new MyViewHolder1(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserPost userPost=postArrayList.get(viewHolder.getAdapterPosition());
        ((MyViewHolder1)viewHolder).tv_user_id.setText(userPost.getUserId());
        ((MyViewHolder1)viewHolder).tv_post_id.setText(userPost.getId());
        ((MyViewHolder1)viewHolder).tv_post_title.setText(userPost.getTitle());
        ((MyViewHolder1)viewHolder).tv_post.setText(userPost.getBody());
    }

    @Override
    public int getItemCount() {
        if(postArrayList!=null){
            return postArrayList.size();
        }
        return 0;
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        public TextView tv_post_id, tv_post_title,tv_post,tv_user_id;

        public MyViewHolder1(View view) {
            super(view);
            tv_user_id = view.findViewById(R.id.tv_userId2);
            tv_post_id= view.findViewById(R.id.tv_id2);
            tv_post_title=view.findViewById(R.id.tv_title);
            tv_post=view.findViewById(R.id.tv_body);
        }

    }
}
