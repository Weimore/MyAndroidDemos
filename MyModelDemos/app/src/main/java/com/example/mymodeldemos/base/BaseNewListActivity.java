package com.example.mymodeldemos.base;

import android.support.v7.widget.LinearLayoutManager;
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
//相比BaseListActivity，内部耦合了BaseQuickAdapter，不过子类使用起来会更加方法
public abstract class BaseNewListActivity<T> extends BaseActivity implements OnRecyclerRefreshListener {

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
        adapter = setUpAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(setLayoutManager());
        recycler.addItemDecoration(setItemDecoration());
        recycler.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(listener, recycler.getRecyclerView());
    }

    protected BaseQuickAdapter.RequestLoadMoreListener listener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            onLoadMoreData();
        }
    };

    @Override
    public void onRefresh() {
        if (dataList.size() > 0) {
            dataList.clear();
            adapter.setNewData(dataList);
        }
        onDataRefresh();   //下拉刷新
    }

    //加载更多
    protected abstract void onLoadMoreData();

    //下拉刷新
    protected abstract void onDataRefresh();

    //设置Adapter
    protected abstract BaseQuickAdapter setUpAdapter();

    //添加样式
    protected RecyclerView.ItemDecoration setItemDecoration() {
        return null;
    }

    //设置LayoutManager
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(this);
    }

    //刷新完成
    protected void onRefreshComplete() {
        recycler.onRefreshComplete();
    }


}
