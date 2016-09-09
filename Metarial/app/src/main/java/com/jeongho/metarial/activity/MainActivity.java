package com.jeongho.metarial.activity;

import android.content.Context;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.activity.setting.SettingActivity;
import com.jeongho.metarial.bean.UserInfoBean;
import com.jeongho.metarial.common.QxbAccount;
import com.jeongho.metarial.common.QxbApplication;
import com.jeongho.metarial.fragment.MainFragment;
import com.jeongho.metarial.login.model.User;
import com.jeongho.metarial.login.view.LoginActivity;
import com.jeongho.metarial.login.view.LoginCallback;
import com.jeongho.metarial.widge.SnackUtil;
import com.jeongho.qxblibrary.Utils.SharedPreferencesUtil;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * 主界面
 * Created by Jeongho on 2016/6/16.
 */
public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, LoginCallback {

    public static final int REQUEST_LOGIN = 0x01;
    public static final int RESULT_LOGIN = 0x01;
    public static final int REQUEST_COLLECT = 0x02;
    public static final int RESULT_COLLECT = 0x02;
    public static final int REQUEST_ATTENTION = 0x03;
    public static final int RESULT_ATTENTION = 0x03;
    public static final int REQUEST_POSTS = 0x04;
    public static final int RESULT_POSTS = 0x04;
    private Toolbar mToolbar;
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
        String userAccount = mSharedPreferencesUtil.getString(SharedPreferencesUtil.USER_ACCOUNT, "");
        String userPwd = mSharedPreferencesUtil.getString(SharedPreferencesUtil.USER_PASSWORD, "");
        User user = new User(userAccount, userPwd);
        user.checkLoginInfo(this);
    }

    private void initView(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            return;
        }

        //初始化Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mToolbar.inflateMenu(R.menu.menu_home);
        mToolbar.setOnMenuItemClickListener(this);
        setSupportActionBar(mToolbar);

        mSharedPreferencesUtil = new SharedPreferencesUtil(
                QxbApplication.getInstance(), SharedPreferencesUtil.USER_DATA);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        //头像
        View headerView = mNavigationView.getHeaderView(0);
        mPortraitCiv = (CircleImageView) headerView.findViewById(R.id.civ_portrait);
        mPortraitCiv.setOnClickListener(this);
        //用户昵称
        mNicknameTv = (TextView) headerView.findViewById(R.id.tv_nickname);

        //初始化MainFragment
        mMainFragment = new MainFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.content_frame, mMainFragment);
        transaction.commit();
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
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (id){
            case R.id.nav_home:
                Log.d("nav", "on home");
                break;
            case R.id.nav_collect:
                switchActivity(id);
                break;
            case R.id.nav_attention:
                switchActivity(id);
                break;
            case R.id.nav_posts:
                switchActivity(id);
                break;
            case R.id.nav_night_mode:
                Log.d("nav", "on home");
                break;
            case R.id.nav_setting:
                SettingActivity.startAction(this);
                break;
        }


        return false;
    }

    private void switchActivity(int id) {
        if (!QxbAccount.isSignUp){
            showLogin(id);
        }else {
            showActivity(id);
        }
    }

    private void showActivity(int id) {
        switch (id){
            case R.id.nav_collect:
                MyCollectActivity.startAction(this);
                break;
            case R.id.nav_attention:
                MyAttentionActivity.startAction(this);
                break;
            case R.id.nav_posts:
                MyPostsActivity.startAction(this);
                break;
        }
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

    /**
     * 用户未登陆 根据侧边栏点击情况跳转Aty
     * @param id
     */
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
            MyCollectActivity.startAction(this);
        }

        if (requestCode == REQUEST_ATTENTION && resultCode == RESULT_ATTENTION){
            QxbAccount.isSignUp = true;
            MyAttentionActivity.startAction(this);
        }

        if (requestCode == REQUEST_POSTS && resultCode == RESULT_POSTS){
            QxbAccount.isSignUp = true;
            MyPostsActivity.startAction(this);
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

    @Override
    public void loginSuccess(String token, String userAccount, String userPwd) {
        mSharedPreferencesUtil.putString(SharedPreferencesUtil.TOKEN, token);
        mSharedPreferencesUtil.putString(SharedPreferencesUtil.USER_ACCOUNT, userAccount);
        mSharedPreferencesUtil.putString(SharedPreferencesUtil.USER_PASSWORD, userPwd);
        QxbAccount.isSignUp = true;
        refreshHead();
    }

    @Override
    public void loginFailed(String error) {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
