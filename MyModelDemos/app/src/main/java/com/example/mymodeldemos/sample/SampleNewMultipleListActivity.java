package com.example.mymodeldemos.sample;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.BaseListActivity;
import com.example.mymodeldemos.base.BaseNewListActivity;
import com.example.mymodeldemos.model.MultipleDataOne;
import com.example.mymodeldemos.utils.DataServer;
import com.example.mymodeldemos.widget.refreshRecyclerWidgets.GridSizeLookUp;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/24.
 */
//基于BaseNewListActivity的测试Actvity
public class SampleNewMultipleListActivity extends BaseNewListActivity<MultipleDataOne> {

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
    protected BaseQuickAdapter setUpAdapter() {
        return new SampleMultipleListAdapter(dataList);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);
        gridLayoutManager.setSpanSizeLookup(new GridSizeLookUp<MultipleDataOne>(dataList,2) {
            @Override
            protected int getDataSpanSize(MultipleDataOne multipleDataOne) {
                return multipleDataOne.getSpanSize();
            }
        });
        return gridLayoutManager;
    }

    //下拉刷新
    @Override
    public void onDataRefresh() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(DataServer.getMultipleDataList());
                onRefreshComplete();
            }
        }, 1000);
    }

    //加载更多
    @Override
    protected void onLoadMoreData() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(DataServer.getMultipleDataList());
                int size = dataList.size();
                if (size <= 70) {
                    adapter.loadMoreComplete();
                } else {
                    adapter.loadMoreEnd();
                }
            }
        }, 1000);
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
