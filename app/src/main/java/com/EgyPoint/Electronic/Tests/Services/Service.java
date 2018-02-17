package com.EgyPoint.Electronic.Tests.Services;

import com.EgyPoint.Electronic.Tests.Models.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Delta on 15/02/2018.
 */

public interface Service {

    @FormUrlEncoded
    @POST("/api/login.php")
    Call<Result> Login(@FieldMap Map<String,String> map);
}
