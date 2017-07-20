package com.example.uniquitousprototype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class EmailInputPage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_input_page);

        findViewById(R.id.accept_email_button).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText emailInputEdit = (EditText) findViewById(R.id.email_input_edittext);
                        String email = emailInputEdit.getText().toString();

                    }
                }
        );
    }

}
