package com.example.jmjapp.Fragment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Net {
    private static Net ourInstance = new Net();
    public static Net getInstance() {
        return ourInstance;
    }
    private Net() {

    }

    //static final String BASEURL = "http://3.34.55.186:8088/api/v1/"; // 학교2
    //static final String BASEURL = "http://192.168.1.37:8088/api/v1/"; // 학교
    //static final String BASEURL = "http://122.202.45.37:8088/api/v1/"; // 집
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://3.34.55.186:8088/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiService apiService;

    public ApiService getApiService(){
        if(apiService == null){
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
