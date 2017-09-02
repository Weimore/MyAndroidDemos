package com.example.mymodeldemos.sample;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.BaseListActivity;
import com.example.mymodeldemos.base.BaseNewListActivity;
import com.example.mymodeldemos.model.DataOne;
import com.example.mymodeldemos.utils.DataServer;
import com.example.mymodeldemos.utils.LogUtils;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/24.
 */

public class SampleNewListActivity extends BaseNewListActivity<DataOne> {

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, "数据测试", MODE_BACK);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.setRefreshing();
    }

    @Override
    protected void onLoadMoreData() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(DataServer.getDataList());
                if (dataList.size() < 45) {
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
            }
        }, 1000);
    }

    //这里是下拉刷新
    @Override
    public void onDataRefresh() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(DataServer.getDataList());
                onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    protected BaseQuickAdapter setUpAdapter() {
        return new SampleListAdapter(R.layout.sample_item_one, dataList);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new GridLayoutManager(this, 2);
    }


    class SampleListAdapter extends BaseQuickAdapter<DataOne, BaseViewHolder> {
        public SampleListAdapter(@LayoutRes int layoutResId, @Nullable List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DataOne item) {
            helper.setImageResource(R.id.item_one_image, item.getImageRes());
            helper.setText(R.id.item_one_topic, item.getTopic());
            helper.setText(R.id.item_one_introduce, item.getIntroduce());
        }
    }
}
