package com.example.apicall.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apicall.R;
import com.example.apicall.adapter.UserListAdapter;
import com.example.apicall.constant.Constant;
import com.example.apicall.listener.CommunicationFragment;
import com.example.apicall.model.UserDetails;
import com.example.apicall.retrofit.RetrofitImplementation;

import java.util.ArrayList;

/**
 * This fragment is used fetch User list from api call using Retrofit
 * Show the userlist using Recycle view
 */
public class UserListFragment extends Fragment implements RetrofitImplementation.SendData {
    private View view;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private UserListAdapter mUserAdapter;
    private ArrayList<UserDetails> mUserDetailsArrayList;
    private CommunicationFragment mListener;
    private ProgressBar pbUserList;

    //empty constructor
    public UserListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_list, container, false);

        pbUserList = view.findViewById(R.id.pb_userlist);
        RetrofitImplementation ret = new RetrofitImplementation(this);
        ret.retrofitDataFetch();
        return view;
    }

    /**
     * This method handle on click of Recycle view item
     * @param position
     */
    private void onClickRecycle(int position) {
        Bundle mBundle = new Bundle();
        mBundle.putString(Constant.USER_ID, mUserDetailsArrayList.get(position).getId());
        mBundle.putString(Constant.USER_NAME, mUserDetailsArrayList.get(position).getmName());
        mListener.sendData(mBundle);
    }

    /**
     * This method used to intialise the variables
     */
    private void init() {
        mRecyclerView = view.findViewById(R.id.rv_user_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mUserAdapter = new UserListAdapter(mUserDetailsArrayList);
        mRecyclerView.setAdapter(mUserAdapter);
        mUserAdapter.setOnclickListener(new UserListAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                onClickRecycle(position);
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        if (context instanceof CommunicationFragment) {
            mListener = (CommunicationFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This is Callback from RetrofitImplementation
     * @param bundle of Bundle type used to get Type and Arraylist from retrofitImplementation
     */

    @Override
    public void callBack(Bundle bundle) {
        pbUserList.setVisibility(View.GONE);
        if (bundle.getString(Constant.TYPE_FROM_MAIN).equals(Constant.SUCCESS)) {
            mUserDetailsArrayList = (ArrayList<UserDetails>) bundle.getSerializable(Constant.USER_ARRAYLIST);
            init();
        }else {
            Toast.makeText(mContext,getString(R.string.data_not_recieved_toast),Toast.LENGTH_LONG).show();
        }
    }
}