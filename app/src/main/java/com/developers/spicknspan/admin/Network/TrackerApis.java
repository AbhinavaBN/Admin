package com.developers.spicknspan.admin.Network;


import com.developers.spicknspan.admin.GPS.EmployeeData;
import com.developers.spicknspan.admin.GPS.EmployeeData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TrackerApis {

@FormUrlEncoded
@POST("login")
Call<String> login(@Field("username") String username, @Field("password") String password);


@GET("fieldEmployees")
Call<ArrayList<EmployeeData>> fieldEmployee();

}
