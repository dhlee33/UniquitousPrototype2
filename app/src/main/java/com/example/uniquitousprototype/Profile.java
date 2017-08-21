package com.example.uniquitousprototype;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydh13 on 2017-08-08.
 */

public class Profile extends AppCompatActivity {
    private TextView name, trade;
    private EditText self;
    private ApiApplication apiApplication;
    private ApiService apiService;
    private SharedPreferences login;
    private ProgressDialog progressDialog;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        login =getSharedPreferences("login",MODE_PRIVATE);
        token =login.getString("token",null);
        apiApplication = (ApiApplication)getApplicationContext();
        apiService = apiApplication.getApiService();
        name = (TextView)findViewById(R.id.name);
        trade = (TextView)findViewById(R.id.trade);
        self = (EditText)findViewById(R.id.self_introduction);
        loading();
        Call<User> loginUserCall = apiService.getUser(token);
        loginUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                apiApplication.setNowUser(response.body());
                name.setText(apiApplication.getNowUser().getName());
                self.setText(apiApplication.getNowUser().getSelf_introduction());
                trade.setText("거래횟수 0회");
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"저장실패!",Toast.LENGTH_LONG).show();
                return;
            }
        });

    }
    public void loading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("로딩중입니다...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void modifyIntroduction(View view)
    {
        String introduction = self.getText().toString();
        apiApplication.getNowUser().setSelfIntroduction(introduction);
        Call<Void> update = apiService.updateIntroduction(token, apiApplication.getNowUser());
        loading();
        update.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void finish(View view)
    {
        finish();
    }
}
