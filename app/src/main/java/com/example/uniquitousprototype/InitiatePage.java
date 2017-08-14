package com.example.uniquitousprototype;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private static final int LOGINPAGE_CONSTANT = 1001;
    private static final int SIGNUPPAGE_CONSTANT = 1002;

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
        startActivityForResult(new Intent(this, LoginPage.class), LOGINPAGE_CONSTANT);
    }

    public void to_signup_page(View v) {
        startActivityForResult(new Intent(this, SignupPage.class), SIGNUPPAGE_CONSTANT);
    }

    public void to_lookup_page(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case LOGINPAGE_CONSTANT:
                finish();
                break;

            case SIGNUPPAGE_CONSTANT:
                finish();
                break;
        }
    }

}
