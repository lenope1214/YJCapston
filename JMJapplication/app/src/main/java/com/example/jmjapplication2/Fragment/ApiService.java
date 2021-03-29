package com.example.jmjapplication2.Fragment;

import com.example.jmjapplication2.dto.Shop;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {
    //static final String BASEURL = "http://3.34.55.186:8088/api/v1/"; // 학교2
    static final String BASEURL = "http://192.168.1.17:8088/api/v1/"; // 학교
    //static final String BASEURL = "http://122.202.45.37:8088/api/v1/"; // 집


    @GET("shopList/{category}")
    Call<List<Shop>> shopList(@Path("category") String category);

    @GET("shopList")
    Call<List<Shop>> shopList();
}
