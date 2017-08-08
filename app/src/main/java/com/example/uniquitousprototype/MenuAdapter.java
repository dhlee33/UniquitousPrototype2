package com.example.uniquitousprototype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YUNKYUSEOK on 2017-08-08.
 */

class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MainMenuItem> mainMenuItems;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_MENU = 1;

    public MenuAdapter(List<MainMenuItem> mainMenuItems) {
        this.mainMenuItems = mainMenuItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_HEADER:
                ViewGroup headerView = (ViewGroup) layoutInflater.inflate(R.layout.menu_header, parent, false);
                return new HeaderHolder(headerView);
            default:
                ViewGroup menuView = (ViewGroup) layoutInflater.inflate(R.layout.menu_item, parent, false);
                return new MenuHolder(menuView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainMenuItem mainMenuItem = mainMenuItems.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_HEADER:
                HeaderHolder headerHolder = (HeaderHolder) holder;
                headerHolder.menuProfileImage.setImageResource(R.drawable.person_icon);
                headerHolder.menuProfileName.setText(mainMenuItem.getName());
                headerHolder.menuProfileRatingBar.setRating(mainMenuItem.getRating());
                break;

            case TYPE_MENU:
                MenuHolder menuHolder = (MenuHolder) holder;
                menuHolder.menuText.setText(mainMenuItem.getText());
                menuHolder.menuIcon.setImageResource(mainMenuItem.getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return mainMenuItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        switch (position) {
            case 0:
                viewType = TYPE_HEADER;
                break;
            default:
                viewType = TYPE_MENU;
                break;
        }
        return viewType;
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        public ImageView menuProfileImage;
        public TextView menuProfileName;
        public RatingBar menuProfileRatingBar;
        public TextView menuProfileTimes;

        public HeaderHolder(View itemView) {
            super(itemView);
            menuProfileImage = itemView.findViewById(R.id.menu_profile_image);
            menuProfileName = itemView.findViewById(R.id.menu_profile_name);
            menuProfileRatingBar = itemView.findViewById(R.id.menu_profile_ratingbar);
            menuProfileTimes = itemView.findViewById(R.id.menu_profile_times);
        }
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
        public ImageView menuIcon;
        public TextView menuText;

        public MenuHolder(View itemView) {
            super(itemView);
            menuIcon = itemView.findViewById(R.id.menu_icon);
            menuText = itemView.findViewById(R.id.menu_text);
        }
    }
}
