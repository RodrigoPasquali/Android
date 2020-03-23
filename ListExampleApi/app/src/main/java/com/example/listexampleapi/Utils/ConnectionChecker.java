package com.example.listexampleapi.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionChecker {
    public static boolean checkInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean connectionInternet = true;

        if (networkInfo != null && networkInfo.isConnected()) {
            connectionInternet = true;
        } else {
            connectionInternet = false;
        }

        return connectionInternet;
    }
}
