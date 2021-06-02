package com.example.jmjapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private static Server instance;
    private final ServerApi api;

    private Server() {
//        String url = "http://3.34.55.186:8088/api/v1/"; // aws
//        String url = "http://192.168.1.65:8088/api/v1/"; // 학교 와이파이 통할때
        String url = "http://10.0.2.2:8088/api/v1/"; // 애뮬로 로컬에서 할때

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ServerApi.class);
    }

    public static Server getInstance() {
        if(instance==null)
            instance=new Server();
        return instance;
    }

    public ServerApi getApi() {
        return api;
    }
}
