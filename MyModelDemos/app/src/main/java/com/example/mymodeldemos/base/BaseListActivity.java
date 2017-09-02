package com.example.mymodeldemos.base;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.OnRecyclerRefreshListener;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/23.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements OnRecyclerRefreshListener{

    protected RefreshRecyclerView recycler;
    protected List<T> dataList;
    protected BaseQuickAdapter adapter;


    @Override
    protected void setUpView() {
        recycler = (RefreshRecyclerView) findViewById(R.id.refresh_recyclerview);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorTheme));
    }

    @Override
    protected void setUpData() {
        dataList = new ArrayList<>();
        recycler.setOnRefreshListener(this);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        recycler.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        recycler.setLayoutManager(layoutManager);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration){
        recycler.addItemDecoration(decoration);
    }

    protected void onRefreshComplete(){
        recycler.onRefreshComplete();
    }

    @Override
    public void onRefresh() {
        if (dataList.size()>0) {
            dataList.clear();
        }
        onDataRefresh();
    }

    protected abstract void onDataRefresh();
}
