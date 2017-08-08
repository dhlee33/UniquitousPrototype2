package com.example.uniquitousprototype;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
    RelativeLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        apiApplication = (ApiApplication) getActivity().getApplicationContext();
        apiService = apiApplication.getApiService();
        taskList = new ArrayList<>();
        layout = (RelativeLayout) inflater.inflate(R.layout.frag_first, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryGroup = (SegmentedGroup) layout.findViewById(R.id.segmented2);
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
                Toast.makeText(getActivity(), "내 소원은 윤규석 메이플 지우는 것", Toast.LENGTH_SHORT).show();
            }
        });
        return layout;
    }
    public void create(String content, String category, int cost, int reward,int id, int type) {
        if (!apiApplication.isLogedIn()) {
            final Intent loginPageIntent = new Intent(getActivity(), LoginPage.class);
            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.not_login);
            dialog.setTitle("에러");
            dialog.findViewById(R.id.cancel).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    }
            );
            dialog.findViewById(R.id.accept_to_login_button).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(loginPageIntent);
                            dialog.dismiss();
                        }
                    }
            );
            dialog.show();
            return;
        }
        Intent createTaskIntent = new Intent(getActivity(), CreateTaskIntent.class);
        createTaskIntent.putExtra("content",content);
        createTaskIntent.putExtra("category",category);
        createTaskIntent.putExtra("cost",cost);
        createTaskIntent.putExtra("reward",reward);
        createTaskIntent.putExtra("type",type);
        createTaskIntent.putExtra("id",id);
        startActivity(createTaskIntent);
    }
    public void loading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("로딩중입니다...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public void category_get(View v) {
        int categoryIndex = categoryGroup.getCheckedRadioButtonId();
        RadioButton c = (RadioButton)layout.findViewById(categoryIndex);
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
