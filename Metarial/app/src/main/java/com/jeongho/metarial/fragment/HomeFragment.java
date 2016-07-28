package com.jeongho.metarial.fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.adapter.ContentPagerAdapter;
import com.jeongho.metarial.adapter.HomeFrmAdapter;
import com.jeongho.metarial.bean.HomeTopNewsBean;
import com.jeongho.metarial.ui.ZoomOutPageTransformer;
import com.jeongho.qxblibrary.Utils.ToastUtil;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Jeongho on 16/6/16.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, HomeFrmAdapter.OnItemClickListener, ContentPagerAdapter.OnPagerClickListener {
    private RecyclerView mRecyclerView;
    private HomeFrmAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private SwipeRefreshLayout mRefreshLayout;
    /**
     * recyclerView显示的最后一个item的position
     */
    private int mLastItemPosition;

    private boolean isMoreLoading = false;

    private HomeTopNewsBean mHomeTopNewsBean;
    private ContentPagerAdapter mCpa;
    private ViewPager mTopVp;

    private List<View> mViewList = new LinkedList<>();
    private List<String> mTitleList = new LinkedList<>();

    private ServerUtil mServerUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        initView(root);
        addHeaderView();
        addFooterView();
        initRefresh(root);

        return root;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mServerUtil = new ServerUtil();
        mServerUtil.getHomeVpData(new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("onError", e.getMessage());
            }

            @Override
            public void onSuccess(String response, int id) {
                Log.d("ok", response);
                Gson gson = new Gson();
                mHomeTopNewsBean = gson.fromJson(response, HomeTopNewsBean.class);
                initTopNews();
            }
        });
    }

    /**
     * 初始化刷新
     */
    private void initRefresh(View root) {
        mRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.home_swipe_refresh);
        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //IDLE表示srcoll已经停止
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        mLastItemPosition + 1 == mAdapter.getItemCount()){
                    if (!isMoreLoading){
                        isMoreLoading = true;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //上拉加载
                                List<String> list = new LinkedList<String>();
                                for (int i = 0; i < 5; i++){
                                    list.add("GG simida" + i);
                                }
                                mAdapter.addMoreBeans(list);

                                ToastUtil.showShort(getContext(), "Asd");
                                isMoreLoading = false;
                            }
                        }, 2000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastItemPosition = mGridLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    /**
     * adapter添加尾布局
     */
    private void addFooterView() {
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.item_footer, mRecyclerView, false);
        mAdapter.setFooterView(footerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    /**
     * adapter添加头布局
     */
    private void addHeaderView() {
        View headerView = getHeaderView();
        //初始化ViewPager
        mTopVp = (ViewPager) headerView.findViewById(R.id.top_vp);
        mTopVp.setPageTransformer(true, new ZoomOutPageTransformer());
        mTopVp.setAdapter(mCpa);
        //recycleView加头布局 尾布局
        mAdapter.setHeaderView(headerView);
    }

    /**
     * recycler头布局
     * @return
     */
    private View getHeaderView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.item_header, mRecyclerView, false);
    }

    private void initView(View root) {
        mRecyclerView = (RecyclerView) root.findViewById(R.id.home_rv);
        mLayoutManager = new LinearLayoutManager(getContext());

        mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //初始化cardView
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0 ; i < 50; i++) {
            list.add("galigeigei" + i);
        }
        mAdapter = new HomeFrmAdapter(getActivity(), list);
    }


    private void initTopNews() {

        mCpa = new ContentPagerAdapter(mViewList, mTitleList);
        mCpa.setOnPagerClickListener(this);
        mTopVp.setAdapter(mCpa);

        for (int i = 0; i < mHomeTopNewsBean.homePage.size(); i++){

            mTitleList.add(mHomeTopNewsBean.homePage.get(i).getTitle());
            final View view = LayoutInflater.from(getContext()).inflate(R.layout.item_top_vp, null);
            final ImageView iv = (ImageView) view.findViewById(R.id.iv);

//            OkHttpUtils
//                    .get()
//                    .url(mHomeTopNewsBean.homePage.get(i).getLogo())
//                    .build()
//                    .execute(new BitmapCallback()
//                    {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//
//                        }
//
//                        @Override
//                        public void onResponse(Bitmap response, int id) {
//
//                        }
//                    });

            mServerUtil.getBitmap(mHomeTopNewsBean.homePage.get(i).getLogo(),
                    new ServerUtil.OnBitmapCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            //可能存在没有返回bitmap的情况
                            //TODO：设置iv为默认图片  在xml中即可
                            Log.e("onError", e.getMessage());
                            mViewList.add(view);
                            mCpa.notifyDataSetChanged();
                        }

                        @Override
                        public void onSuccess(Bitmap response, int id) {
                            //子线程执行  下面bitmapList为null
                            iv.setImageBitmap(response);
                            mViewList.add(view);
                            mCpa.notifyDataSetChanged();
                        }
                    });
        }

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> list = new LinkedList<String>();
                for (int i = 0; i < 5; i++){
                    list.add("GG simida" + i);
                }
                mAdapter.addRefreshBeans(list);
                mRefreshLayout.setRefreshing(false);
                ToastUtil.showShort(getContext(), "haha");
            }
        }, 2000);
    }

    /**
     * 中间部分item点击事件
     * @param view
     * @param position
     */
    @Override
    public void OnItemClick(View view, int position) {
        ToastUtil.showShort(getContext(), position + "");
//        view.setTranslationZ(15);
//        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.out);
//        view.setAnimation(a);
    }

    /**
     * 顶部ViewPager点击事件
     * @param view
     * @param position
     */
    @Override
    public void OnPagerClick(View view, int position) {
        ToastUtil.showShort(getContext(), "pager" + position);
    }

    //异步  没用到。。。。
    class LoadBitmapTask extends AsyncTask<HomeTopNewsBean,Integer, List<Bitmap>>{

        @Override
        protected List<Bitmap> doInBackground(HomeTopNewsBean... params) {
            return null;
        }

        //任务执行前调用
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //返回结果，UI操作
        @Override
        protected void onPostExecute(List<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);
        }
    }
}
