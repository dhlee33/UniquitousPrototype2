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

    @GET("bills")
    Call<TaskResponse> getTaskList();

    @POST("api-token-auth/")
    Call<LoginUser> login(@Body User user);

    @POST("bills/")
    Call<Task> postNewTask(//@Body Task task);
            @Header("Authorization") String token, @Body Task task);

    @DELETE("bills/{id}/")
    Call<Void> deleteTask(@Header("Authorization") String token, @Path("id") int id);

    @POST("signup/")
    Call<User> signupNewUser(@Body User user);

    @PUT("bills/{id}/")
    Call<Task> updateTask(@Header("Authorization") String token, @Path("id") int id, @Body Task task);
}
