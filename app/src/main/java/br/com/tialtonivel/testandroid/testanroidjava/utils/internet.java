package br.com.tialtonivel.testandroid.testanroidjava.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class internet {
    public boolean IsConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = null;
        if (connectivityManager != null) {
            ni = connectivityManager.getActiveNetworkInfo();
        }
        return ni != null && ni.isConnected();
    }
}
