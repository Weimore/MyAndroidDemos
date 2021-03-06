package com.example.mymodeldemos.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.event.OnColorChangedListener;
import com.example.mymodeldemos.event.RowClickedEvent;
import com.example.mymodeldemos.utils.MyImageLoder;
import com.example.mymodeldemos.utils.ScreenUtils;
import com.example.mymodeldemos.widget.rowViewWidgets.ContainerRowView;
import com.example.mymodeldemos.widget.rowViewWidgets.GroupDescriptor;
import com.example.mymodeldemos.widget.rowViewWidgets.RowDescriptor;
import com.example.mymodeldemos.widget.rowViewWidgets.RowViewEnum;
import com.example.mymodeldemos.widget.rowViewWidgets.rowClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/7/10.
 */

public class SlidingMenu extends FrameLayout implements rowClickListener {

    private Context mContext;
    private ImageView mSelfImage;
    private ScrollView mScrollView;
    private List<OnColorChangedListener> colorChangedListeners;

//    private NormalRowView mNormalRowView;
//    private GroupRowView mGroupRowView;
    private ContainerRowView mContainerRowView;


//    private RowDescriptor mRowDescriptor;
//    private GroupDescriptor mGroupDescriptor;

    private List<RowDescriptor> rowDescriptors;
    private List<GroupDescriptor> groupDescriptors;


    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.sliding_menu, this);
        initView();
        initData();
    }


    private void initView() {
        mSelfImage = (ImageView) findViewById(R.id.self_image);
        int height = ScreenUtils.dp2px(mContext, 180);
        Bitmap selfBitmap = MyImageLoder.decodeSampleBitmapForResource(getResources(), R.drawable.selfimage2, ScreenUtils.getSreenWidth(mContext), height);
        mSelfImage.setImageBitmap(selfBitmap);

//        mScrollView = (ScrollView) findViewById(R.id.sliding_menu_scrollview);
//        mNormalRowView= (NormalRowView) findViewById(R.id.nomal_row_view);
//        mGroupRowView= (GroupRowView) findViewById(R.id.group_row_view);
        mContainerRowView = (ContainerRowView) findViewById(R.id.container_row_view);
    }

    private void initData() {
        colorChangedListeners = new ArrayList<>();

        rowDescriptors = new ArrayList<>();
        groupDescriptors=new ArrayList<>();
        rowDescriptors.add(new RowDescriptor(R.drawable.profile, "个人中心", RowViewEnum.PROFILE));
        rowDescriptors.add(new RowDescriptor(R.drawable.camera, "以图搜图",RowViewEnum.SEARCH_PICTURE));
//        mGroupDescriptor=new GroupDescriptor(rowDescriptors);

        groupDescriptors.add(new GroupDescriptor(rowDescriptors));

        rowDescriptors=new ArrayList<RowDescriptor>();
        rowDescriptors.add(new RowDescriptor(R.drawable.support,"续一秒",RowViewEnum.SUPPORT));
        rowDescriptors.add(new RowDescriptor(R.drawable.settings,"设置",RowViewEnum.SETTING));
        rowDescriptors.add(new RowDescriptor(R.drawable.like,"给个好评",RowViewEnum.LIKE));
        rowDescriptors.add(new RowDescriptor(R.drawable.night,"夜间模式",RowViewEnum.NIGHT));
        groupDescriptors.add(new GroupDescriptor(rowDescriptors,"其他"));


//        mNormalRowView.initDatas(mRowDescriptor,this);
//        mGroupRowView.initData(mGroupDescriptor,this);
        mContainerRowView.initData(groupDescriptors,this);

        colorChangedListeners.addAll(mContainerRowView.getColorChangedListeners());
    }

    @Override
    public void onRowClicked(RowViewEnum rowViewEnum) {
        RowClickedEvent event=new RowClickedEvent(rowViewEnum);
        EventBus.getDefault().post(event);
    }

    public void changerColor(int color){
        for(OnColorChangedListener listener:colorChangedListeners){
            listener.changeColor(color);
        }
    }
}
