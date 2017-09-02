package com.example.mymodeldemos.widget.refreshRecyclerWidgets;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mymodeldemos.R;

/**
 * Created by 吴城林 on 2017/8/23.
 */

public class RefreshRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private OnRecyclerRefreshListener listener;

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View v =LayoutInflater.from(context).inflate(R.layout.refresh_recycler_view,this);
        initView(v);
    }

    private void initView(View v) {
        swipeRefreshLayout= (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);

    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration){
        if(decoration!=null){
            recyclerView.addItemDecoration(decoration);
        }
    }

    public void setOnRefreshListener(OnRecyclerRefreshListener listener){
        this.listener = listener;
    }

    @Override
    public void onRefresh() {
        listener.onRefresh();
    }

    public void setRefreshing(){
        // TODO: 2017/8/24 why recyclerView.post
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    public void onRefreshComplete(){
        swipeRefreshLayout.setRefreshing(false);
    }
}
