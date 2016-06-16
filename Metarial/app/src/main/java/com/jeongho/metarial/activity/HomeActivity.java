package com.jeongho.metarial.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jeongho.metarial.R;
import com.jeongho.metarial.adapter.ContentPagerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeongho on 2016/6/16.
 */
public class HomeActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private TabLayout mTab;
    private ViewPager mContentVp;
    private ContentPagerAdapter mPagerAdapter;

    private int[] mLayoutIds = new int[]{R.layout.content_pager_1, R.layout.content_pager_2,
            R.layout.content_pager_3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("骑行邦");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mToolbar.setOnMenuItemClickListener(this);

        mContentVp = (ViewPager) findViewById(R.id.vp_content);
        List<View> views = new LinkedList<>();
        LayoutInflater l = LayoutInflater.from(this);

        for (int layoutId : mLayoutIds){
            View v = l.inflate(layoutId, null);
            views.add(v);
        }

        List<String> titles = new LinkedList<>();
        titles.add("首页");
        titles.add("骑圈");
        titles.add("发现");
        mPagerAdapter = new ContentPagerAdapter(views, titles);
        mContentVp.setAdapter(mPagerAdapter);

        mTab = (TabLayout) findViewById(R.id.tab);
        mTab.setupWithViewPager(mContentVp);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


//    private Toolbar.OnMenuItemClickListener OnMenuItemClick = new Toolbar.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//            return false;
//        }
//    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_notification:
                Log.d("Menu", "notification");
                break;
            case R.id.action_search:
                Log.d("Menu", "search");
                break;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
