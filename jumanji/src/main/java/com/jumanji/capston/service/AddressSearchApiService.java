package com.jumanji.capston.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddressSearchApiService implements ExternalApiService{


    private Gson gson;

    @PostConstruct
    private void setup()
    {
        gson=new GsonBuilder().create();
    }


    public String searchAddress(String apiUrl){

        Map<String, String> requestHeaders = new HashMap<>(); // 키 밸류 (json)으로 보내기.
        // 헤더에 더 필요한 값 넣으려면
        // requestHeaders.put("key", "value"); 넣기!
        String responseBody = get(apiUrl, requestHeaders, "POST");
        return gson.toJson(responseBody);
    }



}
