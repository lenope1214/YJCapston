package com.example.jmjapp.owner;

import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public class DataService {
    private String BASE_URL = "http://3.34.55.186:8088/api/v1/"; // 학교2
    //static final public String BASE_URL = "http://192.168.1.37:8088/api/v1/"; // 학교
    //private String BASE_URL = "http://122.202.45.37:8088/api/v1/"; // 집

    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

    CreateAPI create = retrofitClient.create(CreateAPI.class);
    ReadAPI read = retrofitClient.create(ReadAPI.class);
    UpdateAPI update = retrofitClient.create(UpdateAPI.class);
    DeleteAPI delete = retrofitClient.create(DeleteAPI.class);
}

interface CreateAPI {
    @Multipart
    @POST("shop") // 매장 등록
    Call<Shop> insertShop(@Header("Authorization") String jwt, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("menu") // 메뉴 등록
    Call<ResponseBody> insertMenu(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part file);
    //

    @POST("join") // 회원가입
    Call<String> join(@Body Map<String, String> map);
}

interface ReadAPI {
    @POST("login") // 로그인
    Call<ResponseBody> LoginOne(@Body Map<String, String> map);

    @GET("shop/{id}") // 특정 매장 조회
    Call<Shop> shop(@Path("id") String id);

    @GET("menuList/{shopId}") // 한 매장의 메뉴리스트
    Call<List<Menu>> menuList(@Path("shopId") String shopId);

    @GET("myShop") // 한 유저의 매장리스트
    Call<List<Shop>> myShop2(@Header("Authorization") String jwt);

    @GET("validate/{id}") // 아이디 중복체크
    Call<ResponseBody> validateOne(@Path("id") String id);
}

interface UpdateAPI {
    @PATCH("shop/{shopid}/open") // 매장 오픈 수정
    Call<ResponseBody>  updateOpen(@Header("Authorization") String jwt, @Path("shopid") String shopid);

    @PATCH("shop/{shopid}/reserve") // 매장 예약 수정
    Call<ResponseBody> updateIsRsPos(@Header("Authorization") String jwt, @Path("shopid") String shopid);
}

interface DeleteAPI {

}
