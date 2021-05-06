package com.example.jmjapp.network;

import com.example.jmjapp.dto.MemberDTO;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Shop;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerApi {

    /**
     *
     * 사용자 페이지
     * 사용자 페이지
     * 사용자 페이지
     * 사용자 페이지
     *
     */

    /**
     * create create create create
     */

    @POST("join") // 회원가입
    Call<String> join(@Body Map<String, String> map);

    @POST("payment") // 주문등록
    Call<ResponseBody> payment(@Header("Authorization")String jwt,  @Body Map<String, Object> map);

    /**
     * read read read read
     */

    @POST("login") // 로그인
    Call<ResponseBody> LoginOne(@Body Map<String, String> map);

    @GET("validate/{id}") // 중복체크
    Call<ResponseBody> validateOne(@Path("id") String id);

    @GET("shop/{id}") // 특정 매장 조회
    Call<Shop> shop(@Path("id") String id);

    @POST("order") // 주문서 등록
    Call<ResponseBody> order(@Header("Authorization")String jwt,
                             @Body Map<String, String> map);

    @GET("pay") // 주문 정보 전송
    Call<ResponseBody> pay(@Query("name") String name,
                           @Query("phone") String phone,
                           @Query("amount") int amount);

    @GET("user") // 회원정보 조회
    Call<MemberDTO> getUser(@Header("Authorization") String jwt);

    @GET("shopList") // 매장 카테고리 조회
    Call<List<Shop>> shopList2(@Query("category") String category);

    /**
     * update update update update
     */

    @PATCH("user") // 비밀번호 변경
    Call<ResponseBody> updateOne(@Header("Authorization")String jwt,
                                 @Body Map<String, String> map);

    /**
     * delete delete delete delete
     */

    @DELETE("user") // 회원탈퇴
    Call<ResponseBody> deleteOne(@Header("Authorization") String jwt);





    /**
     *
     * 사업자 페이지
     * 사업자 페이지
     * 사업자 페이지
     * 사업자 페이지
     *
     */

    /**
     * create create create create
     */

    @Multipart
    @POST("shop") // 매장 등록
    Call<Shop> insertShop(@Header("Authorization") String jwt,
                          @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("menu") // 메뉴 등록
    Call<ResponseBody> insertMenu(@Header("Authorization") String jwt,
                                  @PartMap Map<String, RequestBody> map,
                                  @Part MultipartBody.Part file);

    /**
     * read read read read
     */

    @GET("menuList/{shopId}") // 한 매장의 메뉴리스트
    Call<List<Menu>> menuList(@Path("shopId") String shopId);


    @GET("myShop") // 한 유저의 매장리스트
    Call<List<Shop>> myShop2(@Header("Authorization") String jwt);

    /**
     * update update update update
     */

    @PATCH("shop/{shopid}/open") // 매장 오픈 수정
    Call<ResponseBody>  updateOpen(@Header("Authorization") String jwt,
                                   @Path("shopid") String shopid);

    @PATCH("shop/{shopid}/reserve") // 매장 예약 수정
    Call<ResponseBody> updateIsRsPos(@Header("Authorization") String jwt,
                                     @Path("shopid") String shopid);

    @PATCH("shop") // 매장 수정
    Call<ResponseBody> updateShop(@Header("Authorization") String jwt,
                                  @Body Map<String, String> map);

    @PATCH("menu") // 메뉴 수정
    Call<ResponseBody> updateMenu(@Header("Authorization") String jwt,
                                  @Body Map<String, String> map);

    @PATCH("menu/{menuId}/sale") // 메뉴 품절
    Call<ResponseBody> updateSale(@Header("Authorization") String jwt,
                                  @Path("menuId") String menuId);

    @PATCH("menu/{menuId}/popular") // 인기 메뉴
    Call<ResponseBody> updatePopular(@Header("Authorization") String jwt,
                                     @Path("menuId") String menuId);

    /**
     * delete delete delete delete
     */

    @DELETE("menu/{menuId}") // 메뉴 삭제
    Call<ResponseBody> deleteMenu(@Header("Authorization") String jwt,
                                  @Path("menuId") String menuId);

}
