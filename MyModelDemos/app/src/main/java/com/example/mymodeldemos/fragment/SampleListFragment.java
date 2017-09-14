package com.example.mymodeldemos.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.BaseFragment;
import com.example.mymodeldemos.base.BaseListFragment;
import com.example.mymodeldemos.model.MultipleDataOne;
import com.example.mymodeldemos.utils.DataServer;
import com.example.mymodeldemos.utils.LogUtils;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.GridSizeLookUp;

import java.util.List;
import java.util.zip.Inflater;

public class SampleListFragment extends BaseListFragment<MultipleDataOne> implements IChangeTextColor {

    private TextView titleTextView;
    private Button titleMoreButton;
    private int color;

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
                recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData(DataServer.getMultipleDataList());
                        int size = dataList.size();
                        if (size >= 70) {
                            adapter.loadMoreEnd();
                        } else {
                            adapter.loadMoreComplete();
                        }
                    }
                }, 1000);
            }
        };
        return listener;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setSpanSizeLookup(new GridSizeLookUp<MultipleDataOne>(dataList, 2) {
            @Override
            protected int getDataSpanSize(MultipleDataOne multipleDataOne) {
                return multipleDataOne.getSpanSize();
            }
        });
        return gridLayoutManager;
    }

    @Override
    protected BaseQuickAdapter setUpAdapter() {
        adapter = new SampleMultipleListAdapter(dataList);
        return adapter;
    }

    @Override
    protected View setUpContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sample_list, container, false);
        return v;
    }



    //下拉刷新
    @Override
    protected void onDataRefresh() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataList.size() >= 0) {
                    dataList.clear();
                    adapter.setNewData(dataList);
                }
                adapter.addData(DataServer.getMultipleDataList());
                onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void setColor(int color) {
        this.color = color;
        if (titleTextView == null || titleMoreButton == null) {
            return;
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public Fragment getFragment() {
        return this;
    }

//    private void invalidateView() {
//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            titleTextView.invalidate();
//        } else {
//            titleTextView.postInvalidate();
//        }
//    }


    class SampleMultipleListAdapter extends BaseMultiItemQuickAdapter<MultipleDataOne, BaseViewHolder> {
        public SampleMultipleListAdapter(List<MultipleDataOne> data) {
            super(data);
            addItemType(MultipleDataOne.TYPE_HEADER, R.layout.sample_item_one_header);
            addItemType(MultipleDataOne.TYPE_ONE, R.layout.sample_item_one);
            addItemType(MultipleDataOne.TYPE_TWO, R.layout.sample_item_two);
        }

        @Override
        protected void convert(BaseViewHolder helper, MultipleDataOne item) {
            switch (helper.getItemViewType()) {
                case MultipleDataOne.TYPE_HEADER:
                    titleTextView = helper.getView(R.id.item_header_title);
                    titleTextView.setText(item.getTopic());
                    titleMoreButton = helper.getView(R.id.item_header_more);
                    titleTextView.setTextColor(color);
                    titleMoreButton.setBackgroundColor(color);
                    break;
                case MultipleDataOne.TYPE_ONE:
                    helper.setImageResource(R.id.item_one_image, item.getImageRes());
                    helper.setText(R.id.item_one_topic, item.getTopic());
                    helper.setText(R.id.item_one_introduce, item.getIntroduce());
                    break;
                case MultipleDataOne.TYPE_TWO:
                    helper.setImageResource(R.id.item_two_image, item.getImageRes());
                    helper.setText(R.id.item_two_text, item.getTopic());
                    break;
                default:
                    break;
            }
        }
    }
}
