package com.example.uniquitousprototype;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
    Dialog dialog;

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

        /*Call<TaskResponse> call2 = apiService.login(new TaskResponse("parkseungil", "wafflestudio"));
        call2.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {

            }
        });*/

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

        /*Call<TaskResponse> call2 = apiService.login(new TaskResponse("parkseungil", "wafflestudio"));
        call2.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {

            }
        });*/
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
    public void update(View v){
        final CharSequence[] items = {"수정", "삭제"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("작업을 선택하세요")

                .setItems(items, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int index){
                        switch (index)
                        {
                            case 1:
                                break;
                            case 2: Call<TaskResponse> call = apiService.getTaskList();
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
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
