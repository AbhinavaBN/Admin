package com.developers.spicknspan.admin.monitor_hardware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.developers.spicknspan.admin.Activities.MainActivity;

public class Broadcast_to_UI {

    private Activity app;
    check_internet internet;
    public Broadcast_to_UI(Activity app)
    {
        this.app = app;
        internet = new check_internet(app, this);
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
}
