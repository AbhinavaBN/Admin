package com.developers.spicknspan.admin.monitor_hardware;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class check_permissions implements ActivityCompat.OnRequestPermissionsResultCallback{
    private Activity app;
    private static final int MY_PERMISSIONS_REQUEST_GPS = 1;
    public check_permissions(Activity app) {
        this.app = app;
    }

    public boolean check() {

        if(ActivityCompat.checkSelfPermission(app, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED  && ActivityCompat.checkSelfPermission(app,
        Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(app,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            }
            else {
                ActivityCompat.requestPermissions(app,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_GPS);
            }
        }
            return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GPS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    AlertDialog.Builder diag = new AlertDialog.Builder(app);
                    diag.setPositiveButton("Permission Required", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            app.finish();
                        }
                    });
                }
            }
        }
    }
}
