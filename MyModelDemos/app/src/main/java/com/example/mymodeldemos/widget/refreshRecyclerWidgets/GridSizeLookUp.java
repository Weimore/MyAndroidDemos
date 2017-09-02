package com.example.mymodeldemos.widget.refreshRecyclerWidgets;

import android.support.v7.widget.GridLayoutManager;

import java.util.List;

/**
 * Created by 吴城林 on 2017/8/27.
 */
//供GridLayoutManager使用,用于设置布局的宽度
public abstract class GridSizeLookUp<T> extends GridLayoutManager.SpanSizeLookup {

    private final int spanCount;
    private List<T> dataList;

    public GridSizeLookUp(List<T> list,int spanCount) {
        this.dataList =list;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if(position == dataList.size()){ //如果有加载更多的item，则调用该方法，防止数组溢出
            return spanCount;
        }else {
            T t =dataList.get(position);
            return getDataSpanSize(t);
        }
    }

    //重写该方法，返回spanSize大小
    protected abstract int getDataSpanSize(T t);
}

//    class GridSizeLookUp extends GridLayoutManager.SpanSizeLookup {
//        @Override
//        public int getSpanSize(int position) {
//            if(position == dataList.size()){
//                return 2;
//            }else {
//                return dataList.get(position).getSpanSize();
//            }
//        }
//    }
