package com.example.uniquitousprototype;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by YUNKYUSEOK on 2017-07-17.
 */

public class ApiApplication extends Application {
    public static final String BASE_URL = "http://ec2-13-124-51-140.ap-northeast-2.compute.amazonaws.com:8000/";
    private ApiService apiService;
    private boolean auto;
    private User nowUser;
    SharedPreferences login;

    @Override
    public void onCreate() {
        super.onCreate();
        login = getSharedPreferences("login", MODE_PRIVATE);
        auto = login.getBoolean("autologin", false);
    }

    public ApiService getApiService() {
        if (apiService == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            okHttpClient.addInterceptor(loggingInterceptor);
            okHttpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

    public boolean isLogedIn() {
        return login.getBoolean("autologin", false);
    }
    public boolean logedIn(){return login.getString("token",null) !=null;}
    public void logout() {
        SharedPreferences.Editor editor = login.edit();
        editor.remove("token");
        editor.putBoolean("autologin", false);
        editor.commit();
    }

    public void login(LoginUser loginUser) {
        if(loginUser == null) {
            return;
        } else {
            String token = loginUser.getToken();
            SharedPreferences.Editor editor = login.edit();
            editor.putString("token", "Token " + token);
            editor.putBoolean("autologin", true).apply();
            editor.commit();
        }
    }

    public String getToken() {
        String token = null;
        if (isLogedIn()) {
            token = login.getString("token", null);
        }
        return token;
    }

    public User getNowUser() {
        return nowUser;}

    public void setNowUser(User user)
    {
        nowUser = user;
    }
}
