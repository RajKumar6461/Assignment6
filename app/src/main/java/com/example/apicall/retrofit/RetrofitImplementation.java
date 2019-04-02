package com.example.apicall.retrofit;

import android.os.Bundle;
import com.example.apicall.constant.Constant;
import com.example.apicall.listener.Api;
import com.example.apicall.model.UserDetails;
import com.example.apicall.model.UserPost;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class used to perform Retrofit task
 * Two method
 * one is used for fetch User List
 * Second is used fro fetch User post
 *
 */
public class RetrofitImplementation {
    private SendData sendData;

    public RetrofitImplementation(SendData sendData) {
        this.sendData = sendData;
    }

    /**
     * Used to fetch user List from api
     * send callback with bundle
     */

    public void retrofitDataFetch(){
        final Bundle bundle=new Bundle();
        Retrofit mRetrofit=createRetrofit();

        Api api=mRetrofit.create(Api.class);

        Call<ArrayList<UserDetails>> call=api.getUserDetails();
        call.enqueue(new Callback<ArrayList<UserDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<UserDetails>> call, Response<ArrayList<UserDetails>> response) {

                ArrayList<UserDetails> list=new ArrayList<UserDetails>();
                for(int i=0;i<response.body().size();i++){
                    list.add(response.body().get(i));
                }

                bundle.putSerializable(Constant.USER_ARRAYLIST,list);
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.SUCCESS);
                sendData.callBack(bundle);
            }

            @Override
            public void onFailure(Call<ArrayList<UserDetails>> call, Throwable t) {
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.FAILURE);
                sendData.callBack(bundle);
            }

        });

    }

    /**
     *  Used to fetch userPost from api
     *  send callback with bundle
     * @param userId
     */
    public void retofitUserPostFetch(String userId){
        final Bundle bundle=new Bundle();
        Retrofit mRetrofit=createRetrofit();

        Api api=mRetrofit.create(Api.class);

        Call<ArrayList<UserPost>> call=api.getUserPost(Integer.parseInt(userId));
        call.enqueue(new Callback<ArrayList<UserPost>>() {
            @Override
            public void onResponse(Call<ArrayList<UserPost>> call, Response<ArrayList<UserPost>> response) {
                ArrayList<UserPost> list=new ArrayList<UserPost>();
                for(int i=0;i<response.body().size();i++){
                    list.add(response.body().get(i));
                }

                bundle.putSerializable(Constant.USER_POST_LIST,list);
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.SUCCESS);
                sendData.callBack(bundle);
            }

            @Override
            public void onFailure(Call<ArrayList<UserPost>> call, Throwable t) {
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.FAILURE);
                sendData.callBack(bundle);
            }
        });

    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public interface SendData{
        public void callBack(Bundle bundle);
    }

}

