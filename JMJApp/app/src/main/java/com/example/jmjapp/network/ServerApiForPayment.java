package com.example.jmjapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ServerApiForPayment {
    /**
     *
     * 결제 결제 결제 결제 결제 결제 결제 결제 결제 결제 결제 결제
     *
     */

    @GET("iamport/cancel") // 사용자 측 주문 취소 후 환불
    Call<ResponseBody> userRefund(@Header("Authorization")String jwt,
                                  @Query("m_id") String m_id);

    @GET("iamport/shop/cancel") // 사업자 측 주문 취소 후 환불
    Call<ResponseBody> ownerRefund(@Header("Authorization")String jwt,
                                   @Query("shop_id") String shop_id,
                                   @Query("m_id") String m_id);
}
