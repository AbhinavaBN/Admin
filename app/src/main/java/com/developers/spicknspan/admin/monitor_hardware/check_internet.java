package com.developers.spicknspan.admin.monitor_hardware;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.developers.spicknspan.admin.Activities.MainActivity;

public class check_internet extends BroadcastReceiver {
    Activity app;
    private static final String TAG = "BroadcastReceiver";
    Broadcast_to_UI broadcast;
    public check_internet(Activity app, Broadcast_to_UI broadcast){
        this.app = app;
        this.broadcast = broadcast;
        check_permissions per = new check_permissions(app);
        per.check();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            app.registerReceiver(this, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }

    /*private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo;
        try {
            netInfo = cm.getAllNetworkInfo();
        }
        catch (NullPointerException e)
        {
            return false;
        }

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }*/
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Log.e("NetworkCheckReceiver", "NetworkCheckReceiver invoked...");

            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

            if (!noConnectivity) {
                Log.e("NetworkCheckReceiver", "connected");
                broadcast.sendMyBroadcast("connected");
            }
            else {
                alert_function("Network disconnected\nPlease Enable Mobile data or Wifi");
                broadcast.sendMyBroadcast("disconnected");
                Log.e("NetworkCheckReceiver", "disconnected");
            }
        }
    }
    public void alert_function(String info)
    {
        final String information = info;
        app.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alert = new AlertDialog.Builder(app);
                alert.setTitle("Information");
                alert.setMessage(information);
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        });

    }
}
