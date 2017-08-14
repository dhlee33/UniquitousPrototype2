package com.example.uniquitousprototype;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private ViewPager vp;
    private LinearLayout ll;
    private ApiApplication apiApplication;
    private ApiService apiService;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView menuRecyclerView;
    private LinearLayoutManager menuLayoutManager;
    private List<MainMenuItem> mainMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout)findViewById(R.id.ll);
        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();
        vp = (ViewPager)findViewById(R.id.vp);
        TextView tab_first = (TextView)findViewById(R.id.tab_first);
        TextView tab_second = (TextView)findViewById(R.id.tab_second);
        TextView tab_third = (TextView)findViewById(R.id.tab_third);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        tab_first.setOnClickListener(movePageListener);
        tab_first.setTag(0);
        tab_second.setOnClickListener(movePageListener);
        tab_second.setTag(1);
        tab_third.setOnClickListener(movePageListener);
        tab_third.setTag(2);
        tab_first.setSelected(true);

        menuRecyclerView = (RecyclerView) findViewById(R.id.menu_recycler_view);
        menuRecyclerView.setHasFixedSize(true);
        menuLayoutManager = new LinearLayoutManager(this);
        menuRecyclerView.setLayoutManager(menuLayoutManager);
        menuRecyclerView.addItemDecoration(new ItemDividerDecoration(this));
        setMenu();
        menuRecyclerView.setAdapter(new MenuAdapter(mainMenuItems));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, null, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                int i = 0;
                while(i < 3)
                {
                    if(position == i)
                    {
                        ll.findViewWithTag(i).setSelected(true);
                    }
                    else
                    {
                        ll.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private void setMenu() {
        mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new MainMenuItem(R.drawable.person_icon, "홍길동", 8, 7));
        mainMenuItems.add(new MainMenuItem(R.drawable.person_icon, "프로필"));
        mainMenuItems.add(new MainMenuItem(R.drawable.add_friend_icon, "친구찾기"));
        mainMenuItems.add(new MainMenuItem(R.drawable.friend_list_icon, "친구목록"));
        mainMenuItems.add(new MainMenuItem(R.drawable.history_icon, "거래 히스토리"));
        mainMenuItems.add(new MainMenuItem(R.drawable.feedback_icon, "피드백 보내기"));
        mainMenuItems.add(new MainMenuItem(R.drawable.help_icon, "고객 센터"));
        mainMenuItems.add(new MainMenuItem(R.drawable.delete_user_icon, "탈퇴하기"));
        mainMenuItems.add(new MainMenuItem(R.drawable.logout_icon, "로그아웃"));
    }

    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            int i = 0;
            while(i<3)
            {
                if(tag==i)
                {
                    ll.findViewWithTag(i).setSelected(true);
                }
                else
                {
                    ll.findViewWithTag(i).setSelected(false);
                }
                i++;
            }
            vp.setCurrentItem(tag);
        }
    };

    public void menuClick(View v) {
        TextView textView = v.findViewById(R.id.menu_text);
        String text = textView.getText().toString();
        switch (text){
            case "프로필" :
                Intent profilePageIntent = new Intent(this, Profile.class);
                startActivity(profilePageIntent);
                break;

            case "로그아웃" :
                apiApplication.logout();
                Intent initial = new Intent(this, InitiatePage.class);
                startActivity(initial);
                finish();
                break;
        }
    }

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_create:
                create(null,null,-1,-1,-1,0);
                return true;
            case R.id.action_websearch:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_search, menu);
        getMenuInflater().inflate(R.menu.main_create, menu);
        return true;
    }

    public void create(String content, String category, int cost, int reward,int id, int type) {
        if (!apiApplication.isLogedIn()) {
            final Intent loginPageIntent = new Intent(this, LoginPage.class);
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.not_login);
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
                            finish();
                        }
                    }
            );
            dialog.show();
            return;
        }

        Intent createTaskIntent = new Intent(this, CreateTaskIntent.class);
        createTaskIntent.putExtra("content",content);
        createTaskIntent.putExtra("category",category);
        createTaskIntent.putExtra("cost",cost);
        createTaskIntent.putExtra("reward",reward);
        createTaskIntent.putExtra("type",type);
        createTaskIntent.putExtra("id",id);
        startActivity(createTaskIntent);
    }

    public void update(final View v) {
        if (!apiApplication.isLogedIn()) {
            final Intent loginPageIntent = new Intent(this, LoginPage.class);
            final Dialog dialog = new Dialog(this);
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

        final RelativeLayout relativeLayout = (RelativeLayout) v.getParent().getParent().getParent();
        TextView t = (TextView)relativeLayout.findViewById(R.id.recycler_view_id);
        final int id = Integer.parseInt(t.getText().toString());
        final CharSequence[] items = {"수정", "삭제", "취소"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("작업을 선택하세요")
                .setItems(items, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int index){
                        switch (index)
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
                                if (!apiApplication.isLogedIn()) {
                                    return;
                                }
                                String token = apiApplication.getToken();
                                Call<Void> call = apiService.deleteTask(token, id);
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
        startActivity(startNegotiationIntent);
    }
}