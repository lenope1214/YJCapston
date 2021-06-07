package com.jumanji.capston.service.external;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jumanji.capston.config.IamportConfig;
import com.jumanji.capston.data.Order;
import com.jumanji.capston.data.externalData.iamport.Iamport;
import com.jumanji.capston.service.OrderServiceImpl;
import com.jumanji.capston.service.ShopServiceImpl;
import com.jumanji.capston.service.UserServiceImpl;
import com.jumanji.capston.service.external.iamportAndroid.response.IamportResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.sql.Time;
import java.sql.Timestamp;


@Service
public class IamportClientService implements com.jumanji.capston.service.external.ExternalApiService {
    private static final String API_URL = "https://api.iamport.kr";
    private String api_key = null;
    private String api_secret = null;
    private CloseableHttpClient client = null;
    private Gson gson = new Gson();
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    public IamportClientService(IamportConfig config) {
//        System.out.println("StorageServicec constructor...");
        this.api_key = config.getKey();
        this.api_secret = config.getSecret();
        this.client = HttpClientBuilder.create().build();
    }

    public IamportClientService(String api_key, String api_secret) {
        this.api_key = api_key;
        this.api_secret = api_secret;
        this.client = HttpClientBuilder.create().build();
    }

    private IamportResponse<Iamport.AccessToken> getAuth() throws Exception {
        Iamport.AuthData authData = new Iamport.AuthData(api_key, api_secret);
        System.out.println("Iamport api_key : " + api_key);
        System.out.println("Iamport api_secret : " + api_secret);
        String authJsonData = gson.toJson(authData);
        System.out.println("authJsonDate is null?" + (authJsonData == null));
        try {
            StringEntity data = new StringEntity(authJsonData);
            System.out.println("data is null?" + (authJsonData == null));
            HttpPost postRequest = new HttpPost(API_URL + "/users/getToken");
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json");

            postRequest.setEntity(data);

            HttpResponse response =
                    client.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(response);

            Type listType = new TypeToken<IamportResponse<Iamport.AccessToken>>() {
            }.getType();
            IamportResponse<Iamport.AccessToken> auth = gson.fromJson(body, listType);

            return auth;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String postRequest(String path, String token, StringEntity postData) throws URISyntaxException {

        try {

            HttpPost postRequest = new HttpPost(API_URL + path);
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.addHeader("Authorization", token);

            postRequest.setEntity(postData);

            HttpResponse response = client.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(response);

            return body;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getRequest(String path, String token) throws URISyntaxException {

        try {

            HttpGet getRequest = new HttpGet(API_URL + path);
            getRequest.addHeader("Accept", "application/json");
            getRequest.addHeader("Authorization", token);
            System.out.println("getRequest token : " + token);
            HttpResponse response = client.execute(getRequest);
            System.out.println("response.status code : " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode() +"\nmessage : " + response.getStatusLine().getReasonPhrase());
            }

            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(response);

            return body;

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    public String getToken(String authorization) throws Exception {
        String loginId = userService.getMyId(authorization);
        // 유효성 체크
        userService.isLogin(authorization);

        return getToken();
    }

    public String getToken() throws Exception {
        IamportResponse<Iamport.AccessToken> auth = this.getAuth();

        if (auth != null) {
            String token = auth.getResponse().getToken();
            return token;
        }
        return null;
    }

    public IamportResponse<Iamport.Payment> paymentByImpUid(String authorization, String impUid) throws Exception {

        String token = this.getToken(authorization);

        if (token != null) {
            String path = "/payments/" + impUid;
            String response = this.getRequest(path, token);

            Type listType = new TypeToken<IamportResponse<Iamport.Payment>>() {
            }.getType();
            IamportResponse<Iamport.Payment> payment = gson.fromJson(response, listType);

            return payment;
        }
        return null;
    }

    public IamportResponse<Iamport.Payment> paymentByMerchantUid(String merchantUid) throws Exception {

        String token = this.getToken();

        if (token != null) {
            String path = "/payments/find/" + merchantUid + "/paid";
            System.out.println("paymentByMerchantUid - path : " + path);
            String response = this.getRequest(path, token);

            Type listType = new TypeToken<IamportResponse<Iamport.Payment>>() {
            }.getType();
            IamportResponse<Iamport.Payment> payment = gson.fromJson(response, listType);
            System.out.println(payment.toString());
            return payment;
        }

        return null;

    }

    public IamportResponse<Iamport.Payment> paymentByMerchantUid(String authorization, String merchantUid) throws Exception {

        String token = this.getToken(authorization);

        if (token != null) {
            String path = "/payments/find/" + merchantUid + "/paid";
            System.out.println("paymentByMerchantUid - path : " + path);
            String response = this.getRequest(path, token);

            Type listType = new TypeToken<IamportResponse<Iamport.Payment>>() {
            }.getType();
            IamportResponse<Iamport.Payment> payment = gson.fromJson(response, listType);

            return payment;
        }

        return null;
    }

    //    public IamportResponse<Iamport.Payment> cancelPayment(String authorization, Iamport.CancelData cancelData) throws Exception {
    public IamportResponse<Iamport.Payment> cancelPayment(String authorization, String m_id) throws Exception {
        String token = this.getToken(authorization);
        String loginId = userService.getMyId(authorization);
        Long orderId = Long.parseLong(m_id);
        Timestamp orderIdTime = new Timestamp(orderId);
        System.out.println("cancel info >>>>>>>>>" +
                "loginId : " + loginId + "\n" +
                "orderId : " + orderId + "\n" +
                "orderIdTime : " + orderIdTime);
        // 유효성 체크
        userService.isLogin(authorization);
        // shopService.isPresent() 매장 존재여부는 필요 없겠지?
        Order order = orderService.isOwnOrder(orderIdTime, loginId);

        if (token != null) {
            Iamport.CancelData cancelData = new Iamport.CancelData(m_id, false); // imp_uid 가 아니면, m_id 넣게 되어있음.
            System.out.println("m_id : " + cancelData.getMerchant_uid());
            String cancelJsonData = gson.toJson(cancelData);
            StringEntity data = new StringEntity(cancelJsonData);

            String response = this.postRequest("/payments/cancel", token, data);

            Type listType = new TypeToken<IamportResponse<Iamport.Payment>>() {
            }.getType();
            IamportResponse<Iamport.Payment> payment = gson.fromJson(response, listType);
            order.refund();
            orderService.statusUpdate(order);
            return payment;
        }
        return null;
    }
}