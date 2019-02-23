package com.developers.spicknspan.admin.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.developers.spicknspan.admin.Activities.MainActivity;

public class MyBroadCastReceiver extends BroadcastReceiver {
    Activity app;
    public MyBroadCastReceiver(Activity app)
    {
        this.app = app;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        String data= bundle.getString(MainActivity.BROAD_DATA);
        Log.e("myBroadCastReceiver", data);
        alert_function(data);
    }
    public void alert_function(String info)
    {
        final String information = info;
        app.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alert = new AlertDialog.Builder(app);
                alert.setTitle("From main....Information");
                alert.setMessage(information);
                alert.setPositiveButton("OK", null);
                alert.setNegativeButton("Mobile Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        enableDisableMobileData();
                    }
                });
                alert.show();
            }
        });
    }

    public void enableDisableMobileData() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
        app.startActivity(intent);

    }
}
