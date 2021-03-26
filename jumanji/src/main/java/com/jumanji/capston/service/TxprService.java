//package com.jumanji.capston.service;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class TxprService {
//
//    private Gson gson;
//    final String APIURL =  "https://teht.hometax.go.kr/wqAction.do?actionId=ATTABZAA001R08&screenId=UTEABAAA13&popupYn=false&realScreenId=";
//    final String IDTEXTFIELD = "idTextField";
//    final String XMLRAW = "<map id=\"ATTABZAA001R08\"><pubcUserNo/><mobYn>N</mobYn><inqrTrgtClCd>1</inqrTrgtClCd><txprDscmNo>idTextField</txprDscmNo><dongCode>15</dongCode><psbSearch>Y</psbSearch><map id=\"userReqInfoVO\"/></map>"
//
//
//    @PostConstruct
//    private void setup()
//    {
//        gson=new GsonBuilder().create();
//    }
//
//    // National Tax Service check Business license number api.
//    public String searchPlace(String licenseId){
//        try {
//            licenseId = URLEncoder.encode(licenseId, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("encoding fail!",e);
//        }
//        String post = XMLRAW.replace(IDTEXTFIELD, licenseId);
//
//        HttpURLConnection conn = connect(APIURL);
//    }
//
//
//
//    private HttpURLConnection connect(String apiUrl){
//        try {
//            URL url = new URL(apiUrl);
//            return (HttpURLConnection)url.openConnection();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
//        } catch (IOException e) {
//            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
//        }
//    }
//}