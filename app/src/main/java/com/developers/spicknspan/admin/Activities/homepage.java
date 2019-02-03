package com.developers.spicknspan.admin.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.developers.spicknspan.admin.R;
import com.google.android.gms.maps.SupportMapFragment;

import java.lang.reflect.Field;
import java.util.Locale;

public class homepage extends android.support.v4.app.Fragment {

    android.support.v7.widget.GridLayout Grid = null;

    String layout_name = "ver_lay";

    LinearLayout layout[] = new LinearLayout[3];

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            Log.e("getResId",  e.toString());

            return -1;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.e("onclick", String.valueOf(layout.length));
        //int ids[] = {R.id.ver_lay0, R.id.ver_lay1, R.id.ver_lay2};
        View view = inflater.inflate(R.layout.activity_homepage, container, false);
        for (int i = 0; i < layout.length; i++)
        {
            String name = layout_name + String.valueOf(i);
            layout[i] = view.findViewById(getResId(name, R.id.class));
            layout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("onclick", view.toString());
                    
                    Intent intent = new Intent(getActivity(), Maps_start_stop.class);
                    startActivity(intent);
                    /*Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                    startActivity(intent);*/
                    /*String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 16.60572, 77.50);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);*/
                }
            });
        }
        return view;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("homepage","oncreate");
    }
}
