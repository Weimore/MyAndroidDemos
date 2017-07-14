package com.example.mymodeldemos.widget.rowViewWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.utils.ScreenUtils;

import java.util.List;

/**
 * Created by 吴城林 on 2017/7/11.
 */

public class GroupRowView extends LinearLayout {

//    private List<RowDescriptor> mDescriptors;
    private GroupDescriptor mGroupDescriptor;
    private rowClickListener mListener;
    private RowDescriptor mDescriptor;
    private NormalRowView mRowView;
    private String mTitle;

    private Context mContext;

    public GroupRowView(Context context) {
        this(context, null);
    }

    public GroupRowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOrientation(VERTICAL);

    }


    public void initData(GroupDescriptor descriptors, rowClickListener listener) {
        mGroupDescriptor = descriptors;
        mListener = listener;
        mTitle=descriptors.title;
        if (mGroupDescriptor != null && mGroupDescriptor.rowDescriptorList.size() > 0) {
            notifyDataChanged();
        }else {
            setVisibility(View.GONE);
        }
    }


    private void notifyDataChanged() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lp.rightMargin = lp.leftMargin = ScreenUtils.dp2px(mContext, 13);
        View line = null;

        for (int i = 0; i < mGroupDescriptor.rowDescriptorList.size(); i++) {
            mDescriptor = mGroupDescriptor.rowDescriptorList.get(i);
            mRowView = new NormalRowView(mContext);
            mRowView.initDatas(mDescriptor, mListener);
            addView(mRowView);

            if (i <mGroupDescriptor.rowDescriptorList.size() - 1) {
                line = new View(mContext);
                line.setBackgroundResource(R.color.rowLineColor);
                addView(line, lp);
            }

        }

    }
}
