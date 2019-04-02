package com.example.apicall.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.apicall.R;
import com.example.apicall.activity.UserPostActivity;
import com.example.apicall.constant.Constant;

/**
 * This class show user Data when clicked item in UserListFragment
 * Data is passed with help of interface
 * one Button for moving to UserPostActivity for generating posts
 */

public class UserDataFragment extends Fragment {
    private View view;
    private Context mContext;
    private TextView tvId;
    private TextView tvName;
    private ImageView ivUser;
    private Button btPostUser;


    public UserDataFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_data, container, false);
        init();
        btPostUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,UserPostActivity.class);
                intent.putExtra(Constant.USER_ID,tvId.getText().toString().trim());
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     * This method used to initialize the variables
     */
    private void init(){
        ivUser=view.findViewById(R.id.iv_userimage);
        tvId=view.findViewById(R.id.tv_id);
        tvName=view.findViewById(R.id.tv_name);
        btPostUser=view.findViewById(R.id.btn_post);
        btPostUser.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This method used to set values to textview
     * @param bundle from Activity provide data for set Textview
     */

    public void updateDetails(Bundle bundle) {
        ivUser.setImageResource(R.mipmap.ic_launcher);
        tvName.setText(bundle.getString(Constant.USER_NAME));
        tvId.setText(bundle.getString(Constant.USER_ID));

        if(btPostUser.getVisibility()==View.GONE)
            btPostUser.setVisibility(View.VISIBLE);
    }
}
