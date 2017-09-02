package com.example.mymodeldemos.sample;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.BaseListActivity;
import com.example.mymodeldemos.model.DataOne;
import com.example.mymodeldemos.utils.LogUtils;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/24.
 */

public class SampleListActivity extends BaseListActivity {

    private SampleListAdapter adapter;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, "数据测试", MODE_BACK);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        adapter = new SampleListAdapter(R.layout.sample_item_one, dataList);
        setAdapter(adapter);
        setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setOnLoadMoreListener(listener, recycler.getRecyclerView());
    }

    //这里是下拉刷新
    @Override
    public void onDataRefresh() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataList.size()>0) {
                    dataList.clear();
                }
                int size = dataList.size();
                for (int i = size; i < size + 20; i++) {
//                    DataOne data = new DataOne(R.drawable.rella4, "图片主题", "海量图片赏析");
//                    dataList.add(data);
                }
                adapter.notifyDataSetChanged();
                recycler.onRefreshComplete();

            }
        }, 1000);
        LogUtils.d("sendMessage..................................");
    }

    BaseQuickAdapter.RequestLoadMoreListener listener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            recycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int size = dataList.size();
                    for (int i = size; i < size + 20; i++) {
//                        DataOne data = new DataOne(R.drawable.rella4, "图片主题", "海量图片赏析" + i);
//                        dataList.add(data);
                    }
                    if (dataList.size() < 45) {
                        adapter.notifyDataSetChanged();
                        adapter.loadMoreComplete();
                    } else {
                        adapter.notifyDataSetChanged();
                        adapter.loadMoreEnd();
                    }
                }
            }, 1000);
        }
    };


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
