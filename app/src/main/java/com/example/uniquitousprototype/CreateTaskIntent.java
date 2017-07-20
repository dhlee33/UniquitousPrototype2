package com.example.uniquitousprototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YUNKYUSEOK on 2017-07-13.
 */

public class CreateTaskIntent extends AppCompatActivity{
    EditText contentEditText, costEditText, rewardEditText;
    RadioGroup categoryGroup;
    private ApiApplication apiApplication;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task_intent_layout);

        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();

        contentEditText = (EditText)findViewById(R.id.text);
        costEditText = (EditText)findViewById(R.id.cost1);
        rewardEditText = (EditText)findViewById(R.id.cost2);
        categoryGroup = (RadioGroup)findViewById(R.id.radioGroup1);
    }

    public void create(View view){
        String content = contentEditText.getText().toString();
        int cost = Integer.parseInt(costEditText.getText().toString());
        int reward = Integer.parseInt(rewardEditText.getText().toString());
        int categoryIndex = categoryGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)findViewById(categoryIndex);
        String category = radioButton.getText().toString();

        Task newTask = new Task(content, category, cost, reward);
        String token = "Token 036db40131c8e0bf24f2b70d74642b5170f592a6";
        Call<Task> call = apiService.postNewTask(token, newTask);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {

            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });

        finish();
    }
}
