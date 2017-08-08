package com.example.uniquitousprototype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by YUNKYUSEOK on 2017-08-08.
 */

class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        public MenuHolder(View itemView) {
            super(itemView);
        }
    }
}
