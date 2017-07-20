package com.example.uniquitousprototype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YUNKYUSEOK on 2017-07-13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Task> taskList;

    public MyAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task item = taskList.get(position);
        holder.contentView.setText(item.getContent());
        holder.categoryView.setText(item.getCategory());
        holder.costView.setText(String.valueOf(item.getCost()));
        holder.rewardView.setText(String.valueOf(item.getReward()));
        holder.idView.setText(String.valueOf(item.getId()));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contentView;
        public TextView categoryView;
        public TextView costView;
        public TextView rewardView;
        public TextView idView;

        public ViewHolder(View v) {
            super(v);
            contentView = (TextView) v.findViewById(R.id.recycler_view_content);
            categoryView = (TextView) v. findViewById(R.id.recycler_view_category);
            costView = (TextView) v.findViewById(R.id.recycler_view_cost);
            rewardView = (TextView) v.findViewById(R.id.recycler_view_reward);
            idView = (TextView) v.findViewById(R.id.recycler_view_id);
        }
    }
}
