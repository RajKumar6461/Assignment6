package com.example.apicall.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apicall.R;
import com.example.apicall.constant.Constant;
import com.example.apicall.fragment.UserDataFragment;
import com.example.apicall.listener.CommunicationFragment;

/**
 * This is Launcher Activity
 * Having Two fragments of equal size
 * First Fragment show the User List fetch from Api Call
 * Second Fragment shows user data on item click of first fragment
 *
 * @implement Communication fragment for cummunication among fragments
 */


public class MainActivity extends AppCompatActivity implements CommunicationFragment {

    private UserDataFragment userDataFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isConnected()) {
            AlertDialog mAlertDialog=buildDialog().create();
            mAlertDialog.setCanceledOnTouchOutside(false);
            mAlertDialog.show();
        }
        else {
            Toast.makeText(MainActivity.this,getString(R.string.welcome_toast), Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
            userDataFragment=(UserDataFragment)getSupportFragmentManager().findFragmentById(R.id.data_fragment);
        }


    }

    /**
     * This method used to check whether mobile data or connected to wifi or not
     *
     * @return boolean type check whether connected to network or not
     */

    private boolean isConnected() {

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else
            return false;
        }
        else
            return false;
    }

    /**
     * Used to genrate Alert Dialog box if not connected to network
     *
     * @return
     */
    private  AlertDialog.Builder buildDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_dialog_title);
        builder.setMessage(R.string.alert_dialog_message);

        builder.setPositiveButton(R.string.set_possitive_btn_text, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


    @Override
    public void sendData(Bundle bundle) {
        userDataFragment.updateDetails(bundle);
    }
}
