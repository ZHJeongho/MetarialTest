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
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.bean.UserInfoBean;
import com.jeongho.metarial.common.QxbAccount;
import com.jeongho.metarial.common.QxbApplication;
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

    public static final int REQUEST_LOGIN = 0x01;
    public static final int RESULT_LOGIN = 0x01;
    public static final int REQUEST_COLLECT = 0x02;
    public static final int RESULT_COLLECT = 0x02;
    public static final int REQUEST_ATTENTION = 0x03;
    public static final int RESULT_ATTENTION = 0x03;
    public static final int REQUEST_POSTS = 0x04;
    public static final int RESULT_POSTS = 0x04;
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

    //记录第一次点击back的时间
    private long firstBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(savedInstanceState);
        initData();
    }

    private void initData() {
        //mSharedPreferencesUtil.getString()
    }

    private void initView(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            return;
        }

        //初始化Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        //布局文件中 app:title
        //mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mToolbar.setOnMenuItemClickListener(this);

        mSharedPreferencesUtil = new SharedPreferencesUtil(
                QxbApplication.getInstance(), SharedPreferencesUtil.USER_DATA);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        View headerView = mNavigationView.getHeaderView(0);
        mPortraitCiv = (CircleImageView) headerView.findViewById(R.id.civ_portrait);
        mPortraitCiv.setOnClickListener(this);

        mNicknameTv = (TextView) headerView.findViewById(R.id.tv_nickname);
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

        if (id == R.id.nav_collect || id == R.id.nav_attention || id == R.id.nav_posts){
            if (!QxbAccount.isSignUp){
                showLogin(id);
                return true;
            }else {
                showFragment(id);
            }
        }else {
            showFragment(id);
        }
        return true;
    }

    private void showFragment(int id) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragment(transaction);
        Fragment fragment = chooseFragment(id);
        if (null == fragment) {
            Log.d("fragment", "is null");
        }

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.content_frame, fragment);
        }

        transaction.commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void showLogin(int id) {
        Intent intent = new Intent(this, LoginActivity.class);
        switch (id){
            case R.id.nav_collect:
                intent.putExtra("request_code", 0x02);
                startActivityForResult(intent, REQUEST_COLLECT);
                break;
            case R.id.nav_attention:
                intent.putExtra("request_code", 0x03);
                startActivityForResult(intent, REQUEST_ATTENTION);
                break;
            case R.id.nav_posts:
                intent.putExtra("request_code", 0x04);
                startActivityForResult(intent, REQUEST_POSTS);
                break;
        }
    }

    /**
     * 隐藏所有的fragment
     * @param transaction
     */
    private void hideAllFragment(FragmentTransaction transaction) {
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
    }

    private Fragment chooseFragment(int id) {
        Fragment fragment = null;
        switch (id){
            case R.id.nav_home:
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                }
                mToolbar.setTitle(getResources().getString(R.string.app_name));
                fragment = mMainFragment;
                break;
            case R.id.nav_collect:
                if (mCollectFragment == null) {
                    mCollectFragment = new MyCollectFragment();
                }
                mToolbar.setTitle(getResources().getString(R.string.nav_collect));
                fragment = mCollectFragment;
                break;

            case R.id.nav_attention:
                if (mAttentionFragment == null) {
                    mAttentionFragment = new MyAttentionFragment();
                }
                mToolbar.setTitle(getResources().getString(R.string.nav_attention));
                fragment = mAttentionFragment;
                break;
            case R.id.nav_posts:
                if (mPostsFragment == null) {
                    mPostsFragment = new MyPostsFragment();
                }
                mToolbar.setTitle(getResources().getString(R.string.nav_posts));
                fragment = mPostsFragment;
                break;
            case R.id.nav_setting:
                if (mSettingFragment == null) {
                    mSettingFragment = new SettingFragment();
                }
                mToolbar.setTitle(getResources().getString(R.string.nav_setting));
                fragment = mSettingFragment;
                break;
        }
        return fragment;
    }


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
                if (QxbAccount.isSignUp){
                    Intent intent = new Intent(this, UserInfoActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("request_code", 0x01);
                    startActivityForResult(intent, REQUEST_LOGIN);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_LOGIN) {
            //模拟登录
            QxbAccount.isSignUp = true;
            refreshHead();
        }

        if (requestCode == REQUEST_COLLECT && resultCode == RESULT_COLLECT){
            QxbAccount.isSignUp = true;
            showFragment(R.id.nav_collect);
            refreshHead();
        }

        if (requestCode == REQUEST_ATTENTION && resultCode == RESULT_ATTENTION){
            QxbAccount.isSignUp = true;
            showFragment(R.id.nav_attention);
            refreshHead();
        }

        if (requestCode == REQUEST_POSTS && resultCode == RESULT_POSTS){
            QxbAccount.isSignUp = true;
            showFragment(R.id.nav_posts);
            refreshHead();
        }
    }
    //更新头像 用户名
    private void refreshHead() {
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
