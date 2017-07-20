package com.example.uniquitousprototype;

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
    public void createTask(View v)
    {
        create(null,null,-1,-1,-1,0);
    }
    public void create(String content, String category, int cost, int reward,int id, int type) {
        Intent createTaskIntent = new Intent(this, CreateTaskIntent.class);
        createTaskIntent.putExtra("content",content);
        createTaskIntent.putExtra("category",category);
        createTaskIntent.putExtra("cost",cost);
        createTaskIntent.putExtra("reward",reward);
        createTaskIntent.putExtra("type",type);
        createTaskIntent.putExtra("id",id);
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
    public void update(final View v){
        final RelativeLayout relativeLayout = (RelativeLayout) v.getParent().getParent().getParent();
        TextView t = (TextView)relativeLayout.findViewById(R.id.recycler_view_id);
        final int id = Integer.parseInt(t.getText().toString());
        final CharSequence[] items = {"수정", "삭제","취소"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("작업을 선택하세요")
                .setItems(items, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int index){
                        switch(index)
                        {
                            case 0:
                                TextView tcontent = (TextView)relativeLayout.findViewById(R.id.recycler_view_content);
                                TextView tcategory = (TextView)relativeLayout.findViewById(R.id.recycler_view_category);
                                TextView tcost = (TextView)relativeLayout.findViewById(R.id.recycler_view_cost);
                                TextView treward = (TextView)relativeLayout.findViewById(R.id.recycler_view_reward);
                                String content = tcontent.getText().toString();
                                String category = tcategory.getText().toString();
                                int cost = Integer.parseInt(tcost.getText().toString());
                                int reward = Integer.parseInt(treward.getText().toString());
                                create(content,category,cost,reward,id,1);
                                break;
                            case 1:
                                String token = "Token 036db40131c8e0bf24f2b70d74642b5170f592a6";
                                Call<Void> call = apiService.deleteTask(token,id);
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });
                                break;
                            case 2:
                                break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
