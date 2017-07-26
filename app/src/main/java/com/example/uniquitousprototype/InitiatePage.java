package com.example.uniquitousprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class InitiatePage extends AppCompatActivity {
    private ApiApplication apiApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initiate_page);
        apiApplication = (ApiApplication) getApplicationContext();

        if (apiApplication.isLogedIn()) {
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            finish();
        }
    }

    public void to_login_page(View v) {
        Intent loginPageIntent = new Intent(this, LoginPage.class);
        startActivity(loginPageIntent);
    }

    public void to_signup_page(View v) {
        Intent signupIntent = new Intent(this, SignupPage.class);
        startActivity(signupIntent);
    }

    public void to_lookup_page(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
