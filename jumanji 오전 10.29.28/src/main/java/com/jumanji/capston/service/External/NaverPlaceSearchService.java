package com.jumanji.capston.service.external;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class NaverPlaceSearchService implements ExternalApiService {

    private Gson gson;

    @PostConstruct
    private void setup()
    {
        gson=new GsonBuilder().create();
    }

    //naver place search api.
    public String searchPlace(String keyword){
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encoding fail!",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/local.json?query="+keyword+"&display=20&start=1&sort=random";    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "3DwdaAu9DxPsYTNgPQGD");
        requestHeaders.put("X-Naver-Client-Secret", "Fop9WootlX");
        String responseBody = get(apiURL,requestHeaders, "GET");

        System.out.println("네이버에서 받은 결과 = " + responseBody);
        System.out.println("-----------------------------------------");

        return gson.toJson(responseBody);
    }


}