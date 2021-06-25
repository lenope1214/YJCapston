package com.example.jmjapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private static Server instance;
    private final ServerApi api;

    private Server() {
        String url = "http://3.34.55.186:8088/api/v1/"; // aws
        //String url = "http://192.168.1.51:8088/api/v1/"; // 로컬

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ServerApi.class);
    }

    public static Server getInstance() {
        if (instance == null)
            instance = new Server();
        return instance;
    }

    public ServerApi getApi() {
        return api;
    }

}
