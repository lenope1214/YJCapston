package com.jumanji.capston.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class TestController {

    @GetMapping("/addressApiTest")
    public String callAddressApi() {

        StringBuffer result = new StringBuffer();
        String keyword = "북구 동북로 65길 57";  // 얘는 프론트에서 주는걸로 받아와야함. 하지만 테스트니까~
        try {
            String urlStr = "https://www.juso.go.kr/addrlink/addrLinkApi.do" + "?" +
                    "resultType=json" + "&" +
                    "currentPage=1" + "&" +
                    "countPerPage=20" + "&" +
                    "keyword=" + keyword + "&" +
                    "confmKey=U01TX0FVVEgyMDIxMDMxNzE3MjcxNjExMDkzMzY=";

            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setDoOutput (true);
//            urlConnection.setRequestProperty( "Accept", "*");
            urlConnection.setRequestMethod("GET");
            System.out.println(urlConnection.toString());

            System.out.println("문자열은 어떻게 되나요? : \n" + urlStr);
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }

            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "</xmp>";
    }


    @GetMapping("adrSmpl")
    public String addressApiSample() {


        return "성공";
    }

    @GetMapping("ApiSample")
    public void Sample() throws IOException {
        String urlString = "https://www.google.com";
        String line = null;
        InputStream in = null;
        BufferedReader reader = null;
        HttpsURLConnection httpsConn = null;
        try { // Get HTTPS URL connection
            URL url = new URL(urlString);
            httpsConn = (HttpsURLConnection) url.openConnection();
            // Set Hostname verification
            httpsConn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    // Ignore host name verification. It always returns true.
                    return true;
                }
            });

            // Input setting
            httpsConn.setDoInput(true);
            // Output setting
            // httpsConn.setDoOutput(true);
            // Caches setting
            httpsConn.setUseCaches(false);
            // Read Timeout Setting
            httpsConn.setReadTimeout(1000);
            // Connection Timeout setting
            httpsConn.setConnectTimeout(1000);
            // Method Setting(GET/POST)
            httpsConn.setRequestMethod("GET");
            // Header Setting
            httpsConn.setRequestProperty("HeaderKey", "HeaderValue");


            int responseCode = httpsConn.getResponseCode();
            System.out.println("응답코드 : " + responseCode);
            System.out.println("응답메세지 : " + httpsConn.getResponseMessage());
            // SSL setting
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, null, null); // No validation for now
            httpsConn.setSSLSocketFactory(context.getSocketFactory());


            // Connect to host
            httpsConn.connect();
            httpsConn.setInstanceFollowRedirects(true);

            // Print response from host
            if (responseCode == HttpsURLConnection.HTTP_OK) { // 정상 호출 200
                in = httpsConn.getInputStream();
            } else { // 에러 발생
                in = httpsConn.getErrorStream();
            }
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                System.out.printf("%s\n", line);
            }

            reader.close();
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException : " + e);
        } catch (MalformedURLException e) {
            System.out.println(urlString + " is not a URL I understand");
        } catch (IOException e) {
            System.out.println("IOException :" + e);
        } catch (Exception e) {
            System.out.println("error : " + e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (httpsConn != null) {
                httpsConn.disconnect();
            }
        }
    }

}
