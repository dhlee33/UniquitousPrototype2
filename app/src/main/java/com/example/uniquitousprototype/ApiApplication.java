package com.example.uniquitousprototype;

import android.app.Application;
import android.content.res.Configuration;

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
    private LoginUser loginUser;

    @Override
    public void onCreate() {
        super.onCreate();
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

    public void setLoginUser(User user) {
        Call<LoginUser> loginUserCall = apiService.login(user);
        loginUserCall.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, retrofit2.Response<LoginUser> response) {

            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {

            }
        });
    }
}
