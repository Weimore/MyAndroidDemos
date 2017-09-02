package com.example.mymodeldemos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.OnRecyclerRefreshListener;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/26.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements OnRecyclerRefreshListener{

    protected RefreshRecyclerView recycler;
    protected List<T> dataList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = createView(inflater,container,savedInstanceState);
        recycler = view.findViewById(R.id.refresh_recyclerview);
        return view;
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler.setOnRefreshListener(this);
    }

    protected void setAdapter(RecyclerView.Adapter adapter){
        recycler.setAdapter(adapter);
    }

    protected void addItemDecroation(RecyclerView.ItemDecoration itemDecoration){
        recycler.addItemDecoration(itemDecoration);
    }

    protected void setLayoutManager(RecyclerView.LayoutManager manager){
        recycler.setLayoutManager(manager);
    }

    protected void onRefreshComplete(){
        recycler.onRefreshComplete();
    }

    //保留做以后统一处理数据使用
    @Override
    public void onRefresh() {
        onDataRefresh();
    }

    protected abstract void onDataRefresh();

}
