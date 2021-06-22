package com.example.jmjapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server2 {
    private static Server2 instance2;
    private final ServerApiForPayment api2;

    private Server2() {
        String url = "http://3.34.55.186:8088/"; // aws
         //String url = "http://192.168.1.51:8088/"; // 로컬

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api2 = retrofit.create(ServerApiForPayment.class);
    }

    public static Server2 getInstance2() {
        if(instance2==null)
            instance2=new Server2();
        return instance2;
    }

    public ServerApiForPayment getApi2() {
        return api2;
    }

}
