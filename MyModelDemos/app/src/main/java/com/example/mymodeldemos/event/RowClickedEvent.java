package com.example.mymodeldemos.event;

import com.example.mymodeldemos.widget.rowViewWidgets.RowDescriptor;
import com.example.mymodeldemos.widget.rowViewWidgets.RowViewEnum;

/**
 * Created by 吴城林 on 2017/7/30.
 */

public class RowClickedEvent {

    public RowViewEnum rowViewEnum;

    public RowClickedEvent(RowViewEnum rowViewEnum) {
        this.rowViewEnum = rowViewEnum;
    }
}
