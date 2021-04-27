package com.example.jmjapp.Fragment;

import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Shop;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface ApiService {
    static final String BASEURL = "http://3.34.55.186:8088/api/v1/"; // 학교2
//    static final String BASEURL = "http://192.168.1.37:8088/api/v1/"; // 학교
    //static final String BASEURL = "http://122.202.45.37:8088/api/v1/"; // 집


    @GET("shopList/{category}")
    Call<List<Shop>> shopList(@Path("category") String category);

    @GET("shopList")
    Call<List<Shop>> shopList2(@Query("category") String category);

    @GET("shop/{id}")
    Call<Shop> shop(@Path("id") String id);

    @GET("menuList/{shopId}")
    Call<List<Menu>> menuList(@Path("shopId") String shopId);

    @PATCH("menu") // 메뉴 수정
    Call<ResponseBody> updateMenu(@Header("Authorization") String jwt, @Body Map<String, String> map);

    @DELETE("menu/{menuId}") // 메뉴 삭제
    Call<ResponseBody> deleteMenu(@Header("Authorization") String jwt, @Path("menuId") String menuId);

    @PATCH("menu/{menuId}/sale") // 메뉴 품절
    Call<ResponseBody> updateSale(@Header("Authorization") String jwt, @Path("menuId") String menuId);

    @PATCH("menu/{menuId}/popular") // 인기 메뉴
    Call<ResponseBody> updatePopular(@Header("Authorization") String jwt, @Path("menuId") String menuId);
}
