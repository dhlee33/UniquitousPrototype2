package com.example.uniquitousprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by YUNKYUSEOK on 2017-07-16.
 */

public class StartNegotiationIntent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cost_layout);

        final Intent intent = getIntent();
        TextView costView = (TextView) findViewById(R.id.cost_view);
        TextView rewardView = (TextView) findViewById(R.id.reward_view);
        TextView sumView = (TextView) findViewById(R.id.sum_view);

        int cost = intent.getIntExtra("costInt", 1);
        int reward = intent.getIntExtra("rewardInt", 1);
        costView.setText(String.valueOf(cost));
        rewardView.setText(String.valueOf(reward));
        sumView.setText(String.valueOf(cost + reward));
    }
    public void cancle(View v){
        finish();
    }
}
