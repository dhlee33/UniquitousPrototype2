package com.example.uniquitousprototype;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static List<Task> taskList;
    private LinearLayoutManager linearLayoutManager;
    private ApiApplication apiApplication;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();

        taskList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Call<TaskResponse> call = apiService.getTaskList();
        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                taskList = response.body().getResults();
                recyclerView.setAdapter(new MyAdapter(taskList));
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {

            }
        });

    }

    public void createTask(View v) {
        Intent createTaskIntent = new Intent(this, CreateTaskIntent.class);
        startActivity(createTaskIntent);
    }

    public void category_delivery(View v) {
        Call<TaskResponse> call1 = apiService.getTaskList();
        final String clickedCategory = ((Button) v).getText().toString();
        call1.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                List<Task> tmpTaskList = response.body().getResults();
                taskList.clear();
                for (Task tmpTask : tmpTaskList) {
                    if (tmpTask.getCategory().equals(clickedCategory))
                        taskList.add(tmpTask);
                }
                recyclerView.setAdapter(new MyAdapter(taskList));
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {

            }
        });
    }

    public void startNegotiation(View v) {
        CardView cardView = (CardView) v.getParent().getParent().getParent().getParent();
        TextView costText = cardView.findViewById(R.id.recycler_view_cost);
        TextView rewardText = cardView.findViewById(R.id.recycler_view_reward);
        String costString = costText.getText().toString();
        String rewardString = rewardText.getText().toString();
        int costInt = Integer.parseInt(costString);
        int rewardInt = Integer.parseInt(rewardString);

        Intent startNegotiationIntent = new Intent(this, StartNegotiationIntent.class);
        startNegotiationIntent.putExtra("costInt", costInt);
        startNegotiationIntent.putExtra("rewardInt", rewardInt);
        startActivity(startNegotiationIntent);

    }


}
