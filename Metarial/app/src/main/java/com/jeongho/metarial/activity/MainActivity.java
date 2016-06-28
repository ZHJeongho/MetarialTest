package com.jeongho.metarial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jeongho.metarial.R;
import com.jeongho.metarial.fragment.MainFragment;
import com.jeongho.metarial.fragment.MyAttentionFragment;
import com.jeongho.metarial.fragment.MyCollectFragment;
import com.jeongho.metarial.fragment.MyPostsFragment;
import com.jeongho.metarial.fragment.SettingFragment;
import com.jeongho.metarial.login.view.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jeongho on 2016/6/16.
 */
public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar mToolbar;
    private MyCollectFragment mCollectFragment;
    private MyAttentionFragment mAttentionFragment;
    private MyPostsFragment mPostsFragment;
    private SettingFragment mSettingFragment;
    private MainFragment mMainFragment;

    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawerLayout;

    private CircleImageView mPortraitCiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        //初始化Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("骑行邦");
        //布局文件中 app:title
        //mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mToolbar.setOnMenuItemClickListener(this);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        View naviHeader = mNavigationView.getHeaderView(0);
        mPortraitCiv = (CircleImageView) naviHeader.findViewById(R.id.civ_portrait);
        mPortraitCiv.setOnClickListener(this);
        //初始化MainFragment
        mMainFragment = new MainFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.content_frame, mMainFragment);
        transaction.commit();
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
        switch (item.getItemId()) {
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

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = null;

        if (mCollectFragment != null) {
            transaction.hide(mCollectFragment);
        }
        if (mAttentionFragment != null) {
            transaction.hide(mAttentionFragment);
        }
        if (mPostsFragment != null) {
            transaction.hide(mPostsFragment);
        }
        if (mSettingFragment != null) {
            transaction.hide(mSettingFragment);
        }
        if (mMainFragment != null) {
            transaction.hide(mMainFragment);
        }


        if (id == R.id.civ_portrait){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_home) {
            if (mMainFragment == null) {
                mMainFragment = new MainFragment();
            }
            fragment = mMainFragment;
            mToolbar.setTitle("骑行邦");
        } else if (id == R.id.nav_collect) {
            if (mCollectFragment == null) {
                mCollectFragment = new MyCollectFragment();
            }
            fragment = mCollectFragment;
            mToolbar.setTitle("我的收藏");
        } else if (id == R.id.nav_attention) {
            if (mAttentionFragment == null) {
                mAttentionFragment = new MyAttentionFragment();
            }
            fragment = mAttentionFragment;
            mToolbar.setTitle("我的关注");
        } else if (id == R.id.nav_posts) {
            if (mPostsFragment == null) {
                mPostsFragment = new MyPostsFragment();
            }
            fragment = mPostsFragment;
            mToolbar.setTitle("我的帖子");
        } else if (id == R.id.nav_night_mode) {

        } else if (id == R.id.nav_setting) {
            if (mSettingFragment == null) {
                mSettingFragment = new SettingFragment();
            }
            fragment = mSettingFragment;
            mToolbar.setTitle("设置");
        }

        if (null == fragment) {
            return true;
        }

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.content_frame, fragment);
        }

        transaction.commit();

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private long firstBackTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (mNavigationView.isShown()){
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }else {
                    if (System.currentTimeMillis() - firstBackTime > 2000){
                        Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        firstBackTime = System.currentTimeMillis();
                    }else {
                        this.finish();
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.civ_portrait:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
