package com.mac_available.carrotmarket;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitService {

    @Multipart
    @POST("/Available/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart);

    @GET("Available/loadDB.php")
    Call<ArrayList<LocalVO>> loadDataFromServer();
}
