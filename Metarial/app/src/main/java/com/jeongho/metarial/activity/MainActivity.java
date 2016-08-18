package com.jeongho.metarial.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jeongho.metarial.QxbApplication;
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.bean.UserInfoBean;
import com.jeongho.metarial.fragment.MainFragment;
import com.jeongho.metarial.fragment.MyAttentionFragment;
import com.jeongho.metarial.fragment.MyCollectFragment;
import com.jeongho.metarial.fragment.MyPostsFragment;
import com.jeongho.metarial.fragment.SettingFragment;
import com.jeongho.metarial.login.view.LoginActivity;
import com.jeongho.metarial.widge.SnackUtil;
import com.jeongho.qxblibrary.Utils.SharedPreferencesUtil;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 主界面
 * Created by Jeongho on 2016/6/16.
 */
public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static final int LOGIN_REQUEST = 0x01;
    public static final int LOGIN_RESULT = 0x02;
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
    private TextView mNicknameTv;

    private SharedPreferencesUtil mSharedPreferencesUtil;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        //初始化Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        //布局文件中 app:title
        //mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mToolbar.setOnMenuItemClickListener(this);

        mSharedPreferencesUtil = new SharedPreferencesUtil(
                QxbApplication.getContext(), SharedPreferencesUtil.USER_DATA);

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

        mNicknameTv = (TextView) naviHeader.findViewById(R.id.tv_nickname);
        //初始化MainFragment

        if (savedInstanceState != null) {
            return;
        }

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


        if (id == R.id.nav_home) {
            if (mMainFragment == null) {
                mMainFragment = new MainFragment();
            }
            fragment = mMainFragment;
            mToolbar.setTitle(getResources().getString(R.string.app_name));
        } else if (id == R.id.nav_collect) {
            if (mCollectFragment == null) {
                mCollectFragment = new MyCollectFragment();
            }
            fragment = mCollectFragment;
            mToolbar.setTitle(getResources().getString(R.string.nav_collect));
        } else if (id == R.id.nav_attention) {
            if (mAttentionFragment == null) {
                mAttentionFragment = new MyAttentionFragment();
            }
            fragment = mAttentionFragment;
            mToolbar.setTitle(getResources().getString(R.string.nav_attention));
        } else if (id == R.id.nav_posts) {
            if (mPostsFragment == null) {
                mPostsFragment = new MyPostsFragment();
            }
            fragment = mPostsFragment;
            mToolbar.setTitle(getResources().getString(R.string.nav_posts));
        } else if (id == R.id.nav_night_mode) {

        } else if (id == R.id.nav_setting) {
            if (mSettingFragment == null) {
                mSettingFragment = new SettingFragment();
            }
            fragment = mSettingFragment;
            mToolbar.setTitle(getResources().getString(R.string.nav_setting));
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
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mNavigationView.isShown()) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    if (System.currentTimeMillis() - firstBackTime > 2000) {
                        Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        firstBackTime = System.currentTimeMillis();
                    } else {
                        this.finish();
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_portrait:
                //TODO:模拟用户登录前后点击头像效果
                if (isLogin){
                    Intent intent = new Intent(this, UserInfoActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST && resultCode == LOGIN_RESULT) {
            //模拟登录
            isLogin = true;
            //更新头像 用户名
            mNicknameTv.setText(getNickname());
            SnackUtil.createLongSnackbar(mDrawerLayout, "欢迎回来, " + getNickname(), SnackUtil.INFO).show();

            ServerUtil.getBitmap(getPortraitUrl(), new ServerUtil.OnBitmapCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onSuccess(Bitmap response, int id) {
                    mPortraitCiv.setImageBitmap(response);
                }
            });
        }
    }

    private String getNickname() {
        UserInfoBean userInfoBean = getUserInfoBean();
        return userInfoBean.user.nickname;
    }

    private String getPortraitUrl() {
        UserInfoBean userInfoBean = getUserInfoBean();
        return userInfoBean.user.icon;
    }

    private UserInfoBean getUserInfoBean() {
        String userInfo = mSharedPreferencesUtil.getString(SharedPreferencesUtil.USER_INFO, "");
        Gson gson = new Gson();
        return gson.fromJson(userInfo, UserInfoBean.class);
    }
}
