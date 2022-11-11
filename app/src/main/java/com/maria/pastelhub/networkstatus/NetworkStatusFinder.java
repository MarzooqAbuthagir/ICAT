package com.maria.pastelhub.networkstatus;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.maria.pastelhub.log_files.LogFile;

public class NetworkStatusFinder extends Activity {

    boolean connected = false;

    public NetworkStatusFinder() {
//        networkStatus(context);
    }

    public boolean networkStatus(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            new LogFile("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

}
