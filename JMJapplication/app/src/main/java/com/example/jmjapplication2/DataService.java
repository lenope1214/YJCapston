package com.example.jmjapplication2;

import com.example.jmjapplication2.dto.MemberDTO;
import com.example.jmjapplication2.dto.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public class DataService {
    //private String BASE_URL = "http://3.34.55.186:8088/api/v1/"; // 학교2
    static final public String BASE_URL = "http://192.168.1.17:8088/api/v1/"; // 학교
    //private String BASE_URL = "http://122.202.45.37:8088/api/v1/"; // 집

    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

    SelectAPI select = retrofitClient.create(SelectAPI.class);
    JoinAPI join = retrofitClient.create(JoinAPI.class);
    UpdateAPI update = retrofitClient.create(UpdateAPI.class);
    DeleteAPI delete = retrofitClient.create(DeleteAPI.class);
    ValidateAPI validate = retrofitClient.create(ValidateAPI.class);
    LoginAPI login = retrofitClient.create(LoginAPI.class);
    MyShopAPI myShop = retrofitClient.create(MyShopAPI.class);
    InsertShop insertShop = retrofitClient.create(InsertShop.class);
    ShowShopList showShopList = retrofitClient.create(ShowShopList.class);
    ShowShopList2 showShopList2 = retrofitClient.create(ShowShopList2.class);
    ShowShopOne shop = retrofitClient.create(ShowShopOne.class);
    ShopUpdate updateOpen = retrofitClient.create(ShopUpdate.class);
    ShopUpdate updateIsRsPos = retrofitClient.create(ShopUpdate.class);
   // CheckUserAPI getUser = retrofitClient.create(CheckUserAPI.class);
}

interface SelectAPI{
    @GET("select/{id}")
    Call<MemberDTO> selectOne(@Path("id") long id);

    @GET("select")
    Call<List<MemberDTO>> selectAll();
}

interface JoinAPI{
    @POST("join")
    Call<String> join(@Body Map<String, String> map);
}

interface UpdateAPI{
    @POST("update/{id}")
    Call<MemberDTO> updateOne(@Path("id") long id, @Body Map<String, String> map);
}

interface DeleteAPI {
    @POST("delete/{id}")
    Call<ResponseBody> deleteOne(@Path("id") long id);

}

interface ValidateAPI {
    @GET("validate/{id}")
    Call<ResponseBody> validateOne(@Path("id") String id);
}

//interface LoginAPI {
//    @FormUrlEncoded
//    @POST("login")
//    Call<MemberDTO> LoginOne(@Field("id") String id,
//                             @Field("password") String password);
//}

//interface LoginAPI {
//    @FormUrlEncoded
//    @POST("login")
//    Call<MemberDTO> LoginOne(@Body Map<String, String> map);
//}

interface LoginAPI {
    @POST("login")
    Call<ResponseBody> LoginOne(@Body Map<String, String> map);
}

interface MyShopAPI {
    @GET("myShop")
    Call<Shop> myShop(@Header("Authorization") String jwt);

    @GET("myShop")
    Call<List<Shop>> myShop2(@Header("Authorization") String jwt);
}

interface InsertShop {
    @POST("shop")
    Call<Shop> insertShop(@Header("Authorization")String jwt , @Body Map<String, Object> map);
}

//interface CheckUserAPI {
//    @POST("d")
//    Call<MemberDTO> getUser(@Header("d") String d);
//}

interface ShowShopList {
    @GET("shopList")
    Call<Shop> shopList();

}

interface ShowShopList2 {
    @GET("shopList")
    Call<List<Shop>> shopList();
}

interface ShowShopOne {
    @GET("shop/{id}")
    Call<Shop> shop(@Path("id") String id);
}

interface ShopUpdate {
    @PUT("shop/ isOpen")
    Call<ResponseBody>  updateOpen(@Header("Authorization") String jwt);

    @PUT("shop/isRsPos")
    Call<ResponseBody> updateIsRsPos(@Header("Authorization") String jwt);
}
