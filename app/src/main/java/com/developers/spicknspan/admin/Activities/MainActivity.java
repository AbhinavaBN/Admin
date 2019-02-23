package com.developers.spicknspan.admin.Activities;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.developers.spicknspan.admin.GPS.EmployeeData;
import com.developers.spicknspan.admin.Network.ApiClient;
import com.developers.spicknspan.admin.Network.TrackerApis;
import com.developers.spicknspan.admin.R;
import com.developers.spicknspan.admin.helpers.MyBroadCastReceiver;
import com.developers.spicknspan.admin.monitor_hardware.Broadcast_listners;
import com.developers.spicknspan.admin.monitor_hardware.check_internet;
import com.developers.spicknspan.admin.recycler_view.recycler_view_adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static  TrackerApis trackerApis;

    ArrayList<EmployeeData> stored_data = new ArrayList<>();

    public static final String BROADCAST_ACTION = "com.developers.spicknspan.admin";

    public static final String BROAD_DATA = "BROAD_DATA";

    Broadcast_listners Check_Services;

    MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver(this);

    static {
        trackerApis= ApiClient.getClient().create(TrackerApis.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_data_from_server();
        Check_Services = new Broadcast_listners(this);
        registerMyReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(Check_Services);
    }

    private void registerMyReceiver() {

        try
        {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BROADCAST_ACTION);
            registerReceiver(myBroadCastReceiver, intentFilter);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    ArrayList<String> get_names()
    {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < stored_data.size(); i++)
        {
            names.add(stored_data.get(i).getName());
        }
        return names;
    }
    ArrayList<String> get_phone_no()
    {
        ArrayList<String> phone = new ArrayList<>();
        for (int i = 0; i < stored_data.size(); i++)
        {
            phone.add(stored_data.get(i).getPhone());
        }
        return phone;
    }
    void set_recycler_view()
    {
        android.support.v7.widget.RecyclerView recycler = findViewById(R.id.recycle_view);
        recycler.setAdapter(new recycler_view_adapter(this, get_names(),get_phone_no()));
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
    }
    private void get_data_from_server()
    {
        Call<ArrayList<EmployeeData>> datacall = trackerApis.fieldEmployee();
        datacall.enqueue(new Callback<ArrayList<EmployeeData>>() {
            @Override
            public void onResponse(Call<ArrayList<EmployeeData>> call, Response<ArrayList<EmployeeData>> response) {

                //stored_data = response.body();
                for (int i = 0; i < 3; i++)
                {
                    stored_data.addAll(response.body());
                }
                set_recycler_view();
            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeData>> call, Throwable t) {
                stored_data = null;
            }
        });

    }
}
