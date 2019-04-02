package com.example.apicall.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apicall.R;
import com.example.apicall.model.UserDetails;

import java.util.ArrayList;

/**
 * This is Adapter class for RecycleView of User List
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private ArrayList<UserDetails> mUserArrayList;
    private ItemClickListener itemClickListener;

    public void setOnclickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public UserListAdapter(ArrayList<UserDetails> mStudentArrayList)
    {
        this.mUserArrayList=mStudentArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName, tvUserId;

        public MyViewHolder(View view) {
            super(view);
            tvUserName = view.findViewById(R.id.tv_userName);
            tvUserId= view.findViewById(R.id.tv_userId);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null){
                        int po=getAdapterPosition();
                        if(po!=RecyclerView.NO_POSITION){
                            itemClickListener.onItemClickListener(po);
                        }
                    }
                }
            });
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        UserDetails userDetails = mUserArrayList.get(myViewHolder.getAdapterPosition());
        myViewHolder.tvUserName.setText(userDetails.getmName());
        myViewHolder.tvUserId.setText(userDetails.getId());
    }

    @Override
    public int getItemCount() {
        if(mUserArrayList!=null) {
            return mUserArrayList.size();
        }
        return 0;
    }

    public interface ItemClickListener{
        void onItemClickListener(int position);
    }


}
