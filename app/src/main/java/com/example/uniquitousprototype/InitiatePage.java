package com.example.uniquitousprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class InitiatePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initiate_page);
    }

    public void to_login_page(View v) {
        Intent loginPageIntent = new Intent(this, LoginPage.class);
        startActivity(loginPageIntent);
    }

}
