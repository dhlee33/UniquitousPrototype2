package com.example.uniquitousprototype;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class SignupPage extends AppCompatActivity {

    private String idString, passwordString, passwordConfirmString, nameString, emailString;
    private ApiApplication apiApplication;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_input_page);

        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();
    }

    public void accept_id(View view) {
        EditText idEditText = (EditText) findViewById(R.id.id_input_edittext);
        idString = idEditText.getText().toString();
        setContentView(R.layout.password_input_page);
    }

    public void accept_password(View view) {
        EditText passwordEditText = (EditText) findViewById(R.id.password_input_edittext);
        passwordString = passwordEditText.getText().toString();
        if (passwordString.length() < 8) {
            Toast.makeText(SignupPage.this, "비밀번호는 8자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        setContentView(R.layout.password_confirm_page);
    }

    public void confirm_password(View view) {
        EditText passwordConfirmEditText = (EditText) findViewById(R.id.password_confirm_edittext);
        passwordConfirmString = passwordConfirmEditText.getText().toString();
        if (!passwordConfirmString.equals(passwordString)) {
            Toast.makeText(SignupPage.this, "비밀번호와 다릅니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        setContentView(R.layout.name_input_page);
    }

    public void accept_name(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_input_edittext);
        nameString = nameEditText.getText().toString();
        setContentView(R.layout.email_input_page);
    }

    public void accept_email(View view) {
        EditText emailEditText = (EditText) findViewById(R.id.email_input_edittext);
        emailString = emailEditText.getText().toString();

        Call<User> call = apiService.signupNewUser(new User(idString, passwordString, passwordConfirmString, nameString, emailString));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                setContentView(R.layout.complete_signup_page);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void complete_signup(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
