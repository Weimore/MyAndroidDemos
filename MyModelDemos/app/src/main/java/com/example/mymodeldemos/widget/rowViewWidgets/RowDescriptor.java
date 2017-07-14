package com.example.mymodeldemos.widget.rowViewWidgets;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 吴城林 on 2017/7/10.
 */

public class RowDescriptor {

    protected int iconImageId;
    protected String text;
    protected RowViewEnum rowViewEnum;

    public RowDescriptor(int iconImageId, String text, RowViewEnum rowViewEnum) {
        this.iconImageId = iconImageId;
        this.text = text;
        this.rowViewEnum = rowViewEnum;
    }

    public RowDescriptor(int iconImageId, String text) {
        this(iconImageId,text,null);
    }
}
