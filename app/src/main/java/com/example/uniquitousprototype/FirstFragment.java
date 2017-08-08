package com.example.uniquitousprototype;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ydh13 on 2017-08-05.
 */

public class FirstFragment extends Fragment
{
    public FirstFragment()
    {
    }
    public static RecyclerView recyclerView;
    public static List<Task> taskList;
    private ApiApplication apiApplication;
    private ApiService apiService;
    private LinearLayoutManager linearLayoutManager;
    private SegmentedGroup categoryGroup;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();
        taskList = new ArrayList<>();

        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.frag_first, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        return layout;
        categoryGroup = (SegmentedGroup) findViewById(R.id.segmented2);
        loading();
        Call<TaskResponse> call = apiService.getTaskList();
        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                taskList = response.body().getResults();
                recyclerView.setAdapter(new MyAdapter(taskList));
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "내 소원은 윤규석 메이플 지우는 것", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void loading() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("로딩중입니다...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public void category_get(View v) {
        int categoryIndex = categoryGroup.getCheckedRadioButtonId();
        RadioButton c = (RadioButton)findViewById(categoryIndex);
        String categ = c.getText().toString();
        String category = "0";
        switch (categ){
            case "전체":
                Call<TaskResponse> call = apiService.getTaskList();
                loading();
                call.enqueue(new Callback<TaskResponse>() {
                    @Override
                    public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                        taskList = response.body().getResults();
                        recyclerView.setAdapter(new MyAdapter(taskList));
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<TaskResponse> call, Throwable t) {

                    }
                });
                break;
            case "배달":
                category = "DELIVERY";
                break;
            case "숙제":
                category = "HOMEWORK";
                break;
            case "심부름":
                category = "ERRAND";
                break;
            case "기타":
                category = "ETC";
                break;
        }

        if(category != "0") {
            loading();
            Call<TaskResponse> call1 = apiService.categoryGet(category);
            final String clickedCategory = ((Button) v).getText().toString();
            call1.enqueue(new Callback<TaskResponse>() {
                @Override
                public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                    taskList.clear();
                    taskList = response.body().getResults();
                    recyclerView.setAdapter(new MyAdapter(taskList));
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<TaskResponse> call, Throwable t) {

                }
            });
        }
    }
}
