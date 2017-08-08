package com.example.uniquitousprototype;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class LoginPage extends AppCompatActivity {
    EditText idEditText, passwordEditText;
    private ApiApplication apiApplication;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        idEditText = (EditText) findViewById(R.id.input_id);
        passwordEditText = (EditText) findViewById(R.id.input_password);
        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();
    }

    public void login(View view) {
        String id = idEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Call<LoginUser> loginUserCall = apiService.login(new User(id, password));
        loginUserCall.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, retrofit2.Response<LoginUser> response) {
                apiApplication.setLoginUser(response.body());
                isLoginSuccess();
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                return;
            }
        });

    }

    private void isLoginSuccess() {
        if (apiApplication.isLogedIn()) {
            final Intent mainIntent = new Intent(this, MainActivity.class);
            View view = (View) getLayoutInflater().inflate(R.layout.activity_main, null);
            final Dialog loginSuccessDialog = new Dialog(this);
            loginSuccessDialog.setContentView(R.layout.login_success);
            loginSuccessDialog.setTitle("로그인 성공");
            loginSuccessDialog.findViewById(R.id.success_login_button).setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View view) {
                            loginSuccessDialog.dismiss();
                            startActivity(mainIntent);
                            finish();
                        }
                    }
            );
            loginSuccessDialog.show();
        } else {
            final Dialog loginFailDialog = new Dialog(this);
            loginFailDialog.setContentView(R.layout.login_fail);
            loginFailDialog.setTitle("로그인 실패");
            loginFailDialog.findViewById(R.id.fail_login_button).setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View view) {
                            loginFailDialog.dismiss();
                            return;
                        }
                    }
            );
            loginFailDialog.show();
        }
    }
}
