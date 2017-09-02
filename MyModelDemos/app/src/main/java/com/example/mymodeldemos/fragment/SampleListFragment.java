package com.example.mymodeldemos.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.BaseFragment;
import com.example.mymodeldemos.base.BaseListFragment;
import com.example.mymodeldemos.model.MultipleDataOne;
import com.example.mymodeldemos.utils.DataServer;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.GridSizeLookUp;

import java.util.List;
import java.util.zip.Inflater;

public class SampleListFragment extends BaseListFragment<MultipleDataOne> {

    private BaseQuickAdapter adapter;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sample_list,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new SampleMultipleListAdapter(dataList);
        setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setSpanSizeLookup(new GridSizeLookUp<MultipleDataOne>(dataList,2) {
            @Override
            protected int getDataSpanSize(MultipleDataOne multipleDataOne) {
                return multipleDataOne.getSpanSize();
            }
        });
        setLayoutManager(gridLayoutManager);
        adapter.setOnLoadMoreListener(listener,recycler.getRecyclerView());

        recycler.setRefreshing();
    }

    //上拉加载
    BaseQuickAdapter.RequestLoadMoreListener listener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            recycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.addData(DataServer.getMultipleDataList());
                    int size =dataList.size();
                    if(size>= 70){
                        adapter.loadMoreEnd();
                    }else {

                        adapter.loadMoreComplete();
                    }
                }
            },1000);
        }
    };

    //下拉刷新
    @Override
    protected void onDataRefresh() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(dataList .size()>= 0){
                    dataList.clear();
                    adapter.setNewData(dataList);
                }
                adapter.addData(DataServer.getMultipleDataList());
                onRefreshComplete();
            }
        },1000);
    }

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
                    helper.setText(R.id.item_header_title, item.getTopic());
                    break;
                case MultipleDataOne.TYPE_ONE:
                    helper.setImageResource(R.id.item_one_image, item.getImageRes());
                    helper.setText(R.id.item_one_topic, item.getTopic());
                    helper.setText(R.id.item_one_introduce, item.getIntroduce());
                    break;
                case MultipleDataOne.TYPE_TWO:
                    helper.setImageResource(R.id.item_two_image,item.getImageRes());
                    helper.setText(R.id.item_two_text,item.getTopic());
                    break;
                default:
                    break;
            }
        }
    }
}
