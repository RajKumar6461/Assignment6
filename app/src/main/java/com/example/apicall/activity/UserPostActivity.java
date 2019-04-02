package com.example.apicall.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.apicall.R;
import com.example.apicall.adapter.UserPostAdapter;
import com.example.apicall.constant.Constant;
import com.example.apicall.model.UserPost;
import com.example.apicall.retrofit.RetrofitImplementation;

import java.util.ArrayList;

/**
 * This Activity is used for showing User post in Recycle View
 *
 * @see com.example.apicall.retrofit.RetrofitImplementation.SendData  for retrofit callback
 */

public class UserPostActivity extends AppCompatActivity implements RetrofitImplementation.SendData {

    private ArrayList<UserPost> mPostArrayList=new ArrayList<UserPost>();
    private RecyclerView mRecyclerViewPost;
    private UserPostAdapter mUserPostAdapter;
    private ProgressBar pbTillPostList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);
        init();
        RetrofitImplementation retrofitImplementation=new RetrofitImplementation(this);
        retrofitImplementation.retofitUserPostFetch(getIntent().getStringExtra(Constant.USER_ID));
    }

    /**
     * This method used to initialise varianles
     */
    private void init(){
        pbTillPostList=findViewById(R.id.pb_user_post_list);
        mRecyclerViewPost=findViewById(R.id.rv_user_post_list);
        mRecyclerViewPost.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This is overriden method of implemented interface
     * @param bundle from RetrofitImplementation class provide arraylist
     */
    @Override
    public void callBack(Bundle bundle) {
        if(bundle.getString(Constant.TYPE_FROM_MAIN).equals(Constant.SUCCESS)) {

            pbTillPostList.setVisibility(View.GONE);
            mPostArrayList = (ArrayList<UserPost>) bundle.getSerializable(Constant.USER_POST_LIST);
            mUserPostAdapter = new UserPostAdapter(mPostArrayList);
            mRecyclerViewPost.setAdapter(mUserPostAdapter);
        }
        else{

            Toast.makeText(this,R.string.data_not_recieved_toast,Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
