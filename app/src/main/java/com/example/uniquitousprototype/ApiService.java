package com.example.uniquitousprototype;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by YUNKYUSEOK on 2017-07-13.
 */

public interface ApiService {

    @GET("errands/")
    Call<TaskResponse> getTaskList();

    @POST("user/login/")
    Call<LoginUser> login(@Body User user);

    @POST("errands/")
    Call<Task> postNewTask(@Header("Authorization") String token, @Body Task task);

    @DELETE("errands/{id}/")
    Call<Void> deleteTask(@Header("Authorization") String token, @Path("id") int id);

    @POST("user/signup/")
    Call<User> signupNewUser(@Body User user);

    @PUT("errands/{id}/")
    Call<Task> updateTask(@Header("Authorization") String token, @Path("id") int id, @Body Task task);

    @GET("errands/{category}/")
    Call<TaskResponse> categoryGet(@Path("category") String category);

    @GET("user/{id}/")
    Call<User> getUser(@Path("id") int id);

    @GET("user/myErrand/")
    Call<TaskResponse> myTask(@Header("Authorization") String token);
}
