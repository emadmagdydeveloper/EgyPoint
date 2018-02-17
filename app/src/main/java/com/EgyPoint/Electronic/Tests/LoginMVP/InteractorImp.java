package com.EgyPoint.Electronic.Tests.LoginMVP;

import android.content.Context;
import android.text.TextUtils;

import com.EgyPoint.Electronic.Tests.Services.NetworkConnection;
import com.EgyPoint.Electronic.Tests.Models.Result;
import com.EgyPoint.Electronic.Tests.Services.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Delta on 15/02/2018.
 */

public class InteractorImp implements Interactor{
    @Override
    public void Login(String user_name, String user_password, Context context, OnCompleteListener listener) {

        if (TextUtils.isEmpty(user_name))
        {
            listener.setUserNameError();
        }else if (TextUtils.isEmpty(user_password))
        {
            listener.setPasswordError();
        }else
            {
                NetworkConnection connection = new NetworkConnection(context);
                if (connection.getConnection()==true)
                {
                    Login(user_name,user_password,listener);
                }else
                    {
                        listener.onNetworkError();
                    }
            }
    }

    private void Login(String user_name, String user_password, final Interactor.OnCompleteListener listener) {
        Map <String ,String > map = new HashMap<>();
        map.put("myusername",user_name);
        map.put("mypassword",user_password);

        Retrofit retrofit = setUpRetrofit();
        Service service = retrofit.create(Service.class);
        Call<Result> call = service.Login(map);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful())
                {
                    Result result = response.body();
                    if (result.getStatus().equals("success"))
                    {
                        listener.onLoggedInSuccess();
                    }else
                        {
                            listener.onFailed(result.getStatus());
                        }
                }else
                    {
                        listener.onFailed("something went haywire");
                    }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private Retrofit setUpRetrofit()
    {
        OkHttpClient  client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://edu.devoloper.xyz")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
