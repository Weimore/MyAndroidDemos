package com.example.mymodeldemos.widget.rowViewWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.utils.ScreenUtils;

import java.util.List;

/**
 * Created by 吴城林 on 2017/7/11.
 */

public class ContainerRowView extends LinearLayout {

    private List<GroupDescriptor> mGroupDescriptors;
    private rowClickListener mListener;
    private GroupRowView mGroupRowView;
    GroupDescriptor mDescriptor;
    private Context mContext;

    public ContainerRowView(Context context) {
        this(context,null);
    }

    public ContainerRowView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ContainerRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        setOrientation(VERTICAL);
    }

    public void initData(List<GroupDescriptor> groupDescriptors, rowClickListener listener) {
        mGroupDescriptors = groupDescriptors;
        mListener = listener;
        if (mGroupDescriptors != null && mGroupDescriptors.size() > 0) {
            notifyDataChanged();
        }else {
            setVisibility(View.GONE);
        }
    }

    private void notifyDataChanged() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
        lp.topMargin=30;
        View space = null;

        for (int i = 0; i <mGroupDescriptors.size() ; i++) {
            mDescriptor=mGroupDescriptors.get(i);
            mGroupRowView=new GroupRowView(mContext);
            mGroupRowView.initData(mDescriptor,mListener);
            addView(mGroupRowView);

            if(1<mGroupDescriptors.size()){
                space=new View(mContext);
                addView(space,lp);
            }
        }

    }


}
