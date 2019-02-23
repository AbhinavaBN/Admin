package com.developers.spicknspan.admin.monitor_hardware;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.util.Log;

import com.developers.spicknspan.admin.Activities.MainActivity;

public class Broadcast_listners extends BroadcastReceiver {
    Activity app;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Log.e("NetworkCheckReceiver", "NetworkCheckReceiver invoked...");

            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

            if (!noConnectivity) {
                Log.e("NetworkCheckReceiver", "connected");
                sendMyBroadcast("Network connected");
            }
            else {
                alert_function("Network disconnected\nPlease Enable Mobile data or Wifi");
                sendMyBroadcast("Network disconnected");
                Log.e("NetworkCheckReceiver", "disconnected");
            }
        }
        if(intent.getAction().equals(LocationManager.PROVIDERS_CHANGED_ACTION))
        {
            LocationManager manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );

            if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                Log.e("NetworkCheckReceiver", "connected");
                sendMyBroadcast("GPS connected");
            }
            else
            {
                //alert_function("GPS disconnected\nPlease Enable GPS");
                sendMyBroadcast("GPS disconnected");
            }
        }
    }

    public Broadcast_listners(Activity app)
    {
        this.app = app;
        registerBroadcastRecivers();
    }
    public void registerBroadcastRecivers()
    {
        app.registerReceiver(this, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        app.registerReceiver(this, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
    }
    public static void unregisterBroadcastRecivers(Activity activity, Broadcast_listners broadcast_listners)
    {
        activity.unregisterReceiver(broadcast_listners);
    }
    public void sendMyBroadcast(String str)
    {
        try{
            Intent broadcast = new Intent();
            broadcast.setAction(MainActivity.BROADCAST_ACTION);
            broadcast.putExtra(MainActivity.BROAD_DATA, str);
            app.sendBroadcast(broadcast);
        }
        catch (Exception e)
        {

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
