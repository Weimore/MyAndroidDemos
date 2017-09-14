package com.example.mymodeldemos.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.OnRecyclerRefreshListener;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/26.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements OnRecyclerRefreshListener {

    protected RefreshRecyclerView recycler;
    protected List<T> dataList;
    protected BaseQuickAdapter adapter;

    @Override
    protected void setUpData(Bundle saveInstanceState) {
        super.setUpData(saveInstanceState);
        dataList = new ArrayList<>();
        recycler.setOnRefreshListener(this);
        recycler.setAdapter(setUpAdapter());
        recycler.setLayoutManager(setLayoutManager());;
        adapter.setOnLoadMoreListener(setOnLoadMoreListener(), recycler.getRecyclerView());
    }

    protected abstract BaseQuickAdapter.RequestLoadMoreListener setOnLoadMoreListener();

    @Override
    protected void setUpView(View view, Bundle saveInstanceState) {
        super.setUpView(view, saveInstanceState);
        recycler = view.findViewById(R.id.refresh_recyclerview);
    }


    protected abstract BaseQuickAdapter setUpAdapter();

    protected void addItemDecroation(RecyclerView.ItemDecoration itemDecoration) {
        recycler.addItemDecoration(itemDecoration);
    }

    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(context);
    }

    protected void onRefreshComplete() {
        recycler.onRefreshComplete();
    }

    //保留做以后统一处理数据使用
    @Override
    public void onRefresh() {
        onDataRefresh();
    }

    protected abstract void onDataRefresh();

}
