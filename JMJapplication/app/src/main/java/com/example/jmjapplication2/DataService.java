package com.example.jmjapplication2;

import com.example.jmjapplication2.dto.MemberDTO;
import com.example.jmjapplication2.dto.Validate;
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
    private String BASE_URL = "http://192.168.98.1:8885/member/";

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();

    SelectAPI select = retrofitClient.create(SelectAPI.class);
    InsertAPI insert = retrofitClient.create(InsertAPI.class);
    UpdateAPI update = retrofitClient.create(UpdateAPI.class);
    DeleteAPI delete = retrofitClient.create(DeleteAPI.class);
    ValidateAPI validate = retrofitClient.create(ValidateAPI.class);
}

interface SelectAPI{
    @GET("select/{id}")
    Call<MemberDTO> selectOne(@Path("id") long id);

    @GET("select")
    Call<List<MemberDTO>> selectAll();
}

interface InsertAPI{
    @POST("insert")
    Call<MemberDTO> insertOne(@Body Map<String, String> map);
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
    @FormUrlEncoded
    @POST("validate/{userid}")
    Call<ResponseBody> validateOne(@Field("userid") String userid);
}
