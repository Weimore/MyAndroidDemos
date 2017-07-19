package com.example.mymodeldemos.widget.rowViewWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymodeldemos.R;

/**
 * Created by 吴城林 on 2017/7/10.
 */

public class NormalRowView extends LinearLayout implements View.OnClickListener{

    private ImageView mRowViewIcon;
    private TextView mRowViewText;
    private ImageView mRowViewPress;
    private rowClickListener mListener;
    RowDescriptor mDescriptor;
    public NormalRowView(Context context) {
        this(context,null);
    }

    public NormalRowView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NormalRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.nomal_row_view,this);
        init();
    }

    private void init() {
        mRowViewIcon= (ImageView) findViewById(R.id.row_view_icon);
        mRowViewText= (TextView) findViewById(R.id.row_view_text);
        mRowViewPress= (ImageView) findViewById(R.id.row_view_press);
    }

    public void initDatas(RowDescriptor descriptor,rowClickListener listener) {
        mListener=listener;
        mDescriptor=descriptor;
        if(mDescriptor!=null){
            notifyDataChanged();
        }
    }

    private void notifyDataChanged() {
        mRowViewIcon.setBackgroundResource(mDescriptor.iconImageId);
        mRowViewText.setText(mDescriptor.text);
        if (mDescriptor.rowViewEnum!=null){//如果可点击
            mRowViewPress.setVisibility(View.VISIBLE);
            setBackgroundResource(R.drawable.rowbackground);
            setOnClickListener(this);
        }else {//如果不可点击
            mRowViewPress.setVisibility(View.GONE);
            setBackgroundResource(R.color.colorWhite);

        }
    }

    @Override
    public void onClick(View v) {
        if(mListener!=null){
            mListener.onRowClicked(mDescriptor.rowViewEnum);
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                requestDisallowInterceptTouchEvent(true);
//            case MotionEvent.ACTION_MOVE:
//                Log.d("CCC","AAAAAA");
//                requestDisallowInterceptTouchEvent(false);
//            default:
//        }
//        return super.dispatchTouchEvent(ev);
//    }
}
