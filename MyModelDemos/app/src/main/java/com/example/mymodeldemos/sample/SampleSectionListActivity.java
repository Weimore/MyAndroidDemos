package com.example.mymodeldemos.sample;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mymodeldemos.R;
import com.example.mymodeldemos.base.BaseListActivity;
import com.example.mymodeldemos.model.DataOne;
import com.example.mymodeldemos.model.SectionDataOne;
import com.example.mymodeldemos.utils.DataServer;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/24.
 */

public class SampleSectionListActivity extends BaseListActivity<SectionDataOne>{

    private SampleSectionListAdapter adapter;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_base_list, "数据测试", MODE_BACK);

    }

    @Override
    protected void setUpView() {
        super.setUpView();
        adapter = new SampleSectionListAdapter(R.layout.sample_item_one,R.layout.sample_item_one_header,dataList);
        setAdapter(adapter);
        setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter.setOnLoadMoreListener(listener, recycler.getRecyclerView());
    }

    @Override
    protected void setUpData() {
        super.setUpData();
    }

    //这里是下拉刷新
    @Override
    public void onDataRefresh() {
        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataList.size()>=0) {
                    dataList.clear();
                }
                dataList.addAll(DataServer.getSectionDataList());
                adapter.notifyDataSetChanged();
                recycler.onRefreshComplete();
            }
        }, 1000);
    }

    //加载更多
    BaseMultiItemQuickAdapter.RequestLoadMoreListener listener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {
            recycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dataList.addAll(DataServer.getSectionDataList());
                    int size = dataList.size();
                    if (size <= 70) {
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


    class SampleSectionListAdapter extends BaseSectionQuickAdapter<SectionDataOne,BaseViewHolder>{
        public SampleSectionListAdapter(int layoutResId, int sectionHeadResId, List<SectionDataOne> data) {
            super(layoutResId, sectionHeadResId, data);
        }

        @Override
        protected void convertHead(BaseViewHolder helper, SectionDataOne item) {
            helper.setText(R.id.item_header_title,item.header);
        }

        @Override
        protected void convert(BaseViewHolder helper, SectionDataOne section) {
            DataOne item =section.t;
            helper.setImageResource(R.id.item_one_image, item.getImageRes());
            helper.setText(R.id.item_one_topic, item.getTopic());
            helper.setText(R.id.item_one_introduce, item.getIntroduce());
        }
    }
}
