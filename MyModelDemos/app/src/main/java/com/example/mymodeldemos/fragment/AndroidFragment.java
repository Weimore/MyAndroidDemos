package com.example.mymodeldemos.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.BaseListFragment;
import com.example.mymodeldemos.model.Android;
import com.example.mymodeldemos.model.Fuli;
import com.example.mymodeldemos.utils.DataUtils;
import com.example.mymodeldemos.utils.HttpUtils;
import com.example.mymodeldemos.utils.StringUtils;
import com.example.mymodeldemos.utils.imageutils.ImageLoader;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 吴城林 on 2017/9/11.
 */

public class AndroidFragment extends BaseListFragment<Android.ResultsBean> implements IChangeTextColor {

    private static volatile int PAGE = 2;
    private int color;

    private Handler mHandler;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Override
    protected View setUpContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLazyLoadEnable();
        View v = inflater.inflate(R.layout.fragment_sample_list, container, false);
        return v;
    }

    @Override
    protected void setUpView(View view, Bundle saveInstanceState) {
        super.setUpView(view, saveInstanceState);
    }

    @Override
    protected void setUpData(Bundle saveInstanceState) {
        super.setUpData(saveInstanceState);
        recycler.setRefreshing();
    }

    @Override
    protected BaseQuickAdapter.RequestLoadMoreListener setOnLoadMoreListener() {
        //上拉加载
        BaseQuickAdapter.RequestLoadMoreListener listener = new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                HttpUtils.get(DataUtils.ANDROID_URL + PAGE, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final List<Android.ResultsBean> datas = DataUtils.getAndroidResult(response.body().string());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.addData(datas);
                                int size = dataList.size();
                                if (size >= 70) {
                                    adapter.loadMoreEnd();
                                } else {
                                    adapter.loadMoreComplete();
                                    Toast.makeText(context, "已经到底啦！", Toast.LENGTH_SHORT);
                                }
                            }
                        });
                    }
                });
                PAGE++;
            }
        };
        return listener;
    }

    @Override
    protected BaseQuickAdapter setUpAdapter() {
        adapter = new AndroidAdapter(R.layout.sample_item_four);
        return adapter;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return manager;
    }

    @Override
    protected void onDataRefresh() {
        if (dataList.size() >= 0) {
            dataList.clear();
            PAGE = 2;
            adapter.setNewData(dataList);
        }
        HttpUtils.get(DataUtils.ANDROID_URL + 1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final List<Android.ResultsBean> datas = DataUtils.getAndroidResult(response.body().string());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(datas);
                        onRefreshComplete();
                    }
                });
            }
        });

    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    class AndroidAdapter extends BaseQuickAdapter<Android.ResultsBean, BaseViewHolder> {
        public AndroidAdapter(@LayoutRes int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Android.ResultsBean item) {
//            helper.getView(R.id.item_four_image).post(new Runnable() {
//                @Override
//                public void run() {
//                    ImageLoader.getInstance().showImage(helper.getView(R.id.item_four_image), item.getUrl());  //设置图片
//                }
//            });
            ImageLoader.getInstance().showImage(helper.getView(R.id.item_four_image), item.getUrl());  //设置图片
            String data = StringUtils.getSplit(item.getCreatedAt(), "T", 1);
            helper.setText(R.id.item_four_data, data);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler = null;
    }
}
